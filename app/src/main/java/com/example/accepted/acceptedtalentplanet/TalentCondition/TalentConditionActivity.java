package com.example.accepted.acceptedtalentplanet.TalentCondition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_ClaimActivity;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.Home.HomeActivity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.LoginActivity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyprofileActivity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResisterActivity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharingActivity;

public class TalentConditionActivity extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;
    Context mContext;
    TextView Talent_Give_Condition;
    TextView TalentCondition_Give_Keyword1;
    TextView TalentCondition_Give_Keyword2;
    TextView TalentCondition_Give_Keyword3;
    TextView TalentCondition_Give_Text1;
    TextView TalentCondition_Give_Text2;
    Button TalentCondition_Give_btn01;
    Button TalentCondition_Give_btn02;
    Button TalentCondition_Give_btn03;
    LinearLayout TalentCondition_Give_KeywordBox;
    TextView Talent_Take_Condition;
    TextView TalentCondition_Take_Keyword1;
    TextView TalentCondition_Take_Keyword2;
    TextView TalentCondition_Take_Keyword3;
    TextView TalentCondition_Take_Text1;
    TextView TalentCondition_Take_Text2;
    Button TalentCondition_Take_btn01;
    Button TalentCondition_Take_btn02;
    Button TalentCondition_Take_btn03;
    LinearLayout TalentCondition_Take_KeywordBox;
    boolean TalentCondition_Give;
    boolean TalentCondition_Take;
    String TalentCondition_Give_Keyword[] = {"Guitar", "Piano", "Drum"};
    String TalentCondition_Take_Keyword[] = {"영어", "영어 회화", "영어 독학"};
    int GiveTalentConditionCode = 1;
    int TakeTalentConditionCode = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentcondition_activity);
        mContext = getApplicationContext();
        Talent_Give_Condition = (TextView) findViewById(R.id.TalentCondition_Give_Condition);
        TalentCondition_Give_Keyword1 = (TextView) findViewById(R.id.TalentCondition_Give_Keyword1);
        TalentCondition_Give_Keyword2 = (TextView) findViewById(R.id.TalentCondition_Give_Keyword2);
        TalentCondition_Give_Keyword3 = (TextView) findViewById(R.id.TalentCondition_Give_Keyword3);
        TalentCondition_Give_Text1 = (TextView) findViewById(R.id.TalentCondition_Give_TextView1);
        TalentCondition_Give_Text2 = (TextView) findViewById(R.id.TalentCondition_Give_TextView2);
        TalentCondition_Give_btn01 = (Button) findViewById(R.id.TalentCondition_Give_Button1);
        TalentCondition_Give_btn02 = (Button) findViewById(R.id.TalentCondition_Give_Button2);
        TalentCondition_Give_btn03 = (Button) findViewById(R.id.TalentCondition_Give_Button3);
        TalentCondition_Give_KeywordBox = (LinearLayout) findViewById(R.id.TalentCondition_Give_KeywordBox);

        Talent_Take_Condition = (TextView) findViewById(R.id.TalentCondition_Take_Condition);
        TalentCondition_Take_Keyword1 = (TextView) findViewById(R.id.TalentCondition_Take_Keyword1);
        TalentCondition_Take_Keyword2 = (TextView) findViewById(R.id.TalentCondition_Take_Keyword2);
        TalentCondition_Take_Keyword3 = (TextView) findViewById(R.id.TalentCondition_Take_Keyword3);
        TalentCondition_Take_Text1 = (TextView) findViewById(R.id.TalentCondition_Take_TextView1);
        TalentCondition_Take_Text2 = (TextView) findViewById(R.id.TalentCondition_Take_TextView2);
        TalentCondition_Take_btn01 = (Button) findViewById(R.id.TalentCondition_Take_Button1);
        TalentCondition_Take_btn02 = (Button) findViewById(R.id.TalentCondition_Take_Button2);
        TalentCondition_Take_btn03 = (Button) findViewById(R.id.TalentCondition_Take_Button3);
        TalentCondition_Take_KeywordBox = (LinearLayout) findViewById(R.id.TalentCondition_Take_KeywordBox);

        TalentCondition_Give = true;
        TalentCondition_Take = false;


        //ToolBar 적용하기
        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentCondition1_listboxDL);
        drawerView = (View) findViewById(R.id.TalentCondition_container1);
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

        if (TalentCondition_Give == false) {
            TalentCondition_Give_KeywordBox.setVisibility(View.GONE);
            TalentCondition_Give_Text1.setText("\"재능드림이 등록되지 않았습니다.");
            TalentCondition_Give_Text2.setText("재능드림을 등록하여 회원님의 재능을 공유해주세요!\"");
            TalentCondition_Give_btn01.setVisibility(View.GONE);
            TalentCondition_Give_btn02.setVisibility(View.GONE);
            TalentCondition_Give_btn03.setVisibility(View.VISIBLE);
            TalentCondition_Give_btn03.setText("재능드림 등록하기");
        } else

        {
            TalentCondition_Give_KeywordBox.setVisibility(View.VISIBLE);
            TalentCondition_Give_btn01.setVisibility(View.VISIBLE);
            TalentCondition_Give_btn02.setVisibility(View.VISIBLE);
            TalentCondition_Give_btn03.setVisibility(View.GONE);
            TalentCondition_Give_Keyword1.setText(TalentCondition_Give_Keyword[0]);
            TalentCondition_Give_Keyword2.setText(TalentCondition_Give_Keyword[1]);
            TalentCondition_Give_Keyword3.setText(TalentCondition_Give_Keyword[2]);

            if (GiveTalentConditionCode == 1) {
                TalentCondition_Give_Text1.setText("\"현재 회원님의 재능드림 상태는 대기중 입니다.");
                TalentCondition_Give_Text2.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!\"");
                TalentCondition_Give_btn01.setText("관심목록 확인");
                TalentCondition_Give_btn02.setText("T.Sharing");
                Talent_Give_Condition.setText("대기 중...");


            } else if (GiveTalentConditionCode == 2) {
                TalentCondition_Give_Text1.setText("\"현재 회원님의 재능드림 상태는 진행중 입니다.");
                TalentCondition_Give_Text2.setText("다음 단계를 진행해주세요!\"");
                TalentCondition_Give_btn01.setText("완료 하기");
                TalentCondition_Give_btn02.setText("취소 하기");
                Talent_Give_Condition.setText("진행 중...");
            } else if (GiveTalentConditionCode == 3) {
                TalentCondition_Give_Text1.setText("\"현재 회원님의 재능드림 상태는 완료 입니다.");
                TalentCondition_Give_Text2.setText("재능 재등록 또는 재능 수정하기를 진행해주세요!\"");
                TalentCondition_Give_btn01.setText("재능드림 재등록");
                TalentCondition_Give_btn02.setText("재능드림 수정하기");
                Talent_Give_Condition.setText("완료");
            }
        }

        if (TalentCondition_Take == false) {
            TalentCondition_Take_KeywordBox.setVisibility(View.GONE);
            TalentCondition_Take_Text1.setText("\"관심재능이 등록되지 않았습니다.");
            TalentCondition_Take_Text2.setText("관심재능을 등록하여 회원님의 재능을 공유해주세요!\"");
            TalentCondition_Take_btn01.setVisibility(View.GONE);
            TalentCondition_Take_btn02.setVisibility(View.GONE);
            TalentCondition_Take_btn03.setVisibility(View.VISIBLE);
            TalentCondition_Take_btn03.setText("관심재능 등록하기");
        } else

        {
            TalentCondition_Take_KeywordBox.setVisibility(View.VISIBLE);
            TalentCondition_Take_btn01.setVisibility(View.VISIBLE);
            TalentCondition_Take_btn02.setVisibility(View.VISIBLE);
            TalentCondition_Take_btn03.setVisibility(View.GONE);
            TalentCondition_Take_Keyword1.setText(TalentCondition_Take_Keyword[0]);
            TalentCondition_Take_Keyword2.setText(TalentCondition_Take_Keyword[1]);
            TalentCondition_Take_Keyword3.setText(TalentCondition_Take_Keyword[2]);

            if (TakeTalentConditionCode == 1) {
                TalentCondition_Take_Text1.setText("\"현재 회원님의 관심재능 상태는 대기중 입니다.");
                TalentCondition_Take_Text2.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!\"");
                TalentCondition_Take_btn01.setText("관심목록 확인");
                TalentCondition_Take_btn02.setText("T.Sharing");
                Talent_Take_Condition.setText("대기 중...");


            } else if (TakeTalentConditionCode == 2) {
                TalentCondition_Take_Text1.setText("\"현재 회원님의 관심재능 상태는 진행중 입니다.");
                TalentCondition_Take_Text2.setText("다음 단계를 진행해주세요!\"");
                TalentCondition_Take_btn01.setText("완료 하기");
                TalentCondition_Take_btn02.setText("취소 하기");
                Talent_Take_Condition.setText("진행 중...");
            } else if (TakeTalentConditionCode == 3) {
                TalentCondition_Take_Text1.setText("\"현재 회원님의 관심재능 상태는 완료 입니다.");
                TalentCondition_Take_Text2.setText("재능 재등록 또는 재능 수정하기를 진행해주세요!\"");
                TalentCondition_Take_btn01.setText("관심재능 재등록");
                TalentCondition_Take_btn02.setText("관심재능 수정하기");
                Talent_Take_Condition.setText("완료");
            }
        }
        
        
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
        Intent i = new Intent(mContext, TalentConditionActivity.class);
        startActivity(i);
    }

    public void slideMenuLogout(View v){
        SaveSharedPreference.clearUserInfo(mContext);
        Intent i = new Intent(mContext, LoginActivity.class);
        startActivity(i);
        finish();
    }


    public void slideMenuCustomerService(View v){
        Intent i = new Intent(mContext, CustomerService_MainActivity.class);
        startActivity(i);
    }
}
