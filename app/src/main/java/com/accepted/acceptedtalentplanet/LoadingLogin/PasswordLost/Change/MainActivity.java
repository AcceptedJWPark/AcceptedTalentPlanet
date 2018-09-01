package com.accepted.acceptedtalentplanet.LoadingLogin.PasswordLost.Change;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.VolleySingleton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-12-17.
 */

public class MainActivity extends AppCompatActivity {

    private EditText et_NewPassword;
    private Context mContext;
    private String userID;
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
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });


        btn_Next = (Button)findViewById(R.id.btn_Next_Change_PasswordLost);
        btn_Next.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                changePasswrord();
            }
        });

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

                    Intent intent = new Intent(getBaseContext(), com.accepted.acceptedtalentplanet.LoadingLogin.Login.MainActivity.class);
                    startActivity(intent);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
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
