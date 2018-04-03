package com.accepted.acceptedtalentplanet.CustomerService.Notice;

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
import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.VolleySingleton;

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
 * Created by Accepted on 2017-11-01.
 */

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private Context mContext;

    private ArrayList<ListItem> arrayList_Parent = new ArrayList<ListItem>();
    private HashMap<ListItem, ArrayList<String>> arrayList_Child = new HashMap<>();

    private LinearLayout ll_PreContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_noticeactivity);
        mContext = getApplicationContext();


        expandableListView = (ExpandableListView) this.findViewById(R.id.expandableListView_Notice);
        getNoticeList();

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

                        SimpleDateFormat sdf2 = new SimpleDateFormat("yy.MM.dd", Locale.ENGLISH);

                        Date date = new java.sql.Date(Long.parseLong(o.getString("CREATION_DATE")));
                        String dateStr = sdf2.format(date);

                        arrayList_Parent.add(new ListItem(o.getString("NOTICE_TITLE"), o.getString("NOTICE_SUMMARY"), dateStr));
                        ArrayList<String> arrayList = new ArrayList<>();
                        arrayList.add(o.getString("NOTICE_SUMMARY"));

                        arrayList_Child.put(MainActivity.this.arrayList_Parent.get(i), arrayList);
                    }

                    expandableListView.setAdapter(new ELVAdapter(mContext, arrayList_Parent, arrayList_Child));

                    ll_PreContainer = (LinearLayout) findViewById(R.id.ll_PreContainer_Notice);
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
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

    
}
