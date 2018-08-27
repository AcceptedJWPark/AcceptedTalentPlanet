package com.accepted.acceptedtalentplanet.CustomerService.Manual;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.R;


/**
 * Created by Accepted on 2017-10-31.
 */

public class MainActivity extends AppCompatActivity {

    private TextView tv_MyProfile;
    private TextView tv_MyTalent;
    private TextView tv_TSharing;
    private TextView tv_TCondition;
    private TextView tv_Message;
    private TextView tv_CustomerService;
    private TextView tv_System;
    private TextView tv_Friend;
    private TextView tv_SharingList;
    private TextView tv_Interesting;


    private LinearLayout ll_PreContainer;
    private Button btn_NextToQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_manual_activity);

        ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setImageResource(R.drawable.icon_backbtn);
        ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView)findViewById(R.id.iv_RightBtn_Toolbar)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("Talent Planet 사용하기");




        tv_MyProfile = (TextView) findViewById(R.id.tv_MyProfile_Manual);
        tv_MyTalent = (TextView) findViewById(R.id.tv_MyTalent_Manual);
        tv_TSharing = (TextView) findViewById(R.id.tv_TSharing_Manual);
        tv_TCondition = (TextView) findViewById(R.id.tv_TCondition_Manual);
        tv_CustomerService = (TextView) findViewById(R.id.tv_CustomerService_Manual);
        tv_System = (TextView) findViewById(R.id.tv_System_Manual);
        tv_Message = (TextView) findViewById(R.id.tv_Message_Manual);
        tv_Friend = (TextView) findViewById(R.id.tv_Friend_Manual);
        tv_SharingList = (TextView) findViewById(R.id.tv_SharingList_Manual);
        tv_Interesting = (TextView) findViewById(R.id.tv_Interesting_Manual);

        //TODO : 메시지 매뉴얼 작성해야 함 (차후)
        btn_NextToQuestion = (Button) findViewById(R.id.btn_NextToQuestion_Manual);
        btn_NextToQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Question.MainActivity.class);
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
                Intent intent = new Intent(MainActivity.this, com.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","MyProfile");
                startActivity(intent);
            }
        });
        tv_MyTalent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","MyTalent");
                startActivity(intent);
            }
        });

        tv_TSharing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","TSharing");
                startActivity(intent);
            }
        });
        tv_TCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","TCondition");
                startActivity(intent);
            }
        });
        tv_CustomerService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","CustomerService");
                startActivity(intent);
            }
        });
        tv_System.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","System");
                startActivity(intent);
            }
        });
        tv_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","Message");
                startActivity(intent);
            }
        });


        tv_Friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","FriendList");
                startActivity(intent);
            }
        });

        tv_SharingList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","SharingList");
                startActivity(intent);
            }
        });

        tv_Interesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Manual.AnswerList.MainActivity.class);
                intent.putExtra("Value","InterestingList");
                startActivity(intent);
            }
        });




    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}



