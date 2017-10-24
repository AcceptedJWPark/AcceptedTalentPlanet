package com.example.accepted.acceptedtalentplanet.MyProfile;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.accepted.acceptedtalentplanet.R;

public class MyprofileActivity extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile_activity);


        //ToolBar 적용하기
        slidingMenuDL = (DrawerLayout) findViewById(R.id.MyProfile_listboxDL);
        drawerView = (View) findViewById(R.id.MyProfile_container);
        imgDLOpenMenu = (ImageView) findViewById(R.id.DrawerOpenImg);
        imgDLOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.openDrawer(drawerView);

            }
        });


    }
}
