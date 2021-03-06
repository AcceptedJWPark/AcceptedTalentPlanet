package com.accepted.acceptedtalentplanet.CustomerService.Claim.ClaimList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * Created by Accepted on 2017-11-03.
 */

public class MainActivity extends AppCompatActivity {


    private ExpandableListView expandableListView;
    private ArrayList<ListItem_Question> arrayList_Parent;
    private ArrayList<ArrayList<ListItem_Answer>> arrayList_Child;
    private LinearLayout ll_PreContainer;
    private Context mContext;

    private String networkState;

    protected void onCreate(Bundle savedInstanceState)
    {
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
            setContentView(R.layout.customerservice_claimlist_activity);

            ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setImageResource(R.drawable.icon_backbtn);
            ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            ((ImageView)findViewById(R.id.iv_RightBtn_Toolbar)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("신고내역");

            expandableListView = (ExpandableListView) this.findViewById(R.id.expandableListView_ClaimList);
            getClaimList();

            expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
                int lastClickedPosition = 0;

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                    boolean isExpand = (!expandableListView.isGroupExpanded(groupPosition));
                    expandableListView.collapseGroup(lastClickedPosition);
                    if (isExpand) {
                        expandableListView.expandGroup(groupPosition);
                    }
                    lastClickedPosition = groupPosition;
                    return true;
                }
            });
        }
    }

    private void setArrayData()
    {

        arrayList_Parent = new ArrayList<ListItem_Question>();
        arrayList_Child = new ArrayList<ArrayList<ListItem_Answer>>();


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

                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.M.dd", Locale.ENGLISH);

                        Date date = new java.sql.Date(Long.parseLong(o.getString("CREATION_DATE")));
                        String dateStr = sdf2.format(date);
                        String answerFlag = (o.getString("ANSWER_FLAG").equals("Y"))?"조치 완료" : "조치 중";

                        arrayList_Parent.add(new ListItem_Question(o.getString("CLAIM_SUMMARY"), answerFlag, "등록일자 : " +dateStr));
                        ArrayList<ListItem_Answer> arrayList = new ArrayList<ListItem_Answer>();


                        String talentType = null;
                        String str = null;
                        if(o.getString("TALENT_FLAG").equals("Y"))
                        {
                            talentType="재능 드림";
                            str = o.getString("PARTNER_NAME")+ "님과 " + talentType +" "+ o.getString("TALENT_KEYWORD1") + ", " + o.getString("TALENT_KEYWORD2") + ", " +o.getString("TALENT_KEYWORD3") + " 진행의 건";
                        }else if(o.getString("TALENT_FLAG").equals("C"))
                        {
                            talentType="관심 재능";
                            str = o.getString("PARTNER_NAME")+ "님과 " + talentType +" "+ o.getString("TALENT_KEYWORD1") + ", " + o.getString("TALENT_KEYWORD2") + ", " +o.getString("TALENT_KEYWORD3") + " 진행의 건";

                        }else
                        {
                            talentType="등록 안됨";
                            str = "등록 안됨";
                        }

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
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();

                params.put("userID", SaveSharedPreference.getUserId(getBaseContext()));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

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
        }
    }

}


