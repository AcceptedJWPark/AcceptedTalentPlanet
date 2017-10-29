package com.example.accepted.acceptedtalentplanet.TalentCondition;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.accepted.acceptedtalentplanet.R;

public class TalentConditionActivity_3 extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentcondition_3);


        //ToolBar 적용하기
        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentCondition3_listboxDL);
        drawerView = (View) findViewById(R.id.TalentCondition_container3);
        imgDLOpenMenu = (ImageView) findViewById(R.id.DrawerOpenImg);
        imgDLOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.openDrawer(drawerView);

            }
        });
    }

}