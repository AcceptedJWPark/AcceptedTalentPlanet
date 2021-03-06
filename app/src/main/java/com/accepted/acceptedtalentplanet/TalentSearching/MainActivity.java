package com.accepted.acceptedtalentplanet.TalentSearching;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2017-11-24.
 */

public class MainActivity extends AppCompatActivity {

    Button TalentSeraching_searchingBoxOpen;
    Context mContext;

    DrawerLayout slidingMenuDL;
    View drawerView;

    ArrayList<ListItem> talentSearching_listItemArrayList;
    Adapter talentSearching_listAdapter;
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
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("재능 검색");
        if(SaveSharedPreference.getMyThumbPicturePath() != null)
            Glide.with(mContext).load(SaveSharedPreference.getImageUri() + SaveSharedPreference.getMyThumbPicturePath()).into((ImageView) findViewById(R.id.DrawerPicture));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,MainActivity.this,slidingMenuDL,drawerView);
            }
        };
        DrawerLayout_ClickEvent(MainActivity.this,mClicklistener);

                final Button giveButton = (Button)findViewById(R.id.TalentSearching_ShowGive);
                final Button takeButton = (Button)findViewById(R.id.TalentSearching_ShowTake);

                giveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Build.VERSION.SDK_INT >= 16) {
                            giveButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                            takeButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                        }else {
                            giveButton.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                            takeButton.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                        }
                        giveButton.setPaintFlags(giveButton.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                        giveButton.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                        takeButton.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                        takeButton.setPaintFlags(takeButton.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
                    }
                });

                takeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(Build.VERSION.SDK_INT >= 16) {
                            takeButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                            giveButton.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                        }else{
                            takeButton.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                            giveButton.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                        }
                        takeButton.setPaintFlags(giveButton.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                        takeButton.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                        giveButton.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                        giveButton.setPaintFlags(takeButton.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
                    }
                });



        TalentSeraching_searchingBoxOpen = (Button) findViewById(R.id.TalentSeraching_searchingBoxOpen);
        TalentSeraching_searchingBoxOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, com.accepted.acceptedtalentplanet.TalentSearching.SearchingPage.MainActivity.class);
                startActivity(intent);
            }
        });

        retrieveTalent();

    }

    public void retrieveTalent() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSearch/retrieveTalent.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    Log.d("Array = " , obj.toString());
                    talentSearching_listItemArrayList = new ArrayList<>();
                    talentSearching_listAdapter = new Adapter(mContext, talentSearching_listItemArrayList);
                    for(int i = 0; i < obj.length(); i++) {
                        JSONObject o = obj.getJSONObject(i);
                        Log.d("Obj = ", o.toString());
                        talentSearching_listItemArrayList.add(new ListItem(R.drawable.picure_basic, o.getString("USER_NAME"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), o.getString("seq")));

                    }
                    TalentSearching_ListView = (ListView) findViewById(R.id.TalentSearching_ListView);
                    TalentSearching_ListView.setAdapter(talentSearching_listAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
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
        postRequestQueue.add(postJsonRequest);
    }

}

