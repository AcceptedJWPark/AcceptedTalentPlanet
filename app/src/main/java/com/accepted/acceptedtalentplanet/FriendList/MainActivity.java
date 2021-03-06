package com.accepted.acceptedtalentplanet.FriendList;

import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.Friend;
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
 * Created by Accepted on 2017-09-29.
 */

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ListView listView_Give;
    private ListView listView_Take;

    private ArrayList<ListItem> arrayList_Original;
    private ArrayList<Friend> arrayList_Friend;
    private ArrayList<ListItem> arrayList;
    private Adapter adapter;

    private LinearLayout ll_PreContainer;
    private Button btn_SelectGive;
    private Button btn_SelectTake;

    private boolean talentFlag;

    private String networkState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();

        ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setImageResource(R.drawable.icon_backbtn);
        ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView)findViewById(R.id.iv_RightBtn_Toolbar)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("친구 목록");


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
            setContentView(R.layout.friendlist_activity);

            talentFlag = true;  //getIntent().getStringExtra("talentFlag").equals("Y")

            arrayList_Friend = SaveSharedPreference.getFriendList(mContext);

            if (arrayList_Friend.size() > 0)
                getFriendInfo();

            listView_Give = (ListView) findViewById(R.id.listView_Give_FriendList);
            listView_Take = (ListView) findViewById(R.id.listView_Take_FriendList);


            btn_SelectGive = (Button) findViewById(R.id.btn_SelectGive_FriendList);
            btn_SelectGive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= 16) {
                        btn_SelectGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                        btn_SelectTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                    } else {
                        btn_SelectGive.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                        btn_SelectTake.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                    }
                    btn_SelectGive.setPaintFlags(btn_SelectGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                    btn_SelectGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                    btn_SelectTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                    btn_SelectTake.setPaintFlags(btn_SelectGive.getPaintFlags() & ~Paint.FAKE_BOLD_TEXT_FLAG);
                    listView_Give.setVisibility(View.VISIBLE);
                    listView_Take.setVisibility(View.GONE);

                    if (arrayList_Friend.size() > 0) {

                        arrayList.clear();

                        for (ListItem item : arrayList_Original) {
                            Log.d("TaletTypeCode", String.valueOf(item.getTalentType()));
                            if (item.getTalentType() == 1)
                                arrayList.add(item);
                        }


                    }
                    adapter = new Adapter(mContext, arrayList);
                    listView_Give.setAdapter(adapter);
                    talentFlag = true;

                }
            });
            btn_SelectTake = (Button) findViewById(R.id.btn_SelectTake_FriendList);
            btn_SelectTake.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= 16) {
                        btn_SelectTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                        btn_SelectGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                    } else {
                        btn_SelectTake.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                        btn_SelectGive.setBackgroundDrawable(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                    }
                    btn_SelectTake.setPaintFlags(btn_SelectGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                    btn_SelectTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                    btn_SelectGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                    btn_SelectGive.setPaintFlags(btn_SelectGive.getPaintFlags() & ~Paint.FAKE_BOLD_TEXT_FLAG);
                    listView_Take.setVisibility(View.VISIBLE);
                    listView_Give.setVisibility(View.GONE);
                    if (arrayList_Friend.size() > 0) {
                        arrayList.clear();

                        for (ListItem item : arrayList_Original) {
                            Log.d("TaletTypeCode", String.valueOf(item.getTalentType()));
                            if (item.getTalentType() == 2)
                                arrayList.add(item);
                        }

                    }
                    adapter = new Adapter(mContext, arrayList);
                    listView_Take.setAdapter(adapter);
                    talentFlag = false;
                }
            });


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
            arrayList_Friend = SaveSharedPreference.getFriendList(mContext);

            if (arrayList_Friend.size() > 0)
                getFriendInfo();
            else {
                arrayList = new ArrayList<>();
                arrayList_Original = new ArrayList<>();
                adapter = new Adapter(mContext, arrayList);
                if (listView_Give.getVisibility() == View.VISIBLE) {
                    Log.d("list View Friend", "C");
                    listView_Give.setAdapter(adapter);
                } else {
                    Log.d("list View Friend", "D");
                    listView_Take.setAdapter(adapter);
                }
            }
        }
    }

    public void getFriendInfo() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "FriendList/getFriendInfo.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    arrayList = new ArrayList<>();
                    arrayList_Original = new ArrayList<>();
                    JSONArray obj = new JSONArray(response);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        Log.d("friend = ", o.toString());
                        String statusFlag = o.getString("STATUS_FLAG");
                        int talentConditionCode;
                        switch(statusFlag){
                            case "P":
                                talentConditionCode = 1;
                                break;
                            case "M":
                                talentConditionCode = 2;
                                break;
                            case "C":
                                talentConditionCode = 3;
                                break;
                            default:
                                talentConditionCode = 1;
                        }
                        int talentCode = (o.getString("TALENT_FLAG").equals("Y"))?2 : 1;
                        ListItem target = new ListItem(R.drawable.picure_basic, o.getString("USER_NAME"), o.getString("USER_ID"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), talentConditionCode, talentCode, o.getString("TALENT_ID"), o.getString("S_FILE_PATH"));
                        arrayList_Original.add(target);
                        if(talentFlag) {
                            if (talentCode == 1) {
                                arrayList.add(target);
                            }
                        }else{
                            if (talentCode == 2) {
                                arrayList.add(target);
                            }
                        }
                    }

                    adapter = new Adapter(mContext, arrayList);
                    if(listView_Give.getVisibility() == View.VISIBLE) {
                        Log.d("list View Friend", "A");
                        listView_Give.setAdapter(adapter);
                    }else{
                        Log.d("list View Friend", "B");
                        listView_Take.setAdapter(adapter);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < arrayList_Friend.size(); i++){
                    Friend f = arrayList_Friend.get(i);
                    sb.append(f.getUserID()).append("\n").append(f.getPartnerTalentType());
                    sb.append("\t");
                }
                Log.d("Array = ", sb.toString());
                params.put("userArray", sb.toString());
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }




}



