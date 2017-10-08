package com.example.accepted.acceptedtalentplanet;

/**
 * Created by kwonhong on 2017-10-01.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RegistEmailActivity extends  AppCompatActivity {

    private String joinCode;
    private boolean confirmEmailCheck = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_email);

        EditText email = (EditText)findViewById(R.id.et_join_mail);
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                confirmEmailCheck = false;
                joinCode = "aaaaa";
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void goNext(View v){
        if(confirmEmailCheck) {
            EditText email = (EditText) findViewById(R.id.et_join_mail);
            Intent intent = new Intent(this, RegistPasswordActivity.class);
            intent.putExtra("email", email.getText().toString());

            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "E-Mail 인증을 진행해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void emailCheck(View v){
        Log.d("Login Start", "start");
        final EditText email = (EditText)findViewById(R.id.et_join_mail);

        final String jEmail = email.getText().toString();

        Log.d("userID", jEmail);

        RequestFuture<String> future = RequestFuture.newFuture();

        final StringRequest sendMailRequest = new StringRequest(Request.Method.POST, "http://192.168.123.3:8080/Accepted/Regist/sendMail.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
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
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("email", jEmail);
                params.put("joinCode", joinCode);
                Log.d("setting param", jEmail);

                return params;
            }
        };

        final RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, "http://192.168.123.3:8080/Accepted/Regist/checkMail.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    Log.d("start", "response");
                    JSONObject obj = new JSONObject(response);
                    joinCode = obj.getString("result");
                    //String joinCode = obj.getString("joinCode");
                    Toast.makeText(getApplicationContext(), joinCode, Toast.LENGTH_SHORT).show();
                    Log.d("result", response);
                    Log.d("response", joinCode);
                    postRequestQueue.add(sendMailRequest);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
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
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();

                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);
    }

}