package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-11-03.
 */

public class CustomerService_OnebyOneQuestionActivity extends AppCompatActivity {

LinearLayout CustomerService_OnebyOneQuesition_PreBtn;
EditText onebyonequestion_EditTxt;
TextView CustomerService_onebyoneTextLimit;

    //TODO : 1:1 문의하기 문의내역 서버에서 확인하고 서버에 답변 하는 방법?

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_onebyonequestionactivity);

        CustomerService_OnebyOneQuesition_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_OnebyOneQuesition_PreBtn);
        CustomerService_OnebyOneQuesition_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CustomerService_onebyoneTextLimit = (TextView) findViewById(R.id.CustomerService_onebyoneTextLimit);
        onebyonequestion_EditTxt = (EditText) findViewById(R.id.onebyonequestion_EditTxt);
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
                    hideKeyboard(v);
                }

            }
        });

    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }



}


