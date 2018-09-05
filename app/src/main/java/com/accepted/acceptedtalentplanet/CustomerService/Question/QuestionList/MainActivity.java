package com.accepted.acceptedtalentplanet.CustomerService.Question.QuestionList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.VolleySingleton;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
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
    private Context mContext;

    private LinearLayout ll_PreContainer;

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
            setContentView(R.layout.customerservice_questionlistactivity);

            ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setImageResource(R.drawable.icon_backbtn);
            ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            ((ImageView)findViewById(R.id.iv_RightBtn_Toolbar)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("나의 문의내역");



            expandableListView = (ExpandableListView) this.findViewById(R.id.expandableListView_QuestionList);
            getQuestionList();

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


       /* arrayList_Parent.add(new ListItem_Question(
                "문의 내용 (서버에서 받아오기)",
                "답변 대기/완료",
                "등록 시간"));
        sizeList ++;
        arrayList_Child.add(new ArrayList<ListItem_Answer>());
        arrayList_Child.get(sizeList).add(new ListItem_Answer(
                "문의 내용 (서버에서 받아오기)",
                "답변 내용"));*/


    }

    public void getQuestionList() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Customer/getQuestionList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray obj = new JSONArray(response);

                    arrayList_Parent = new ArrayList<ListItem_Question>();
                    arrayList_Child = new ArrayList<ArrayList<ListItem_Answer>>();

                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        String str = (o.getString("ANSWER_FLAG").equals("N")) ? "답변 대기" : "답변 완료";

                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.M.dd", Locale.ENGLISH);

                        Date date = new java.sql.Date(Long.parseLong(o.getString("CREATION_DATE")));
                        String dateStr = sdf2.format(date);

                        arrayList_Parent.add(new ListItem_Question(o.getString("QUESTION_SUMMARY"), str, "등록일자 : " +dateStr));
                        ArrayList<ListItem_Answer> arrayList = new ArrayList<ListItem_Answer>();
                        if(!o.getString("ANSWER_FLAG").equals("N")) {
                            arrayList.add(new ListItem_Answer(o.getString("QUESTION_SUMMARY"), o.getString("ANSWER_SUMMARY")));
                        }else{
                            arrayList.add(new ListItem_Answer(o.getString("QUESTION_SUMMARY"), "답변이 등록되지 않았습니다."));
                        }

                        arrayList_Child.add(arrayList);
                    }

                    expandableListView.setAdapter(new ELVAdapter(mContext, arrayList_Parent, arrayList_Child));


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


