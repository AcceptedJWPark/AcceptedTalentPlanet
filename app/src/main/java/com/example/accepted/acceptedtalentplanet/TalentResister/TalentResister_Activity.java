package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
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
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_ListAdapter;
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_ListItem;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.System.System_Activity;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

public class TalentResister_Activity extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;


    MyTalent TalentGive;
    MyTalent TalentTake;

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
    TextView No_Talent_Txt;
    boolean TalentResister_Give;
    boolean TalentResister_Take;
    boolean TalentFlag = true;
    int G_Level;
    int T_Level;

    String TalentResister_Give_Keyword[];
    String TalentResister_Give_Location[];
    String TalentResister_Give_Level;
    int TalentResister_Give_Point;

    String TalentResister_Take_Keyword[];
    String TalentResister_Take_Location[];
    String TalentResister_Take_Level;
    int TalentResister_Take_Point;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_resist);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.toolbarTxt)).setText("My Talent");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,TalentResister_Activity.this,slidingMenuDL,drawerView);
            }
        };
        DrawerLayout_ClickEvent(TalentResister_Activity.this,mClicklistener);

        Intent i = getIntent();
        TalentFlag = i.getBooleanExtra("GiveFlag", true);




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
        No_Talent_Txt = (TextView) findViewById(R.id.TalentResister_NoTalent_TextView);

        TalentResister_Give = true;
        TalentResister_Take = true;

        TalentGive = SaveSharedPreference.getGiveTalentData(mContext);
        TalentTake = SaveSharedPreference.getTakeTalentData(mContext);

        if(TalentGive == null) {
            TalentResister_Give = false;
        }
        else{
            TalentResister_Give_Keyword = TalentGive.getKeywordArray();
            TalentResister_Give_Location = TalentGive.getLocationArray();
            TalentResister_Give_Point = TalentGive.getPoint();
            TalentResister_Give_Level = TalentGive.getLevel();
        }

        if(TalentTake == null) {
            TalentResister_Take = false;
        }
        else{
            TalentResister_Take_Keyword = TalentTake.getKeywordArray();
            TalentResister_Take_Location = TalentTake.getLocationArray();
            TalentResister_Take_Point = TalentTake.getPoint();
            TalentResister_Take_Level = TalentTake.getLevel();

        }

        if(TalentFlag) {
            ShowGiveBtnClicked();
        }
        else {
            ShowTakeBtnClicked();
        }


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

        getTalentStatus();

    }

    public void ShowGiveBtnClicked () {
        if (TalentResister_Give == false) {
            TalentResister_Box.setVisibility(View.GONE);
            TalentResister_textView.setVisibility(View.GONE);
            No_Talent_Txt.setVisibility(View.VISIBLE);
            TalentResister_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_whitebackground));
            TalentResister_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_graybackground));
            TalentResister_ShowTakeBtn.setTextColor(Color.parseColor("#d2d2d2"));
            TalentResister_ShowGiveBtn.setTextColor(Color.parseColor("#505050"));
            No_Talent_Txt.setText("\"재능드림이 등록되지 않았습니다.\n\n 재능드림을 등록하여 회원님의 재능을 공유해주세요!\"");
            TalentResister_Give_modifyBtn.setVisibility(View.VISIBLE);
            TalentResister_Take_modifyBtn.setVisibility(View.INVISIBLE);
            TalentResister_Give_modifyBtn.setText("재능드림 등록하기");
        }
        else {
            TalentResister_Box.setVisibility(View.VISIBLE);
            TalentResister_textView.setVisibility(View.VISIBLE);
            No_Talent_Txt.setVisibility(View.GONE);
            TalentResister_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_whitebackground));
            TalentResister_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_graybackground));
            TalentResister_ShowTakeBtn.setTextColor(Color.parseColor("#d2d2d2"));
            TalentResister_ShowGiveBtn.setTextColor(Color.parseColor("#505050"));
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
        TalentFlag = true;
    }

    public void ShowTakeBtnClicked () {
        if (TalentResister_Take == false) {
            TalentResister_Box.setVisibility(View.GONE);
            TalentResister_textView.setVisibility(View.GONE);
            No_Talent_Txt.setVisibility(View.VISIBLE);
            TalentResister_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_whitebackground));
            TalentResister_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_graybackground));
            TalentResister_ShowGiveBtn.setTextColor(Color.parseColor("#d2d2d2"));
            TalentResister_ShowTakeBtn.setTextColor(Color.parseColor("#505050"));
            No_Talent_Txt.setText("\"관심재능이 등록되지 않았습니다.\n\n 관심재능을 등록하여 회원님의 관심사를 시작해보세요!\"");
            TalentResister_Take_modifyBtn.setVisibility(View.VISIBLE);
            TalentResister_Give_modifyBtn.setVisibility(View.INVISIBLE);
            TalentResister_Take_modifyBtn.setText("관심재능 등록하기");
        }
        else {
            TalentResister_Box.setVisibility(View.VISIBLE);
            TalentResister_textView.setVisibility(View.VISIBLE);
            No_Talent_Txt.setVisibility(View.GONE);
            TalentResister_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_whitebackground));
            TalentResister_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.small_button_graybackground));
            TalentResister_ShowGiveBtn.setTextColor(Color.parseColor("#d2d2d2"));
            TalentResister_ShowTakeBtn.setTextColor(Color.parseColor("#505050"));
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
        TalentFlag = false;

        //ToolBar 적용하기

    }

    public void registTalent(View v){
        Intent i = new Intent(mContext, TalentResister_Talent_Activity.class);
        i.putExtra("talentFlag", TalentFlag);
        if(TalentFlag) {
            i.putExtra("HavingDataFlag", TalentResister_Give);
            if (TalentResister_Give) {
                if(TalentGive.getStatus() != null && TalentGive.getStatus().equals("M")){
                    Toast.makeText(getApplicationContext(), "재능공유 중에는 수정할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                i.putExtra("Data", TalentGive);
            }

        }else{
            i.putExtra("HavingDataFlag", TalentResister_Take);
            if(TalentResister_Take){
                if(TalentTake.getStatus() != null && TalentTake.getStatus().equals("M")){
                    Toast.makeText(getApplicationContext(), "재능공유 중에는 수정할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                i.putExtra("Data", TalentTake);
            }
        }

        startActivity(i);
    }

    public void getTalentStatus() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentRegist/getTalentStatus.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);

                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);
                        if(o.getString("TALENT_FLAG").equals("Y")){
                            TalentGive.setStatus(o.getString("STATUS_FLAG"));
                        }else{
                            TalentTake.setStatus(o.getString("STATUS_FLAG"));
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

                        JSONArray obj = new JSONArray(res);
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
