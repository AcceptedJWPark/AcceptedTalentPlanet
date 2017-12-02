package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-11-03.
 */

public class CustomerService_OnebyOneQuestionActivity extends AppCompatActivity {

LinearLayout CustomerService_OnebyOneQuesition_PreBtn;
EditText onebyonequestion_EditTxt;

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

        onebyonequestion_EditTxt = (EditText) findViewById(R.id.onebyonequestion_EditTxt);
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


