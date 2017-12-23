package com.example.accepted.acceptedtalentplanet.LoadingLogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.Home.Home_Activity;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-09-20.
 */

public class Loading_Activity extends AppCompatActivity {

    Context mContext;


    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_start);

        mContext = getApplicationContext();
        getMyTalent();

    }

    private void startLoading()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(SaveSharedPreference.getUserId(Loading_Activity.this).length() == 0) {
                    intent = new Intent(getBaseContext(), Login_Activity.class);
                }else{
                    intent = new Intent(getBaseContext(), Home_Activity.class);
                }
                    startActivity(intent);
                    finish();

            }
        },3000);
    }

    public void getMyTalent(){



        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentRegist/getMyTalent.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);

                        GeoPoint[] gpArr = new GeoPoint[3];
                        gpArr[0] = new GeoPoint(Double.parseDouble(obj.getString("GP_LAT1")), Double.parseDouble(obj.getString("GP_LNG1")));
                        gpArr[1] = new GeoPoint(Double.parseDouble(obj.getString("GP_LAT2")), Double.parseDouble(obj.getString("GP_LNG2")));
                        gpArr[2] = new GeoPoint(Double.parseDouble(obj.getString("GP_LAT3")), Double.parseDouble(obj.getString("GP_LNG3")));

                        MyTalent talent = new MyTalent();
                        talent.setMyTalent(obj.getString("TALENT_KEYWORD1"), obj.getString("TALENT_KEYWORD2"), obj.getString("TALENT_KEYWORD3"), obj.getString("LOCATION1"), obj.getString("LOCATION2"), obj.getString("LOCATION1"), obj.getString("T_POINT"), obj.getString("LEVEL"), gpArr);
                        talent.setTalentID(obj.getString("seq"));
                        if(obj.getString("TALENT_FLAG").equals("Y")){
                            SaveSharedPreference.setGiveTalentData(mContext, talent);
                        }else{
                            SaveSharedPreference.setTakeTalentData(mContext, talent);
                        }

                    }

                    startLoading();
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
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
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", SaveSharedPreference.getUserId(mContext));


                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }
}
