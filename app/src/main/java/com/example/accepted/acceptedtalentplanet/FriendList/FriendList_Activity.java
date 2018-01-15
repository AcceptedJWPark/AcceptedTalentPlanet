package com.example.accepted.acceptedtalentplanet.FriendList;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-09-29.
 */

public class FriendList_Activity extends AppCompatActivity {

    Context mContext;
    ListView Friendlist_ListView_Give;
    ListView Friendlist_ListView_Take;
    ArrayList<FriendList_Item> Friendlist_original;
    ArrayList<FriendList_Item> Friendlist_arrayList;
    FriendList_Adapter FriendList_Adapter;
    LinearLayout FriendList_PreBtn;

    Button Friendlist_ShowGive;
    Button Friendlist_ShowTake;
    ArrayList<String> friendList;

    boolean talentFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendlist_activity);

        mContext = getApplicationContext();

        talentFlag = true;  //getIntent().getStringExtra("talentFlag").equals("Y")

        friendList = SaveSharedPreference.getFriendList(mContext);

        if(friendList.size() > 0)
            getFriendList();

        Friendlist_ListView_Give = (ListView) findViewById(R.id.FriendList_ListView_GIVE);
        Friendlist_ListView_Take = (ListView) findViewById(R.id.FriendList_ListView_TAKE);





        Friendlist_ShowGive = (Button) findViewById(R.id.FriendList_ShowGive);
        Friendlist_ShowGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friendlist_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                Friendlist_ShowGive.setPaintFlags(Friendlist_ShowGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                Friendlist_ShowGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                Friendlist_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                Friendlist_ShowTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                Friendlist_ShowTake.setPaintFlags(Friendlist_ShowGive.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
                Friendlist_ListView_Give.setVisibility(View.VISIBLE);
                Friendlist_ListView_Take.setVisibility(View.GONE);

                if(friendList.size() > 0) {

                    Friendlist_arrayList.clear();

                    for (FriendList_Item item : Friendlist_original) {
                        Log.d("TaletTypeCode", String.valueOf(item.getTalentType_CODE()));
                        if (item.getTalentType_CODE() == 1)
                            Friendlist_arrayList.add(item);
                    }

                    FriendList_Adapter = new FriendList_Adapter(mContext, Friendlist_arrayList);
                    Friendlist_ListView_Give.setAdapter(FriendList_Adapter);
                }

            }
        });
        Friendlist_ShowTake = (Button) findViewById(R.id.FriendList_ShowTake);
        Friendlist_ShowTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Friendlist_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                Friendlist_ShowTake.setPaintFlags(Friendlist_ShowGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                Friendlist_ShowTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                Friendlist_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                Friendlist_ShowGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                Friendlist_ShowGive.setPaintFlags(Friendlist_ShowGive.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
                Friendlist_ListView_Take.setVisibility(View.VISIBLE);
                Friendlist_ListView_Give.setVisibility(View.GONE);
                if(friendList.size() > 0) {
                    Friendlist_arrayList.clear();

                    for (FriendList_Item item : Friendlist_original) {
                        Log.d("TaletTypeCode", String.valueOf(item.getTalentType_CODE()));
                        if (item.getTalentType_CODE() == 2)
                            Friendlist_arrayList.add(item);
                    }

                    FriendList_Adapter = new FriendList_Adapter(mContext, Friendlist_arrayList);
                    Friendlist_ListView_Take.setAdapter(FriendList_Adapter);
                }
            }
        });

        FriendList_PreBtn = (LinearLayout) findViewById(R.id.FriendList_PreBtn);
        FriendList_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getFriendList() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "FriendList/getFriendList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    Friendlist_arrayList = new ArrayList<>();
                    Friendlist_original = new ArrayList<>();
                    JSONArray obj = new JSONArray(response);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
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
                        int talentCode = (o.getString("TALENT_FLAG").equals("Y"))?1 : 2;
                        FriendList_Item target = new FriendList_Item(R.drawable.textpicture, o.getString("USER_NAME"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), talentConditionCode, talentCode, o.getString("TALENT_ID"));
                        Friendlist_original.add(target);
                        if(talentFlag) {
                            if (talentCode == 1) {
                                Friendlist_arrayList.add(target);
                            }
                        }else{
                            if (talentCode == 2) {
                                Friendlist_arrayList.add(target);
                            }
                        }
                    }

                    FriendList_Adapter = new FriendList_Adapter(mContext, Friendlist_arrayList);
                    Friendlist_ListView_Give.setAdapter(FriendList_Adapter);


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
                StringBuilder sb = new StringBuilder();
                for(String s : friendList){
                    sb.append(s);
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



