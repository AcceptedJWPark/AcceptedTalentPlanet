package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.Alarm.Alarm_Activity;
import com.example.accepted.acceptedtalentplanet.FriendList.FriendList_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.System.System_Activity;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

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
    LinearLayout CustomerService_ManualLL;

    DrawerLayout slidingMenuDL;
    View drawerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.toolbarTxt)).setText("고객센터");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        slidingMenuDL = (DrawerLayout) findViewById(R.id.CustomerService_listboxDL);
        drawerView = (View) findViewById(R.id.CustomerService_container);

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,CustomerService_MainActivity.this,slidingMenuDL,drawerView);
            }
        };
        DrawerLayout_ClickEvent(CustomerService_MainActivity.this,mClicklistener);


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

        CustomerService_ManualLL = (LinearLayout) findViewById(R.id.CustomerService_ManualLL);
        CustomerService_ManualLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CustomerService_ManualActivity.class);
                startActivity(intent);
            }
        });

    }

}
