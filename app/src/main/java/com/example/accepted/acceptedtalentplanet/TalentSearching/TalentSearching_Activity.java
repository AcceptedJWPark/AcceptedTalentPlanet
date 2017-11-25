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

import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-11-24.
 */

public class TalentSearching_Activity extends AppCompatActivity {

    Button TalentSeraching_searchingBoxOpen;
    Context mContext;

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;

    TextView ToolbarTxt;

    ArrayList<TalentSearching_ListItem> talentSearching_listItemArrayList;
    TalentSearching_ListAdapter talentSearching_listAdapter;
    ListView TalentSearching_ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsearching_activity);

        mContext = getApplicationContext();

        talentSearching_listItemArrayList = new ArrayList<>();
        talentSearching_listAdapter = new TalentSearching_ListAdapter(mContext, talentSearching_listItemArrayList);
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성1","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성2","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성3","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성4","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성5","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성6","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성7","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성8","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성9","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성10","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"정우성11","기타","피아노","드럼"));

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





                ToolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        ToolbarTxt.setText("재능 검색");

        TalentSeraching_searchingBoxOpen = (Button) findViewById(R.id.TalentSeraching_searchingBoxOpen);
        TalentSeraching_searchingBoxOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TalentSearching_SearchingPage_Activity.class);
                startActivity(intent);
            }
        });

        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentSearching_listboxDL);

        drawerView = (View) findViewById(R.id.TalentSearching_container);
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

}

