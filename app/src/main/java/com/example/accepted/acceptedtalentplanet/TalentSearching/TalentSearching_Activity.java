package com.example.accepted.acceptedtalentplanet.TalentSearching;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.Alarm.Alarm_Activity;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.FriendList.FriendList_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.System.System_Activity;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;

import java.util.ArrayList;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2017-11-24.
 */

public class TalentSearching_Activity extends AppCompatActivity {

    Button TalentSeraching_searchingBoxOpen;
    Context mContext;

    DrawerLayout slidingMenuDL;
    View drawerView;

    ArrayList<TalentSearching_ListItem> talentSearching_listItemArrayList;
    TalentSearching_ListAdapter talentSearching_listAdapter;
    ListView TalentSearching_ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsearching_activity);

        mContext = getApplicationContext();

        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentSearching_listboxDL);
        drawerView = (View) findViewById(R.id.TalentSearching_container);
        ((TextView) findViewById(R.id.toolbarTxt)).setText("재능 검색");
        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,TalentSearching_Activity.this,slidingMenuDL,drawerView);
            }
        };
        DrawerLayout_ClickEvent(TalentSearching_Activity.this,mClicklistener);

        talentSearching_listItemArrayList = new ArrayList<>();
        talentSearching_listAdapter = new TalentSearching_ListAdapter(mContext, talentSearching_listItemArrayList);
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"박종우","기타","기타 연습","기타 독주"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"민권홍","피아노","피아노 연주","Piano"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"유성택","복싱","권투","격투기"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"임하슬람","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"김정태","공무원 시험","공무원 9급","공무원 7급"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"우승제","축구","풋살","축구 프리킥"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"김용인","먹방","아프리카 bj","먹는 방송"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"배대명","비트박스","BeatBox","북치기 박치기"));

        TalentSearching_ListView = (ListView) findViewById(R.id.TalentSearching_ListView);
        TalentSearching_ListView.setAdapter(talentSearching_listAdapter);


                final Button giveButton = (Button)findViewById(R.id.TalentSearching_ShowGive);
                final Button takeButton = (Button)findViewById(R.id.TalentSearching_ShowTake);

                giveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        giveButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                        giveButton.setTextColor(getResources().getColor(R.color.textColor));
                        takeButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                        takeButton.setTextColor(Color.parseColor("#d2d2d2"));
                    }
                });

                takeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        takeButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                        takeButton.setTextColor(getResources().getColor(R.color.textColor));
                        giveButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                        giveButton.setTextColor(Color.parseColor("#d2d2d2"));
                    }
                });



        TalentSeraching_searchingBoxOpen = (Button) findViewById(R.id.TalentSeraching_searchingBoxOpen);
        TalentSeraching_searchingBoxOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TalentSearching_SearchingPage_Activity.class);
                startActivity(intent);
            }
        });

    }


}
