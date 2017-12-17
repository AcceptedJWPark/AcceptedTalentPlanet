package com.example.accepted.acceptedtalentplanet.TalentSharing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.Alarm.Alarm_Activity;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.accepted.acceptedtalentplanet.FriendList.FriendList_Activity;
import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.Home.Home_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.System.System_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_LocationList;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;
    ImageView ActionBar_AlarmView;

    TextView ToolbarTxt;
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

        ToolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        ToolbarTxt.setText("T.Sharing");

        //ToolBar 적용하기
        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentSharing_listboxDL);
        drawerView = (View) findViewById(R.id.TalentSharing_container);
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
                        TalentSharing_ListItem target = new TalentSharing_ListItem(R.drawable.textpicture, o.getString("USER_NAME"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), o.getString("seq"), o.getString("TALENT_FLAG"), o.getString("STATUS_FLAG"),findMinDistanceBetween(o.getString("GP_LAT1"), o.getString("GP_LNG1"), o.getString("GP_LAT2"), o.getString("GP_LNG2"), o.getString("GP_LAT3"), o.getString("GP_LNG3"), o.getString("TALENT_FLAG").equals("Y")), "Profile 보기", o.getString("USER_ID"));
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
                        Toast.makeText(mContext,"TalentSharing_TalentFlag = Give",Toast.LENGTH_SHORT).show();
                        TalentSharing_GiveCheck.setFocusableInTouchMode(true);
                        TalentSharing_GiveCheck.performClick();
                    }else if(flag.equals("Take"))
                    {
                        Toast.makeText(mContext,"TalentSharing_TalentFlag = Take",Toast.LENGTH_SHORT).show();
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
                TalentSharing_GiveCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                TalentSharing_GiveCheck.setTextColor(getResources().getColor(R.color.textColor));
                TalentSharing_TakeCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                TalentSharing_TakeCheck.setTextColor(Color.parseColor("#d2d2d2"));
                TalentSharing_PageTxt.setText(SaveSharedPreference.getUserName(mContext) + "님의 재능을 공유할 수 있는 회원리스트 입니다.");
            }else{
                TalentSharing_TakeCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                TalentSharing_TakeCheck.setTextColor(getResources().getColor(R.color.textColor));
                TalentSharing_GiveCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                TalentSharing_GiveCheck.setTextColor(Color.parseColor("#d2d2d2"));
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

            TalentSharing_Adapter = new TalentSharing_ListAdapter(mContext, TalentSharingList);
            TalentSharingListView.setAdapter(TalentSharing_Adapter);
        }
    };


    String findMinDistanceBetween(String lat1, String lng1, String lat2, String lng2, String lat3, String lng3,  boolean isGive){
        MyTalent mt;
        if(isGive)
            mt = SaveSharedPreference.getGiveTalentData(mContext);
        else
            mt = SaveSharedPreference.getTakeTalentData(mContext);

        if(mt == null){
            return "0km";
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

        return String.format("%.1f", arrDistance[0] / 1000) + "km";

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


}



