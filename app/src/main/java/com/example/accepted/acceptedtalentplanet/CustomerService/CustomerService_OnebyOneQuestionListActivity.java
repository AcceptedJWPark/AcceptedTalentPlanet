package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
 * Created by Accepted on 2017-11-03.
 */

public class CustomerService_OnebyOneQuestionListActivity extends AppCompatActivity {


    ExpandableListView ExpandableListViewTest;

    private ArrayList<CustomerService_OnebyOneQuetiontItem> GroupDataList;
    private ArrayList<ArrayList<CustomerService_OnebyOneAnswerItem>> ChildDataList;
    Context mContext;

    LinearLayout CustomerService_OnebyOneQuesitionList_PreBtn;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_onebyonequestionlistactivity);

        mContext = getBaseContext();

        ExpandableListViewTest = (ExpandableListView) this.findViewById(R.id.CustomerService_OnebyOne_ELV);
        getQuestionList();


    }

    private void setArrayData()
    {

        GroupDataList = new ArrayList<CustomerService_OnebyOneQuetiontItem>();
        ChildDataList = new ArrayList<ArrayList<CustomerService_OnebyOneAnswerItem>>();

        int sizeList = 0;

        GroupDataList.add(new CustomerService_OnebyOneQuetiontItem(
                "T.Sharing 클릭시 오류가 발생합니다. 원인이 무엇인지 궁금하며 조속히 해결 바랍니다.",
                "[답변 완료]",
                "2017.11.05 13:42 등록"));
        ChildDataList.add(new ArrayList<CustomerService_OnebyOneAnswerItem>());
        ChildDataList.get(sizeList).add(new CustomerService_OnebyOneAnswerItem(
                "T.Sharing 클릭시 오류가 발생합니다. 원인이 무엇인지 궁금하며 조속히 해결 바랍니다.",
                "해당 문제에 대해 현재 원인 파악 중이며 빠른 시일 내에 해결 조치 하도록 하겠습니다. 불편을 끼쳐드려 죄송합니다."));



       /* GroupDataList.add(new CustomerService_OnebyOneQuetiontItem(
                "문의 내용 (서버에서 받아오기)",
                "답변 대기/완료",
                "등록 시간"));
        sizeList ++;
        ChildDataList.add(new ArrayList<CustomerService_OnebyOneAnswerItem>());
        ChildDataList.get(sizeList).add(new CustomerService_OnebyOneAnswerItem(
                "문의 내용 (서버에서 받아오기)",
                "답변 내용"));*/


    }

    public void getQuestionList() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Customer/getQuestionList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray obj = new JSONArray(response);

                    GroupDataList = new ArrayList<CustomerService_OnebyOneQuetiontItem>();
                    ChildDataList = new ArrayList<ArrayList<CustomerService_OnebyOneAnswerItem>>();

                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        String str = (o.getString("ANSWER_FLAG").equals("N")) ? "[답변 대기]" : "[답변 완료]";
                        GroupDataList.add(new CustomerService_OnebyOneQuetiontItem(o.getString("QUESTION_SUMMARY"), str, o.getString("CREATION_DATE")));
                        ArrayList<CustomerService_OnebyOneAnswerItem> arrayList = new ArrayList<CustomerService_OnebyOneAnswerItem>();
                        if(!o.getString("ANSWER_FLAG").equals("N")) {
                            arrayList.add(new CustomerService_OnebyOneAnswerItem(o.getString("QUESTION_SUMMARY"), o.getString("ANSWER_SUMMARY")));
                        }else{
                            arrayList.add(new CustomerService_OnebyOneAnswerItem(o.getString("QUESTION_SUMMARY"), "답변이 등록되지 않았습니다."));
                        }

                        ChildDataList.add(arrayList);
                    }

                    ExpandableListViewTest.setAdapter(new CustomerService_OnebyOneQuestionExpandableLVAdapter(mContext, GroupDataList, ChildDataList));

                    CustomerService_OnebyOneQuesitionList_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_OnebyOneQuesitionList_PreBtn);
                    CustomerService_OnebyOneQuesitionList_PreBtn.setOnClickListener(new View.OnClickListener() {
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

                params.put("userID", SaveSharedPreference.getUserId(mContext));
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

}


