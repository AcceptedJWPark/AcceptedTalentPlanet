package com.example.accepted.acceptedtalentplanet.TalentSearching;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_ListAdapter;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_ListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2017-11-24.
 */

public class TalentSearching_Activity extends AppCompatActivity {

    Button TalentSeraching_searchingBoxOpen;
    Context mContext;

    DrawerLayout slidingMenuDL;
    View drawerView;

    ArrayList<TalentSearching_ListItem> talentSearching_listItemArrayList;
    TalentSearching_ListAdapter talentSearching_listAdapter;
    ListView TalentSearching_ListView;

    String keyword;
    String location1;
    String location2;
    String beginLevel;
    String endLevel;
    String beginPoint;
    String endPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsearching_activity);

        mContext = getApplicationContext();

        Intent i = getIntent();

        keyword = i.getStringExtra("keyword");
        location1 = i.getStringExtra("location1");
        location2 = i.getStringExtra("location2");
        beginLevel = String.valueOf(i.getIntExtra("beginLevel", 1));
        endLevel = String.valueOf(i.getIntExtra("endLevel", 1));
        beginPoint = i.getStringExtra("beginPoint");
        endPoint = i.getStringExtra("endPoint");


        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentSearching_listboxDL);
        drawerView = (View) findViewById(R.id.TalentSearching_container);
        ((TextView) findViewById(R.id.toolbarTxt)).setText("재능 검색");
        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,TalentSearching_Activity.this,slidingMenuDL,drawerView);
            }
        };
        DrawerLayout_ClickEvent(TalentSearching_Activity.this,mClicklistener);

        talentSearching_listItemArrayList = new ArrayList<>();
        talentSearching_listAdapter = new TalentSearching_ListAdapter(mContext, talentSearching_listItemArrayList);
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"박종우","기타","기타 연습","기타 독주"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"민권홍","피아노","피아노 연주","Piano"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"유성택","복싱","권투","격투기"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.testpicture2,"임하슬람","기타","피아노","드럼"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"김정태","공무원 시험","공무원 9급","공무원 7급"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.testpicture2,"우승제","축구","풋살","축구 프리킥"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.textpicture,"김용인","먹방","아프리카 bj","먹는 방송"));
        talentSearching_listItemArrayList.add(new TalentSearching_ListItem(R.drawable.testpicture2,"배대명","비트박스","BeatBox","북치기 박치기"));

        TalentSearching_ListView = (ListView) findViewById(R.id.TalentSearching_ListView);
        TalentSearching_ListView.setAdapter(talentSearching_listAdapter);


                final Button giveButton = (Button)findViewById(R.id.TalentSearching_ShowGive);
                final Button takeButton = (Button)findViewById(R.id.TalentSearching_ShowTake);

                giveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        giveButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                        giveButton.setPaintFlags(giveButton.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                        giveButton.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                        takeButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                        takeButton.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                        takeButton.setPaintFlags(takeButton.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
                    }
                });

                takeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        takeButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                        takeButton.setPaintFlags(giveButton.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                        takeButton.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                        giveButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                        giveButton.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                        giveButton.setPaintFlags(takeButton.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
                    }
                });



        TalentSeraching_searchingBoxOpen = (Button) findViewById(R.id.TalentSeraching_searchingBoxOpen);
        TalentSeraching_searchingBoxOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TalentSearching_SearchingPage_Activity.class);
                startActivity(intent);
            }
        });

    }

    public void retrieveTalent() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSearch/retrieveTalent.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);

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
                params.put("keyword", keyword);
                params.put("location1", location1);
                params.put("location2", location2);
                params.put("beginLevel", beginLevel);
                params.put("endLevel", endLevel);
                params.put("beginPoint", beginPoint);
                params.put("endPoint", endPoint);

                return params;
            }
        };
    }

}

