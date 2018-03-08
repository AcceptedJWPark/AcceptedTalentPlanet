package com.example.accepted.acceptedtalentplanet.TalentResister.Point;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private String talent1, talent2, talent3;
    private String location1, location2, location3;
    private boolean isTalentRegisted;
    private boolean isHavingData;
    private MyTalent data;
    private int point;
    private int level;
    private EditText et_Point;

    private GeoPoint[] geoPoint = new GeoPoint[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_point);

        mContext = getApplicationContext();

        Intent i = getIntent();
        isTalentRegisted = i.getBooleanExtra("talentFlag", true);
        talent1 = i.getStringExtra("talent1");
        talent2 = i.getStringExtra("talent2");
        talent3 = i.getStringExtra("talent3");
        location1 = i.getStringExtra("loc1");
        location2 = i.getStringExtra("loc2");
        location3 = i.getStringExtra("loc3");
        geoPoint = SaveSharedPreference.getGeoPointArr(mContext);
        level = i.getIntExtra("level", 1);

        et_Point = (EditText) findViewById(R.id.et_Point_TalentResister);
        et_Point.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                hideKeyboard(v, mContext);
            }
        });


        isHavingData = i.getBooleanExtra("isHavingData", false);
        if(isHavingData)
        {
            data = (MyTalent)i.getSerializableExtra("data");
            point = data.getPoint();
            et_Point.setText(String.valueOf(point));
        }


    }

    public void goSave(View v){


        final String point = et_Point.getText().toString();
        int myTalentPoint = SaveSharedPreference.getTalentPoint(mContext);

        if(!isTalentRegisted && myTalentPoint < Integer.parseInt(point)){
            Toast.makeText(getApplicationContext(), "현재 사용가능 포인트는 " + myTalentPoint +"P 입니다.", Toast.LENGTH_SHORT).show();
            return;
        }else if(Integer.parseInt(point) > 300){
            Toast.makeText(getApplicationContext(), "최대 선택가능 포인트는 300P 입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentRegist/goRegist.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    if(result.equals("success")){
                        Toast.makeText(getApplicationContext(), "등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        MyTalent mt = (isTalentRegisted) ? SaveSharedPreference.getGiveTalentData(mContext) : SaveSharedPreference.getTakeTalentData(mContext);
                        mt.setStatus("P");
                        mt.setTalentID(obj.getString("talentID"));
                    }else if(result.equals("TalentRegistFail")){
                        Toast.makeText(getApplicationContext(), "재능 등록이 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "등록이 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(getBaseContext(), com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                String talentFlag = (isTalentRegisted)?"Y":"N";
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("talent1", talent1);
                params.put("talent2", talent2);
                params.put("talent3", talent3);
                params.put("loc1", location1);
                params.put("loc2", location2);
                params.put("loc3", location3);
                params.put("level", String.valueOf(level));
                params.put("point", point);
                params.put("talentFlag", talentFlag);
                params.put("gpLat1", String.valueOf(geoPoint[0].getLat()));
                params.put("gpLat2", String.valueOf(geoPoint[1].getLat()));
                params.put("gpLat3", String.valueOf(geoPoint[2].getLat()));
                params.put("gpLng1", String.valueOf(geoPoint[0].getLng()));
                params.put("gpLng2", String.valueOf(geoPoint[1].getLng()));
                params.put("gpLng3", String.valueOf(geoPoint[2].getLng()));

                Log.d(String.valueOf(geoPoint[0].getLat()), String.valueOf(geoPoint[0].getLng()));
                MyTalent mt = new MyTalent();
                mt.setMyTalent(talent1, talent2, talent3, location1, location2, location3, point, String.valueOf(level), geoPoint);
                mt.setStatus("P");
                if(isTalentRegisted)
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
