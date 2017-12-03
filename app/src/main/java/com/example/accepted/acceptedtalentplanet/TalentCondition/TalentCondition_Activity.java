package com.example.accepted.acceptedtalentplanet.TalentCondition;

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

import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.Home.Home_Activity;
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_Activity;
import com.example.accepted.acceptedtalentplanet.InterestingList.Interesting_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.System.System_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;

public class TalentCondition_Activity extends AppCompatActivity {

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

    Button TalentCondition_ShowGive;
    Button TalentCondition_ShowTake;
    LinearLayout Talent_Give_ConditionBox;
    LinearLayout Talent_Take_ConditionBox;

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
    String TalentCondition_Give_Keyword[];
    String TalentCondition_Take_Keyword[];

    MyTalent GiveTalent, TakeTalent;
    int GiveTalentConditionCode = 1;
    int TakeTalentConditionCode = 3;

    TextView ToolbarTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentcondition_activity);


        ToolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        ToolbarTxt.setText("나의 재능 현황");

        mContext = getApplicationContext();

        GiveTalent = SaveSharedPreference.getGiveTalentData(mContext);
        TakeTalent = SaveSharedPreference.getTakeTalentData(mContext);

        Talent_Give_Condition = (TextView) findViewById(R.id.TalentCondition_Give_Condition);
        TalentCondition_Give_Keyword1 = (TextView) findViewById(R.id.TalentCondition_Give_Keyword1);
        TalentCondition_Give_Keyword2 = (TextView) findViewById(R.id.TalentCondition_Give_Keyword2);
        TalentCondition_Give_Keyword3 = (TextView) findViewById(R.id.TalentCondition_Give_Keyword3);
        TalentCondition_Give_Text2 = (TextView) findViewById(R.id.TalentCondition_Give_TextView2);
        TalentCondition_Give_btn01 = (Button) findViewById(R.id.TalentCondition_Give_Button1);
        TalentCondition_Give_btn02 = (Button) findViewById(R.id.TalentCondition_Give_Button2);
        TalentCondition_Give_btn03 = (Button) findViewById(R.id.TalentCondition_Give_Button3);
        TalentCondition_Give_KeywordBox = (LinearLayout) findViewById(R.id.TalentCondition_Give_KeywordBox);

        Talent_Take_Condition = (TextView) findViewById(R.id.TalentCondition_Take_Condition);
        TalentCondition_Take_Keyword1 = (TextView) findViewById(R.id.TalentCondition_Take_Keyword1);
        TalentCondition_Take_Keyword2 = (TextView) findViewById(R.id.TalentCondition_Take_Keyword2);
        TalentCondition_Take_Keyword3 = (TextView) findViewById(R.id.TalentCondition_Take_Keyword3);
        TalentCondition_Take_Text2 = (TextView) findViewById(R.id.TalentCondition_Take_TextView2);
        TalentCondition_Take_btn01 = (Button) findViewById(R.id.TalentCondition_Take_Button1);
        TalentCondition_Take_btn02 = (Button) findViewById(R.id.TalentCondition_Take_Button2);
        TalentCondition_Take_btn03 = (Button) findViewById(R.id.TalentCondition_Take_Button3);
        TalentCondition_Take_KeywordBox = (LinearLayout) findViewById(R.id.TalentCondition_Take_KeywordBox);

        TalentCondition_Give_btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InterestingList_Activity.class);
                intent.putExtra("TalentFlag", "Give");
                startActivity(intent);
            }
        });

        TalentCondition_Take_btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, InterestingList_Activity.class);
                intent.putExtra("TalentFlag", "Take");

                startActivity(intent);
            }
        });

        TalentCondition_Give = (GiveTalent == null)?false : true;
        TalentCondition_Take = (TakeTalent == null)?false : true;

        if(TalentCondition_Give)
            TalentCondition_Give_Keyword = GiveTalent.getKeywordArray();

        if(TalentCondition_Take)
            TalentCondition_Take_Keyword = TakeTalent.getKeywordArray();

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


        TalentCondition_ShowGive = (Button) findViewById(R.id.TalentCondition_ShowGive);
        TalentCondition_ShowTake = (Button) findViewById(R.id.TalentCondition_ShowTake);
        Talent_Give_ConditionBox = (LinearLayout) findViewById(R.id.Talent_Give_ConditionBox);
        Talent_Take_ConditionBox = (LinearLayout) findViewById(R.id.Talent_Take_ConditionBox);

        TalentCondition_ShowGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Talent_Give_ConditionBox.setVisibility(View.VISIBLE);
                Talent_Take_ConditionBox.setVisibility(View.GONE);
                TalentCondition_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                TalentCondition_ShowGive.setTextColor(getResources().getColor(R.color.textColor));
                TalentCondition_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                TalentCondition_ShowTake.setTextColor(Color.parseColor("#d2d2d2"));
            }
        });

        TalentCondition_ShowTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Talent_Take_ConditionBox.setVisibility(View.VISIBLE);
                Talent_Give_ConditionBox.setVisibility(View.GONE);
                TalentCondition_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                TalentCondition_ShowTake.setTextColor(getResources().getColor(R.color.textColor));
                TalentCondition_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                TalentCondition_ShowGive.setTextColor(Color.parseColor("#d2d2d2"));
            }
        });



        if (TalentCondition_Give == false) {
            TalentCondition_Give_KeywordBox.setVisibility(View.GONE);
            TalentCondition_Give_Text2.setText("재능드림이 등록되지 않았습니다.\n\n재능드림을 등록하여 회원님의 재능을 공유해주세요!\"");
            TalentCondition_Give_btn01.setVisibility(View.GONE);
            TalentCondition_Give_btn02.setVisibility(View.GONE);
            TalentCondition_Give_btn03.setVisibility(View.VISIBLE);
            Talent_Give_Condition.setText("미등록");
            TalentCondition_Give_btn03.setText("재능드림 등록하기");
            TalentCondition_Give_btn03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, TalentResister_Activity.class);
                    startActivity(i);
                    finish();
                }
            });
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
                TalentCondition_Give_Text2.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!\"");
                TalentCondition_Give_btn01.setText("관심목록 확인");
                TalentCondition_Give_btn02.setText("T.Sharing");
                Talent_Give_Condition.setText("대기 중...");


            } else if (GiveTalentConditionCode == 2) {
                TalentCondition_Give_Text2.setText("다음 단계를 진행해주세요!\"");
                TalentCondition_Give_btn01.setText("완료 하기");
                TalentCondition_Give_btn02.setText("취소 하기");
                Talent_Give_Condition.setText("진행 중...");
            } else if (GiveTalentConditionCode == 3) {
                TalentCondition_Give_Text2.setText("재능 재등록 또는 재능 수정하기를 진행해주세요!\"");
                TalentCondition_Give_btn01.setText("재능드림 재등록");
                TalentCondition_Give_btn02.setText("재능드림 수정하기");
                TalentCondition_Give_btn02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(mContext, TalentResister_Activity.class);
                        startActivity(i);
                        finish();
                    }
                });
                Talent_Give_Condition.setText("완료");
            }
        }

        if (TalentCondition_Take == false) {
            TalentCondition_Take_KeywordBox.setVisibility(View.GONE);
            TalentCondition_Take_Text2.setText("관심재능이 등록되지 않았습니다.\n\n관심재능을 등록하여 회원님의 재능을 공유해주세요!\"");
            TalentCondition_Take_btn01.setVisibility(View.GONE);
            TalentCondition_Take_btn02.setVisibility(View.GONE);
            TalentCondition_Take_btn03.setVisibility(View.VISIBLE);
            Talent_Take_Condition.setText("미등록");
            TalentCondition_Take_btn03.setText("관심재능 등록하기");
            TalentCondition_Take_btn03.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, TalentResister_Activity.class);
                    i.putExtra("GiveFlag", false);
                    startActivity(i);
                    finish();
                }
            });
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
                TalentCondition_Take_Text2.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!\"");
                TalentCondition_Take_btn01.setText("관심목록 확인");
                TalentCondition_Take_btn02.setText("T.Sharing");
                Talent_Take_Condition.setText("대기 중...");


            } else if (TakeTalentConditionCode == 2) {
                TalentCondition_Take_Text2.setText("다음 단계를 진행해주세요!\"");
                TalentCondition_Take_btn01.setText("완료 하기");
                TalentCondition_Take_btn02.setText("취소 하기");
                Talent_Take_Condition.setText("진행 중...");
            } else if (TakeTalentConditionCode == 3) {
                TalentCondition_Take_Text2.setText("재능 재등록 또는 재능 수정하기를 진행해주세요!\"");
                TalentCondition_Take_btn01.setText("관심재능 재등록");
                TalentCondition_Take_btn02.setText("관심재능 수정하기");
                TalentCondition_Take_btn02.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(mContext, TalentResister_Activity.class);
                        i.putExtra("GiveFlag", false);
                        startActivity(i);
                        finish();
                    }
                });
                Talent_Take_Condition.setText("완료");
            }
        }
        
        
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

    public void slideMenuSystem(View v){
        Intent i = new Intent(mContext, System_Activity.class);
        startActivity(i);
    }

}
