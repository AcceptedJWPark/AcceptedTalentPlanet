package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_ListItem;
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_Popup;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

/**
 * Created by Accepted on 2017-11-03.
 */

public class CustomerService_OnebyOneQuestionActivity extends AppCompatActivity {

    LinearLayout CustomerService_OnebyOneQuesition_PreBtn;
    EditText onebyonequestion_EditTxt;
    TextView CustomerService_onebyoneTextLimit;
    Button CustomerService_requestQuestion;

    Context mContext;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_onebyonequestionactivity);

        mContext = getBaseContext();

        CustomerService_OnebyOneQuesition_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_OnebyOneQuesition_PreBtn);
        CustomerService_OnebyOneQuesition_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CustomerService_onebyoneTextLimit = (TextView) findViewById(R.id.CustomerService_onebyoneTextLimit);
        onebyonequestion_EditTxt = (EditText) findViewById(R.id.onebyonequestion_EditTxt);
        CustomerService_requestQuestion = (Button) findViewById(R.id.CustomerService_requestQuestion);

        onebyonequestion_EditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                CustomerService_onebyoneTextLimit.setText(String.valueOf(s.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        onebyonequestion_EditTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v,mContext);
                }

            }
        });

        CustomerService_requestQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQuestion();
            }
        });

    }

    public void requestQuestion() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
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
        }, new Response.ErrorListener() {
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
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();

                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("questionSummary", String.valueOf(onebyonequestion_EditTxt.getText()));
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }



}


