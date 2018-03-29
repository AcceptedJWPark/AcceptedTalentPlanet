package com.example.accepted.acceptedtalentplanet.System;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.MyFirebaseMessagingService;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.checkDuplicatedLogin;


public class MainActivity extends AppCompatActivity {

    private Context mContext;

    private DrawerLayout drawerLayout;
    private View view_DrawerLayout;

    private Switch swc_MessageRing;
    private Switch swc_ConditionRing;
    private Switch swc_AnswerRing;

    private TextView tv_Password;
    private LinearLayout ll_NoActivity;
    private Button btn_Save;

    private boolean isActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_activity);

        mContext = getApplicationContext();


        btn_Save = (Button) findViewById(R.id.btn_Save_System);
        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSharedPreference.setPrefPushGrant(mContext, swc_MessageRing.isChecked(), swc_ConditionRing.isChecked(), swc_AnswerRing.isChecked());
                finish();
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_System);
        view_DrawerLayout = (View) findViewById(R.id.view_DrawerLayout_System);
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("설정");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));
        if(SaveSharedPreference.getMyPicture() != null)
            ((ImageView) findViewById(R.id.DrawerPicture)).setImageBitmap(SaveSharedPreference.getMyPicture());

        if(MyFirebaseMessagingService.isNewMessageArrive){
            findViewById(R.id.Icon_NewMessage).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.Icon_NewMessage).setVisibility(View.GONE);
        }

        final View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,MainActivity.this, drawerLayout, view_DrawerLayout);
            }
        };
        DrawerLayout_ClickEvent(MainActivity.this,mClicklistener);


        swc_MessageRing = (Switch) findViewById(R.id.swc_MessageRing_System);
        swc_MessageRing.setChecked(SaveSharedPreference.getMessagePushGrant(mContext));
        swc_MessageRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swc_MessageRing.isChecked())
                {
                    swc_MessageRing.setText("켜짐");
                }
                else {
                    swc_MessageRing.setText("꺼짐");
                }
            }
        });
        swc_ConditionRing = (Switch) findViewById(R.id.swc_ConditionRing_System);
        swc_ConditionRing.setChecked(SaveSharedPreference.getConditionPushGrant(mContext));
        swc_ConditionRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swc_ConditionRing.isChecked())
                {
                    swc_ConditionRing.setText("켜짐");
                }
                else {
                    swc_ConditionRing.setText("꺼짐");
                }
            }
        });

        swc_AnswerRing = (Switch) findViewById(R.id.swc_AnswerRing_System);
        swc_AnswerRing.setChecked(SaveSharedPreference.getAnswerPushGrant(mContext));
        swc_AnswerRing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swc_AnswerRing.isChecked())
                {
                    swc_AnswerRing.setText("켜짐");
                }
                else {
                    swc_AnswerRing.setText("꺼짐");
                }
            }
        });


        //TODO:비밀번호 변경하는 로직 필요
        tv_Password = (TextView) findViewById(R.id.tv_Password_System);
        tv_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.LoadingLogin.PasswordLost.Confirm.MainActivity.class);
                startActivity(i);
            }
        });

        //TODO:휴면 계정 등록 → 등록 된 재능 미등록 처리, 계정 활성화 → 재능 등록 다이얼로그 띄우기
        //TODO:휴면 계정일 경우 로그인 시 계정활성화 쪽으로 화면 이동


//        isActivity = true;
//
//        ll_NoActivity = (LinearLayout) findViewById(R.id.ll_NoActivity_System);
//        final TextView tv_NoActivity = (TextView) findViewById(R.id.tv_NoActivity_System);
//        ll_NoActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(MainActivity.this);
//                float textSize = getResources().getDimension(R.dimen.DialogTxtSize);
//                if (isActivity) {
//                    AlarmDeleteDialog.setMessage("회원님이 등록하신 재능은 미등록 처리 됩니다.\n\n휴면 계정으로 등록 하시겠습니까?")
//                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    isActivity = false;
//                                    tv_NoActivity.setText("휴면 계정");
//                                    dialog.cancel();
//                                }
//                            })
//                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                }
//                            });
//                    AlertDialog alertDialog = AlarmDeleteDialog.create();
//                    alertDialog.show();
//                    TextView msgView = (TextView) alertDialog.findViewById(android.R.id.message);
//                    msgView.setTextSize(textSize);
//                } else {
//                    AlarmDeleteDialog.setMessage("계정을 활성화 하시겠습니까?")
//                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    isActivity = true;
//                                    tv_NoActivity.setText("계정 활성화 중");
//                                    dialog.cancel();
//                                }
//                            })
//                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                }
//                            });
//                    AlertDialog alertDialog = AlarmDeleteDialog.create();
//                    alertDialog.show();
//                    TextView msgView = (TextView) alertDialog.findViewById(android.R.id.message);
//                    msgView.setTextSize(textSize);
//                }
//            }
//        });

    }

    @Override
    public void onResume(){
        super.onResume();
        checkDuplicatedLogin(mContext, this);
        drawerLayout.closeDrawers();
        if(MyFirebaseMessagingService.isNewMessageArrive){
            findViewById(R.id.Icon_NewMessage).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.Icon_NewMessage).setVisibility(View.GONE);
        }
    }



}


