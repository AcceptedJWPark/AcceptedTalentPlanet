package com.example.accepted.acceptedtalentplanet.MyProfile;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.CommonFunction;
import com.example.accepted.acceptedtalentplanet.Home.HomeActivity;
import com.example.accepted.acceptedtalentplanet.R;

public class MyprofileActivity extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile_activity);

        slidingMenuDL = (DrawerLayout) findViewById(R.id.MyProfile_listboxDL);

        //ToolBar 적용하기
        drawerView = (View) findViewById(R.id.MyProfile_container);
        imgDLOpenMenu = (ImageView) findViewById(R.id.DrawerOpenImg);
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

        CommonFunction c = new CommonFunction();
        c.setMenuClickEvents(getBaseContext());

    }

}
