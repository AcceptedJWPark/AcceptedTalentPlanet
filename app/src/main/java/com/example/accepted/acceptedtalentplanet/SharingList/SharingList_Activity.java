package com.example.accepted.acceptedtalentplanet.SharingList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
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
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_ListAdapter;
import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_ListItem;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2017-09-29.
 */

public class SharingList_Activity extends AppCompatActivity {

    Context mContext;
    public ListView SharingList_ListView_Give;
    ListView SharingList_ListView_Take;
    ArrayList<SharingList_Item> SharingList_arrayList;
    ArrayList<SharingList_Item> SharingList_arrayList_Origin;
    public SharingList_Adapter SharingList_Adapter1;
    SharingList_Adapter SharingList_Adapter2;
    LinearLayout SharingList_PreBtn;

    boolean isGiveTalent = true;

    Button SharingList_ShowGive;
    Button SharingList_ShowTake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharinglist_activity);

        mContext = getApplicationContext();


        SharingList_ListView_Give = (ListView) findViewById(R.id.SharingList_ListView_Give);
        SharingList_ListView_Take = (ListView) findViewById(R.id.SharingList_ListView_Take);

//        SharingList_arrayList_Give = new ArrayList<>();
//        SharingList_Adapter1 = new SharingList_Adapter(SharingList_Activity.this, SharingList_arrayList_Give);
//        SharingList_ListView_Give.setAdapter(SharingList_Adapter1);
//
//        SharingList_arrayList_Take = new ArrayList<>();
//        SharingList_Adapter2 = new SharingList_Adapter(SharingList_Activity.this, SharingList_arrayList_Take);
//        SharingList_ListView_Take.setAdapter(SharingList_Adapter2);

        //TODO:한 개의 어레이 리스트와 어뎁터로 두 개의 리스트뷰에 적용 시킬 수는 없나?
//        SharingList_arrayList_Give.add(new SharingList_Item("박종우"+"님과 ",3,"2017.12.02 04:14","기타","기타 연주","기타 독학",1, "1"));
//        SharingList_arrayList_Give.add(new SharingList_Item("민권홍"+"님과 ",4,"2017.12.03 11:22","스타크래프트","스타 리마스터","스타크래프트 파이썬",1, "2"));
//        SharingList_arrayList_Give.add(new SharingList_Item("배대명"+"님과 ",4,"2017.12.04 13:01","농구","길거리 농구","농구 드리블",1, "3"));
//        SharingList_arrayList_Give.add(new SharingList_Item("우승제"+"님과 ",5,"2017.12.04 19:22","축구","축구 드리블","축구 프리킥",1, "2"));
//        SharingList_arrayList_Give.add(new SharingList_Item("우승제"+"님의 ",1,"2017.12.07 19:22","축구","축구 드리블","축구 프리킥",1, "1"));
//        SharingList_arrayList_Give.add(new SharingList_Item("우승제"+"님의 ",2,"2017.12.09 19:22","수학","수능 수학","미적분",1, "3"));
//
//        SharingList_arrayList_Take.add(new SharingList_Item("유성택"+"님과 ",1,"2017.12.01 14:24","노래","보컬","오디션",2, "1"));
//        SharingList_arrayList_Take.add(new SharingList_Item("유성택"+"님과 ",2,"2017.12.03 14:24","연기","뮤지컬","공연",2, "2"));
//        SharingList_arrayList_Take.add(new SharingList_Item("유성택"+"님과 ",3,"2017.12.03 14:24","드럼","드럼 연주","드럼 독학",2, "3"));
//        SharingList_arrayList_Take.add(new SharingList_Item("김진만"+"님과 ",4,"2017.12.04 14:24","영어","영어 말하기","영어 스피킹",2, "4"));
//        SharingList_arrayList_Take.add(new SharingList_Item("임하슬람"+"님과 ",4,"2017.12.02 14:24","수학1","미분과 적분","미적분",2, "5"));
//        SharingList_arrayList_Take.add(new SharingList_Item("윤택"+"님과 ",5,"2017.12.02 14:24","공무원 시험","공무원 7급","공무원 9급",2, "6"));
//

        getSharingList();

        SharingList_ShowGive = (Button) findViewById(R.id.SharingList_ShowGive);
        SharingList_ShowGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharingList_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                SharingList_ShowGive.setTextColor(getResources().getColor(R.color.textColor));
                SharingList_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                SharingList_ShowTake.setTextColor(Color.parseColor("#d2d2d2"));

                SharingList_arrayList.clear();
                for(SharingList_Item item : SharingList_arrayList_Origin){
                    if(item.TalentType_CODE() == 1)
                        SharingList_arrayList.add(item);
                }
                SharingList_Adapter1 = new SharingList_Adapter(SharingList_Activity.this, SharingList_arrayList);
                SharingList_ListView_Give.setAdapter(SharingList_Adapter1);

                SharingList_ListView_Give.setVisibility(View.VISIBLE);
                SharingList_ListView_Take.setVisibility(View.GONE);
            }
        });
        SharingList_ShowTake = (Button) findViewById(R.id.SharingList_ShowTake);
        SharingList_ShowTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharingList_ShowTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_graybackground));
                SharingList_ShowTake.setTextColor(getResources().getColor(R.color.textColor));
                SharingList_ShowGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.small_button_whitebackground));
                SharingList_ShowGive.setTextColor(Color.parseColor("#d2d2d2"));

                SharingList_arrayList.clear();
                for(SharingList_Item item : SharingList_arrayList_Origin){
                    if(item.TalentType_CODE() == 2)
                        SharingList_arrayList.add(item);
                }
                SharingList_Adapter1 = new SharingList_Adapter(SharingList_Activity.this, SharingList_arrayList);
                SharingList_ListView_Take.setAdapter(SharingList_Adapter1);

                SharingList_ListView_Take.setVisibility(View.VISIBLE);
                SharingList_ListView_Give.setVisibility(View.GONE);
            }
        });

        SharingList_PreBtn = (LinearLayout) findViewById(R.id.SharingList_PreBtn);
        SharingList_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        //SharingList_Adapter1.notifyDataSetChanged();
    }

    public void getSharingList() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "SharingList/getSharingList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    SharingList_arrayList_Origin = new ArrayList<>();
                    SharingList_arrayList = new ArrayList<>();
                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);

                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd hh:mm", Locale.ENGLISH);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        ParsePosition pos = new ParsePosition(0);
                        Date date = sdf.parse(o.getString("CREATION_DATE"), pos);
                        String dateStr = sdf2.format(date);

                        int TalentType = (o.getString("TALENT_FLAG").equals("Y"))?1: 2;

                        SharingList_Item target = new SharingList_Item(o.getString("USER_NAME") + "님과", Integer.parseInt(o.getString("STATUS")), dateStr, o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), TalentType, o.getString("TALENT_ID"));
                        SharingList_arrayList_Origin.add(target);
                        if(isGiveTalent){
                            if(TalentType == 1){
                                SharingList_arrayList.add(target);
                            }
                        }else{
                            if(TalentType == 2){
                                SharingList_arrayList.add(target);
                            }
                        }

                    }
                    SharingList_Adapter1 = new SharingList_Adapter(SharingList_Activity.this, SharingList_arrayList);
                    SharingList_ListView_Give.setAdapter(SharingList_Adapter1);

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

                        JSONArray obj = new JSONArray(res);
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



