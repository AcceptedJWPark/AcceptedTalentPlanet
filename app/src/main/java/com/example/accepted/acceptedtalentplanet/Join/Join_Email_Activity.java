package com.example.accepted.acceptedtalentplanet.Join;

/**
 * Created by kwonhong on 2017-10-01.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

public class Join_Email_Activity extends  AppCompatActivity {

    private String joinCode;
    private boolean confirmEmailCheck = false;
    Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_email);

        EditText emailCheck = (EditText) findViewById(R.id.Join_joinCode);
        EditText email = (EditText)findViewById(R.id.Join_Email);
        findViewById(R.id.btnAccept).setOnClickListener(mClickListener);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                confirmEmailCheck = false;
                joinCode = "aaaaa";
                findViewById(R.id.btnAccept).setOnClickListener(mClickListener);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        emailCheck.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v);
                }

            }
        });
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v);
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

    public void goNext(View v){
        if(confirmEmailCheck) {
            EditText email = (EditText) findViewById(R.id.Join_Email);
            Intent intent = new Intent(this, Join_Password_Activity.class);
            intent.putExtra("email", email.getText().toString());
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "E-Mail 인증을 진행해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void emailCheck(View v){

        Log.d("Login Start", "start");
        final EditText email = (EditText)findViewById(R.id.Join_Email);
        final String jEmail = email.getText().toString();
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

        final StringRequest sendMailRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/sendMail.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){

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
                    //String joinCode = obj.getString("joinCode");
                    Toast.makeText(getApplicationContext(), joinCode, Toast.LENGTH_SHORT).show();
                    Log.d("result", response);
                    Log.d("response", joinCode);
                    postRequestQueue.add(sendMailRequest);
                    LinearLayout acceptEmail = (LinearLayout)findViewById(R.id.accept_join_mail);
                    acceptEmail.setVisibility(View.VISIBLE);
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
                    if(result.equals("duplicated")){
                        Toast.makeText(getApplicationContext(), "이미 가입된 E-Mail 입니다.", Toast.LENGTH_SHORT).show();
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

   /* public final boolean isValidEmail(CharSequence target)
    {
        if(TextUtils.isEmpty(target))
        {
            return false;
        }
        else
        {
            Toast.makeText(getApplicationContext(),"잘못된 E-mail 주소입니다.",Toast.LENGTH_SHORT).show();
            return Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }*/

    Button.OnClickListener mClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            EditText Home_joinCode = (EditText) findViewById(R.id.Join_joinCode);
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
    };

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
