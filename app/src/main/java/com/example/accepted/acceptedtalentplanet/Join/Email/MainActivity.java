package com.example.accepted.acceptedtalentplanet.Join.Email;

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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.RequestFuture;
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

    private String emailCode;
    private boolean isconfirmEmailCheck = false;
    private Context mContext;
    private EditText et_EmailCode;
    private EditText et_Email;

    private RelativeLayout rl_preContainer;
    private TextView tv_TitleContainer;
    private LinearLayout ll_EmailContainer;
    private LinearLayout ll_EmailAcceptContainer;
    private View trashView1;
    private View trashView2;
    private Button btn_Accept;

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

        et_EmailCode = (EditText) findViewById(R.id.et_EmailCode_Email_Join);
        et_Email = (EditText)findViewById(R.id.et_Email_Join);

        rl_preContainer = (RelativeLayout) findViewById(R.id.rl_preContainer_join_email);
        tv_TitleContainer = (TextView) findViewById(R.id.tv_TitleContainer_join_email);
        ll_EmailContainer = (LinearLayout) findViewById(R.id.ll_birthContainer_join_email);
        ll_EmailAcceptContainer = (LinearLayout) findViewById(R.id.ll_AcceptEmailContainer_Join_email);
        trashView1 = (View) findViewById(R.id.trashView1_join_email);
        trashView2 = (View) findViewById(R.id.trashView2_join_email);
        btn_Accept = (Button) findViewById(R.id.btn_Accept_join_email);


        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);


        int preContainer= (int) (metrics.heightPixels*0.06);
        int Title_height = (int) (metrics.heightPixels*0.1);
        int emailAccept_height = (int) (metrics.heightPixels*0.05);
        int trashView1_height= (int) (metrics.heightPixels*0.02);
        int trashView2_height= (int) (metrics.heightPixels*0.02);
        int nextBtn_height= (int) (metrics.heightPixels*0.04);

        ViewGroup.LayoutParams params1 = rl_preContainer.getLayoutParams();
        ViewGroup.LayoutParams params2 = tv_TitleContainer.getLayoutParams();
        ViewGroup.LayoutParams params3 = ll_EmailContainer.getLayoutParams();
        ViewGroup.LayoutParams params4 = ll_EmailAcceptContainer.getLayoutParams();
        ViewGroup.LayoutParams params5 = trashView1.getLayoutParams();
        ViewGroup.LayoutParams params6 = trashView2.getLayoutParams();
        ViewGroup.LayoutParams params7 = btn_Accept.getLayoutParams();

        params1.height = preContainer;
        params2.height = Title_height;
        params3.height = emailAccept_height;
        params4.height = emailAccept_height;
        params5.height = trashView1_height;
        params6.height = trashView2_height;
        params7.height = nextBtn_height;

        rl_preContainer.setLayoutParams(params1);
        tv_TitleContainer.setLayoutParams(params2);
        ll_EmailContainer.setLayoutParams(params3);
        ll_EmailAcceptContainer.setLayoutParams(params4);
        trashView1.setLayoutParams(params5);
        trashView2.setLayoutParams(params6);
        btn_Accept.setLayoutParams(params7);


        findViewById(R.id.btn_ConfirmEmail).setOnClickListener(mClickListener);
                et_Email.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                isconfirmEmailCheck = false;
                emailCode = "default";
                findViewById(R.id.btn_ConfirmEmail).setOnClickListener(mClickListener);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        et_EmailCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }

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
            Intent intent = new Intent(this, com.example.accepted.acceptedtalentplanet.Join.Password.MainActivity.class);
            intent.putExtra("email", et_Email.getText().toString());
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "E-Mail 인증을 진행해주세요.", Toast.LENGTH_SHORT).show();
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

        Log.d("userID", jEmail);

        RequestFuture<String> future = RequestFuture.newFuture();
        final RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();

        final StringRequest sendMailRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/sendMail.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){

            }
        }, SaveSharedPreference.getErrorListener()
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("email", jEmail);
                params.put("emailCode", emailCode);
                Log.d("setting param", jEmail);

                return params;
            }
        };

        sendMailRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        final StringRequest emailCheckReq = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Regist/checkMail.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {

                    JSONObject obj = new JSONObject(response);
                    emailCode = obj.getString("result");

                    Log.d("result", response);
                    Log.d("response", emailCode);
                    postRequestQueue.add(sendMailRequest);
                    Toast.makeText(mContext, "E-mail에서 인증번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                    ((LinearLayout)findViewById(R.id.ll_AcceptEmailContainer_Join_email)).setVisibility(View.VISIBLE);
                    ((Button) findViewById(R.id.btn_Accept_join_email)).setVisibility(View.VISIBLE);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()
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
        },SaveSharedPreference.getErrorListener()
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


    Button.OnClickListener mClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            String acceptJoinCode = et_EmailCode.getText().toString();

            if (acceptJoinCode.equals(emailCode)) {
                isconfirmEmailCheck = true;
                Toast.makeText(getApplicationContext(), "E-Mail 인증이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                ((Button)findViewById(R.id.btn_ConfirmEmail)).setOnClickListener(null);
            }
            else if(!acceptJoinCode.equals(emailCode))
            {
                Toast.makeText(getApplicationContext(), "인증번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
            }

        }
    };


}
