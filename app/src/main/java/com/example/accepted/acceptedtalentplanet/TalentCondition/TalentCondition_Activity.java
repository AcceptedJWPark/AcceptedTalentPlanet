package com.example.accepted.acceptedtalentplanet.TalentCondition;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_ListAdapter;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_ListItem;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Popup_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

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

    int GiveTalentConditionCode;
    int TakeTalentConditionCode;

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
        TalentCondition_Take_Registed(TalentCondition_Take_Registed,TakeTalentConditionCode);

        TalentCondition_ShowGive = (Button) findViewById(R.id.TalentCondition_ShowGive);
        TalentCondition_ShowGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentCondition_TakeorGiveTalent.setText("재능드림 : ");
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
                TalentCondition_TakeorGiveTalent.setText("관심재능 : ");
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

        Intent i = getIntent();
        if (i.getStringExtra("TalentCondition_TalentFlag") == null)
        {
            return;
        }
        else if(i.getStringExtra("TalentCondition_TalentFlag").equals("Give"))
        {
            TalentCondition_ShowGive.setFocusableInTouchMode(true);
            TalentCondition_ShowGive.performClick();
        }
        else if(i.getStringExtra("TalentCondition_TalentFlag").equals("Take"))
        {
            TalentCondition_ShowTake.setFocusableInTouchMode(true);
            TalentCondition_ShowTake.performClick();
        }


        TalentCondition_PictureLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TalentSharing_Popup_Activity.class);
                startActivity(intent);
            }
        });

        getMyTalent();
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



    public void TalentCondition_Give_Registed(boolean check_GiveTalent, int Code) {
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
        } else {
            switch (Code) {
                case 1: {
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
                    TalentCondition_TextView.setText("재능을 공유하였다면 완료하기 버튼을 눌러주세요!");
                    TalentCondition_Button1.setText("완료 하기");
                    TalentCondition_Button2.setText("진행 취소");
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    TalentCondition_PictureLL.setVisibility(View.VISIBLE);
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
                                            Toast.makeText(mContext, "확인 클림 됨", Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(mContext, "취소 클림 됨", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(mContext, "확인 클림 됨", Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(mContext, "취소 클림 됨", Toast.LENGTH_SHORT).show();
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
                            Intent intent = new Intent(mContext, TalentSharing_Activity.class);
                            intent.putExtra("TalentSharing_TalentFlag","Take");
                            startActivity(intent);
                        }
                    });
                    break;
                }
                case 2: {
                    TalentCondition_TextView.setText("재능을 공유하였다면 완료하기 버튼을 눌러주세요!");
                    TalentCondition_Button1.setText("완료 하기");
                    TalentCondition_Button2.setText("진행 취소");
                    TalentCondition_Button1.setVisibility(View.VISIBLE);
                    TalentCondition_Button2.setVisibility(View.VISIBLE);
                    TalentCondition_PictureLL.setVisibility(View.VISIBLE);
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
                                            Toast.makeText(mContext, "확인 클림 됨", Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(mContext, "취소 클림 됨", Toast.LENGTH_SHORT).show();
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
                                            Toast.makeText(mContext, "확인 클림 됨", Toast.LENGTH_SHORT).show();
                                            dialog.cancel();
                                        }
                                    })
                                    .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(mContext, "취소 클림 됨", Toast.LENGTH_SHORT).show();
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

    public void getMyTalent() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentCondition/getMyTalent.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);
                        if(o.getString("TALENT_FLAG").equals("N")){
                            TalentCondition_Give_Registed = true;
                            String status = o.getString("STATUS_FLAG");
                            switch (status){
                                case "P":
                                    GiveTalentConditionCode = 1;
                                    break;
                                case "M":
                                    GiveTalentConditionCode = 2;
                                    break;
                                case "C":
                                    GiveTalentConditionCode = 3;
                                    break;
                            }
                        }else{
                            TalentCondition_Take_Registed = true;
                            String status = o.getString("STATUS_FLAG");
                            switch (status){
                                case "P":
                                    TakeTalentConditionCode = 1;
                                    break;
                                case "M":
                                    TakeTalentConditionCode = 2;
                                    break;
                                case "C":
                                    TakeTalentConditionCode = 3;
                                    break;
                            }
                        }
                    }
                    Log.d("LOG >>>>" , TalentCondition_Give_Registed + ", " + GiveTalentConditionCode + ", " + TalentCondition_Take_Registed + ", " + TakeTalentConditionCode);
                    TalentCondition_Give_Registed(TalentCondition_Give_Registed,GiveTalentConditionCode);

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

}
