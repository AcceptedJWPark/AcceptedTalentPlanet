package com.accepted.acceptedtalentplanet.Join.Birth;

/**
 * Created by kwonhong on 2017-10-01.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MainActivity extends AppCompatActivity {

    public String email, phone;
    public String pw;
    public String name;
    public String gender;
    public String birth;
    public boolean genderPBS;
    public boolean birthPBS;

    private EditText et_Year;
    private EditText et_Month;
    private EditText et_Day;

    private Context mContext;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_birthday);
        Intent intent = getIntent();

        mContext = getApplicationContext();

        //TODO:월에 01이 아니라 1로 입력하면 잘못저장됨. 확인필요

        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
        gender = intent.getStringExtra("gender");
        genderPBS = intent.getBooleanExtra("genderPBS", true);

        ((LinearLayout)findViewById(R.id.ll_preContainer_Birth_Join)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        et_Year = (EditText)findViewById(R.id.et_Yeat_birth_Join);
        et_Month = (EditText)findViewById(R.id.et_Month_birth_Join);
        et_Day = (EditText)findViewById(R.id.et_Day_birth_Join);

        et_Year.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(v,mContext);
            }
        });

        et_Month.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(v,mContext);
            }
        });

        et_Day.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(v,mContext);
            }
        });

        Log.d("result", "email = " + email + ", pw = " + pw + ", name" + name + ", gender = " + gender);
    }

    public void goRegist(View v) {
        CheckBox birthCheckbox = (CheckBox) findViewById(R.id.Join_BirthDayNoShowCheck);
        birthPBS = !birthCheckbox.isChecked();

        String birthYearTxt = et_Year.getText().toString();
        String birthMonthTxt = et_Month.getText().toString();
        String birthDayTxt = et_Day.getText().toString();

        if(birthYearTxt == null || birthYearTxt.isEmpty() || birthMonthTxt == null || birthMonthTxt.isEmpty() || birthDayTxt == null || birthDayTxt.isEmpty()){
            Toast.makeText(mContext, "생년월일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        Pattern pattern = Pattern.compile("^((19|20)[0-9]{2})");
        Matcher matcher = pattern.matcher(birthYearTxt);
        if(!matcher.find()){
            Toast.makeText(mContext, "년도 형식을 확인해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            df.setLenient(false);
            df.parse(birthYearTxt + birthMonthTxt + birthDayTxt);
        }catch (Exception e){
            Toast.makeText(mContext, "생년월일을 확인해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(Integer.parseInt(birthMonthTxt) < 10)
            birthMonthTxt = "0"+birthMonthTxt.substring(birthMonthTxt.length() - 1);
        if(Integer.parseInt(birthDayTxt) < 10)
            birthDayTxt = "0"+birthDayTxt.substring(birthDayTxt.length() - 1);


        birth = birthYearTxt + birthMonthTxt + birthDayTxt;


        if (birthYearTxt.length() == 0 || birthMonthTxt.length() == 0 || birthDayTxt.length() == 0) {
            Toast.makeText(mContext, "생년월일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            Log.d("Login Start", "start");

            RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
            StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/goRegist.do", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject obj = new JSONObject(response);
                        String result = obj.getString("result");
                        if (result.equals("success")) {
                            Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
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
                    params.put("userID", email);
                    params.put("userPW", pw);
                    params.put("userName", name);
                    params.put("userGender", gender);
                    params.put("userBirth", birth);
                    params.put("phone", phone);
                    params.put("genderFlag", (genderPBS)?"Y":"N");
                    params.put("birthFlag", (birthPBS)?"Y":"N");
                    return params;
                }
            };

            postRequestQueue.add(postJsonRequest);
        }
    }
}
