package com.example.accepted.acceptedtalentplanet.TalentSharing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.Home.HomeActivity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.LoginActivity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyprofileActivity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentConditionActivity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-10-24.
 */

public class TalentSharingActivity extends AppCompatActivity {
    ArrayList<TalentSharingListItem> TalentSharingList;
    TalentSharingListAdapter TalentSharing_Adapter;
    Context mContext;
    ListView TalentSharingListView;
    LinearLayout TalentShringSearchingOpen;
    LinearLayout TalentShringSearchingBox;
    Button searchingBtn;
    Button profileShowBtn;

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;

    // 검색조건 관련 변수
    String Keyword1, Keyword2, Keyword3, Location1, Location2, Location3;
    int Level, Point;
    boolean isGiveTalent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsharing_activity);

        mContext = getApplicationContext();

        TalentSharingListView = (ListView) findViewById(R.id.TalentSharing_LV);
        TalentSharingList = new ArrayList<>();

        getTalentSharing();

    }

    public void slideMenuHome(View v){
        Intent i = new Intent(mContext, HomeActivity.class);
        startActivity(i);
    }

    public void slideMenuProfile(View v){
        Intent i = new Intent(mContext, MyprofileActivity.class);
        startActivity(i);
    }

    public void slideMenuTalent(View v){
        Intent i = new Intent(mContext, TalentResisterActivity.class);
        startActivity(i);
    }

    public void slideMenuTS(View v){
        Intent i = new Intent(mContext, TalentSharingActivity.class);
        startActivity(i);
    }

    public void slideMenuMyTalent(View v){
        Intent i = new Intent(mContext, TalentConditionActivity.class);
        startActivity(i);
    }

    public void slideMenuLogout(View v){
        SaveSharedPreference.clearUserInfo(mContext);
        Intent i = new Intent(mContext, LoginActivity.class);
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
                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);
                        TalentSharingList.add(new TalentSharingListItem(R.drawable.textpicture, o.getString("USER_NAME"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), "0km", "Profile 보기"));

                    }


                    TalentSharing_Adapter = new TalentSharingListAdapter(mContext, TalentSharingList);
                    TalentSharingListView.setAdapter(TalentSharing_Adapter);


                    TalentShringSearchingBox = (LinearLayout) findViewById(R.id.TalentSharing_searchingBoxLL);
                    TalentShringSearchingOpen = (LinearLayout) findViewById(R.id.TalentSharing_searchingBoxOpen);
                    TalentShringSearchingBox.setVisibility(View.GONE);
                    TalentShringSearchingOpen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (TalentShringSearchingBox.getVisibility() == View.GONE) {
                                TalentShringSearchingBox.setVisibility(View.VISIBLE);
                            } else if (TalentShringSearchingBox.getVisibility() == View.VISIBLE)
                                TalentShringSearchingBox.setVisibility(View.GONE);
                        }
                    });
                    TalentShringSearchingBox = (LinearLayout) findViewById(R.id.TalentSharing_searchingBoxLL);
                    TalentShringSearchingOpen = (LinearLayout) findViewById(R.id.TalentSharing_searchingBoxOpen);
                    TalentShringSearchingBox.setVisibility(View.GONE);
                    TalentShringSearchingOpen.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (TalentShringSearchingBox.getVisibility() == View.GONE) {
                                TalentShringSearchingBox.setVisibility(View.VISIBLE);
                            } else if (TalentShringSearchingBox.getVisibility() == View.VISIBLE)
                                TalentShringSearchingBox.setVisibility(View.GONE);
                        }
                    });

                    searchingBtn = (Button) findViewById(R.id.searchingBtn);
                    searchingBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TalentShringSearchingBox.setVisibility(View.GONE);
                        }
                    });


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



