package com.example.accepted.acceptedtalentplanet;

/**
 * Created by kwonhong on 2017-10-01.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RegistBirthActivity extends  AppCompatActivity {

    public String email;
    public String pw;
    public String name;
    public String gender;
    public String birth;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_birthday);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");

        Log.d("result", "email = " + email + ", pw = " + pw + ", name" + name + ", gender = " + gender);
    }

    public void goRegist(View v){
        EditText birthYear = (EditText)findViewById(R.id.et_join_birth_year);
        EditText birthMonth = (EditText)findViewById(R.id.et_join_birth_month);
        EditText birthDay = (EditText)findViewById(R.id.et_join_birth_day);
        birth = birthYear.getText().toString() + birthMonth.getText().toString() + birthDay.getText().toString();


        Log.d("Login Start", "start");

        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, "http://192.168.123.3:8080/Accepted/Regist/goRegist.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    Log.d("result", response);
                }
                catch(JSONException e){
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
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", email);
                params.put("userPW", pw);
                params.put("userName", name);
                params.put("userGender", gender);
                params.put("userBirth", birth);

                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

}
