package com.example.accepted.acceptedtalentplanet.CustomerService.Claim.ClaimList;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.MyFirebaseMessagingService.countAlarmPush_Claim;

/**
 * Created by Accepted on 2017-11-03.
 */

public class MainActivity extends AppCompatActivity {


    private ExpandableListView expandableListView;
    private ArrayList<ListItem_Question> arrayList_Parent;
    private ArrayList<ArrayList<ListItem_Answer>> arrayList_Child;
    private LinearLayout ll_PreContainer;
    private Context mContext;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_claimlist_activity);

        countAlarmPush_Claim = 0;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);

        mContext = getApplicationContext();

        ll_PreContainer = (LinearLayout) findViewById(R.id.ll_PreContainer_ClaimList);
        ll_PreContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        expandableListView = (ExpandableListView) this.findViewById(R.id.expandableListView_ClaimList);
        getClaimList();

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            int lastClickedPosition = 0;
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean isExpand = (!expandableListView.isGroupExpanded(groupPosition));
                expandableListView.collapseGroup(lastClickedPosition);
                if(isExpand)
                {
                    expandableListView.expandGroup(groupPosition);
                }lastClickedPosition = groupPosition;
                return true;
            }
        });


    }

    private void setArrayData()
    {

        arrayList_Parent = new ArrayList<ListItem_Question>();
        arrayList_Child = new ArrayList<ArrayList<ListItem_Answer>>();

        int sizeList = 0;


        arrayList_Parent.add(new ListItem_Question(
                "이 분이 완료 버튼을 누르지 않아 다음 단계로 진행이 안되네요.",
                "[조치 완료]",
                "2017.11.05 13:42 등록"));
        arrayList_Child.add(new ArrayList<ListItem_Answer>());
        arrayList_Child.get(sizeList).add(new ListItem_Answer(
                "박종우님과 재능 드림 기타, 기타 연주, 기타 독주 진행의 건",
                "금품 요구",
                "2017.12.11 22:20",
                "이 분이 완료 버튼을 누르지 않아 다음 단계로 진행이 안되네요.",
                "해당 신고에 대해 확인 중에 있습니다. 조속히 처리하도록 하겠습니다."
        ));

    }

    public void getClaimList() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Customer/getClaimList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray obj = new JSONArray(response);

                    arrayList_Parent = new ArrayList<ListItem_Question>();
                    arrayList_Child = new ArrayList<ArrayList<ListItem_Answer>>();

                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);

                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);

                        Date date = new java.sql.Date(Long.parseLong(o.getString("CREATION_DATE")));
                        String dateStr = sdf2.format(date);
                        String answerFlag = (o.getString("ANSWER_FLAG").equals("Y"))?"[조치 완료]" : "[조치 중]";

                        arrayList_Parent.add(new ListItem_Question(o.getString("CLAIM_SUMMARY"), answerFlag, dateStr));
                        ArrayList<ListItem_Answer> arrayList = new ArrayList<ListItem_Answer>();
                        String talentType = (o.getString("TALENT_FLAG").equals("Y"))?"재능 드림" : "관심 재능";
                        String str = o.getString("PARTNER_NAME")+ "님과 " + talentType +" "+ o.getString("TALENT_KEYWORD1") + ", " + o.getString("TALENT_KEYWORD2") + ", " +o.getString("TALENT_KEYWORD3") + " 진행의 건";
                        String claimType;
                        switch (o.getString("CLAIM_TYPE")){
                            case "1":
                                claimType = "금품 요구";
                                break;
                            case "2":
                                claimType = "폭언 및 욕설";
                                break;
                            case "3":
                                claimType = "No-Show";
                                break;
                            case "4":
                                claimType = "허위 광고";
                                break;
                            default:
                                claimType = "기타";
                        }

                        if(!o.getString("ANSWER_FLAG").equals("N")) {
                            arrayList.add(new ListItem_Answer(str, claimType, dateStr, o.getString("CLAIM_SUMMARY"), o.getString("ANSWER_SUMMARY")));
                        }else{
                            arrayList.add(new ListItem_Answer(str, claimType, dateStr, o.getString("CLAIM_SUMMARY"),"해당 신고에 대해 확인 중에 있습니다. 조속히 처리하도록 하겠습니다."));
                        }

                        arrayList_Child.add(arrayList);
                    }

                    expandableListView.setAdapter(new ELVAdapter(getBaseContext(), arrayList_Parent, arrayList_Child));

                    ll_PreContainer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });


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

                params.put("userID", SaveSharedPreference.getUserId(getBaseContext()));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

}


