package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.accepted.acceptedtalentplanet.R;

public class TalentResisterActivity_1 extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_resist1);

        //ToolBar 적용하기
        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentResister1_listboxDL);
        drawerView = (View) findViewById(R.id.TalentCondition_container1);
        imgDLOpenMenu = (ImageView) findViewById(R.id.DrawerOpenImg);
        imgDLOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.openDrawer(drawerView);

            }
        });

    }

}
