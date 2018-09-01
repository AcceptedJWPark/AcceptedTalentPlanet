package com.accepted.acceptedtalentplanet.Join.Phone;

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
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
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
import java.util.regex.Pattern;

import static com.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MainActivity extends  AppCompatActivity {


    private Context mContext;
    EditText phoneNo;
    EditText confirmNo;
    String certNum;
    boolean isCheckCertNum = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_phone);

        mContext = getApplicationContext();
        phoneNo = findViewById(R.id.et_Phone_Join);
        confirmNo = findViewById(R.id.et_PhoneCode_Join);
        phoneNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {hideKeyboard(v,mContext);}}});
        confirmNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {hideKeyboard(v,mContext);}}});

        phoneNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isCheckCertNum = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void goNext(View v){
        if(isCheckCertNum) {
            Intent intent = new Intent(this, com.accepted.acceptedtalentplanet.Join.Email.MainActivity.class);
            intent.putExtra("phone", phoneNo.getText().toString().replaceAll("-",""));
            startActivity(intent);
        }else{
            Toast.makeText(mContext, "휴대폰 인증을 진행해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendSMS(View v){

        Log.d("Login Start", "start");
        final String phone = phoneNo.getText().toString().replaceAll("-","");
        /*isValidEmail(jEmail);*/

        //E-mail 주소 패턴 확인
        if(TextUtils.isEmpty(phone))
        {
            return;
        }
        else if(!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", phone))
        {
            Toast.makeText(getApplicationContext(),"잘못된 휴대폰 번호입니다.",Toast.LENGTH_SHORT).show();
            return;
        }

        final RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();

        final StringRequest sendSMSRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "/Member/sendJoinSMS.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    certNum = obj.getString("certNum");
                    Toast.makeText(mContext, "휴대폰에서 인증번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)
        ) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("sRecieveNum", phone);

                return params;
            }
        };

        postRequestQueue.add(sendSMSRequest);
    }

    public void confirmPhone(View v){
        if(certNum == null || certNum.isEmpty()){
            Toast.makeText(mContext, "인증번호를 발급하세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(certNum.equals(confirmNo.getText().toString())){
            isCheckCertNum = true;
            Toast.makeText(mContext, "휴대폰 인증이 완료되었습니다.", Toast.LENGTH_SHORT).show();
        }else{
            isCheckCertNum = false;
            Toast.makeText(mContext, "인증번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
        }
    }
}




