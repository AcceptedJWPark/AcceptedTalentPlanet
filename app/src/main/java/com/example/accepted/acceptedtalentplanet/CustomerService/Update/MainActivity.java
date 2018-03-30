package com.example.accepted.acceptedtalentplanet.CustomerService.Update;

import android.content.Context;
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


/**
 * Created by Accepted on 2017-10-31.
 */

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;

    private ArrayList<ListItem> arrayList_Parent = new ArrayList<ListItem>();
    private HashMap<ListItem, ArrayList<String>> arrayList_Child = new HashMap<>();

    private Context mContext;

    private LinearLayout ll_preContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_updateactivity);

        mContext = getBaseContext();

        expandableListView = (ExpandableListView) this.findViewById(R.id.expandableListView_Update);
        getUpdateList();

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


    //TODO : 업데이트 내역을 서버에서 확인하고 서버에 입력해서 답변 다는 방법?
    private void setArrayData() {

        arrayList_Parent.add(new ListItem
                ("Application Service Open",
                        "Talent Planet Service Open : Mar.2018 \n Version : 1.01",
                        "Mar.2018"));


        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("Talent Planet Service Open : Mar.2018 \n Version : 1.01");


        arrayList_Child.put(arrayList_Parent.get(0), arrayList1);

    }

    public void getUpdateList() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Customer/getUpdateList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();
                    JSONArray obj = new JSONArray(response);
                    for (int i = 0; i < obj.length(); i++) {
                        JSONObject o = obj.getJSONObject(i);

                        SimpleDateFormat sdf2 = new SimpleDateFormat("yy.M.dd", Locale.ENGLISH);

                        Date date = new java.sql.Date(Long.parseLong(o.getString("CREATION_DATE")));
                        String dateStr = sdf2.format(date);


                        arrayList_Parent.add(new ListItem(o.getString("NOTICE_TITLE"), o.getString("NOTICE_SUMMARY"), dateStr));
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(o.getString("NOTICE_SUMMARY"));

                        arrayList_Child.put(arrayList_Parent.get(i), arrayList);
                    }

                    expandableListView.setAdapter(new ELVAdapter(mContext, arrayList_Parent, arrayList_Child));

                    ll_preContainer = (LinearLayout) findViewById(R.id.ll_preContainer_Update);
                    ll_preContainer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            finish();
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
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
