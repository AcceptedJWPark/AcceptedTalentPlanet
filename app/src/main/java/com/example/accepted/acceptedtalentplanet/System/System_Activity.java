package com.example.accepted.acceptedtalentplanet.System;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;


public class System_Activity extends AppCompatActivity {

    Context mContext;

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;
    TextView ToolbarTxt;

    Switch System_MessageRing_Switch;
    Switch System_ConditionRing_Switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_activity);

        mContext = getApplicationContext();


        Button System_SaveBtn = (Button) findViewById(R.id.System_SaveBtn);
        System_SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ToolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        ToolbarTxt.setText("설정");

        //ToolBar 적용하기
        slidingMenuDL = (DrawerLayout) findViewById(R.id.System_listboxDL);
        drawerView = (View) findViewById(R.id.System_container);
        imgDLOpenMenu = (ImageView) findViewById(R.id.ActionBar_Listview);
        DrawerCloseImg = (ImageView) findViewById(R.id.DrawerCloseImg);


        imgDLOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.openDrawer(drawerView);

            }
        });

        DrawerCloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.closeDrawer(drawerView);
            }
        });
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));


        System_MessageRing_Switch = (Switch) findViewById(R.id.System_MessageRing_Switch);
        System_MessageRing_Switch.setChecked(true);
        System_MessageRing_Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System_MessageRing_Switch.isChecked())
                {
                    System_MessageRing_Switch.setText("켜짐");
                }
                else
                    System_MessageRing_Switch.setText("꺼짐");
            }
        });
        System_ConditionRing_Switch = (Switch) findViewById(R.id.System_ConditionRing_Switch);
        System_ConditionRing_Switch.setChecked(true);
        System_ConditionRing_Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System_ConditionRing_Switch.isChecked())
                {
                    System_ConditionRing_Switch.setText("켜짐");
                }
                else
                    System_ConditionRing_Switch.setText("꺼짐");
            }
        });
    }

    public void slideMenuTalentSearching(View v){
        Intent i = new Intent(mContext, TalentSearching_Activity.class);
        startActivity(i);
    }

    public void slideMenuProfile(View v){
        Intent i = new Intent(mContext, MyProfile_Activity.class);
        startActivity(i);
    }

    public void slideMenuTalent(View v){
        Intent i = new Intent(mContext, TalentResister_Activity.class);
        startActivity(i);
    }

    public void slideMenuTS(View v){
        Intent i = new Intent(mContext, TalentSharing_Activity.class);
        startActivity(i);
    }

    public void slideMenuMyTalent(View v){
        Intent i = new Intent(mContext, TalentCondition_Activity.class);
        startActivity(i);
    }

    public void slideMenuLogout(View v){
        SaveSharedPreference.clearUserInfo(mContext);
        Intent i = new Intent(mContext, Login_Activity.class);
        startActivity(i);
        finish();
    }

    public void slideMenuCustomerService(View v){
        Intent i = new Intent(mContext, CustomerService_MainActivity.class);
        startActivity(i);
    }

    public void slideMenuSystem(View v){
        Intent i = new Intent(mContext, System_Activity.class);
        startActivity(i);
    }

}


