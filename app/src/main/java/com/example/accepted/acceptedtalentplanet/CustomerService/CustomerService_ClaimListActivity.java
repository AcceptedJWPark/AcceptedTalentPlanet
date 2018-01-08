package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

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
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Adapter;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Item;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Accepted on 2017-11-03.
 */

public class CustomerService_ClaimListActivity extends AppCompatActivity {


    ExpandableListView ExpandableListViewTest;

    private ArrayList<CustomerService_ClaimListQuestionItem> GroupDataList;
    private ArrayList<ArrayList<CustomerService_ClaimListAnswerItem>> ChildDataList;

    LinearLayout CustomerService_ClaimList_PreBtn;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_claimlist_activity);

        ExpandableListViewTest = (ExpandableListView) this.findViewById(R.id.CustomerService_ClaimList_ELV);
        getClaimList();


    }

    private void setArrayData()
    {

        GroupDataList = new ArrayList<CustomerService_ClaimListQuestionItem>();
        ChildDataList = new ArrayList<ArrayList<CustomerService_ClaimListAnswerItem>>();

        int sizeList = 0;

        //TODO: 신고할 때 공유내역이 있으면 신고 내역에 공유 내역이 제목으로, 없으면 신고 내용이 제목으로 되어야 함
        //TODO: 신고하기 버튼에 공유내역 없을 시 Dialog 달아야 함 ("공유 내역과 첨부 파일이 없으면 조치가 어려울 수 있습니다.")
        //TODO: 신고 대상(공유 내역), 신고 유형, 신고 일자, 신고 내용이 신고 내역으로 들어가야 함. 없으면 공유 내역 없으면 신고 대상 "-"

               /*
       GroupDataList.add(new CustomerService_OnebyOneQuetiontItem(
                "문의 내용 (서버에서 받아오기)",
                "답변 대기/완료",
                "등록 시간"));
                sizeList ++;
        ChildDataList.add(new ArrayList<CustomerService_OnebyOneAnswerItem>());
        ChildDataList.get(sizeList).add(new CustomerService_OnebyOneAnswerItem(
                "문의 내용 (서버에서 받아오기)",
                "답변 내용"));
                */

        GroupDataList.add(new CustomerService_ClaimListQuestionItem(
                "이 분이 완료 버튼을 누르지 않아 다음 단계로 진행이 안되네요.",
                "[조치 완료]",
                "2017.11.05 13:42 등록"));
        ChildDataList.add(new ArrayList<CustomerService_ClaimListAnswerItem>());
        ChildDataList.get(sizeList).add(new CustomerService_ClaimListAnswerItem(
                "박종우님과 재능 드림 기타, 기타 연주, 기타 독주 진행의 건",
                "금품 요구",
                "2017.12.11 22:20",
                "이 분이 완료 버튼을 누르지 않아 다음 단계로 진행이 안되네요.",
                "해당 신고에 대해 확인 중에 있습니다. 조속히 처리하도록 하겠습니다."
        ));

    }

    public void getClaimList() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Customer/getClaimList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray obj = new JSONArray(response);

                    GroupDataList = new ArrayList<CustomerService_ClaimListQuestionItem>();
                    ChildDataList = new ArrayList<ArrayList<CustomerService_ClaimListAnswerItem>>();

                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);

                        SimpleDateFormat sdf2 = new SimpleDateFormat("dd MMM, yyyy", Locale.ENGLISH);

                        Date date = new java.sql.Date(Long.parseLong(o.getString("CREATION_DATE")));
                        String dateStr = sdf2.format(date);
                        String answerFlag = (o.getString("ANSWER_FLAG").equals("Y"))?"[조치 완료]" : "[조치 중]";

                        GroupDataList.add(new CustomerService_ClaimListQuestionItem(o.getString("CLAIM_SUMMARY"), answerFlag, dateStr));
                        ArrayList<CustomerService_ClaimListAnswerItem> arrayList = new ArrayList<CustomerService_ClaimListAnswerItem>();
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
                            arrayList.add(new CustomerService_ClaimListAnswerItem(str, claimType, dateStr, o.getString("CLAIM_SUMMARY"), o.getString("ANSWER_SUMMARY")));
                        }else{
                            arrayList.add(new CustomerService_ClaimListAnswerItem(str, claimType, dateStr, o.getString("CLAIM_SUMMARY"),"해당 신고에 대해 확인 중에 있습니다. 조속히 처리하도록 하겠습니다."));
                        }

                        ChildDataList.add(arrayList);
                    }

                    ExpandableListViewTest.setAdapter(new CustomerService_ClaimListExpandableLVAdapter(getBaseContext(), GroupDataList, ChildDataList));

                    CustomerService_ClaimList_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_ClaimList_PreBtn);
                    CustomerService_ClaimList_PreBtn.setOnClickListener(new View.OnClickListener() {
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

                params.put("userID", SaveSharedPreference.getUserId(getBaseContext()));
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

}


