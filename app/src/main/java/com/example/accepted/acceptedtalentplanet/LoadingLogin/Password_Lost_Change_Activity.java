package com.example.accepted.acceptedtalentplanet.LoadingLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

/**
 * Created by Accepted on 2017-12-17.
 */

public class Password_Lost_Change_Activity extends AppCompatActivity {

    EditText PasswordLost_newPassword;
    Context mContext;
    String userID;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_passwordlost_change_activity);

        mContext = getApplicationContext();

        userID = getIntent().getStringExtra("userID");
        PasswordLost_newPassword = (EditText) findViewById(R.id.PasswordLost_newPassword);

        PasswordLost_newPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }
            }
        });



        Button PasswordLost_changePassword = (Button)findViewById(R.id.PasswordLost_changePassword);
        PasswordLost_changePassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                changePasswrord();
            }
        });

    }

    public void changePasswrord() {


        if(PasswordLost_newPassword.getText().toString().length() == 0)
        {
            Toast.makeText(mContext,"비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(PasswordLost_newPassword.getText().toString().length() < 6)
        {
            Toast.makeText(mContext, "비밀번호는 6자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        else if (checkPw(PasswordLost_newPassword))
        {
            Toast.makeText(mContext, "비밀번호는 영문, 숫자, 특수문자 중 두 개 이상의 조합으로 이루어져야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
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

                    Intent intent = new Intent(getBaseContext(), Login_Activity.class);
                    startActivity(intent);
                    finish();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        Log.d("res", res);

                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("userID", userID);
                params.put("userPW", PasswordLost_newPassword.getText().toString());

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
