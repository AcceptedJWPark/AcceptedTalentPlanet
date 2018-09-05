package com.accepted.acceptedtalentplanet.Join.Email;

/**
 * Created by kwonhong on 2017-10-01.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import static com.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MainActivity extends  AppCompatActivity {

    private String phone;
    private boolean isconfirmEmailCheck = false;
    private Context mContext;
    private EditText et_Email;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_email);

        mContext = getApplicationContext();

        ((LinearLayout)findViewById(R.id.ll_pre_Email_Join)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        phone = getIntent().getStringExtra("phone");

        et_Email = (EditText)findViewById(R.id.et_Email_Join);
        et_Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isconfirmEmailCheck = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        et_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }

            }
        });
    }

    public void goNext(View v){
        if(isconfirmEmailCheck) {
            Intent intent = new Intent(this, com.accepted.acceptedtalentplanet.Join.Password.MainActivity.class);
            intent.putExtra("email", et_Email.getText().toString());
            intent.putExtra("phone", phone);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "E-Mail 중복체크를 진행해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void emailCheck(View v){

        Log.d("Login Start", "start");
        final String jEmail = et_Email.getText().toString();
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

        final RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
//
//        final StringRequest sendMailRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/sendMail.do", new Response.Listener<String>(){
//            @Override
//            public void onResponse(String response){
//
//            }
//        }, SaveSharedPreference.getErrorListener(mContext)
//        ) {
//            @Override
//            protected Map<String, String> getParams(){
//                Map<String, String> params = new HashMap();
//                params.put("email", jEmail);
//                params.put("emailCode", emailCode);
//                Log.d("setting param", jEmail);
//
//                return params;
//            }
//        };
//
//        sendMailRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        final StringRequest emailCheckReq = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/checkMail.do", new Response.Listener<String>(){
//            @Override
//            public void onResponse(String response){
//                try {
//
//                    JSONObject obj = new JSONObject(response);
//                    emailCode = obj.getString("result");
//
//                    Log.d("result", response);
//                    Log.d("response", emailCode);
//                    postRequestQueue.add(sendMailRequest);
//                    Toast.makeText(mContext, "E-mail에서 인증번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
//                    ((Button) findViewById(R.id.btn_Accept_join_email)).setVisibility(View.VISIBLE);
//                }
//                catch(JSONException e){
//                    e.printStackTrace();
//                }
//                catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }, SaveSharedPreference.getErrorListener(mContext)
//        ) {
//            @Override
//            protected Map<String, String> getParams(){
//                Map<String, String> params = new HashMap();
//
//                return params;
//            }
//        };

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
                    Toast.makeText(getApplicationContext(), "사용가능한 E-Mail 입니다.", Toast.LENGTH_SHORT).show();
                    isconfirmEmailCheck = true;


                }
                catch(JSONException e){
                    e.printStackTrace();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        },SaveSharedPreference.getErrorListener(mContext)
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



}
