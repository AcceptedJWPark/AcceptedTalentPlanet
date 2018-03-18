package com.example.accepted.acceptedtalentplanet.Join.Birth;

/**
 * Created by kwonhong on 2017-10-01.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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

public class MainActivity extends  AppCompatActivity {

    public String email;
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

    private RelativeLayout rl_preContainer;
    private TextView tv_TitleContainer;
    private LinearLayout ll_birthContainer;
    private View trashView;
    private Button btn_Accept;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_birthday);
        Intent intent = getIntent();

        mContext = getApplicationContext();

        //TODO:월에 01이 아니라 1로 입력하면 잘못저장됨. 확인필요

        email = intent.getStringExtra("email");
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

        rl_preContainer = (RelativeLayout) findViewById(R.id.rl_preContainer_join_birth);
        tv_TitleContainer = (TextView) findViewById(R.id.tv_TitleContainer_join_birth);
        ll_birthContainer = (LinearLayout) findViewById(R.id.ll_birthContainer_join_birth);
        trashView = findViewById(R.id.trashView_join_birth);
        btn_Accept = (Button) findViewById(R.id.btn_Accept_birth_join);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int preContainer_height = (int) (metrics.heightPixels*0.06);
        int titleContainer_height = (int) (metrics.heightPixels*0.1);
        int birthContainer_height= (int) (metrics.heightPixels*0.065);
        int trashView_height= (int) (metrics.heightPixels*0.02);
        int btn_Accept_height= (int) (metrics.heightPixels*0.04);

        ViewGroup.LayoutParams params1 = rl_preContainer.getLayoutParams();
        ViewGroup.LayoutParams params2 = tv_TitleContainer.getLayoutParams();
        ViewGroup.LayoutParams params3 = ll_birthContainer.getLayoutParams();
        ViewGroup.LayoutParams params4 = trashView.getLayoutParams();
        ViewGroup.LayoutParams params5 = btn_Accept.getLayoutParams();

        params1.height = preContainer_height;
        params2.height = titleContainer_height;
        params3.height = birthContainer_height;
        params4.height = trashView_height;
        params5.height = btn_Accept_height;

        rl_preContainer.setLayoutParams(params1);
        tv_TitleContainer.setLayoutParams(params2);
        ll_birthContainer.setLayoutParams(params3);
        trashView.setLayoutParams(params4);
        btn_Accept.setLayoutParams(params5);



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

                        Intent intent = new Intent(getBaseContext(), com.example.accepted.acceptedtalentplanet.LoadingLogin.Login.MainActivity.class);
                        startActivity(intent);
                        finish();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, SaveSharedPreference.getErrorListener()) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap();
                    params.put("userID", email);
                    params.put("userPW", pw);
                    params.put("userName", name);
                    params.put("userGender", gender);
                    params.put("userBirth", birth);
                    params.put("genderFlag", (genderPBS)?"Y":"N");
                    params.put("birthFlag", (birthPBS)?"Y":"N");
                    return params;
                }
            };

            postRequestQueue.add(postJsonRequest);
        }
    }
}
