package com.example.accepted.acceptedtalentplanet;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.Home.HomeActivity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.LoginActivity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyprofileActivity;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentConditionActivity_1;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResisterActivity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharingActivity;


/**
 * Created by kwonhong on 2017-10-28.
 */

public class CommonFunction extends AppCompatActivity {
    public void setMenuClickEvents(Context c){
        final Context context = c;
        TextView tv_home = (TextView)findViewById(R.id.SlidingMenu_Home);
        TextView tv_profile = (TextView)findViewById(R.id.SlidingMenu_Profile);
        TextView tv_talent = (TextView)findViewById(R.id.SlidingMenu_MyTalentResister);
        TextView tv_tShare = (TextView)findViewById(R.id.SlidingMenu_T_Sharing);
        TextView tv_myTalent = (TextView)findViewById(R.id.SlidingMenu_MyTalentProcess);
        TextView tv_message = (TextView)findViewById(R.id.SlidingMenu_MessageBox);
        TextView tv_custCenter = (TextView)findViewById(R.id.SlidingMenu_ServiceCenter);
        TextView tv_setting = (TextView)findViewById(R.id.SlidingMenu_System);
        LinearLayout tv_logout = (LinearLayout)findViewById(R.id.SlidingMenu_LogOut);

        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i =  new Intent(context, HomeActivity.class);
                startActivity(i);
            }
        });

        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(context, MyprofileActivity.class);
                startActivity(i);
            }
        });

        tv_talent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(context, TalentResisterActivity.class);
                startActivity(i);
            }
        });

        tv_tShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(context, TalentSharingActivity.class);
                startActivity(i);
            }
        });

        tv_myTalent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(context, TalentConditionActivity_1.class);
                startActivity(i);
            }
        });

        tv_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =  new Intent(context, HomeActivity.class);
                startActivity(i);
            }
        });

        tv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveSharedPreference.clearUserInfo(context);
                Intent i =  new Intent(context, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
