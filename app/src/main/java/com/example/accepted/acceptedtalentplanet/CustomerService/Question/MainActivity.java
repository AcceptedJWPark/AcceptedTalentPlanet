package com.example.accepted.acceptedtalentplanet.CustomerService.Question;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

/**
 * Created by Accepted on 2017-11-03.
 */

public class MainActivity extends AppCompatActivity {

    private LinearLayout ll_PreContainer;
    private EditText et_Question;
    private TextView tv_TxtCount;
    private Button btn_Save;

    private Context mContext;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_questionactivity);

        mContext = getBaseContext();

        ll_PreContainer = (LinearLayout) findViewById(R.id.ll_PreContainer_Question);
        ll_PreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_TxtCount = (TextView) findViewById(R.id.tv_TxtCount_Question);
        et_Question = (EditText) findViewById(R.id.et_Question_Question);
        btn_Save = (Button) findViewById(R.id.btn_Save_Question);

        et_Question.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv_TxtCount.setText(String.valueOf(s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_Question.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v,mContext);
                }

            }
        });

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_Question.getText().length() == 0)
                {
                    Toast.makeText(mContext,"문의 내역을 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    requestQuestion();
                    finish();
                }
            }
        });

    }

    public void requestQuestion() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Customer/requestQuestion.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                     JSONObject obj = new JSONObject(response);

                     if(obj.getString("result").equals("success")){
                         Toast.makeText(mContext,"문의 완료되었습니다.",Toast.LENGTH_SHORT).show();
                     }else{
                         Toast.makeText(mContext,"문의에 실패하였습니다.",Toast.LENGTH_SHORT).show();
                     }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();

                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("questionSummary", String.valueOf(et_Question.getText()));
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }



}


