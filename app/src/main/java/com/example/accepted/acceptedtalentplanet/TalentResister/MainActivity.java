package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private View view_drawerView;


    private MyTalent givetalent;
    private MyTalent takeTalent;

    private Context mContext;

    private Button btn_ShowGive;
    private Button btn_ShowTake;
    private Button btn_ModifyGive;
    private Button btn_ModifyTake;

    private TextView tv_Talent1;
    private TextView tv_Talent2;
    private TextView tv_Talent3;
    private TextView tv_Location1;
    private TextView tv_Location2;
    private TextView tv_Location3;
    private TextView tv_Level;
    private TextView tv_Point;

    private LinearLayout ll_TxtContainer;
    private TextView tv_NoTalent;
    private boolean is_GiveTalentResistered;
    private boolean is_TakeTalentResistered;
    private boolean talentFlag = true;

    private String giveTalentList[];
    private String giveLocationList[];
    private String giveLevel;
    private int givePoint;

    private String takeKeywordList[];
    private String takeLocationList[];
    private String takeLevel;
    private int takePoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_resist);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("My Talent");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,MainActivity.this, drawerLayout, view_drawerView);
            }
        };
        DrawerLayout_ClickEvent(MainActivity.this,mClicklistener);

        Intent i = getIntent();
        talentFlag = i.getBooleanExtra("GiveFlag", true);




        btn_ShowGive = (Button) findViewById(R.id.btn_ShowGive_TalentResister);
        btn_ShowTake = (Button) findViewById(R.id.btn_ShowTake_TalentResister);
        btn_ModifyGive = (Button) findViewById(R.id.btn_ModifyGive_TalentResister);
        btn_ModifyTake = (Button) findViewById(R.id.btn_ModifyTake_TalentResister);
        tv_Talent1 = (TextView) findViewById(R.id.tv_Talent1_TalentResister);
        tv_Talent2 = (TextView) findViewById(R.id.tv_Talent_TalentResister);
        tv_Talent3 = (TextView) findViewById(R.id.tv_Talent3_TalentResister);
        tv_Location1 = (TextView) findViewById(R.id.tv_Location1_TalentResister);
        tv_Level = (TextView) findViewById(R.id.tv_Level_TalentResister);
        tv_Point = (TextView) findViewById(R.id.tv_Point_TalentResister);
        ll_TxtContainer = (LinearLayout) findViewById(R.id.ll_TxtContainer_TalentResister);
        tv_NoTalent = (TextView) findViewById(R.id.tv_NoTalent_TalentRegister);

        is_GiveTalentResistered = true;
        is_TakeTalentResistered = true;

        givetalent = SaveSharedPreference.getGiveTalentData(mContext);
        takeTalent = SaveSharedPreference.getTakeTalentData(mContext);

        if(givetalent == null) {
            is_GiveTalentResistered = false;
        }
        else{
            giveTalentList = givetalent.getKeywordArray();
            giveLocationList = givetalent.getLocationArray();
            givePoint = givetalent.getPoint();
            giveLevel = givetalent.getLevel();
        }

        if(takeTalent == null) {
            is_TakeTalentResistered = false;
        }
        else{
            takeKeywordList = takeTalent.getKeywordArray();
            takeLocationList = takeTalent.getLocationArray();
            takePoint = takeTalent.getPoint();
            takeLevel = takeTalent.getLevel();

        }

        if(talentFlag) {
            ShowGiveBtnClicked();
        }
        else {
            ShowTakeBtnClicked();
        }


        btn_ShowGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowGiveBtnClicked();
            }
        });

        btn_ShowTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowTakeBtnClicked();
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_TalentResister);

        view_drawerView = (View) findViewById(R.id.view_drawerView_TalentResister);

        getTalentStatus();

    }

    public void ShowGiveBtnClicked () {
        if (is_GiveTalentResistered == false) {
            ll_TxtContainer.setVisibility(View.GONE);
            tv_NoTalent.setVisibility(View.VISIBLE);
            btn_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
            btn_ShowGive.setPaintFlags(btn_ShowGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
            btn_ShowGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
            btn_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
            btn_ShowTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
            btn_ShowTake.setPaintFlags(btn_ShowTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
            tv_NoTalent.setText("\"재능드림이 등록되지 않았습니다.\n\n 재능드림을 등록하여 회원님의 재능을 공유해주세요!\"");
            btn_ModifyGive.setVisibility(View.VISIBLE);
            btn_ModifyTake.setVisibility(View.INVISIBLE);
            btn_ModifyGive.setText("재능드림 등록하기");
        }
        else {
            ll_TxtContainer.setVisibility(View.VISIBLE);
            tv_NoTalent.setVisibility(View.GONE);
            btn_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
            btn_ShowGive.setPaintFlags(btn_ShowGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
            btn_ShowGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
            btn_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
            btn_ShowTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
            btn_ShowTake.setPaintFlags(btn_ShowTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
            btn_ModifyGive.setVisibility(View.VISIBLE);
            btn_ModifyTake.setVisibility(View.INVISIBLE);
            tv_Talent1.setText(giveTalentList[0]);
            tv_Talent2.setText(giveTalentList[1]);
            tv_Talent3.setText(giveTalentList[2]);

            tv_Location1.setText(giveLocationList[0]);
            tv_Level.setText(giveLevel);
            tv_Point.setText(Integer.toString(givePoint) + "P");
        }
        talentFlag = true;
    }

    public void ShowTakeBtnClicked () {
        if (is_TakeTalentResistered == false) {
            ll_TxtContainer.setVisibility(View.GONE);
            tv_NoTalent.setVisibility(View.VISIBLE);
            btn_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
            btn_ShowTake.setPaintFlags(btn_ShowGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
            btn_ShowTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
            btn_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
            btn_ShowGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
            btn_ShowGive.setPaintFlags(btn_ShowTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);

            tv_NoTalent.setText("\"관심재능이 등록되지 않았습니다.\n\n 관심재능을 등록하여 회원님의 관심사를 시작해보세요!\"");
            btn_ModifyTake.setVisibility(View.VISIBLE);
            btn_ModifyGive.setVisibility(View.INVISIBLE);
            btn_ModifyTake.setText("관심재능 등록하기");
        }
        else {
            ll_TxtContainer.setVisibility(View.VISIBLE);
            tv_NoTalent.setVisibility(View.GONE);
            btn_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
            btn_ShowTake.setPaintFlags(btn_ShowGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
            btn_ShowTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
            btn_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
            btn_ShowGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
            btn_ShowGive.setPaintFlags(btn_ShowTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
            btn_ModifyTake.setVisibility(View.VISIBLE);
            btn_ModifyGive.setVisibility(View.INVISIBLE);
            tv_Talent1.setText(takeKeywordList[0]);
            tv_Talent2.setText(takeKeywordList[1]);
            tv_Talent3.setText(takeKeywordList[2]);

            tv_Location1.setText(takeLocationList[0]);
            tv_Level.setText(takeLevel);
            tv_Point.setText(Integer.toString(takePoint) + "P");
        }
        talentFlag = false;

        //ToolBar 적용하기

    }

    public void registTalent(View v){
        Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.Talent.MainActivity.class);
        i.putExtra("talentFlag", talentFlag);
        if(talentFlag) {
            i.putExtra("HavingDataFlag", is_GiveTalentResistered);
            if (is_GiveTalentResistered) {
                if(givetalent.getStatus() != null && givetalent.getStatus().equals("M")){
                    Toast.makeText(getApplicationContext(), "재능공유 진행 중에는 수정할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                i.putExtra("Data", givetalent);
            }

        }else{
            i.putExtra("HavingDataFlag", is_TakeTalentResistered);
            if(is_TakeTalentResistered){
                if(takeTalent.getStatus() != null && takeTalent.getStatus().equals("M")){
                    Toast.makeText(getApplicationContext(), "재능공유 진행 중에는 수정할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                i.putExtra("Data", takeTalent);
            }
        }

        startActivity(i);
    }

    public void getTalentStatus() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentRegist/getTalentStatus.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);

                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);
                        if(o.getString("TALENT_FLAG").equals("Y")){
                            givetalent.setStatus(o.getString("STATUS_FLAG"));
                        }else{
                            takeTalent.setStatus(o.getString("STATUS_FLAG"));
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
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }


}
