package com.accepted.acceptedtalentplanet.InterestingList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.VolleySingleton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Accepted on 2017-10-24.
 */

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ListView listView;

    private ArrayList<ListItem> arrayList;
    private Adapter adapter;
    private boolean giveTalentFlag;

    private LinearLayout ll_PreContainer;

    private Button btn_SelectGive;
    private Button btn_SelectTake;

    private String userID;

    private String networkState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        networkState = SaveSharedPreference.getWhatKindOfNetwork(mContext);
        if(networkState.equals(SaveSharedPreference.NONE_STATE) || (networkState.equals(SaveSharedPreference.WIFI_STATE) && !SaveSharedPreference.isOnline())){
            setContentView(R.layout.error_page);
            ((Button)findViewById(R.id.btn_RefreshErrorPage)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recreate();
                }
            });
        }else {
            setContentView(R.layout.interestinglist_activity);

            ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setImageResource(R.drawable.icon_backbtn);
            ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            ((ImageView)findViewById(R.id.iv_RightBtn_Toolbar)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("재능 공유 내역");


            listView = (ListView) findViewById(R.id.lv_InterestingList);
            arrayList = new ArrayList<>();

            userID = SaveSharedPreference.getUserId(mContext);


            btn_SelectGive = (Button) findViewById(R.id.btn_SelectGive_InterestingList);
            btn_SelectTake = (Button) findViewById(R.id.btn_SelectTake_InterestingList);

            btn_SelectGive.setOnClickListener(changeTalentFlag);
            btn_SelectTake.setOnClickListener(changeTalentFlag);

            giveTalentFlag = getIntent().getStringExtra("TalentFlag").equals("Give");
            if (giveTalentFlag) {
                btn_SelectGive.setFocusableInTouchMode(true);
                btn_SelectGive.performClick();
            } else {
                btn_SelectTake.setFocusableInTouchMode(true);
                btn_SelectTake.performClick();
            }

        }

    }

    @Override
     public void onResume(){
        super.onResume();
        networkState = SaveSharedPreference.getWhatKindOfNetwork(mContext);
        if(networkState.equals(SaveSharedPreference.NONE_STATE) || (networkState.equals(SaveSharedPreference.WIFI_STATE) && !SaveSharedPreference.isOnline())){
            setContentView(R.layout.error_page);
            ((Button)findViewById(R.id.btn_RefreshErrorPage)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    recreate();
                }
            });
        }else {
            arrayList = new ArrayList<>();
            getInterestList();
        }
    }

    Button.OnClickListener changeTalentFlag = new View.OnClickListener(){
        public void onClick(View v){
            giveTalentFlag = (((Button)v).getId() == R.id.btn_SelectGive_InterestingList) ? true : false;

            if(giveTalentFlag){
                if(Build.VERSION.SDK_INT >= 16) {
                    btn_SelectGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                    btn_SelectTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                }else{
                    btn_SelectGive.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                    btn_SelectTake.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                }
                btn_SelectGive.setPaintFlags(btn_SelectGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                btn_SelectGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                btn_SelectTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                btn_SelectTake.setPaintFlags(btn_SelectTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
            }else{
                if(Build.VERSION.SDK_INT >= 16) {
                    btn_SelectTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                    btn_SelectGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                }else{
                    btn_SelectTake.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                    btn_SelectGive.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                }
                btn_SelectTake.setPaintFlags(btn_SelectGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                btn_SelectTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                btn_SelectGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                btn_SelectGive.setPaintFlags(btn_SelectTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
            }
            getInterestList();

        }
    };

    //TODO:관심 목록에서 보낸 관심일 때는 프로필 팝업창의 "진행 또는 취소"버튼 비활성화 처리
    //TODO:관심 주고 받았을 때 아이콘 모양이 반대??
    public void getInterestList() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Interest/getInterestList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    arrayList.clear();
                    String str = (giveTalentFlag)?"재능드림":"관심재능";
                    for (int index = 0; index < obj.length(); index++) {

                        JSONObject o = obj.getJSONObject(index);
                        int type = Integer.parseInt(o.getString("TYPE_FLAG"));
                        ListItem target = new ListItem(R.drawable.picure_basic, o.getString("USER_NAME"), o.getString("USER_ID"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), "["+str+"]", o.getString("CREATION_DATE") + " 등록", o.getString("TALENT_ID"),type, o.getString("S_FILE_PATH"));
                        arrayList.add(0,target);

                        try {
                            String dbName = "/accepted.db";
                            SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + dbName, null);

                            sqLiteDatabase.execSQL("INSERT OR REPLACE INTO TB_READED_INTEREST(USER_ID, TALENT_ID) VALUES ('"+ userID + "', " + o.getInt("MATCHED_KEY") + ")");
                            sqLiteDatabase.close();
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }

                    adapter = new Adapter(mContext, arrayList);
                    listView.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("userID", userID);
                params.put("talentFlag", (giveTalentFlag)?"Give":"Take");
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }


}



