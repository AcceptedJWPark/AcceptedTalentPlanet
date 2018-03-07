package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.content.Context;
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
import com.example.accepted.acceptedtalentplanet.FriendList.FriendList_Adapter;
import com.example.accepted.acceptedtalentplanet.FriendList.FriendList_Item;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

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
 * Created by Accepted on 2017-11-01.
 */

public class CustomerService_NoticeActivity extends AppCompatActivity {

    ExpandableListView ExpandableListViewTest;
    Context mContext;

    private ArrayList<CustomerService_NoticeListItem> arrayGroup = new ArrayList<CustomerService_NoticeListItem>();
    private HashMap<CustomerService_NoticeListItem, ArrayList<String>> arrayChild = new HashMap<>();

    LinearLayout CustomerService_Notice_PreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_noticeactivity);
        mContext = getApplicationContext();


        ExpandableListViewTest = (ExpandableListView) this.findViewById(R.id.CustomerService_Notice_ELV);
        getNoticeList();


    }


    public void getNoticeList() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Customer/getNoticeList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
                    JSONArray obj = new JSONArray(response);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);

                        SimpleDateFormat sdf2 = new SimpleDateFormat("MMM, yyyy", Locale.ENGLISH);

                        Date date = new java.sql.Date(Long.parseLong(o.getString("CREATION_DATE")));
                        String dateStr = sdf2.format(date);

                        arrayGroup.add(new CustomerService_NoticeListItem(o.getString("NOTICE_TITLE"), o.getString("NOTICE_SUMMARY"), dateStr));
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(o.getString("NOTICE_SUMMARY"));

                        arrayChild.put(arrayGroup.get(i), arrayList);
                    }

                    ExpandableListViewTest.setAdapter(new CustomerService_NoticeExpandableLVAdapter(mContext, arrayGroup, arrayChild));

                    CustomerService_Notice_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_Notice_PreBtn);
                    CustomerService_Notice_PreBtn.setOnClickListener(new View.OnClickListener() {
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
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }
    
}
