package com.example.accepted.acceptedtalentplanet.SharingList;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Accepted on 2017-09-29.
 */

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ListView listView_Give;
    private ListView listView_Take;
    private ArrayList<ListItem> arrayList;
    private ArrayList<ListItem> arrayList_Original;
    private Adapter adapter;
    private LinearLayout ll_PreContainer;

    private boolean isGiveTalent = true;

    private Button btn_SelectGive;
    private Button btn_SelectTake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharinglist_activity);

        mContext = getApplicationContext();


        listView_Give = (ListView) findViewById(R.id.listView_Give_SharingList);
        listView_Take = (ListView) findViewById(R.id.listView_Take_SharingList);


        getSharingList();

        btn_SelectGive = (Button) findViewById(R.id.btn_SelectGive_SharingList);
        btn_SelectGive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btn_SelectGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                btn_SelectGive.setPaintFlags(btn_SelectGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                btn_SelectGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                btn_SelectTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                btn_SelectTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                btn_SelectTake.setPaintFlags(btn_SelectTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);

                arrayList.clear();
                for(ListItem item : arrayList_Original){
                    if(item.TalentType_CODE() == 1)
                        arrayList.add(item);
                }
                adapter = new Adapter(MainActivity.this, arrayList);
                listView_Give.setAdapter(adapter);

                listView_Give.setVisibility(View.VISIBLE);
                listView_Take.setVisibility(View.GONE);
            }
        });
        btn_SelectTake = (Button) findViewById(R.id.btn_SelectTake_SharingList);
        btn_SelectTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_SelectTake.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                btn_SelectTake.setPaintFlags(btn_SelectGive.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                btn_SelectTake.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                btn_SelectGive.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                btn_SelectGive.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                btn_SelectGive.setPaintFlags(btn_SelectTake.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);

                arrayList.clear();
                for(ListItem item : arrayList_Original){
                    if(item.TalentType_CODE() == 2)
                        arrayList.add(item);
                }
                adapter = new Adapter(MainActivity.this, arrayList);
                listView_Take.setAdapter(adapter);

                listView_Take.setVisibility(View.VISIBLE);
                listView_Give.setVisibility(View.GONE);
            }
        });

        ll_PreContainer = (LinearLayout) findViewById(R.id.ll_PreContainer_SharingList);
        ll_PreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onResume(){
        super.onResume();
        //adapter.notifyDataSetChanged();
    }

    public void getSharingList() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "SharingList/getSharingList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    arrayList_Original = new ArrayList<>();
                    arrayList = new ArrayList<>();
                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);

                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd hh:mm", Locale.ENGLISH);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Log.d("date = ", o.getString("CREATION_DATE"));
                        ParsePosition pos = new ParsePosition(0);

                        Date date = new java.sql.Date(Long.parseLong(o.getString("CREATION_DATE")));
                        String dateStr = sdf2.format(date);

                        int TalentType = (o.getString("TALENT_FLAG").equals("Y"))?2: 1;

                        ListItem target = new ListItem(o.getString("USER_NAME") + "님과", Integer.parseInt(o.getString("STATUS")), dateStr, o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), TalentType, o.getString("TALENT_ID"), o.getString("MY_TALENT_ID"));
                        arrayList_Original.add(target);
                        if(isGiveTalent){
                            if(TalentType == 1){
                                arrayList.add(target);
                            }
                        }else{
                            if(TalentType == 2){
                                arrayList.add(target);
                            }
                        }

                    }
                    adapter = new Adapter(MainActivity.this, arrayList);
                    listView_Give.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch(Exception e){
                    e.printStackTrace();
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



}



