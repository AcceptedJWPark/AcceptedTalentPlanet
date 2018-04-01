package com.example.accepted.acceptedtalentplanet.TalentSharing;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
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
import com.bumptech.glide.Glide;
import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.MyFirebaseMessagingService;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
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
    private static ArrayList<ListItem> arrayList_Original;
    private static ArrayList<ListItem> arrayList;
    private Adapter adapter;
    private Context mContext;
    private ListView listView;

    Activity activity;

    private DrawerLayout drawerLayout;
    private View view_DarawerLayout;

    private TextView tv_Txt;

    private Button btn_giveSelect;
    private Button btn_takeSelect;


    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    // 검색조건 관련 변수
    private boolean isGiveTalent = true;

    private int interval = 100;
    private final int maxInterval = 30000;
    private int count = 0;

    static Thread thread1;
    boolean running = false;

    SQLiteDatabase sqliteDatabase;
    private ProgressBar progressBar;
    private LinearLayout progressBarContainer;
    int progressRate = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentsharing_activity);

        mContext = getApplicationContext();

        if(SaveSharedPreference.getFcmToken(mContext) == null || SaveSharedPreference.getFcmToken(mContext).isEmpty()){
            SaveSharedPreference.setPrefFcmToken(mContext,FirebaseInstanceId.getInstance().getToken());
            saveFcmToken();
        }

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("T.Sharing");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        if(MyFirebaseMessagingService.isNewMessageArrive){
            findViewById(R.id.Icon_NewMessage).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.Icon_NewMessage).setVisibility(View.GONE);
        }

        if(SaveSharedPreference.getMyThumbPicturePath() != null)
            Glide.with(mContext).load(SaveSharedPreference.getImageUri() + SaveSharedPreference.getMyThumbPicturePath()).into((ImageView) findViewById(R.id.DrawerPicture));

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

        retrieveMessage();
        getTalentSharing();

        btn_giveSelect.setOnClickListener(changeTalentFlag);
        btn_takeSelect.setOnClickListener(changeTalentFlag);


        Intent i = getIntent();
        String flag = i.getStringExtra("TalentSharing_TalentFlag");
        if (flag == null) flag = "Give";
        if (flag.equals("Give")) {
            btn_giveSelect.setFocusableInTouchMode(true);
            btn_giveSelect.performClick();
        } else if (flag.equals("Take")) {
            btn_takeSelect.setFocusableInTouchMode(true);
            btn_takeSelect.performClick();
        }

        ((ImageView) findViewById(R.id.iv_renew_TalentSharing)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTalentSharing();
            }
        });

    }

    public void getTalentSharing() {
        arrayList = new ArrayList<>();
        arrayList_Original = new ArrayList<>();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBarContainer = (LinearLayout) findViewById(R.id.progressBarContainer_TalentSharing);
        progressBarContainer.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/getTalentSharing.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    arrayList.clear();
                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);
                        double distance = findMinDistanceBetween(o.getString("GP_LAT"), o.getString("GP_LNG"), o.getString("TALENT_FLAG").equals("Y"));
                        ListItem target = new ListItem(R.drawable.picure_basic, o.getString("USER_NAME"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), o.getString("seq"), o.getString("TALENT_FLAG"), o.getString("STATUS_FLAG"), (String.format("%.1f", distance) + "km"), "Profile 보기", o.getString("USER_ID"), distance, o.getString("S_FILE_PATH"));

                        arrayList_Original.add(target);
                        if (isGiveTalent) {
                            if (o.getString("TALENT_FLAG").equals("N"))
                                arrayList.add(target);
                        } else {
                            if (o.getString("TALENT_FLAG").equals("Y"))
                                arrayList.add(target);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                MyTalent mt = (isGiveTalent)?SaveSharedPreference.getGiveTalentData(mContext) : SaveSharedPreference.getTakeTalentData(mContext);


                Collections.sort(arrayList);
                adapter = new Adapter(mContext, arrayList);
                listView.setAdapter(adapter);
                progressBarContainer.setVisibility(View.GONE);
                if(mt != null && (mt.getStatus().equals("C") || mt.getStatus().equals("M"))){
                    listView.setVisibility(View.GONE);
                }else{
                    listView.setVisibility(View.VISIBLE);
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

    Button.OnClickListener changeTalentFlag = new View.OnClickListener() {
        public void onClick(View v) {
            tv_Txt = (TextView) findViewById(R.id.tv_Txt_TalentSharing);
            isGiveTalent = (((Button) v).getId() == R.id.btn_giveSelect_TalentSharing) ? true : false;
            ((ImageView) findViewById(R.id.iv_renew_TalentSharing)).setVisibility(View.VISIBLE);
            if (isGiveTalent) {
                MyTalent mt = SaveSharedPreference.getGiveTalentData(mContext);

                if (mt == null) {

                    Toast.makeText(mContext, "재능 드림 등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                    i.putExtra("GiveFlag", true);
                    i.putExtra("TalentCondition_TalentFlag", "Give");
                    startActivity(i);
                    finish();
                } else if (mt.getStatus() == null || mt.getStatus().equals("C")) {
//                    Toast.makeText(mContext, "재능 드림 재등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
//                    i.putExtra("GiveFlag", true);
//                    i.putExtra("TalentCondition_TalentFlag", "Give");
//                    startActivity(i);
//                    finish();
                    arrayList.clear();
                    listView.setAdapter(adapter);
                    listView.setVisibility(View.GONE);
                    tv_Txt.setText("재능 드림 재등록을 먼저 진행해주세요.");
                    ((ImageView) findViewById(R.id.iv_renew_TalentSharing)).setVisibility(View.GONE);
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setText("나의 재능 현황");
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                            i.putExtra("GiveFlag", true);
                            i.putExtra("TalentCondition_TalentFlag", "Give");
                            startActivity(i);
                        }
                    });

                } else if(mt.getStatus().equals("M"))
                {
                    tv_Txt.setText("회원님의 재능 드림이 진행 중입니다.");
                    listView.setVisibility(View.GONE);
                    ((ImageView) findViewById(R.id.iv_renew_TalentSharing)).setVisibility(View.GONE);
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setText("나의 재능 현황");
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                            i.putExtra("GiveFlag", true);
                            i.putExtra("TalentCondition_TalentFlag", "Give");
                            startActivity(i);
                        }
                    });
                }
                else
                {
                    tv_Txt.setText(SaveSharedPreference.getUserName(mContext) + "님의 재능을 공유할 수 있는 회원입니다.");
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setVisibility(View.GONE);
                }

                btn_giveSelect.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                btn_giveSelect.setPaintFlags(btn_giveSelect.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                btn_giveSelect.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                btn_takeSelect.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                btn_takeSelect.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                btn_takeSelect.setPaintFlags(btn_takeSelect.getPaintFlags() & ~Paint.FAKE_BOLD_TEXT_FLAG);


            } else {
                MyTalent mt = SaveSharedPreference.getTakeTalentData(mContext);

                if (mt == null) {
                    Toast.makeText(mContext, "관심 재능 등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                    i.putExtra("GiveFlag", false);
                    i.putExtra("TalentCondition_TalentFlag", "Take");
                    startActivity(i);
                    finish();
                } else if (mt.getStatus() == null || mt.getStatus().equals("C")) {
//                    Toast.makeText(mContext, "관심 재능 재등록을 먼저 진행해주세요.", Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
//                    i.putExtra("GiveFlag", false);
//                    i.putExtra("TalentCondition_TalentFlag", "Take");
//                    startActivity(i);
//                    finish();
                    arrayList.clear();
                    listView.setAdapter(adapter);
                    tv_Txt.setText("관심 재능 재등록을 먼저 진행해주세요.");
                    listView.setVisibility(View.GONE);
                    ((ImageView) findViewById(R.id.iv_renew_TalentSharing)).setVisibility(View.GONE);
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setText("나의 재능 현황");
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                            i.putExtra("GiveFlag", false);
                            i.putExtra("TalentCondition_TalentFlag", "Take");
                            startActivity(i);
                        }
                    });
                }
                else if(mt.getStatus().equals("M"))
                {
                    tv_Txt.setText("회원님의 관심 재능이 진행 중입니다.");
                    listView.setVisibility(View.GONE);
                    ((ImageView) findViewById(R.id.iv_renew_TalentSharing)).setVisibility(View.GONE);
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setVisibility(View.VISIBLE);
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setText("나의 재능 현황");
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                            i.putExtra("GiveFlag", false);
                            i.putExtra("TalentCondition_TalentFlag", "Take");
                            startActivity(i);
                        }
                    });
                }
                else
                {
                    tv_Txt.setText(SaveSharedPreference.getUserName(mContext) + "님께 재능을 공유할 수 있는 회원입니다.");
                    ((TextView) findViewById(R.id.clickToCondition_TalentSharing)).setVisibility(View.GONE);
                }

                btn_takeSelect.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                btn_takeSelect.setPaintFlags(btn_takeSelect.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                btn_takeSelect.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                btn_giveSelect.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                btn_giveSelect.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                btn_giveSelect.setPaintFlags(btn_giveSelect.getPaintFlags() & ~Paint.FAKE_BOLD_TEXT_FLAG);
            }

            arrayList.clear();
            ListItem tmp;
            for (int index = 0; index < arrayList_Original.size(); index++) {
                tmp = arrayList_Original.get(index);
                if (tmp.getTalentFlag() && !isGiveTalent)
                    arrayList.add(tmp);
                else if (tmp.getTalentFlag() == false && isGiveTalent)
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


        double distance = 0;

        Location locationA = new Location("A");
        locationA.setLatitude(gpPoint1.getLat());
        locationA.setLongitude(gpPoint1.getLng());

        Location locationB = new Location("B");
        locationB.setLatitude(gpPoint2.getLat());
        locationB.setLongitude(gpPoint2.getLng());

        distance = locationA.distanceTo(locationB);


        return distance / 1000;
    }

    public void saveFcmToken() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/saveFCMToken.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("result").equals("success")) {
                        Log.d("saveToken", "토큰 저장 성공");

                    } else {
                        Log.d("saveToken", "토큰 저장 실패");
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
                params.put("fcmToken", SaveSharedPreference.getFcmToken(mContext));


                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    public void retrieveMessage() {
        int maxMessageID = 0;
        try {
            String dbName = "/accepted.db";
            SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + dbName, null);

            String selectMaxMessage = "SELECT IFNULL(MAX(D01.MESSAGE_ID), 0) AS MESSAGE_ID\n" +
                                      "FROM   TB_CHAT_LOG D01\n" +
                                      "     , TB_CHAT_ROOM D02\n" +
                                      "WHERE  D01.ROOM_ID = D02.ROOM_ID\n" +
                                      "AND    D02.MASTER_ID = '" + SaveSharedPreference.getUserId(mContext) + "'";
            Cursor cursor = sqLiteDatabase.rawQuery(selectMaxMessage, null);
            cursor.moveToFirst();

            maxMessageID = cursor.getInt(0);

            cursor.close();
            sqLiteDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (maxMessageID == 0)
            return;

        final int lastMessageID = maxMessageID;

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Chat/retrieveMessage.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("result").equals("success")) {
                        interval = 100;
                        count = 0;
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
                params.put("lastMessageID", String.valueOf(lastMessageID));

                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {

            finish();
            super.onBackPressed();

        } else {
            backPressedTime = tempTime;
            Toast.makeText(mContext, "뒤로가기를 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        drawerLayout.closeDrawers();
        if(MyFirebaseMessagingService.isNewMessageArrive){
            findViewById(R.id.Icon_NewMessage).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.Icon_NewMessage).setVisibility(View.GONE);
        }
    }


}



