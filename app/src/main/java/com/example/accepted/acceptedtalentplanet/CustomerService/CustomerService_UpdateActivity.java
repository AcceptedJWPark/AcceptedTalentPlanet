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
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

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
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_UpdateActivity extends AppCompatActivity {

    ExpandableListView ExpandableListViewTest;

    private ArrayList<CustomerService_UpdateListItem> arrayGroup = new ArrayList<CustomerService_UpdateListItem>();
    private HashMap<CustomerService_UpdateListItem, ArrayList<String>> arrayChild = new HashMap<>();

    Context mContext;

    LinearLayout CustomerService_Update_PreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_updateactivity);

        mContext = getBaseContext();

        ExpandableListViewTest = (ExpandableListView) this.findViewById(R.id.CustomerService_Update_ELV);
        getUpdateList();


    }


    //TODO : 업데이트 내역을 서버에서 확인하고 서버에 입력해서 답변 다는 방법?
    private void setArrayData()
    {

        arrayGroup.add(new CustomerService_UpdateListItem
                ("Application Service Open",
                "Talent Planet Service Open : Mar.2018 \n Version : 1.01",
                "Mar.2018"));


        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("Talent Planet Service Open : Mar.2018 \n Version : 1.01");


        arrayChild.put(arrayGroup.get(0),arrayList1);

    }

    public void getUpdateList() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Customer/getUpdateList.do", new Response.Listener<String>() {
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


                        arrayGroup.add(new CustomerService_UpdateListItem(o.getString("NOTICE_TITLE"), o.getString("NOTICE_SUMMARY"), dateStr));
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(o.getString("NOTICE_SUMMARY"));

                        arrayChild.put(arrayGroup.get(i), arrayList);
                    }

                    ExpandableListViewTest.setAdapter(new CustomerService_UpdateExpandableLVAdapter(mContext, arrayGroup, arrayChild));

                    CustomerService_Update_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_Update_PreBtn);
                    CustomerService_Update_PreBtn.setOnClickListener(new View.OnClickListener() {
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
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

    }
