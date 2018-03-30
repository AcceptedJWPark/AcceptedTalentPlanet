package com.example.accepted.acceptedtalentplanet.System.PasswordChange;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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


public class MainActivity extends AppCompatActivity {


    private Context mContext;
    private LinearLayout ll_NewPswContainer_Old;
    private LinearLayout ll_NewPswContainer_New;

    private TextView tv_TitleContainer;

    private EditText et_OldPassword;
    private EditText et_NewPassword;

    private Button btn_Save;

    private View trashView1;
    private View trashView2;

    private InputMethodManager imm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_passworchange_change_activity);

        mContext = getApplicationContext();

        tv_TitleContainer = findViewById(R.id.tv_TitleContainer_psw_System);
        ll_NewPswContainer_Old = findViewById(R.id.ll_NewPswContainer_Old_System);
        ll_NewPswContainer_New = findViewById(R.id.ll_NewPswContainer_New_System);
        et_OldPassword = findViewById(R.id.et_OldPassword_System);
        et_NewPassword = findViewById(R.id.et_NewPassword_System);
        btn_Save = findViewById(R.id.btn_Next_PasswordChange_System);
        trashView1 = findViewById(R.id.trashView1_psw_System);
        trashView2 = findViewById(R.id.trashView2_psw_System);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int Title_height = (int) (metrics.heightPixels*0.1);
        int password_height = (int) (metrics.heightPixels*0.06);
        int trashView1_height= (int) (metrics.heightPixels*0.01);
        int trashView2_height= (int) (metrics.heightPixels*0.02);
        int nextBtn_height = (int) (metrics.heightPixels*0.04);

        ViewGroup.LayoutParams params1 = tv_TitleContainer.getLayoutParams();
        ViewGroup.LayoutParams params2 = ll_NewPswContainer_Old.getLayoutParams();
        ViewGroup.LayoutParams params3 = ll_NewPswContainer_New.getLayoutParams();
        ViewGroup.LayoutParams params4 = trashView1.getLayoutParams();
        ViewGroup.LayoutParams params5 = trashView2.getLayoutParams();
        ViewGroup.LayoutParams params6 = btn_Save.getLayoutParams();

        params1.height = Title_height;
        params2.height = password_height;
        params3.height = password_height;
        params4.height = trashView1_height;
        params5.height = trashView2_height;
        params6.height = nextBtn_height;

        tv_TitleContainer.setLayoutParams(params1);
        ll_NewPswContainer_Old.setLayoutParams(params2);
        ll_NewPswContainer_New.setLayoutParams(params3);
        trashView1.setLayoutParams(params4);
        trashView2.setLayoutParams(params5);
        btn_Save.setLayoutParams(params6);

        et_OldPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v,mContext);
                }
            }
        });
        et_NewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }
            }
        });

        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPw();
            }
        });


    }

    public void checkPw()
    {
        final String userID = SaveSharedPreference.getUserId(mContext);
        final String userPW = et_OldPassword.getText().toString();
        final String userNewPw = et_NewPassword.getText().toString();


        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/checkLoginInfo.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    if(result.equals("success")){
                        changePw(userID, userNewPw);

                    }else if(result.equals("fail")){
                        Toast.makeText(getApplicationContext(), "기존 비밀번호를 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", userID);
                params.put("userPW", userPW);

                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }


    public void changePw(final String userId, final String userNewPw)
    {

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/changePassword.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    if(result.equals("success")){
                        Log.d(userNewPw,"성공");
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getBaseContext(), com.example.accepted.acceptedtalentplanet.LoadingLogin.Login.MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("Activity", true);
                                startActivity(intent);
                            }
                        },500);

                    }else if(result.equals("fail")){
                        Toast.makeText(getApplicationContext(), "기존 비밀번호를 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", userId);
                params.put("userPW", userNewPw);
                return params;
            }
        };

        if(userNewPw.length() == 0)
        {
            Toast.makeText(mContext,"새로운 비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(userNewPw.length() < 6)
        {
            Toast.makeText(mContext, "비밀번호는 6자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(checkPwPattern(et_NewPassword))
        {
            Toast.makeText(mContext, "비밀번호는 영문, 숫자, 특수문자 중 두 개 이상의 조합으로 이루어져야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        postRequestQueue.add(postJsonRequest);

    }

    public boolean checkPwPattern(EditText edt)
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


