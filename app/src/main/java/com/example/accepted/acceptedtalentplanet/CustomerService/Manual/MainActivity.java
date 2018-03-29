package com.example.accepted.acceptedtalentplanet.CustomerService.Manual;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.checkDuplicatedLogin;

/**
 * Created by Accepted on 2017-10-31.
 */

public class MainActivity extends AppCompatActivity {

    private TextView tv_MyProfile;
    private TextView tv_MyTalent;
    private TextView tv_TSharing;
    private TextView tv_TCondition;
    private TextView tv_TSearching;
    private TextView tv_Message;
    private TextView tv_CustomerService;
    private TextView tv_System;
    private TextView tv_Alarm;
    private TextView tv_Friend;
    private TextView tv_SharingList;
    private TextView tv_Interesting;

    private Context mContext;

    private LinearLayout ll_PreContainer;
    private Button btn_NextToQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_manual_activity);

        mContext = getApplicationContext();

        tv_MyProfile = (TextView) findViewById(R.id.tv_MyProfile_Manual);
        tv_MyTalent = (TextView) findViewById(R.id.tv_MyTalent_Manual);
        tv_TSharing = (TextView) findViewById(R.id.tv_TSharing_Manual);
        tv_TCondition = (TextView) findViewById(R.id.tv_TCondition_Manual);
        tv_TSearching = (TextView) findViewById(R.id.tv_TSearching_Manual);
        tv_CustomerService = (TextView) findViewById(R.id.tv_CustomerService_Manual);
        tv_System = (TextView) findViewById(R.id.tv_System_Manual);
        tv_Message = (TextView) findViewById(R.id.tv_Message_Manual);
        tv_Alarm = (TextView) findViewById(R.id.tv_Alarm_Manual);
        tv_Friend = (TextView) findViewById(R.id.tv_Friend_Manual);
        tv_SharingList = (TextView) findViewById(R.id.tv_SharingList_Manual);
        tv_Interesting = (TextView) findViewById(R.id.tv_Interesting_Manual);

        //TODO : 메시지 매뉴얼 작성해야 함 (차후)
        btn_NextToQuestion = (Button) findViewById(R.id.btn_NextToQuestion_Manual);
        btn_NextToQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Question.MainActivity.class);
                startActivity(i);
            }
        });
        ll_PreContainer = (LinearLayout) findViewById(R.id.ll_PreContainer_Manual);
        ll_PreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_MyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","MyProfile");
                startActivity(intent);
            }
        });
        tv_MyTalent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","MyTalent");
                startActivity(intent);
            }
        });

        tv_TSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","TSharing");
                startActivity(intent);
            }
        });
        tv_TCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","TCondition");
                startActivity(intent);
            }
        });
        tv_TSearching.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","TSearching");
                startActivity(intent);
            }
        });
        tv_CustomerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","CustomerService");
                startActivity(intent);
            }
        });
        tv_System.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","System");
                startActivity(intent);
            }
        });
        tv_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","Message");
                startActivity(intent);
            }
        });

        tv_Alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","Alarm");
                startActivity(intent);
            }
        });

        tv_Friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","FriendList");
                startActivity(intent);
            }
        });

        tv_SharingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","SharingList");
                startActivity(intent);
            }
        });

        tv_Interesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","InterestingList");
                startActivity(intent);
            }
        });




    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onResume(){
        super.onResume();
        checkDuplicatedLogin(mContext, this);
    }


}



