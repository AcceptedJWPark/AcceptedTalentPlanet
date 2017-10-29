package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.Home.HomeActivity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.LoginActivity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyprofileActivity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentConditionActivity_1;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharingActivity;

public class TalentResisterActivity extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;

    Context mContext;

    Button TalentResister_ShowGiveBtn;
    Button TalentResister_ShowTakeBtn;
    TextView TalentResister_textView;
    Button TalentResister_Give_modifyBtn;
    Button TalentResister_Take_modifyBtn;
    TextView TalentResister_Keyword1;
    TextView TalentResister_Keyword2;
    TextView TalentResister_Keyword3;
    TextView TalentResister_Location1;
    TextView TalentResister_Location2;
    TextView TalentResister_Location3;
    TextView TalentResister_Level;
    TextView TalentResister_Point;
    LinearLayout TalentResister_Box;
    boolean TalentResister_Give;
    boolean TalentResister_Take;

    String TalentResister_Give_Keyword[] = {"Guitar", "Piano", "Drum"};
    String TalentResister_Give_Location[] = {"경기도 파주시 광탄면", "서울특별시 마포구 상수동", "경기도 고양시 일산동구"};
    String TalentResister_Give_Level = "중급(Intermediate)";
    int TalentResister_Give_Point = 150;

    String TalentResister_Take_Keyword[] = {"영어", "영어 회화", "영어 독학"};
    String TalentResister_Take_Location[] = {"부산광역시 해운대구 해운대동", "서울특별시 서대문구 창천동", "서울특별시 용산구 보광동"};
    String TalentResister_Take_Level = "상급(Advanced)";
    int TalentResister_Take_Point = 350;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_resist1);
        mContext = getApplicationContext();

        TalentResister_ShowGiveBtn = (Button) findViewById(R.id.TalentResister_ShowGive);
        TalentResister_ShowTakeBtn = (Button) findViewById(R.id.TalentResister_ShowTake);
        TalentResister_textView = (TextView) findViewById(R.id.TalentResister_MainTextView);
        TalentResister_Give_modifyBtn = (Button) findViewById(R.id.TalentResister_Give_ModifyBtn);
        TalentResister_Take_modifyBtn = (Button) findViewById(R.id.TalentResister_Take_ModifyBtn);
        TalentResister_Keyword1 = (TextView) findViewById(R.id.TalentResister_Keyword1);
        TalentResister_Keyword2 = (TextView) findViewById(R.id.TalentResister_Keyword2);
        TalentResister_Keyword3 = (TextView) findViewById(R.id.TalentResister_Keyword3);
        TalentResister_Location1 = (TextView) findViewById(R.id.TalentResister_Location1);
        TalentResister_Location2 = (TextView) findViewById(R.id.TalentResister_Location2);
        TalentResister_Location3 = (TextView) findViewById(R.id.TalentResister_Location3);
        TalentResister_Level = (TextView) findViewById(R.id.TalentResister_Level);
        TalentResister_Point = (TextView) findViewById(R.id.TalentResister_Point);
        TalentResister_Box = (LinearLayout) findViewById(R.id.TalentResister_boxLL);

        TalentResister_Give = true;
        TalentResister_Take = true;

        ShowGiveBtnClicked();


        TalentResister_ShowGiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowGiveBtnClicked();
            }
        });

        TalentResister_ShowTakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowTakeBtnClicked();
            }
        });

        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentResister1_listboxDL);

        drawerView = (View) findViewById(R.id.TalentResister_container1);
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


    }

    public void ShowGiveBtnClicked () {
        if (TalentResister_Give == false) {
            TalentResister_Box.setVisibility(View.GONE);
            TalentResister_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_whitebg));
            TalentResister_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_bluebg));
            TalentResister_ShowTakeBtn.setTextColor(Color.parseColor("#bebebe"));
            TalentResister_ShowGiveBtn.setTextColor(Color.parseColor("#ffffff"));
            TalentResister_textView.setText("\"재능드림이 등록되지 않았습니다.\n\n 재능드림을 등록하여 회원님의 재능을 공유해주세요!\"");
            TalentResister_Give_modifyBtn.setVisibility(View.VISIBLE);
            TalentResister_Take_modifyBtn.setVisibility(View.INVISIBLE);
            TalentResister_Give_modifyBtn.setText("재능드림 등록하기");
        }
        else {
            TalentResister_Box.setVisibility(View.VISIBLE);
            TalentResister_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_whitebg));
            TalentResister_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_bluebg));
            TalentResister_ShowTakeBtn.setTextColor(Color.parseColor("#bebebe"));
            TalentResister_ShowGiveBtn.setTextColor(Color.parseColor("#ffffff"));
            TalentResister_textView.setText("\"재능드림을 통해 회원님의 재능을 공유해주세요!\"");
            TalentResister_Give_modifyBtn.setVisibility(View.VISIBLE);
            TalentResister_Take_modifyBtn.setVisibility(View.INVISIBLE);
            TalentResister_Keyword1.setText(TalentResister_Give_Keyword[0]);
            TalentResister_Keyword2.setText(TalentResister_Give_Keyword[1]);
            TalentResister_Keyword3.setText(TalentResister_Give_Keyword[2]);
            TalentResister_Location1.setText(TalentResister_Give_Location[0]);
            TalentResister_Location2.setText(TalentResister_Give_Location[1]);
            TalentResister_Location3.setText(TalentResister_Give_Location[2]);
            TalentResister_Level.setText(TalentResister_Give_Level);
            TalentResister_Point.setText(Integer.toString(TalentResister_Give_Point) + "P");
        }
    }

    public void ShowTakeBtnClicked () {
        if (TalentResister_Take == false) {
            TalentResister_Box.setVisibility(View.GONE);
            TalentResister_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_whitebg));
            TalentResister_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_bluebg));
            TalentResister_ShowGiveBtn.setTextColor(Color.parseColor("#bebebe"));
            TalentResister_ShowTakeBtn.setTextColor(Color.parseColor("#ffffff"));
            TalentResister_textView.setText("\"관심재능이 등록되지 않았습니다.\n\n 관심재능을 등록하여 회원님의 관심사를 시작해보세요!\"");
            TalentResister_Take_modifyBtn.setVisibility(View.VISIBLE);
            TalentResister_Give_modifyBtn.setVisibility(View.INVISIBLE);
            TalentResister_Take_modifyBtn.setText("관심재능 등록하기");
        }
        else {
            TalentResister_Box.setVisibility(View.VISIBLE);
            TalentResister_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_whitebg));
            TalentResister_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_bluebg));
            TalentResister_ShowGiveBtn.setTextColor(Color.parseColor("#bebebe"));
            TalentResister_ShowTakeBtn.setTextColor(Color.parseColor("#ffffff"));
            TalentResister_textView.setText("\"관심재능을 통해 회원님의 관심사를 시작해보세요!\"");
            TalentResister_Take_modifyBtn.setVisibility(View.VISIBLE);
            TalentResister_Give_modifyBtn.setVisibility(View.INVISIBLE);
            TalentResister_Keyword1.setText(TalentResister_Take_Keyword[0]);
            TalentResister_Keyword2.setText(TalentResister_Take_Keyword[1]);
            TalentResister_Keyword3.setText(TalentResister_Take_Keyword[2]);
            TalentResister_Location1.setText(TalentResister_Take_Location[0]);
            TalentResister_Location2.setText(TalentResister_Take_Location[1]);
            TalentResister_Location3.setText(TalentResister_Take_Location[2]);
            TalentResister_Level.setText(TalentResister_Take_Level);
            TalentResister_Point.setText(Integer.toString(TalentResister_Take_Point) + "P");
        }


        //ToolBar 적용하기
    }
    public void slideMenuHome(View v){
        Intent i = new Intent(mContext, HomeActivity.class);
        startActivity(i);
    }

    public void slideMenuProfile(View v){
        Intent i = new Intent(mContext, MyprofileActivity.class);
        startActivity(i);
    }

    public void slideMenuTalent(View v){
        Intent i = new Intent(mContext, TalentResisterActivity.class);
        startActivity(i);
    }

    public void slideMenuTS(View v){
        Intent i = new Intent(mContext, TalentSharingActivity.class);
        startActivity(i);
    }

    public void slideMenuMyTalent(View v){
        Intent i = new Intent(mContext, TalentConditionActivity_1.class);
        startActivity(i);
    }

    public void slideMenuLogout(View v){
        SaveSharedPreference.clearUserInfo(mContext);
        Intent i = new Intent(mContext, LoginActivity.class);
        startActivity(i);
        finish();
    }


}
