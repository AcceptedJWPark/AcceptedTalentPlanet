package com.accepted.acceptedtalentplanet.LoadingLogin.PasswordLost.Confirm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.VolleySingleton;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-12-17.
 */

public class MainActivity extends AppCompatActivity {

    Context mContext;
    EditText et_Email;
    EditText et_EmailCode;
    private String joinCode;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_passwordlost_accept_activity);

        mContext = getApplicationContext();

        et_Email = (EditText) findViewById(R.id.et_Email_Confirm_PasswordLost);



        et_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });
        et_EmailCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });



    }

    public void PasswordLost_emailCheck(View v)
    {
        Log.d("클릭 됨","클릭 됨");
        emailCheck(v);
    }

    public void goNext(View v)
    {
        final String jEmail = et_Email.getText().toString();
        Intent i = new Intent(mContext, com.accepted.acceptedtalentplanet.LoadingLogin.PasswordLost.Change.MainActivity.class);
        i.putExtra("userID", jEmail);
        startActivity(i);
    }

    public void emailCheck(View v) {

        final String jEmail = et_Email.getText().toString();
        /*isValidEmail(jEmail);*/


        //E-mail 주소 패턴 확인
        if (TextUtils.isEmpty(jEmail)) {
            return;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(jEmail).matches()) {
            Toast.makeText(getApplicationContext(), "잘못된 E-mail 주소입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

            RequestFuture<String> future = RequestFuture.newFuture();
            final RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();

            //TODO : 비밀번호 확인하는 로직 필요

            final StringRequest sendMailRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/sendFindPWMail.do", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("mailrequest", "true");
                }
            }, SaveSharedPreference.getErrorListener(mContext)
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap();
                    params.put("email", jEmail);
                    params.put("joinCode", joinCode);
                    Log.d("setting param", jEmail);

                    return params;
                }
            };

            sendMailRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            final StringRequest emailCheckReq = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/checkMail.do", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject obj = new JSONObject(response);
                        joinCode = obj.getString("result");

                        Log.d("result", response);
                        Log.d("response", joinCode);
                        postRequestQueue.add(sendMailRequest);

                        Toast.makeText(mContext, "E-mail에서 인증번호를 확인해주세요.", Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, SaveSharedPreference.getErrorListener(mContext)
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap();

                    return params;
                }
            };

            StringRequest emailDupCheckReq = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/checkDupID.do", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        String result = obj.getString("result");
                        if (!result.equals("duplicated")) {
                            Toast.makeText(getApplicationContext(), "E-Mail이 존재하지 않습니다. 다시 확인하여주세요.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        postRequestQueue.add(emailCheckReq);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, SaveSharedPreference.getErrorListener(mContext)
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap();
                    params.put("userID", jEmail);
                    return params;
                }
            };

            postRequestQueue.add(emailDupCheckReq);

        }



}
