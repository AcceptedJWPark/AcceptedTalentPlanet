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
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_Activity;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Popup_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static android.view.View.GONE;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

public class TalentCondition_Activity extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;

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

    LinearLayout TalentCondition_TextBoxLL;
    LinearLayout TalentCondition_BtnBoxLL;

    LinearLayout TalentCondition_ProfileShowLL;


    Boolean TalentCondition_Give_Registed = false;
    Boolean TalentCondition_Take_Registed = false;

    int GiveTalentConditionCode;
    int TakeTalentConditionCode;
    String flag;
    String giveTalentID, takeTalentID, targetGiveTalentID, targetTakeTalentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentcondition_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.toolbarTxt)).setText("나의 재능 현황");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,TalentCondition_Activity.this,slidingMenuDL,drawerView);
            }
        };
        DrawerLayout_ClickEvent(TalentCondition_Activity.this,mClicklistener);

        getMyTalent();

        TalentCondition_Condition = (TextView) findViewById(R.id.TalentCondition_Condition);
        TalentCondition_TakeorGiveTalent = (TextView)findViewById(R.id.TalentCondition_TakeorGiveTalent);
        TalentCondition_TextView = (TextView) findViewById(R.id.TalentCondition_TextView);

        TalentCondition_Button1 = (Button) findViewById(R.id.TalentCondition_Button1);
        TalentCondition_Button2 = (Button) findViewById(R.id.TalentCondition_Button2);
        TalentCondition_Button3 = (Button) findViewById(R.id.TalentCondition_Button3);
        TalentCondition_PictureLL = (LinearLayout) findViewById(R.id.TalentCondition_PictureLL);


        TalentCondition_Give_Registed(TalentCondition_Give_Registed,GiveTalentConditionCode);
        TalentCondition_Take_Registed(TalentCondition_Take_Registed,TakeTalentConditionCode);

        TalentCondition_ShowGive = (Button) findViewById(R.id.TalentCondition_ShowGive);
        TalentCondition_ShowGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentCondition_TakeorGiveTalent.setText("재능드림 : ");
                TalentCondition_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                TalentCondition_ShowGive.setPaintFlags(TalentCondition_ShowGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                TalentCondition_ShowGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                TalentCondition_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                TalentCondition_ShowTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                TalentCondition_ShowTake.setPaintFlags(TalentCondition_ShowTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);

                TalentCondition_Give_Registed(TalentCondition_Give_Registed,GiveTalentConditionCode);
            }
        });

        TalentCondition_ShowTake = (Button) findViewById(R.id.TalentCondition_ShowTake);
        TalentCondition_ShowTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentCondition_TakeorGiveTalent.setText("관심재능 : ");
                TalentCondition_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                TalentCondition_ShowTake.setPaintFlags(TalentCondition_ShowGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                TalentCondition_ShowTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                TalentCondition_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                TalentCondition_ShowGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                TalentCondition_ShowGive.setPaintFlags(TalentCondition_ShowTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
                TalentCondition_Take_Registed(TalentCondition_Take_Registed, TakeTalentConditionCode);
            }
        });


        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentCondition1_listboxDL);
        drawerView = (View) findViewById(R.id.TalentCondition_container1);

        Intent i = getIntent();
        flag = i.getStringExtra("TalentCondition_TalentFlag");
        if(flag == null) flag = "Give";

        else if(flag.equals("Give"))
        {
            TalentCondition_ShowGive.setFocusableInTouchMode(true);
            TalentCondition_ShowGive.performClick();
        }
        else if(flag.equals("Take"))
        {
            TalentCondition_ShowTake.setFocusableInTouchMode(true);
            TalentCondition_ShowTake.performClick();
        }


        TalentCondition_PictureLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TalentSharing_Popup_Activity.class);
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
        TalentCondition_TextBoxLL = (LinearLayout) findViewById(R.id.TalentCondition_TextBoxLL);
        TalentCondition_BtnBoxLL = (LinearLayout) findViewById(R.id.TalentCondition_BtnBoxLL);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) TalentCondition_TextBoxLL.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) TalentCondition_BtnBoxLL.getLayoutParams();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int marginvalue = Math.round(30*dm.density);

        flag = "Give";
        if (!check_GiveTalent) {
            TalentCondition_TextView.setText("재능드림을 등록하여 회원님의 재능을 공유해주세요!");
            TalentCondition_PictureLL.setVisibility(GONE);
            TalentCondition_Button1.setVisibility(GONE);
            TalentCondition_Button2.setVisibility(GONE);
            TalentCondition_Button3.setVisibility(View.VISIBLE);
            params1.bottomMargin = marginvalue;
            params2.bottomMargin = marginvalue;
            params2.topMargin = 0;
            TalentCondition_TextBoxLL.setLayoutParams(params1);
            TalentCondition_BtnBoxLL.setLayoutParams(params2);
            TalentCondition_Condition.setText("미등록");
            TalentCondition_Button3.setText("재능드림 등록하기");
            TalentCondition_Button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, TalentResister_Activity.class);
                    i.putExtra("GiveFlag",true);
                    startActivity(i);
                    finish();
                }
            });
        } else {
            switch (Code) {
                case 1: {
                    TalentCondition_TextView.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!");
                    TalentCondition_Button1.setText("관심목록 확인");
                    TalentCondition_Button2.setText("T.Sharing");
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = marginvalue;
                    params2.topMargin = 0;
                    TalentCondition_TextBoxLL.setLayoutParams(params1);
                    TalentCondition_BtnBoxLL.setLayoutParams(params2);
                    TalentCondition_Button3.setVisibility(GONE);
                    TalentCondition_PictureLL.setVisibility(GONE);
                    TalentCondition_Condition.setText("대기 중...");
                    TalentCondition_Button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, InterestingList_Activity.class);
                            intent.putExtra("TalentFlag", "Give");
                            startActivity(intent);
                        }
                    });
                    TalentCondition_Button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, TalentSharing_Activity.class);
                            intent.putExtra("TalentSharing_TalentFlag","Give");
                            startActivity(intent);
                        }
                    });
                    break;
                }
                case 2: {
                    TalentCondition_TextView.setText("아래 회원과 재능을 공유하였다면 완료하기 버튼을 눌러주세요!");
                    TalentCondition_Button1.setText("완료 하기");
                    TalentCondition_Button2.setText("진행 취소");
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    TalentCondition_PictureLL.setVisibility(View.VISIBLE);
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = 0;
                    params2.topMargin = marginvalue;
                    TalentCondition_TextBoxLL.setLayoutParams(params1);
                    TalentCondition_Button3.setVisibility(GONE);
                    TalentCondition_Condition.setText("진행 중...");
                    final AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(TalentCondition_Activity.this);
                    TalentCondition_Button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
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
                    TalentCondition_Button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlarmDeleteDialog.setMessage("진행 취소 하시겠습니까?")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            cancelSharingTalent();
                                            dialog.cancel();
                                            Intent i = new Intent(TalentCondition_Activity.this, TalentCondition_Activity.class);
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
                    TalentCondition_TextView.setText("재능 재등록을 진행해야 회원님의 재능이 활성화 됩니다.");
                    TalentCondition_Button1.setText("재능드림 재등록");
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = marginvalue;
                    params2.topMargin = 0;
                    TalentCondition_TextBoxLL.setLayoutParams(params1);
                    TalentCondition_BtnBoxLL.setLayoutParams(params2);
                    final AlertDialog.Builder AlarmReregist = new AlertDialog.Builder(TalentCondition_Activity.this);
                    TalentCondition_Button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlarmReregist.setMessage("재등록 하시겠습니까?")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            reRegistTalent();
                                            dialog.cancel();
                                            Intent i = new Intent(TalentCondition_Activity.this, TalentCondition_Activity.class);
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
                    TalentCondition_Button2.setText("재능드림 수정하기");
                    TalentCondition_Button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(mContext, TalentResister_Activity.class);
                            startActivity(i);
                        }
                    });
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    TalentCondition_PictureLL.setVisibility(View.GONE);
                    TalentCondition_Button3.setVisibility(GONE);
                    TalentCondition_Condition.setText("완료");
                    break;
                }

            }
        }
    }


    public void TalentCondition_Take_Registed(boolean check_TakeTalent, int Code) {

        TalentCondition_TextBoxLL = (LinearLayout) findViewById(R.id.TalentCondition_TextBoxLL);
        TalentCondition_BtnBoxLL = (LinearLayout) findViewById(R.id.TalentCondition_BtnBoxLL);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) TalentCondition_TextBoxLL.getLayoutParams();
        LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) TalentCondition_BtnBoxLL.getLayoutParams();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        int marginvalue = Math.round(30*dm.density);

        flag = "Take";
        if (!check_TakeTalent) {
            TalentCondition_TextView.setText("관심재능을 등록하여 회원님의 재능을 공유해주세요!");
            TalentCondition_PictureLL.setVisibility(GONE);
            TalentCondition_Button1.setVisibility(GONE);
            TalentCondition_Button2.setVisibility(GONE);
            TalentCondition_Button3.setVisibility(View.VISIBLE);
            params1.bottomMargin = marginvalue;
            params2.bottomMargin = marginvalue;
            params2.topMargin = 0;
            TalentCondition_TextBoxLL.setLayoutParams(params1);
            TalentCondition_BtnBoxLL.setLayoutParams(params2);
            TalentCondition_Condition.setText("미등록");
            TalentCondition_Button3.setText("관심재능 등록하기");
            TalentCondition_Button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, TalentResister_Activity.class);
                    i.putExtra("GiveFlag",false);
                    startActivity(i);
                    finish();
                }
            });
        } else {
            switch (Code) {
                case 1: {
                    TalentCondition_TextView.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!");
                    TalentCondition_Button1.setText("관심목록 확인");
                    TalentCondition_Button2.setText("T.Sharing");
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    TalentCondition_Button3.setVisibility(GONE);
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = marginvalue;
                    params2.topMargin = 0;
                    TalentCondition_TextBoxLL.setLayoutParams(params1);
                    TalentCondition_BtnBoxLL.setLayoutParams(params2);
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
                            Intent intent = new Intent(mContext, TalentSharing_Activity.class);
                            intent.putExtra("TalentSharing_TalentFlag","Take");
                            startActivity(intent);
                        }
                    });
                    break;
                }
                case 2: {
                    TalentCondition_TextView.setText("아래 회원과 재능을 공유하였다면 완료하기 버튼을 눌러주세요!");
                    TalentCondition_Button1.setText("완료 하기");
                    TalentCondition_Button2.setText("진행 취소");
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    TalentCondition_PictureLL.setVisibility(View.VISIBLE);
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = 0;
                    params2.topMargin = marginvalue;
                    TalentCondition_TextBoxLL.setLayoutParams(params1);
                    TalentCondition_Button3.setVisibility(GONE);
                    TalentCondition_Condition.setText("진행 중...");
                    final AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(TalentCondition_Activity.this);
                    TalentCondition_Button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlarmDeleteDialog.setMessage("재능공유 완료 시 포인트 공유가 이루어집니다.")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            completeSharingTalent();
                                            dialog.cancel();
                                            Intent i = new Intent(TalentCondition_Activity.this, TalentCondition_Activity.class);
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
                    TalentCondition_Button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlarmDeleteDialog.setMessage("진행 취소 하시겠습니까?")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            cancelSharingTalent();
                                            dialog.cancel();
                                            Intent i = new Intent(TalentCondition_Activity.this, TalentCondition_Activity.class);
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
                    TalentCondition_TextView.setText("재능 재등록을 진행해야 회원님의 재능이 활성화 됩니다.");
                    TalentCondition_Button1.setText("관심재능 재등록");
                    TalentCondition_Button2.setText("관심재능 수정하기");
                    params1.bottomMargin = marginvalue;
                    params2.bottomMargin = marginvalue;
                    params2.topMargin = 0;
                    TalentCondition_TextBoxLL.setLayoutParams(params1);
                    TalentCondition_BtnBoxLL.setLayoutParams(params2);
                    final AlertDialog.Builder AlarmReregist = new AlertDialog.Builder(TalentCondition_Activity.this);
                    TalentCondition_Button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlarmReregist.setMessage("재등록 하시겠습니까?")
                                    .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            reRegistTalent();
                                            dialog.cancel();
                                            Intent i = new Intent(TalentCondition_Activity.this, TalentCondition_Activity.class);
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
                    TalentCondition_Button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(mContext, TalentResister_Activity.class);
                            i.putExtra("TalentFlag", false);
                            startActivity(i);
                        }
                    });
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    TalentCondition_PictureLL.setVisibility(View.GONE);
                    TalentCondition_Button3.setVisibility(GONE);
                    TalentCondition_Condition.setText("완료");
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
                            TalentCondition_Give_Registed = true;
                            String status = o.getString("STATUS_FLAG");
                            giveTalentID = o.getString("seq");
                            switch (status){
                                case "P":
                                    GiveTalentConditionCode = 1;
                                    break;
                                case "M":
                                    GiveTalentConditionCode = 2;
                                    targetGiveTalentID = o.getString("TARGET_TALENT_ID");
                                    break;
                                case "C":
                                    GiveTalentConditionCode = 3;
                                    targetGiveTalentID = o.getString("TARGET_TALENT_ID");
                                    break;
                            }
                        }else{
                            TalentCondition_Take_Registed = true;
                            String status = o.getString("STATUS_FLAG");
                            takeTalentID = o.getString("seq");
                            switch (status){
                                case "P":
                                    TakeTalentConditionCode = 1;
                                    break;
                                case "M":
                                    TakeTalentConditionCode = 2;
                                    targetTakeTalentID = o.getString("TARGET_TALENT_ID");
                                    break;
                                case "C":
                                    TakeTalentConditionCode = 3;
                                    targetTakeTalentID = o.getString("TARGET_TALENT_ID");
                                    break;
                            }
                        }
                    }

                    if(flag.equals("Give"))
                        TalentCondition_Give_Registed(TalentCondition_Give_Registed,GiveTalentConditionCode);
                    else
                        TalentCondition_Take_Registed(TalentCondition_Take_Registed, TakeTalentConditionCode);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        Log.d("res", res);

                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }
        }) {
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        Log.d("res", res);

                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                TalentCondition_ShowTake = (Button) findViewById(R.id.TalentCondition_ShowTake);
                params.put("talentID", ((String)TalentCondition_TakeorGiveTalent.getText()).equals("관심재능 : ") ? takeTalentID : giveTalentID);
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
                        boolean talentFlag = ((String)TalentCondition_TakeorGiveTalent.getText()).equals("관심재능 : ") ? true : false;
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
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        Log.d("res", res);

                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                TalentCondition_ShowTake = (Button) findViewById(R.id.TalentCondition_ShowTake);
                params.put("talentID", ((String)TalentCondition_TakeorGiveTalent.getText()).equals("관심재능 : ") ? takeTalentID : giveTalentID);
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

                        if(((String)TalentCondition_TakeorGiveTalent.getText()).equals("관심재능 : "))
                        {
                            MyTalent mt = SaveSharedPreference.getTakeTalentData(mContext);
                            mt.setTalentID(obj.getString("talentID"));
                            SaveSharedPreference.setTakeTalentData(mContext, mt);
                        }
                        else
                        {
                            MyTalent mt = SaveSharedPreference.getGiveTalentData(mContext);
                            mt.setTalentID(obj.getString("talentID"));
                            SaveSharedPreference.setGiveTalentData(mContext, mt);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        Log.d("res", res);

                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                TalentCondition_ShowTake = (Button) findViewById(R.id.TalentCondition_ShowTake);
                params.put("talentID", ((String)TalentCondition_TakeorGiveTalent.getText()).equals("관심재능 : ") ? takeTalentID : giveTalentID);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }
}
