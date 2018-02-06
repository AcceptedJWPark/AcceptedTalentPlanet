package com.example.accepted.acceptedtalentplanet.TalentSharing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
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
import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2017-10-24.
 */

public class TalentSharing_Activity extends AppCompatActivity {
    ArrayList<TalentSharing_ListItem> OriginTalentSharingList;
    ArrayList<TalentSharing_ListItem> TalentSharingList;
    TalentSharing_ListAdapter TalentSharing_Adapter;
    Context mContext;
    ListView TalentSharingListView;

    DrawerLayout slidingMenuDL;
    View drawerView;

    TextView TalentSharing_PageTxt;

    Button TalentSharing_GiveCheck;
    Button TalentSharing_TakeCheck;


    // 검색조건 관련 변수
    boolean isGiveTalent = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsharing_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.toolbarTxt)).setText("T.Sharing");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,TalentSharing_Activity.this,slidingMenuDL,drawerView);
            }
        };
        DrawerLayout_ClickEvent(TalentSharing_Activity.this,mClicklistener);



        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentSharing_listboxDL);
        drawerView = (View) findViewById(R.id.TalentSharing_container);

        TalentSharing_GiveCheck = (Button) findViewById(R.id.TalentSharing_GiveCheck);
        TalentSharing_TakeCheck = (Button) findViewById(R.id.TalentSharing_TakeCheck);

        TalentSharingListView = (ListView) findViewById(R.id.TalentSharing_LV);
        TalentSharingList = new ArrayList<>();
        OriginTalentSharingList = new ArrayList<>();
        getTalentSharing();

    }

    public void getTalentSharing() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/getTalentSharing.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    TalentSharingList.clear();
                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);

                        double distance = findMinDistanceBetween(o.getString("GP_LAT1"), o.getString("GP_LNG1"), o.getString("GP_LAT2"), o.getString("GP_LNG2"), o.getString("GP_LAT3"), o.getString("GP_LNG3"), o.getString("TALENT_FLAG").equals("Y"));
                        TalentSharing_ListItem target = new TalentSharing_ListItem(R.drawable.textpicture, o.getString("USER_NAME"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), o.getString("seq"), o.getString("TALENT_FLAG"), o.getString("STATUS_FLAG"),(String.format("%.1f",  distance) + "km"), "Profile 보기", o.getString("USER_ID"), distance);
                        OriginTalentSharingList.add(target);
                        if(isGiveTalent){
                            if(o.getString("TALENT_FLAG").equals("N"))
                                TalentSharingList.add(target);
                        }
                        else{
                            if(o.getString("TALENT_FLAG").equals("Y"))
                                TalentSharingList.add(target);
                        }
                    }



                    TalentSharing_Adapter = new TalentSharing_ListAdapter(mContext, TalentSharingList);
                    TalentSharingListView.setAdapter(TalentSharing_Adapter);
                    TalentSharing_GiveCheck.setOnClickListener(changeTalentFlag);
                    TalentSharing_TakeCheck.setOnClickListener(changeTalentFlag);


                    Intent i = getIntent();
                    String flag = i.getStringExtra("TalentSharing_TalentFlag");
                    if(flag == null) flag = "Give";
                    if(flag.equals("Give"))
                    {
                        TalentSharing_GiveCheck.setFocusableInTouchMode(true);
                        TalentSharing_GiveCheck.performClick();
                    }else if(flag.equals("Take"))
                    {
                        TalentSharing_TakeCheck.setFocusableInTouchMode(true);
                        TalentSharing_TakeCheck.performClick();
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
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

    Button.OnClickListener changeTalentFlag = new View.OnClickListener(){
        public void onClick(View v){
            TalentSharing_PageTxt = (TextView)findViewById(R.id.TalentSharing_PageTxt);
            isGiveTalent = (((Button)v).getId() == R.id.TalentSharing_GiveCheck) ? true : false;

            if(isGiveTalent){
                MyTalent mt = SaveSharedPreference.getGiveTalentData(mContext);


                if(mt == null){
                    Toast.makeText(mContext, "재능 기부 등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, TalentResister_Activity.class);
                    i.putExtra("GiveFlag", true);
                    startActivity(i);
                    finish();
                }else if(mt.getStatus() == null || mt.getStatus().equals("C")){
                    Toast.makeText(mContext, "재능 기부 재등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, TalentCondition_Activity.class);
                    i.putExtra("GiveFlag", true);
                    startActivity(i);
                    finish();
                }

                TalentSharing_GiveCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                TalentSharing_GiveCheck.setPaintFlags(TalentSharing_GiveCheck.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                TalentSharing_GiveCheck.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                TalentSharing_TakeCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                TalentSharing_TakeCheck.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                TalentSharing_TakeCheck.setPaintFlags(TalentSharing_TakeCheck.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);

                TalentSharing_PageTxt.setText(SaveSharedPreference.getUserName(mContext) + "님의 재능을 공유할 수 있는 회원리스트 입니다.");

            }else{
                MyTalent mt = SaveSharedPreference.getTakeTalentData(mContext);
                
                if(mt == null){
                    Toast.makeText(mContext, "관심 재능 등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, TalentResister_Activity.class);
                    i.putExtra("GiveFlag", false);
                    startActivity(i);
                    finish();
                }else if(mt.getStatus() == null || mt.getStatus().equals("C")){
                    Toast.makeText(mContext, "관심 재능 재등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, TalentCondition_Activity.class);
                    i.putExtra("GiveFlag", false);
                    startActivity(i);
                    finish();
                }

                TalentSharing_TakeCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                TalentSharing_TakeCheck.setPaintFlags(TalentSharing_TakeCheck.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                TalentSharing_TakeCheck.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                TalentSharing_GiveCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                TalentSharing_GiveCheck.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                TalentSharing_GiveCheck.setPaintFlags(TalentSharing_GiveCheck.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
                TalentSharing_PageTxt.setText(SaveSharedPreference.getUserName(mContext) + "님께 재능을 공유할 수 있는 회원리스트 입니다.");
            }

            TalentSharingList.clear();
            TalentSharing_ListItem tmp;
            for(int index = 0; index < OriginTalentSharingList.size(); index++){
                tmp = OriginTalentSharingList.get(index);
                if(tmp.getTalentFlag() && !isGiveTalent)
                    TalentSharingList.add(tmp);
                else if(tmp.getTalentFlag() == false && isGiveTalent)
                    TalentSharingList.add(tmp);
            }
            Collections.sort(TalentSharingList);

            TalentSharing_Adapter = new TalentSharing_ListAdapter(mContext, TalentSharingList);
            TalentSharingListView.setAdapter(TalentSharing_Adapter);
        }
    };


    double findMinDistanceBetween(String lat1, String lng1, String lat2, String lng2, String lat3, String lng3,  boolean isGive){
        MyTalent mt;
        if(isGive)
            mt = SaveSharedPreference.getTakeTalentData(mContext);
        else
            mt = SaveSharedPreference.getGiveTalentData(mContext);

        if(mt == null){
            return 00000;
        }

        double[] arrDistance = new double[9];
        GeoPoint[] arrGp = mt.getArrGeoPoint();
        GeoPoint[] arrGp2 = new GeoPoint[3];
        arrGp2[0] = new GeoPoint(Double.valueOf(lat1), Double.valueOf(lng1));
        arrGp2[1] = new GeoPoint(Double.valueOf(lat2), Double.valueOf(lng2));
        arrGp2[2] = new GeoPoint(Double.valueOf(lat3), Double.valueOf(lng3));
        GeoPoint gp, gp2;
        int index = 0;

        for(int i = 0; i < 3; i++){
            gp = arrGp[i];
            for(int j = 0; j < 3; j++){
                gp2 = arrGp2[j];
                if(gp2.getLng() == 0.0d || gp.getLng() == 0.0d){
                    arrDistance[index++] = 999999999;

                    continue;
                }
                double distance = 0;
                Location locationA = new Location("A");
                locationA.setLatitude(gp.getLat());
                locationA.setLongitude(gp.getLng());
                Location locationB = new Location("B");
                locationB.setLatitude(gp2.getLat());
                locationB.setLongitude(gp2.getLng());
                distance = locationA.distanceTo(locationB);

                arrDistance[index++] = distance;

            }
        }

        Arrays.sort(arrDistance);

        return arrDistance[0] / 1000;

    }


}



