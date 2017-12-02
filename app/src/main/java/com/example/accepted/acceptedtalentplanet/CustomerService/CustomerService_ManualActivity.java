package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_ManualActivity extends AppCompatActivity {

    TextView CustomerService_manual_MyProfile;
    TextView CustomerService_manual_MyTalent;
    TextView CustomerService_manual_TSharing;
    TextView CustomerService_manual_TCondition;
    TextView CustomerService_manual_TSearching;
    TextView CustomerService_manual_CustomerService;
    TextView CustomerService_manual_System;
    TextView CustomerService_manual_Message;

    EditText CustomerService_Manual_EditTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_manual_activity);

        CustomerService_manual_MyProfile = (TextView) findViewById(R.id.CustomerService_manual_MyProfile);
        CustomerService_manual_MyTalent = (TextView) findViewById(R.id.CustomerService_manual_MyTalent);
        CustomerService_manual_TSharing = (TextView) findViewById(R.id.CustomerService_manual_TSharing);
        CustomerService_manual_TCondition = (TextView) findViewById(R.id.CustomerService_manual_TCondition);
        CustomerService_manual_TSearching = (TextView) findViewById(R.id.CustomerService_manual_TSearching);
        CustomerService_manual_CustomerService = (TextView) findViewById(R.id.CustomerService_manual_CustomerService);
        CustomerService_manual_System = (TextView) findViewById(R.id.CustomerService_manual_System);
        CustomerService_manual_Message = (TextView) findViewById(R.id.CustomerService_manual_Message);

        CustomerService_manual_MyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerService_ManualActivity.this,CustomerService_Manual_AnswerList_Activity.class);
                intent.putExtra("Value","MyProfile");
                startActivity(intent);
            }
        });
        CustomerService_manual_MyTalent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerService_ManualActivity.this,CustomerService_Manual_AnswerList_Activity.class);
                intent.putExtra("Value","MyTalent");
                startActivity(intent);
            }
        });

        CustomerService_manual_TSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustomerService_Manual_AnswerList_Activity.class);
                intent.putExtra("Value","TSharing");
                startActivity(intent);
            }
        });
        CustomerService_manual_TCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustomerService_Manual_AnswerList_Activity.class);
                intent.putExtra("Value","TCondition");
                startActivity(intent);
            }
        });
        CustomerService_manual_TSearching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustomerService_Manual_AnswerList_Activity.class);
                intent.putExtra("Value","TSearching");
                startActivity(intent);
            }
        });
        CustomerService_manual_CustomerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustomerService_Manual_AnswerList_Activity.class);
                intent.putExtra("Value","CustomerService");
                startActivity(intent);
            }
        });
        CustomerService_manual_System.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustomerService_Manual_AnswerList_Activity.class);
                intent.putExtra("Value","System");
                startActivity(intent);
            }
        });
        CustomerService_manual_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CustomerService_Manual_AnswerList_Activity.class);
                intent.putExtra("Value","Message");
                startActivity(intent);
            }
        });


        CustomerService_Manual_EditTxt = (EditText) findViewById(R.id.CustomerService_Manual_EditTxt);
        CustomerService_Manual_EditTxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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



