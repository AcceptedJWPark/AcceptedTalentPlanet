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
import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.Home.Home_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
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

    TextView ToolbarTxt;

    // 검색조건 관련 변수
    boolean isGiveTalent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsharing_activity);

        mContext = getApplicationContext();

        ToolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        ToolbarTxt.setText("T.Sharing");

        TalentSharingListView = (ListView) findViewById(R.id.TalentSharing_LV);
        TalentSharingList = new ArrayList<>();
        OriginTalentSharingList = new ArrayList<>();
        getTalentSharing();



        LocationList locationList = new LocationList();

        String Location_List[] = locationList.Location_List;
        ArrayAdapter<String> Location_ListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,Location_List);
        Location_EditTxt = (AutoCompleteTextView) findViewById(R.id.TalentSharing_serchingBox_Location1);
        Location_EditTxt.setAdapter(Location_ListAdapter);

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
                        TalentSharing_ListItem target = new TalentSharing_ListItem(R.drawable.textpicture, o.getString("USER_NAME"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), o.getString("TALENT_ID"), o.getString("TALENT_FLAG"), o.getString("STATUS_FLAG"),findMinDistanceBetween(o.getString("GP_LAT1"), o.getString("GP_LNG1"), o.getString("GP_LAT2"), o.getString("GP_LNG2"), o.getString("GP_LAT3"), o.getString("GP_LNG3"), o.getString("TALENT_FLAG").equals("Y")), "Profile 보기", o.getString("USER_ID"));
                        OriginTalentSharingList.add(target);
                        if(isGiveTalent){
                            if(o.getString("TALENT_FLAG").equals("Y"))
                            TalentSharingList.add(target);
                        }
                        else{
                            TalentSharingList.add(target);
                        }
                    }


                    TalentSharing_Adapter = new TalentSharing_ListAdapter(mContext, TalentSharingList);
                    TalentSharingListView.setAdapter(TalentSharing_Adapter);




                    //ToolBar 적용하기
                    slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentSharing_listboxDL);
                    drawerView = (View) findViewById(R.id.TalentSharing_container);
                    imgDLOpenMenu = (ImageView) findViewById(R.id.ActionBar_Listview);
                    DrawerCloseImg = (ImageView) findViewById(R.id.DrawerCloseImg);


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


                    ((Button)findViewById(R.id.TalentSharing_GiveCheck)).setOnClickListener(changeTalentFlag);
                    ((Button)findViewById(R.id.TalentSharing_TakeCheck)).setOnClickListener(changeTalentFlag);
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
            Button giveButton = (Button)findViewById(R.id.TalentSharing_GiveCheck);
            Button takeButton = (Button)findViewById(R.id.TalentSharing_TakeCheck);

            isGiveTalent = (((Button)v).getId() == R.id.TalentSharing_GiveCheck) ? true : false;

            if(isGiveTalent){
                giveButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                giveButton.setTextColor(getResources().getColor(R.color.textColor));
                takeButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                takeButton.setTextColor(Color.parseColor("#d2d2d2"));
            }else{
                takeButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                takeButton.setTextColor(getResources().getColor(R.color.textColor));
                giveButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                giveButton.setTextColor(Color.parseColor("#d2d2d2"));
            }

            TalentSharingList.clear();
            TalentSharing_ListItem tmp;
            for(int index = 0; index < OriginTalentSharingList.size(); index++){
                tmp = OriginTalentSharingList.get(index);
                if(tmp.getTalentFlag() && isGiveTalent)
                    TalentSharingList.add(tmp);
                else if(tmp.getTalentFlag() == false && isGiveTalent == false)
                    TalentSharingList.add(tmp);
            }

            TalentSharing_Adapter = new TalentSharing_ListAdapter(mContext, TalentSharingList);
            TalentSharingListView.setAdapter(TalentSharing_Adapter);
        }
    };

    private GeoPoint findGeoPoint(String address) {
        Geocoder geocoder = new Geocoder(this);
        Address addr;
        GeoPoint location = null;
        try {
            List<Address> listAddress = geocoder.getFromLocationName(address, 1);
            if (listAddress.size() > 0) { // 주소값이 존재 하면
                addr = listAddress.get(0); // Address형태로
                double lat = addr.getLatitude();
                double lng = addr.getLongitude();
                location = new GeoPoint(lat, lng);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }

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

}



