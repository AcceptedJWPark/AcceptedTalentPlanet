package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
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
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class TalentResister_Point_Activity extends AppCompatActivity {
    private Context mContext;
    private String Talent1, Talent2, Talent3;
    private String Location1, Location2, Location3;
    private boolean TalentRegister_Flag;
    private boolean HavingDataFlag;
    private MyTalent Data;
    private int Point;
    private int level;
    EditText TalentResister_Talent_Point;

    GeoPoint[] arrGp = new GeoPoint[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_point);

        mContext = getApplicationContext();

        Intent i = getIntent();
        TalentRegister_Flag = i.getBooleanExtra("talentFlag", true);
        Talent1 = i.getStringExtra("talent1");
        Talent2 = i.getStringExtra("talent2");
        Talent3 = i.getStringExtra("talent3");
        Location1 = i.getStringExtra("loc1");
        Location2 = i.getStringExtra("loc2");
        Location3 = i.getStringExtra("loc3");
        arrGp = SaveSharedPreference.getGeoPointArr(mContext);
        level = i.getIntExtra("level", 1);

        TalentResister_Talent_Point = (EditText) findViewById(R.id.TalentResister_Talent_Point);
        TalentResister_Talent_Point.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(v, mContext);
            }
        });


        HavingDataFlag = i.getBooleanExtra("HavingDataFlag", false);
        if(HavingDataFlag)
        {
            Data = (MyTalent)i.getSerializableExtra("Data");
            Point = Data.getPoint();
            ((EditText)findViewById(R.id.TalentResister_Talent_Point)).setText(String.valueOf(Point));
        }


    }

    public void goSave(View v){


        final String point = ((EditText)findViewById(R.id.TalentResister_Talent_Point)).getText().toString();
        int myTalentPoint = SaveSharedPreference.getTalentPoint(mContext);

        if(!TalentRegister_Flag && myTalentPoint < Integer.parseInt(point)){
            Toast.makeText(getApplicationContext(), "현재 사용가능 포인트는 " + myTalentPoint +"P 입니다.", Toast.LENGTH_SHORT).show();
            return;
        }else if(Integer.parseInt(point) > 300){
            Toast.makeText(getApplicationContext(), "최대 선택가능 포인트는 300P 입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentRegist/goRegist.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    if(result.equals("success")){
                        Toast.makeText(getApplicationContext(), "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        MyTalent mt = (TalentRegister_Flag) ? SaveSharedPreference.getGiveTalentData(mContext) : SaveSharedPreference.getTakeTalentData(mContext);
                        mt.setStatus("P");
                        mt.setTalentID(obj.getString("talentID"));
                    }else if(result.equals("TalentRegistFail")){
                        Toast.makeText(getApplicationContext(), "재능 등록이 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "등록이 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(getBaseContext(), TalentResister_Activity.class);
                    startActivity(intent);
                    finish();

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
                String talentFlag = (TalentRegister_Flag)?"Y":"N";
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("talent1", Talent1);
                params.put("talent2", Talent2);
                params.put("talent3", Talent3);
                params.put("loc1", Location1);
                params.put("loc2", Location2);
                params.put("loc3", Location3);
                params.put("level", String.valueOf(level));
                params.put("point", point);
                params.put("talentFlag", talentFlag);
                params.put("gpLat1", String.valueOf(arrGp[0].getLat()));
                params.put("gpLat2", String.valueOf(arrGp[1].getLat()));
                params.put("gpLat3", String.valueOf(arrGp[2].getLat()));
                params.put("gpLng1", String.valueOf(arrGp[0].getLng()));
                params.put("gpLng2", String.valueOf(arrGp[1].getLng()));
                params.put("gpLng3", String.valueOf(arrGp[2].getLng()));

                Log.d(String.valueOf(arrGp[0].getLat()), String.valueOf(arrGp[0].getLng()));
                MyTalent mt = new MyTalent();
                mt.setMyTalent(Talent1, Talent2, Talent3, Location1, Location2, Location3, point, String.valueOf(level), arrGp);
                mt.setStatus("P");
                if(TalentRegister_Flag)
                    SaveSharedPreference.setGiveTalentData(mContext, mt);
                else
                    SaveSharedPreference.setTakeTalentData(mContext, mt);


                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    private GeoPoint findGeoPoint(String address) {
        Geocoder geocoder = new Geocoder(this);
        Address addr;
        GeoPoint location = null;
        try {
            List<Address> listAddress = geocoder.getFromLocationName(address, 1);
            if (listAddress.size() > 0) { // 주소값이 존재 하면
                addr = listAddress.get(0); // Address형태로
                double lat = addr.getLatitude();
                double lng = addr.getLongitude();
                location = new GeoPoint(lat, lng);

                Log.d("Location Log", address + " : " + lat + ", " +lng);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return location;
    }

}
