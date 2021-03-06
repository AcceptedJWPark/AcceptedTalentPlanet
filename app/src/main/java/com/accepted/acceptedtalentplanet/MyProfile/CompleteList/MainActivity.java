package com.accepted.acceptedtalentplanet.MyProfile.CompleteList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private ListView listView;
    private ArrayList<ListItem> arrayList;
    private Adapter adapter;
    private LinearLayout ll_PreContainer;

    private String networkState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
            setContentView(R.layout.myprofile_completelist_activity);

            ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setImageResource(R.drawable.icon_backbtn);
            ((ImageView)findViewById(R.id.iv_leftBtn_Toolbar)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            ((ImageView)findViewById(R.id.iv_RightBtn_Toolbar)).setVisibility(View.GONE);
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("포인트 사용 내역");


            listView = (ListView) findViewById(R.id.listView_CompleteList_MyProfile);

            getPointHistory();
        }

    }

    public void getPointHistory() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Profile/getPointHistory.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    arrayList = new ArrayList<>();
                    for (int index = 0; index < obj.length(); index++) {
                        JSONObject o = obj.getJSONObject(index);

                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd hh:mm", Locale.ENGLISH);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                        Log.d("date = ", o.getString("LAST_UPDATE_DATE"));
                        ParsePosition pos = new ParsePosition(0);

                        Date date = new java.sql.Date(Long.parseLong(o.getString("LAST_UPDATE_DATE")));
                        String dateStr = sdf2.format(date);

                        int TalentType = (o.getString("TALENT_FLAG").equals("Y"))?1:2;
                        String point = (TalentType == 1)? "-":"+";
                        point = point + o.getString("T_POINT") + "p";
                        arrayList.add(0,new ListItem(TalentType, o.getString("USER_NAME")+ "님과", dateStr, o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), point));


                    }

                    adapter = new Adapter(MainActivity.this, arrayList);
                    listView.setAdapter(adapter);

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
