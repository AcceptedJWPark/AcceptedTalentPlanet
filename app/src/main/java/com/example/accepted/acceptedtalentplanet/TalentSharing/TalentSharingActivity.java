package com.example.accepted.acceptedtalentplanet.TalentSharing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class TalentSharingActivity extends AppCompatActivity {
    ArrayList<TalentSharingListItem> TalentSharingList;
    TalentSharingListAdapter TalentSharing_Adapter;
    Context mContext;
    ListView TalentSharingListView;
    LinearLayout TalentShringSearchingOpen;
    LinearLayout TalentShringSearchingBox;
    Button searchingBtn;
    Button profileShowBtn;

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsharing_activity);

        mContext = getApplicationContext();

        TalentSharingListView = (ListView) findViewById(R.id.TalentSharing_LV);
        TalentSharingList = new ArrayList<>();

        TalentSharingList.add(new TalentSharingListItem(R.drawable.textpicture, "박종우", "Guitar","Drum","Piano", "7.12km", "Profile 보기"));
        TalentSharingList.add(new TalentSharingListItem(R.drawable.textpicture, "정우성", "Guitar","Vocal","Piano", "10.2km", "Profile 보기"));
        TalentSharingList.add(new TalentSharingListItem(R.drawable.textpicture, "민권홍", "Guitar","노래","Piano", "13km", "Profile 보기"));
        TalentSharingList.add(new TalentSharingListItem(R.drawable.textpicture, "김용인", "Guitar","Drum","기타", "15km", "Profile 보기"));
        TalentSharingList.add(new TalentSharingListItem(R.drawable.textpicture, "김진만", "Guitar","기타","Piano", "16km", "Profile 보기"));
        TalentSharingList.add(new TalentSharingListItem(R.drawable.textpicture, "배대명", "Guitar","Drum","통기타", "16.7km", "Profile 보기"));
        TalentSharingList.add(new TalentSharingListItem(R.drawable.textpicture, "우승제", "기타연주","기타독주","Piano", "31km", "Profile 보기"));
        TalentSharingList.add(new TalentSharingListItem(R.drawable.textpicture, "유성택", "일렉기타","기타","기타연습", "45km", "Profile 보기"));


        TalentSharing_Adapter = new TalentSharingListAdapter(mContext, TalentSharingList);
        TalentSharingListView.setAdapter(TalentSharing_Adapter);


        TalentShringSearchingBox = (LinearLayout) findViewById(R.id.TalentSharing_searchingBoxLL);
        TalentShringSearchingOpen = (LinearLayout) findViewById(R.id.TalentSharing_searchingBoxOpen);
        TalentShringSearchingOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TalentShringSearchingBox.getVisibility() == View.GONE) {
                    TalentShringSearchingBox.setVisibility(View.VISIBLE);
                }else if (TalentShringSearchingBox.getVisibility() == View.VISIBLE)
                    TalentShringSearchingBox.setVisibility(View.GONE);
            }
        });

        searchingBtn = (Button) findViewById(R.id.searchingBtn);
        searchingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TalentShringSearchingBox.setVisibility(View.GONE);
            }
        });

        profileShowBtn = (Button) findViewById(R.id.TalentSharing_ShowProfile);
        profileShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TalentSharingActivity.this, TalentSharingPopup.class);
                startActivity(intent);
            }
        });

        //ToolBar 적용하기
        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentSharing_listboxDL);
        drawerView = (View) findViewById(R.id.TalentSharing_container);
        imgDLOpenMenu = (ImageView) findViewById(R.id.DrawerOpenImg);
        imgDLOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.openDrawer(drawerView);

            }
        });

    }

}
