package com.example.accepted.acceptedtalentplanet.InterestingList;

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
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

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

public class InterestingList_Activity extends AppCompatActivity {

    Context mContext;
    ListView Interesting_List;

    ArrayList<InterestingList_ListItem> InterestingList_ArrayList;
    InterestingList_ListAdapter InterestingList_Adapter;
    boolean giveTalentFlag;

    LinearLayout InterestingList_PreBtn;

    Button InterestingList_GiveCheck;
    Button InterestingList_TakeCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interestinglist_activity);


        mContext = getApplicationContext();
        Interesting_List = (ListView) findViewById(R.id.Interesting_List);
        InterestingList_ArrayList = new ArrayList<>();



        InterestingList_PreBtn = (LinearLayout) findViewById(R.id.InterestingList_PreBtn);
        InterestingList_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        InterestingList_GiveCheck = (Button) findViewById(R.id.InterestingList_GiveCheck);
        InterestingList_TakeCheck = (Button) findViewById(R.id.InterestingList_TakeCheck);

        InterestingList_GiveCheck.setOnClickListener(changeTalentFlag);
        InterestingList_TakeCheck.setOnClickListener(changeTalentFlag);

        giveTalentFlag = getIntent().getStringExtra("TalentFlag").equals("Give");
        if (giveTalentFlag)
        {
            InterestingList_GiveCheck.setFocusableInTouchMode(true);
            InterestingList_GiveCheck.performClick();
        }
        else
        {
            InterestingList_TakeCheck.setFocusableInTouchMode(true);
            InterestingList_TakeCheck.performClick();
        }

        getInterestList();

    }

    Button.OnClickListener changeTalentFlag = new View.OnClickListener(){
        public void onClick(View v){
            giveTalentFlag = (((Button)v).getId() == R.id.InterestingList_GiveCheck) ? true : false;

            if(giveTalentFlag){
                InterestingList_GiveCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                InterestingList_GiveCheck.setPaintFlags(InterestingList_GiveCheck.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                InterestingList_GiveCheck.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                InterestingList_TakeCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                InterestingList_TakeCheck.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                InterestingList_TakeCheck.setPaintFlags(InterestingList_TakeCheck.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
            }else{
                InterestingList_TakeCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_clicked));
                InterestingList_TakeCheck.setPaintFlags(InterestingList_GiveCheck.getPaintFlags() | Paint.FAKE_BOLD_TEXT_FLAG);
                InterestingList_TakeCheck.setTextColor(getResources().getColor(R.color.textcolor_giveortake_clicked));
                InterestingList_GiveCheck.setBackground(ContextCompat.getDrawable(mContext, R.drawable.bgr_giveortake_unclicked));
                InterestingList_GiveCheck.setTextColor(getResources().getColor(R.color.textcolor_giveortake_unclicked));
                InterestingList_GiveCheck.setPaintFlags(InterestingList_TakeCheck.getPaintFlags() &~ Paint.FAKE_BOLD_TEXT_FLAG);
            }
            getInterestList();

        }
    };

    //TODO:관심 목록에서 보낸 관심일 때는 프로필 팝업창의 "진행 또는 취소"버튼 비활성화 처리
    public void getInterestList() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Interest/getInterestList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    InterestingList_ArrayList.clear();
                    String str = (giveTalentFlag)?"재능드림":"관심재능";
                    for (int index = 0; index < obj.length(); index++) {

                        JSONObject o = obj.getJSONObject(index);
                        int type = Integer.parseInt(o.getString("TYPE_FLAG"));
                        InterestingList_ListItem target = new InterestingList_ListItem(R.drawable.testpicture, o.getString("USER_NAME"), o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), "["+str+"]", o.getString("CREATION_DATE") + " 등록", o.getString("TALENT_ID"),type);
                        InterestingList_ArrayList.add(target);
                    }

                    InterestingList_Adapter = new InterestingList_ListAdapter(mContext, InterestingList_ArrayList);
                    Interesting_List.setAdapter(InterestingList_Adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("talentFlag", (giveTalentFlag)?"Give":"Take");
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

}



