package com.example.accepted.acceptedtalentplanet.LoadingLogin.PasswordLost.Change;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

/**
 * Created by Accepted on 2017-12-17.
 */

public class MainActivity extends AppCompatActivity {

    private EditText et_NewPassword;
    private Context mContext;
    private String userID;

    private TextView tv_TitleContainer;
    private LinearLayout ll_NewPswContainer;
    private View trashView;
    private Button btn_Next;



    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_passwordlost_change_activity);

        mContext = getApplicationContext();

        userID = getIntent().getStringExtra("userID");
        et_NewPassword = (EditText) findViewById(R.id.et_NewPassword_Change_PasswordLost);

        et_NewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }
            }
        });

        tv_TitleContainer = (TextView) findViewById(R.id.tv_TitleContainer_pswlost_change);
        ll_NewPswContainer = (LinearLayout) findViewById(R.id.ll_NewPswContainer_Change_PasswordLost);
        trashView = findViewById(R.id.trashView1_psw_Change);


        btn_Next = (Button)findViewById(R.id.btn_Next_Change_PasswordLost);
        btn_Next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                changePasswrord();
            }
        });


        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int Title_height = (int) (metrics.heightPixels*0.1);
        int newPsw_height = (int) (metrics.heightPixels*0.06);
        int trashView1_height= (int) (metrics.heightPixels*0.02);
        int nextBtn_height= (int) (metrics.heightPixels*0.04);

        ViewGroup.LayoutParams params1 = tv_TitleContainer.getLayoutParams();
        ViewGroup.LayoutParams params2 = ll_NewPswContainer.getLayoutParams();
        ViewGroup.LayoutParams params3 = btn_Next.getLayoutParams();
        ViewGroup.LayoutParams params4 = trashView.getLayoutParams();

        params1.height = Title_height;
        params2.height = newPsw_height;
        params3.height = nextBtn_height;
        params4.height = trashView1_height;

        tv_TitleContainer.setLayoutParams(params1);
        ll_NewPswContainer.setLayoutParams(params2);
        btn_Next.setLayoutParams(params3);
        trashView.setLayoutParams(params4);

    }

    public void changePasswrord() {


        if(et_NewPassword.getText().toString().length() == 0)
        {
            Toast.makeText(mContext,"비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(et_NewPassword.getText().toString().length() < 6)
        {
            Toast.makeText(mContext, "비밀번호는 6자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (checkPw(et_NewPassword))
        {
            Toast.makeText(mContext, "비밀번호는 영문, 숫자, 특수문자 중 두 개 이상의 조합으로 이루어져야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/changePassword.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    if (result.equals("success")) {
                        Toast.makeText(getApplicationContext(), "비밀번호가 정상적으로 변경되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호 변경에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(getBaseContext(), com.example.accepted.acceptedtalentplanet.LoadingLogin.Login.MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("userID", userID);
                params.put("userPW", et_NewPassword.getText().toString());

                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public boolean checkPw(EditText edt)
    {
        int engNum=0;
        int numNum=0;
        int speNum=0;
        int totalNum=0;

        for(int i =0; i<edt.getText().length(); i++)
        {
            if(Character.isLetter(edt.getText().charAt(i)))
            {
                engNum++;
            }
            if(Character.isDigit(edt.getText().charAt(i)))
            {
                numNum++;
            }
            if(!Character.isLetterOrDigit(edt.getText().charAt(i)))
            {
                speNum++;
            }
        }

        if (engNum == 0)
        {
            totalNum++;
        }
        if(numNum == 0)
        {
            totalNum++;
        }
        if(speNum == 0)
        {
            totalNum++;
        }
        if (totalNum < 2)
        {
            return false;
        }
        return  true;
    }
}
