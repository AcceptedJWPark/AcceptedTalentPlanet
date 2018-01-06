package com.example.accepted.acceptedtalentplanet.MyProfile;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.accepted.acceptedtalentplanet.MyProfileData;
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

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MyProfile_CompleteList_Activity extends AppCompatActivity {

    Context mContext;
    ListView CompleteList_ListView;
    ArrayList<MyProfile_CompleteList_Item> CompleteList_arrayList;
    MyProfile_CompleteList_Adapter CompleteList_Adapter;
    LinearLayout CompleteList_PreBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile_completelist_activity);

        mContext = getApplicationContext();

        CompleteList_ListView = (ListView) findViewById(R.id.CompleteList_ListView);

        getPointHistory();
        CompleteList_PreBtn = (LinearLayout) findViewById(R.id.CompleteList_PreBtn);
        CompleteList_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void getPointHistory() {
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Profile/getPointHistory.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray obj = new JSONArray(response);
                    CompleteList_arrayList = new ArrayList<>();
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
                        CompleteList_arrayList.add(new MyProfile_CompleteList_Item(TalentType, o.getString("USER_NAME")+ "님과", dateStr, o.getString("TALENT_KEYWORD1"), o.getString("TALENT_KEYWORD2"), o.getString("TALENT_KEYWORD3"), point));


                    }

                    CompleteList_Adapter = new MyProfile_CompleteList_Adapter(MyProfile_CompleteList_Activity.this, CompleteList_arrayList);
                    CompleteList_ListView.setAdapter(CompleteList_Adapter);

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

                        JSONArray obj = new JSONArray(res);
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
