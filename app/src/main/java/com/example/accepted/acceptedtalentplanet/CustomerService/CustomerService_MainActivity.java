package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.Home.HomeActivity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.LoginActivity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyprofileActivity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentConditionActivity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResisterActivity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharingActivity;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_MainActivity extends AppCompatActivity {

    Context mContext;
    LinearLayout CustomerService_Introduction;
    LinearLayout CustomerService_UpdateList;
    LinearLayout CustomerService_Version;
    LinearLayout CustomerService_Notice;
    LinearLayout CustomerService_FAQ;
    LinearLayout CustomerService_OnebyOneQuestion;
    LinearLayout CustomerService_OnebyOneQuestionList;
    LinearLayout CustomerService_Claim;
    LinearLayout CustomerService_ClaimList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_activity);

        CustomerService_Introduction = (LinearLayout) findViewById(R.id.CustomerService_IntroductionLL);
        CustomerService_Introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_IntroductionActivity.class);
                startActivity(intent);
            }
        });

        CustomerService_UpdateList = (LinearLayout) findViewById(R.id.CustomerService_UpdateListLL);
        CustomerService_UpdateList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_UpdateActivity.class);
                startActivity(intent);
            }
        });

        CustomerService_Version = (LinearLayout) findViewById(R.id.CustomerService_VersionLL);
        CustomerService_Version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_VersionActivity.class);
                startActivity(intent);
            }
        });

        CustomerService_Notice = (LinearLayout) findViewById(R.id.CustomerService_NoticeLL);
        CustomerService_Notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_NoticeActivity.class);
                startActivity(intent);
            }
        });


        CustomerService_FAQ = (LinearLayout) findViewById(R.id.CustomerService_FAQLL);
        CustomerService_FAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_FaqActivity.class);
                startActivity(intent);
            }
        });

        CustomerService_OnebyOneQuestion = (LinearLayout) findViewById(R.id.CustomerService_OnebyOneQuestionLL);
        CustomerService_OnebyOneQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_OnebyOneQuestionActivity.class);
                startActivity(intent);
            }
        });


        CustomerService_OnebyOneQuestionList = (LinearLayout) findViewById(R.id.CustomerService_OnebuOneQuestionListLL);
        CustomerService_OnebyOneQuestionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_OnebyOneQuestionListActivity.class);
                startActivity(intent);
            }
        });

        CustomerService_Claim = (LinearLayout) findViewById(R.id.CustomerService_ClaimLL);
        CustomerService_Claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_ClaimActivity.class);
                startActivity(intent);
            }
        });

        CustomerService_ClaimList = (LinearLayout) findViewById(R.id.CustomerService_ClaimListLL);
        CustomerService_ClaimList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_ClaimListActivity.class);
                startActivity(intent);
            }
        });

    }

    public void slideMenuHome(View v){
        Intent i = new Intent(mContext, HomeActivity.class);
        startActivity(i);
    }

    public void slideMenuProfile(View v){
        Intent i = new Intent(mContext, MyprofileActivity.class);
        startActivity(i);
    }

    public void slideMenuTalent(View v){
        Intent i = new Intent(mContext, TalentResisterActivity.class);
        startActivity(i);
    }

    public void slideMenuTS(View v){
        Intent i = new Intent(mContext, TalentSharingActivity.class);
        startActivity(i);
    }

    public void slideMenuMyTalent(View v){
        Intent i = new Intent(mContext, TalentConditionActivity.class);
        startActivity(i);
    }

    public void slideMenuLogout(View v){
        SaveSharedPreference.clearUserInfo(mContext);
        Intent i = new Intent(mContext, LoginActivity.class);
        startActivity(i);
        finish();
    }


    public void slideMenuCustomerService(View v){
        Intent i = new Intent(mContext, CustomerService_MainActivity.class);
        startActivity(i);
    }
}
