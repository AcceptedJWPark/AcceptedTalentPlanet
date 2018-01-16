package com.example.accepted.acceptedtalentplanet.LoadingLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
import com.android.volley.toolbox.RequestFuture;
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

public class Password_Lost_Accept_Activity extends AppCompatActivity {

    LinearLayout accept_join_mail;
    Button accept_join_mail_btn;
    Context mContext;
    EditText PasswordLost_Email;
    EditText PasswordLost_EmailCode;

    private String joinCode;
    private boolean confirmEmailCheck = false;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_passwordlost_accept_activity);

        mContext = getApplicationContext();

        PasswordLost_Email = (EditText) findViewById(R.id.PasswordLost_Email);
        PasswordLost_EmailCode = (EditText) findViewById(R.id.PasswordLost_EmailCode);

        PasswordLost_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v,mContext);
                }
            }
        });
        PasswordLost_EmailCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }
            }
        });



    }

    public Response.ErrorListener el = new Response.ErrorListener() {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void PasswordLost_emailCheck(View v)
    {
        emailCheck(v);
    }

    public void goNext(View v)
    {   PasswordLost_Email = (EditText) findViewById(R.id.PasswordLost_Email);
        final String jEmail = PasswordLost_Email.getText().toString();
        Intent i = new Intent(mContext, Password_Lost_Change_Activity.class);
        i.putExtra("userID", jEmail);
        startActivity(i);
    }

    public void emailCheck(View v){

        PasswordLost_Email = (EditText) findViewById(R.id.PasswordLost_Email);
        final String jEmail = PasswordLost_Email.getText().toString();
        /*isValidEmail(jEmail);*/


        //E-mail 주소 패턴 확인
        if(TextUtils.isEmpty(jEmail))
        {
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(jEmail).matches())
        {
            Toast.makeText(getApplicationContext(),"잘못된 E-mail 주소입니다.",Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("userID", jEmail);

        RequestFuture<String> future = RequestFuture.newFuture();
        final RequestQueue postRequestQueue = Volley.newRequestQueue(this);

        //TODO : 비밀번호 확인하는 로직 필요

        final StringRequest sendMailRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/sendFindPWMail.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                accept_join_mail.setVisibility(View.VISIBLE);
            }
        }, el
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("email", jEmail);
                params.put("joinCode", joinCode);
                Log.d("setting param", jEmail);

                return params;
            }
        };

        final StringRequest emailCheckReq = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/checkMail.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {

                    JSONObject obj = new JSONObject(response);
                    joinCode = obj.getString("result");

                    Log.d("result", response);
                    Log.d("response", joinCode);
                    postRequestQueue.add(sendMailRequest);
                    LinearLayout acceptEmail = (LinearLayout)findViewById(R.id.accept_join_mail);
                    Toast.makeText(mContext, "E-mail에서 인증번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    acceptEmail.setVisibility(View.VISIBLE);
                    accept_join_mail_btn = (Button) findViewById(R.id.accept_join_mail_btn);
                    accept_join_mail_btn.setVisibility(View.VISIBLE);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, el
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();

                return params;
            }
        };

        StringRequest emailDupCheckReq = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/checkDupID.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    if(!result.equals("duplicated")){
                        Toast.makeText(getApplicationContext(), "E-Mail이 존재하지 않습니다. 다시 확인하여주세요.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    postRequestQueue.add(emailCheckReq);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        },el
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", jEmail);
                return params;
            }
        };

        postRequestQueue.add(emailDupCheckReq);
    }




    public void PasswordLost_emailAccept(View v) {
            EditText Home_joinCode = (EditText) findViewById(R.id.PasswordLost_EmailCode);
            String acceptJoinCode = Home_joinCode.getText().toString();

            if (acceptJoinCode.equals(joinCode)) {
                confirmEmailCheck = true;
                Toast.makeText(getApplicationContext(), "E-Mail 인증이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Button acceptBtn = (Button) findViewById(R.id.btnAccept);
                findViewById(R.id.btnAccept).setOnClickListener(null);
            }
            else if(!acceptJoinCode.equals(joinCode))
            {
                Toast.makeText(getApplicationContext(), "인증번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
            }

        }


}
