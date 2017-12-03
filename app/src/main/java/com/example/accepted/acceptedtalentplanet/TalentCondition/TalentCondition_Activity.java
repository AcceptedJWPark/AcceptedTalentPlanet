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

import com.example.accepted.acceptedtalentplanet.Alarm.Alarm_Activity;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.FriendList.FriendList_Activity;
import com.example.accepted.acceptedtalentplanet.Home.Home_Activity;
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.System.System_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Popup_Activity;

import org.w3c.dom.Text;

import static android.view.View.GONE;

public class TalentCondition_Activity extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;
    ImageView ActionBar_AlarmView;

    Button TalentCondition_ShowGive;
    Button TalentCondition_ShowTake;

    Context mContext;

    TextView TalentCondition_Condition;
    TextView TalentCondition_TakeorGiveTalent;

    TextView TalentCondition_TextView;

    Button TalentCondition_Button1;
    Button TalentCondition_Button2;
    Button TalentCondition_Button3;

    LinearLayout TalentCondition_PictureLL;

    Boolean TalentCondition_Give_Registed = true;
    Boolean TalentCondition_Take_Registed = true;

    int GiveTalentConditionCode = 3;
    int TakeTalentConditionCode = 2;

    TextView ToolbarTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentcondition_activity);


        ToolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        ToolbarTxt.setText("나의 재능 현황");
        mContext = getApplicationContext();


        TalentCondition_Condition = (TextView) findViewById(R.id.TalentCondition_Condition);
        TalentCondition_TakeorGiveTalent = (TextView)findViewById(R.id.TalentCondition_TakeorGiveTalent);
        TalentCondition_TextView = (TextView) findViewById(R.id.TalentCondition_TextView);

        TalentCondition_Button1 = (Button) findViewById(R.id.TalentCondition_Button1);
        TalentCondition_Button2 = (Button) findViewById(R.id.TalentCondition_Button2);
        TalentCondition_Button3 = (Button) findViewById(R.id.TalentCondition_Button3);
        TalentCondition_PictureLL = (LinearLayout) findViewById(R.id.TalentCondition_PictureLL);

        TalentCondition_Give_Registed(TalentCondition_Give_Registed,GiveTalentConditionCode);

        TalentCondition_ShowGive = (Button) findViewById(R.id.TalentCondition_ShowGive);
        TalentCondition_ShowGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentCondition_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                TalentCondition_ShowGive.setTextColor(getResources().getColor(R.color.textColor));
                TalentCondition_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                TalentCondition_ShowTake.setTextColor(Color.parseColor("#d2d2d2"));
                TalentCondition_Give_Registed(TalentCondition_Give_Registed,GiveTalentConditionCode);
            }
        });

        TalentCondition_ShowTake = (Button) findViewById(R.id.TalentCondition_ShowTake);
        TalentCondition_ShowTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentCondition_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                TalentCondition_ShowTake.setTextColor(getResources().getColor(R.color.textColor));
                TalentCondition_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                TalentCondition_ShowGive.setTextColor(Color.parseColor("#d2d2d2"));
                    TalentCondition_Take_Registed(TalentCondition_Take_Registed, TakeTalentConditionCode);
            }
        });

        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentCondition1_listboxDL);
        drawerView = (View) findViewById(R.id.TalentCondition_container1);
        imgDLOpenMenu = (ImageView) findViewById(R.id.ActionBar_Listview);
        DrawerCloseImg = (ImageView) findViewById(R.id.DrawerCloseImg);
        ActionBar_AlarmView = (ImageView) findViewById(R.id.ActionBar_AlarmView);
        ActionBar_AlarmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Alarm_Activity.class);
                startActivity(intent);
            }
        });

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


        TalentCondition_PictureLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TalentSharing_Popup_Activity.class);
                startActivity(intent);
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

    public void slideMenuSystem(View v){
        Intent i = new Intent(mContext, System_Activity.class);
        startActivity(i);
    }

    public void slideMenuTalentSharingList(View v){
        Intent i = new Intent(mContext, SharingList_Activity.class);
        startActivity(i);
    }

    public void slideFriendList(View v){
        Intent i = new Intent(mContext, FriendList_Activity.class);
        startActivity(i);
    }



    public void TalentCondition_Give_Registed(boolean check_GiveTalent, int Code)
    {
        if (!check_GiveTalent) {
            TalentCondition_TextView.setText("재능드림을 등록하여 회원님의 재능을 공유해주세요!");
            TalentCondition_PictureLL.setVisibility(GONE);
            TalentCondition_Button1.setVisibility(GONE);
            TalentCondition_Button2.setVisibility(GONE);
            TalentCondition_Button3.setVisibility(View.VISIBLE);
            TalentCondition_Condition.setText("미등록");
            TalentCondition_Button3.setText("재능드림 등록하기");
            TalentCondition_Button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, TalentResister_Activity.class);
                    startActivity(i);
                    finish();
                }
            });
        }
        else
        {
        switch (Code)
        {
            case 1: {
                TalentCondition_TakeorGiveTalent.setText("재능드림 : ");
                TalentCondition_TextView.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!");
                TalentCondition_Button1.setText("관심목록 확인");
                TalentCondition_Button2.setText("T.Sharing");
                TalentCondition_Button1.setVisibility(View.VISIBLE);
                TalentCondition_Button2.setVisibility(View.VISIBLE);
                TalentCondition_Button3.setVisibility(GONE);
                TalentCondition_PictureLL.setVisibility(GONE);
                TalentCondition_Condition.setText("대기 중...");
                TalentCondition_Button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,InterestingList_Activity.class);
                        intent.putExtra("TalentFlag","Give");
                        startActivity(intent);
                    }
                });
                TalentCondition_Button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //TODO:TalentSharing에서 재능드림 버튼 포커스 이동
                        Intent intent = new Intent(mContext,TalentSharing_Activity.class);
                        startActivity(intent);
                    }
                });
                break;
            }
            case 2: {
                TalentCondition_TakeorGiveTalent.setText("재능드림 : ");
                TalentCondition_TextView.setText("재능을 공유하였다면 완료하기 버튼을 눌러주세요!");
                TalentCondition_Button1.setText("완료 하기");
                TalentCondition_Button2.setText("진행 취소");
                TalentCondition_Button1.setVisibility(View.VISIBLE);
                TalentCondition_Button2.setVisibility(View.VISIBLE);
                TalentCondition_PictureLL.setVisibility(View.VISIBLE);
                TalentCondition_Button3.setVisibility(GONE);
                TalentCondition_Condition.setText("진행 중...");
                break;
            }
            case 3: {
                TalentCondition_TakeorGiveTalent.setText("재능드림 : ");
                TalentCondition_TextView.setText("재능 재등록을 진행해야 회원님의 재능이 활성화 됩니다.");
                TalentCondition_Button1.setText("재능드림 재등록");
                TalentCondition_Button2.setText("재능드림 수정하기");
                TalentCondition_Button1.setVisibility(View.VISIBLE);
                TalentCondition_Button2.setVisibility(View.VISIBLE);
                TalentCondition_PictureLL.setVisibility(View.VISIBLE);
                TalentCondition_Button3.setVisibility(GONE);
                TalentCondition_Condition.setText("완료");
                break;
            }

        }
    }
    }

    public void TalentCondition_Take_Registed(boolean check_TakeTalent, int Code) {
        if (!check_TakeTalent) {
            TalentCondition_TextView.setText("관심재능을 등록하여 회원님의 재능을 공유해주세요!");
            TalentCondition_PictureLL.setVisibility(GONE);
            TalentCondition_Button1.setVisibility(GONE);
            TalentCondition_Button2.setVisibility(GONE);
            TalentCondition_Button3.setVisibility(View.VISIBLE);
            TalentCondition_Condition.setText("미등록");
            TalentCondition_Button3.setText("관심재능 등록하기");
            TalentCondition_Button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, TalentResister_Activity.class);
                    startActivity(i);
                    finish();
                }
            });
        } else {
            switch (Code) {
                case 1: {
                    TalentCondition_TakeorGiveTalent.setText("관심재능 : ");
                    TalentCondition_TextView.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!");
                    TalentCondition_Button1.setText("관심목록 확인");
                    TalentCondition_Button2.setText("T.Sharing");
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    TalentCondition_Button3.setVisibility(GONE);
                    TalentCondition_PictureLL.setVisibility(GONE);
                    TalentCondition_Condition.setText("대기 중...");
                    TalentCondition_Button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, InterestingList_Activity.class);
                            intent.putExtra("TalentFlag", "Take");
                            startActivity(intent);
                        }
                    });
                    TalentCondition_Button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //TODO:TalentSharing에서 관심재능 버튼 포커스 이동
                            Intent intent = new Intent(mContext, TalentSharing_Activity.class);
                            startActivity(intent);
                        }
                    });
                    break;
                }
                case 2: {
                    TalentCondition_TakeorGiveTalent.setText("관심재능 : ");
                    TalentCondition_TextView.setText("재능을 공유하였다면 완료하기 버튼을 눌러주세요!");
                    TalentCondition_Button1.setText("완료 하기");
                    TalentCondition_Button2.setText("진행 취소");
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    TalentCondition_PictureLL.setVisibility(View.VISIBLE);
                    TalentCondition_Button3.setVisibility(GONE);
                    TalentCondition_Condition.setText("진행 중...");
                    break;
                }
                case 3: {
                    TalentCondition_TakeorGiveTalent.setText("관심재능 : ");
                    TalentCondition_TextView.setText("재능 재등록을 진행해야 회원님의 재능이 활성화 됩니다.");
                    TalentCondition_Button1.setText("관심재능 재등록");
                    TalentCondition_Button2.setText("관심재능 수정하기");
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    TalentCondition_PictureLL.setVisibility(View.VISIBLE);
                    TalentCondition_Button3.setVisibility(GONE);
                    TalentCondition_Condition.setText("완료");
                    break;
                }

            }
        }
    }

}
