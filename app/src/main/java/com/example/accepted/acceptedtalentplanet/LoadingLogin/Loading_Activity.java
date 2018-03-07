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
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

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

        startLoading();

    }

    private void startLoading()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                getMyTalent();
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

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentRegist/getMyTalent.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        Log.d("tqtq", obj.toString());

                        GeoPoint[] gpArr = new GeoPoint[3];
                        gpArr[0] = new GeoPoint(Double.parseDouble(obj.getString("GP_LAT1")), Double.parseDouble(obj.getString("GP_LNG1")));
                        gpArr[1] = new GeoPoint(Double.parseDouble(obj.getString("GP_LAT2")), Double.parseDouble(obj.getString("GP_LNG2")));
                        gpArr[2] = new GeoPoint(Double.parseDouble(obj.getString("GP_LAT3")), Double.parseDouble(obj.getString("GP_LNG3")));

                        MyTalent talent = new MyTalent();
                        talent.setMyTalent(obj.getString("TALENT_KEYWORD1"), obj.getString("TALENT_KEYWORD2"), obj.getString("TALENT_KEYWORD3"), obj.getString("LOCATION1"), obj.getString("LOCATION2"), obj.getString("LOCATION3"), obj.getString("T_POINT"), obj.getString("LEVEL"), gpArr);
                        talent.setStatus(obj.getString("STATUS_FLAG"));
                        talent.setTalentID(obj.getString("seq"));
                        if(obj.getString("TALENT_FLAG").equals("Y")){
                            SaveSharedPreference.setGiveTalentData(mContext, talent);
                        }else{
                            SaveSharedPreference.setTakeTalentData(mContext, talent);
                        }
                    }
                    getMyTalentPoint();

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", SaveSharedPreference.getUserId(mContext));


                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void getMyTalentPoint(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/getMyTalentPoint.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    int talentPoint = 0;
                    if(response.length() != 0) {
                        JSONObject obj = new JSONObject(response);
                        talentPoint = Integer.parseInt(obj.getString("TALENT_POINT"));
                    }
                    SaveSharedPreference.setPrefTalentPoint(mContext, talentPoint);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
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
