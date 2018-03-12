package com.example.accepted.acceptedtalentplanet.TalentCondition;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.GONE;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View view_drawerView;

    private Button btn_SelectGive;
    private Button btn_SelectTake;

    private Context mContext;

    private TextView tv_Condition;
    private TextView tv_TalentType;
    private TextView tv_Txt;

    private Button btn_Left;
    private Button btn_Right;
    private Button btn_TalentRegist;

    private LinearLayout ll_PictureContainer;

    private LinearLayout ll_TextContainer;
    private LinearLayout ll_BtnContainer;

    private Boolean isGiveRegisted = false;
    private Boolean isTakeRegisted = false;

    private int giveTalentCode;
    private int takeTalentCode;

    private String flag, giveStatus, takeStatus;
    private String giveTalentID, takeTalentID, targetGiveTalentID, targetTakeTalentID;

    private boolean givePartnerCompFlag = false;
    private boolean takePartnerCompFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentcondition_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("나의 재능 현황");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,MainActivity.this, drawerLayout, view_drawerView);
            }
        };
        DrawerLayout_ClickEvent(MainActivity.this,mClicklistener);

        getMyTalent();

        tv_Condition = (TextView) findViewById(R.id.tv_Condition_TalentCondition);
        tv_TalentType = (TextView)findViewById(R.id.tv_TalentType_TalentCondition);
        tv_Txt = (TextView) findViewById(R.id.tv_Txt_TalentCondition);

        btn_Left = (Button) findViewById(R.id.btn_Left_TalentCondition);
        btn_Right = (Button) findViewById(R.id.btn_Right_TalentCondition);
        btn_TalentRegist = (Button) findViewById(R.id.btn_TalentRegist_TalentCondition);
        ll_PictureContainer = (LinearLayout) findViewById(R.id.ll_PictureContainer_TalentCondition);


        TalentCondition_Give_Registed(isGiveRegisted, giveTalentCode);
        TalentCondition_Take_Registed(isTakeRegisted, takeTalentCode);

        btn_SelectGive = (Button) findViewById(R.id.btn_SelectGive_TalentCondition);
        btn_SelectGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_TalentType.setText("재능드림 : ");
                btn_SelectGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                btn_SelectGive.setPaintFlags(btn_SelectGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                btn_SelectGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                btn_SelectTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                btn_SelectTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                btn_SelectTake.setPaintFlags(btn_SelectTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);

                TalentCondition_Give_Registed(isGiveRegisted, giveTalentCode);
            }
        });

        btn_SelectTake = (Button) findViewById(R.id.btn_SelectTake_TalentCondition);
        btn_SelectTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_TalentType.setText("관심재능 : ");
                btn_SelectTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                btn_SelectTake.setPaintFlags(btn_SelectGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                btn_SelectTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                btn_SelectGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                btn_SelectGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                btn_SelectGive.setPaintFlags(btn_SelectTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
                TalentCondition_Take_Registed(isTakeRegisted, takeTalentCode);
            }
        });


        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_TalentCondition);
        view_drawerView = (View) findViewById(R.id.view_drawerView_TalentCondition);

        Intent i = getIntent();
        flag = i.getStringExtra("TalentCondition_TalentFlag");
        if(flag == null) flag = "Give";

        else if(flag.equals("Give"))
        {
            btn_SelectGive.setFocusableInTouchMode(true);
            btn_SelectGive.performClick();
        }
        else if(flag.equals("Take"))
        {
            btn_SelectTake.setFocusableInTouchMode(true);
            btn_SelectTake.performClick();
        }


        ll_PictureContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentSharing.Popup.MainActivity.class);
                Log.d("FLAG = ", flag + ", " +targetGiveTalentID + targetTakeTalentID);
                if(flag.equals("Give")){
                    intent.putExtra("TalentID", targetGiveTalentID);
                    intent.putExtra("TalentFlag", "Give");
                }else{
                    intent.putExtra("TalentID", targetTakeTalentID);
                    intent.putExtra("TalentFlag", "Take");
                }
                startActivity(intent);
            }
        });



    }


    public void TalentCondition_Give_Registed(boolean check_GiveTalent, int Code) {
        ll_TextContainer = (LinearLayout) findViewById(R.id.ll_TextContainer_TalentCondition);
        ll_BtnContainer = (LinearLayout) findViewById(R.id.ll_BtnContainer_TalentCondition);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) ll_TextContainer.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) ll_BtnContainer.getLayoutParams();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int marginvalue = Math.round(30*dm.density);

        flag = "Give";
        if (!check_GiveTalent) {
            tv_Txt.setText("재능드림을 등록하여 회원님의 재능을 공유해주세요!");
            ll_PictureContainer.setVisibility(GONE);
            btn_Left.setVisibility(GONE);
            btn_Right.setVisibility(GONE);
            btn_TalentRegist.setVisibility(View.VISIBLE);
            params1.bottomMargin = marginvalue;
            params2.bottomMargin = marginvalue;
            params2.topMargin = 0;
            ll_TextContainer.setLayoutParams(params1);
            ll_BtnContainer.setLayoutParams(params2);
            tv_Condition.setText("미등록");
            btn_TalentRegist.setText("재능드림 등록하기");
            btn_TalentRegist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                    i.putExtra("GiveFlag",true);
                    startActivity(i);
                    finish();
                }
            });
        } else {
            switch (Code) {
                case 1: {
                    tv_Txt.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!");
                    btn_Left.setText("관심목록 확인");
                    btn_Right.setText("T.Sharing");
                    btn_Left.setVisibility(View.VISIBLE);
                    btn_Right.setVisibility(View.VISIBLE);
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = marginvalue;
                    params2.topMargin = 0;
                    ll_TextContainer.setLayoutParams(params1);
                    ll_BtnContainer.setLayoutParams(params2);
                    btn_TalentRegist.setVisibility(GONE);
                    ll_PictureContainer.setVisibility(GONE);
                    tv_Condition.setText("대기 중...");
                    btn_Left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, com.example.accepted.acceptedtalentplanet.InterestingList.MainActivity.class);
                            intent.putExtra("TalentFlag", "Give");
                            startActivity(intent);
                        }
                    });
                    btn_Right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentSharing.MainActivity.class);
                            intent.putExtra("TalentSharing_TalentFlag","Give");
                            startActivity(intent);
                        }
                    });
                    break;
                }
                case 2: {
                    tv_Txt.setText("아래 회원과 재능을 공유하였다면 완료하기 버튼을 눌러주세요!");
                    btn_Left.setText("완료 하기");
                    btn_Right.setText("진행 취소");
                    btn_Left.setVisibility(View.VISIBLE);
                    btn_Right.setVisibility(View.VISIBLE);
                    ll_PictureContainer.setVisibility(View.VISIBLE);
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = 0;
                    params2.topMargin = marginvalue;
                    ll_TextContainer.setLayoutParams(params1);
                    btn_TalentRegist.setVisibility(GONE);
                    tv_Condition.setText("진행 중...");
                    final AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(MainActivity.this);
                    btn_Left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(giveStatus.equals("C")){
                                Toast.makeText(mContext, "상대방이 완료를 누르지 않았습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            AlarmDeleteDialog.setMessage("재능공유 완료 시 포인트 공유가 이루어집니다.")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            completeSharingTalent();
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
                    });
                    btn_Right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(giveStatus.equals("C")){
                                Toast.makeText(mContext, "상대방이 완료를 누르지 않았습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            AlarmDeleteDialog.setMessage("진행 취소 하시겠습니까?")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            cancelSharingTalent();
                                            dialog.cancel();
                                            Intent i = new Intent(MainActivity.this, MainActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                            startActivity(i);
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
                    });
                    break;
                }


                case 3: {
                    tv_Txt.setText("재능 재등록을 진행해야 회원님의 재능이 활성화 됩니다.");
                    btn_Left.setText("재능드림 재등록");
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = marginvalue;
                    params2.topMargin = 0;
                    ll_TextContainer.setLayoutParams(params1);
                    ll_BtnContainer.setLayoutParams(params2);
                    final AlertDialog.Builder AlarmReregist = new AlertDialog.Builder(MainActivity.this);
                    btn_Left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlarmReregist.setMessage("재등록 하시겠습니까?")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            reRegistTalent();
                                            dialog.cancel();
                                            Intent i = new Intent(MainActivity.this, MainActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                            startActivity(i);
                                        }
                                    })
                                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alertDialog = AlarmReregist.create();
                            alertDialog.show();
                        }
                    });
                    btn_Right.setText("재능드림 수정하기");
                    btn_Right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                            startActivity(i);
                        }
                    });
                    btn_Left.setVisibility(View.VISIBLE);
                    btn_Right.setVisibility(View.VISIBLE);
                    ll_PictureContainer.setVisibility(View.GONE);
                    btn_TalentRegist.setVisibility(GONE);
                    tv_Condition.setText("완료");
                    break;
                }

            }
        }
    }


    public void TalentCondition_Take_Registed(boolean check_TakeTalent, int Code) {
        ll_TextContainer = (LinearLayout) findViewById(R.id.ll_TextContainer_TalentCondition);
        ll_BtnContainer = (LinearLayout) findViewById(R.id.ll_BtnContainer_TalentCondition);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) ll_TextContainer.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) ll_BtnContainer.getLayoutParams();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int marginvalue = Math.round(30*dm.density);

        flag = "Take";
        if (!check_TakeTalent) {
            tv_Txt.setText("관심재능을 등록하여 회원님의 재능을 공유해주세요!");
            ll_PictureContainer.setVisibility(GONE);
            btn_Left.setVisibility(GONE);
            btn_Right.setVisibility(GONE);
            btn_TalentRegist.setVisibility(View.VISIBLE);
            params1.bottomMargin = marginvalue;
            params2.bottomMargin = marginvalue;
            params2.topMargin = 0;
            ll_TextContainer.setLayoutParams(params1);
            ll_BtnContainer.setLayoutParams(params2);
            tv_Condition.setText("미등록");
            btn_TalentRegist.setText("관심재능 등록하기");
            btn_TalentRegist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                    i.putExtra("GiveFlag",false);
                    startActivity(i);
                    finish();
                }
            });
        } else {
            switch (Code) {
                case 1: {
                    tv_Txt.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!");
                    btn_Left.setText("관심목록 확인");
                    btn_Right.setText("T.Sharing");
                    btn_Left.setVisibility(View.VISIBLE);
                    btn_Right.setVisibility(View.VISIBLE);
                    btn_TalentRegist.setVisibility(GONE);
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = marginvalue;
                    params2.topMargin = 0;
                    ll_TextContainer.setLayoutParams(params1);
                    ll_BtnContainer.setLayoutParams(params2);
                    ll_PictureContainer.setVisibility(GONE);
                    tv_Condition.setText("대기 중...");
                    btn_Left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, com.example.accepted.acceptedtalentplanet.InterestingList.MainActivity.class);
                            intent.putExtra("TalentFlag", "Take");
                            startActivity(intent);
                        }
                    });
                    btn_Right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentSharing.MainActivity.class);
                            intent.putExtra("TalentSharing_TalentFlag","Take");
                            startActivity(intent);
                        }
                    });
                    break;
                }
                case 2: {
                    tv_Txt.setText("아래 회원과 재능을 공유하였다면 완료하기 버튼을 눌러주세요!");
                    btn_Left.setText("완료 하기");
                    btn_Right.setText("진행 취소");
                    btn_Left.setVisibility(View.VISIBLE);
                    btn_Right.setVisibility(View.VISIBLE);
                    ll_PictureContainer.setVisibility(View.VISIBLE);
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = 0;
                    params2.topMargin = marginvalue;
                    ll_TextContainer.setLayoutParams(params1);
                    btn_TalentRegist.setVisibility(GONE);
                    tv_Condition.setText("진행 중...");
                    final AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(MainActivity.this);
                    btn_Left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(!takePartnerCompFlag){
                                Toast.makeText(mContext, "상대방이 완료하기를 누르지 않았습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            AlarmDeleteDialog.setMessage("재능공유 완료 시 포인트 공유가 이루어집니다.")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            completeSharingTalent();
                                            dialog.cancel();
                                            Intent i = new Intent(MainActivity.this, MainActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                            startActivity(i);
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
                    });
                    btn_Right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(takePartnerCompFlag){
                                Toast.makeText(mContext, "상대방이 이미 완료를 눌러 취소 불가합니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            AlarmDeleteDialog.setMessage("진행 취소 하시겠습니까?")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            cancelSharingTalent();
                                            dialog.cancel();
                                            Intent i = new Intent(MainActivity.this, MainActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                            startActivity(i);
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
                    });
                    break;
                }
                case 3: {
                    tv_Txt.setText("재능 재등록을 진행해야 회원님의 재능이 활성화 됩니다.");
                    btn_Left.setText("관심재능 재등록");
                    btn_Right.setText("관심재능 수정하기");
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = marginvalue;
                    params2.topMargin = 0;
                    ll_TextContainer.setLayoutParams(params1);
                    ll_BtnContainer.setLayoutParams(params2);
                    final AlertDialog.Builder AlarmReregist = new AlertDialog.Builder(MainActivity.this);
                    btn_Left.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlarmReregist.setMessage("재등록 하시겠습니까?")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            reRegistTalent();
                                            dialog.cancel();
                                            Intent i = new Intent(MainActivity.this, MainActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                            startActivity(i);
                                        }
                                    })
                                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog alertDialog = AlarmReregist.create();
                            alertDialog.show();
                        }
                    });
                    btn_Right.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                            i.putExtra("TalentFlag", false);
                            startActivity(i);
                        }
                    });
                    btn_Left.setVisibility(View.VISIBLE);
                    btn_Right.setVisibility(View.VISIBLE);
                    ll_PictureContainer.setVisibility(View.GONE);
                    btn_TalentRegist.setVisibility(GONE);
                    tv_Condition.setText("완료");
                    break;
                }

            }
        }
    }

    public void getMyTalent() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentCondition/getMyTalent.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);

                        if(o.getString("TALENT_FLAG").equals("Y")){
                            isGiveRegisted = true;
                            giveStatus = o.getString("STATUS_FLAG");
                            giveTalentID = o.getString("seq");
                            givePartnerCompFlag = o.getString("TARGET_COMP_FLAG").equals("C");

                            switch (giveStatus){
                                case "P":
                                    giveTalentCode = 1;
                                    break;
                                case "M":
                                    giveTalentCode = 2;
                                    targetGiveTalentID = o.getString("TARGET_TALENT_ID");
                                    break;
                                case "C":
                                    if(givePartnerCompFlag)
                                        giveTalentCode = 3;
                                    else
                                        giveTalentCode = 2;
                                    targetGiveTalentID = o.getString("TARGET_TALENT_ID");
                                    break;
                            }
                        }else{
                            isTakeRegisted = true;
                            takeStatus = o.getString("STATUS_FLAG");
                            takeTalentID = o.getString("seq");
                            takePartnerCompFlag = o.getString("TARGET_COMP_FLAG").equals("C");

                            switch (takeStatus){
                                case "P":
                                    takeTalentCode = 1;
                                    break;
                                case "M":
                                    takeTalentCode = 2;
                                    targetTakeTalentID = o.getString("TARGET_TALENT_ID");
                                    break;
                                case "C":
                                    takeTalentCode = 3;
                                    targetTakeTalentID = o.getString("TARGET_TALENT_ID");
                                    break;
                            }
                        }
                    }

                    if(flag.equals("Give"))
                        TalentCondition_Give_Registed(isGiveRegisted, giveTalentCode);
                    else
                        TalentCondition_Take_Registed(isTakeRegisted, takeTalentCode);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

    public void cancelSharingTalent() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/cancelSharingTalent.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "취소가 완료되었습니다.", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(mContext, "이미 상대방이 완료를 눌러 취소할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("talentID", ((String) tv_TalentType.getText()).equals("관심재능 : ") ? takeTalentID : giveTalentID);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

    public void completeSharingTalent() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/completeSharingTalent.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "재능공유에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        int point = Integer.parseInt(obj.getString("point"));
                        boolean talentFlag = ((String) tv_TalentType.getText()).equals("관심재능 : ") ? true : false;
                        MyTalent mt = (talentFlag)?SaveSharedPreference.getGiveTalentData(mContext):SaveSharedPreference.getTakeTalentData(mContext);

                        mt.setStatus("C");
                        if(!talentFlag){
                            SaveSharedPreference.setGiveTalentData(mContext, mt);
                        }else{
                            SaveSharedPreference.setTakeTalentData(mContext, mt);
                        }
                        SaveSharedPreference.setPrefTalentPoint(mContext, point);
                    }else{

                        Toast.makeText(mContext, "재능공유에 실패하였습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("talentID", ((String) tv_TalentType.getText()).equals("관심재능 : ") ? takeTalentID : giveTalentID);
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

    public void reRegistTalent() {
        if(flag.equals("Take")){
            MyTalent mt = SaveSharedPreference.getTakeTalentData(mContext);
            int myTalentPoint = SaveSharedPreference.getTalentPoint(mContext);
            if(mt.getPoint() > myTalentPoint){
                Toast.makeText(mContext, "현재 사용가능한 포인트는 "+ myTalentPoint + "P 입니다.", Toast.LENGTH_SHORT);
                return;
            }
        }
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/reRegistTalent.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){

                        if(((String) tv_TalentType.getText()).equals("관심재능 : "))
                        {
                            MyTalent mt = SaveSharedPreference.getTakeTalentData(mContext);
                            mt.setTalentID(obj.getString("talentID"));
                            mt.setStatus("P");
                            SaveSharedPreference.setTakeTalentData(mContext, mt);
                        }
                        else
                        {
                            MyTalent mt = SaveSharedPreference.getGiveTalentData(mContext);
                            mt.setTalentID(obj.getString("talentID"));
                            mt.setStatus("P");
                            SaveSharedPreference.setGiveTalentData(mContext, mt);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("talentID", ((String) tv_TalentType.getText()).equals("관심재능 : ") ? takeTalentID : giveTalentID);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }
}