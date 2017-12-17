package com.example.accepted.acceptedtalentplanet.System;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.Alarm.Alarm_Activity;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.FriendList.FriendList_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Password_Lost_Accept_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;


public class System_Activity extends AppCompatActivity {

    Context mContext;

    DrawerLayout slidingMenuDL;
    View drawerView;

    Switch System_MessageRing_Switch;
    Switch System_ConditionRing_Switch;
    Switch System_Answer_Switch;

    TextView System_PasswordChange;
    TextView System_NouseAccount;

    boolean System_UseorNot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_activity);

        mContext = getApplicationContext();


        Button System_SaveBtn = (Button) findViewById(R.id.System_SaveBtn);
        System_SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        slidingMenuDL = (DrawerLayout) findViewById(R.id.System_listboxDL);
        drawerView = (View) findViewById(R.id.System_container);
        ((TextView) findViewById(R.id.toolbarTxt)).setText("고객센터");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,System_Activity.this,slidingMenuDL,drawerView);
            }
        };
        DrawerLayout_ClickEvent(System_Activity.this,mClicklistener);


        System_MessageRing_Switch = (Switch) findViewById(R.id.System_MessageRing_Switch);
        System_MessageRing_Switch.setChecked(true);
        System_MessageRing_Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System_MessageRing_Switch.isChecked())
                {
                    System_MessageRing_Switch.setText("켜짐");
                }
                else
                    System_MessageRing_Switch.setText("꺼짐");
            }
        });
        System_ConditionRing_Switch = (Switch) findViewById(R.id.System_ConditionRing_Switch);
        System_ConditionRing_Switch.setChecked(true);
        System_ConditionRing_Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System_ConditionRing_Switch.isChecked())
                {
                    System_ConditionRing_Switch.setText("켜짐");
                }
                else
                    System_ConditionRing_Switch.setText("꺼짐");
            }
        });

        System_Answer_Switch = (Switch) findViewById(R.id.System_Answer_Switch);
        System_Answer_Switch.setChecked(true);
        System_Answer_Switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System_Answer_Switch.isChecked())
                {
                    System_Answer_Switch.setText("켜짐");
                }
                else
                    System_Answer_Switch.setText("꺼짐");
            }
        });


        //TODO:비밀번호 변경하는 로직 필요
        System_PasswordChange = (TextView) findViewById(R.id.System_PasswordChange);
        System_PasswordChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, Password_Lost_Accept_Activity.class);
                startActivity(i);
            }
        });

        //TODO:휴면 계정 등록 → 등록 된 재능 미등록 처리, 계정 활성화 → 재능 등록 다이얼로그 띄우기
        System_UseorNot = true;

        System_NouseAccount = (TextView) findViewById(R.id.System_NouseAccount);
        System_NouseAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(System_Activity.this);
                if (System_UseorNot) {
                    AlarmDeleteDialog.setMessage("회원님이 등록하신 재능은 미등록 처리 됩니다.\n휴면 계정으로 등록 하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TextView System_AcountActivateorNot = (TextView) findViewById(R.id.System_AcountActivateorNot);
                                    System_UseorNot = false;
                                    System_AcountActivateorNot.setText("휴면 계정");
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = AlarmDeleteDialog.create();
                    alertDialog.show();
                } else {
                    AlarmDeleteDialog.setMessage("계정을 활성화 하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TextView System_AcountActivateorNot = (TextView) findViewById(R.id.System_AcountActivateorNot);
                                    System_UseorNot = true;
                                    System_AcountActivateorNot.setText("계정 활성화 중");
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alertDialog = AlarmDeleteDialog.create();
                    alertDialog.show();
                }
            }
        });


    }


}


