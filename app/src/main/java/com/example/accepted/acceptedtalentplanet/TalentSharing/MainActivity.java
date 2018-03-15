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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2017-10-24.
 */

public class MainActivity extends AppCompatActivity {
    ArrayList<ListItem> arrayList_Original;
    ArrayList<ListItem> arrayList;
    Adapter adapter;
    Context mContext;
    ListView listView;

    DrawerLayout drawerLayout;
    View view_DarawerLayout;

    TextView tv_Txt;

    Button btn_giveSelect;
    Button btn_takeSelect;


    // 검색조건 관련 변수
    boolean isGiveTalent = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsharing_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("T.Sharing");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,MainActivity.this, drawerLayout, view_DarawerLayout);
            }
        };
        DrawerLayout_ClickEvent(MainActivity.this,mClicklistener);



        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_TalentSharing);
        view_DarawerLayout = (View) findViewById(R.id.view_DarawerLayout_TalentSharing);

        btn_giveSelect = (Button) findViewById(R.id.btn_giveSelect_TalentSharing);
        btn_takeSelect = (Button) findViewById(R.id.btn_takeSelect_TalentSharing);

        listView = (ListView) findViewById(R.id.listView_TalentSharing);
        arrayList = new ArrayList<>();
        arrayList_Original = new ArrayList<>();
        getTalentSharing();

    }

    public void getTalentSharing() {


        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();


        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/getTalentSharing.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    arrayList.clear();
                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);
                        Log.d("getTalentSharing", o.toString());
                        double distance = findMinDistanceBetween(o.getString("GP_LAT"), o.getString("GP_LNG"), o.getString("TALENT_FLAG").equals("Y"));
                        ListItem target = new ListItem(R.drawable.testpicture, o.getString("USER_NAME"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), o.getString("seq"), o.getString("TALENT_FLAG"), o.getString("STATUS_FLAG"),(String.format("%.1f",  distance) + "km"), "Profile 보기", o.getString("USER_ID"), distance, o.getString("FILE_DATA"));
                        arrayList_Original.add(target);
                        if(isGiveTalent){
                            if(o.getString("TALENT_FLAG").equals("N"))
                                arrayList.add(target);
                        }
                        else{
                            if(o.getString("TALENT_FLAG").equals("Y"))
                                arrayList.add(target);
                        }
                    }



                    adapter = new Adapter(mContext, arrayList);
                    listView.setAdapter(adapter);
                    btn_giveSelect.setOnClickListener(changeTalentFlag);
                    btn_takeSelect.setOnClickListener(changeTalentFlag);


                    Intent i = getIntent();
                    String flag = i.getStringExtra("TalentSharing_TalentFlag");
                    if(flag == null) flag = "Give";
                    if(flag.equals("Give"))
                    {
                        btn_giveSelect.setFocusableInTouchMode(true);
                        btn_giveSelect.performClick();
                    }else if(flag.equals("Take"))
                    {
                        btn_takeSelect.setFocusableInTouchMode(true);
                        btn_takeSelect.performClick();
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

    Button.OnClickListener changeTalentFlag = new View.OnClickListener(){
        public void onClick(View v){
            tv_Txt = (TextView)findViewById(R.id.tv_Txt_TalentSharing);
            isGiveTalent = (((Button)v).getId() == R.id.btn_giveSelect_TalentSharing) ? true : false;

            if(isGiveTalent){
                MyTalent mt = SaveSharedPreference.getGiveTalentData(mContext);


                if(mt == null){
                    Toast.makeText(mContext, "재능 기부 등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                    i.putExtra("GiveFlag", true);
                    startActivity(i);
                    finish();
                }else if(mt.getStatus() == null || mt.getStatus().equals("C")){
                    Toast.makeText(mContext, "재능 기부 재등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                    i.putExtra("GiveFlag", true);
                    startActivity(i);
                    finish();
                }

                btn_giveSelect.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                btn_giveSelect.setPaintFlags(btn_giveSelect.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                btn_giveSelect.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                btn_takeSelect.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                btn_takeSelect.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                btn_takeSelect.setPaintFlags(btn_takeSelect.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);

                tv_Txt.setText(SaveSharedPreference.getUserName(mContext) + "님의 재능을 공유할 수 있는 회원리스트 입니다.");

            }else{
                MyTalent mt = SaveSharedPreference.getTakeTalentData(mContext);
                
                if(mt == null){
                    Toast.makeText(mContext, "관심 재능 등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                    i.putExtra("GiveFlag", false);
                    startActivity(i);
                    finish();
                }else if(mt.getStatus() == null || mt.getStatus().equals("C")){
                    Toast.makeText(mContext, "관심 재능 재등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                    i.putExtra("GiveFlag", false);
                    startActivity(i);
                    finish();
                }

                btn_takeSelect.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                btn_takeSelect.setPaintFlags(btn_takeSelect.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                btn_takeSelect.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                btn_giveSelect.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                btn_giveSelect.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                btn_giveSelect.setPaintFlags(btn_giveSelect.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
                tv_Txt.setText(SaveSharedPreference.getUserName(mContext) + "님께 재능을 공유할 수 있는 회원리스트 입니다.");
            }

            arrayList.clear();
            ListItem tmp;
            for(int index = 0; index < arrayList_Original.size(); index++){
                tmp = arrayList_Original.get(index);
                if(tmp.getTalentFlag() && !isGiveTalent)
                    arrayList.add(tmp);
                else if(tmp.getTalentFlag() == false && isGiveTalent)
                    arrayList.add(tmp);
            }
            Collections.sort(arrayList);

            adapter = new Adapter(mContext, arrayList);
            listView.setAdapter(adapter);
        }
    };


    double findMinDistanceBetween(String lat, String lng, boolean isGive) {
        MyTalent mt;

        if (isGive)
            mt = SaveSharedPreference.getTakeTalentData(mContext);
        else
            mt = SaveSharedPreference.getGiveTalentData(mContext);

        if (mt == null) {
            return 00000;
        }

        GeoPoint gpPoint1 = mt.getArrGeoPoint();
        GeoPoint gpPoint2 = new GeoPoint(Double.valueOf(lat), Double.valueOf(lng));

        Log.d("My GP", gpPoint1.getLat() + ", " + gpPoint1.getLng());
        Log.d("Target GP", gpPoint2.getLat() + ", " + gpPoint2.getLng());

        double distance = 0;

        Location locationA = new Location("A");
        locationA.setLatitude(gpPoint1.getLat());
        locationA.setLongitude(gpPoint1.getLng());

        Location locationB = new Location("B");
        locationB.setLatitude(gpPoint2.getLat());
        locationB.setLongitude(gpPoint2.getLng());

        distance = locationA.distanceTo(locationB);

        Log.d("distance", distance + "");

        return distance / 1000;
    }

}



