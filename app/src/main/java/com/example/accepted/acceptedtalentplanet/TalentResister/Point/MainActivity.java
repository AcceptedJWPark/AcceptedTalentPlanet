package com.example.accepted.acceptedtalentplanet.TalentResister.Point;

import android.content.Context;
import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private String talent1, talent2, talent3;
    private String location;
    private boolean isTalentRegisted;
    private boolean isHavingData;
    private MyTalent data;
    private int point;
    private int level;
    private EditText et_Point;

    private GeoPoint geoPoint = null;

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
        location = i.getStringExtra("loc");
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
                        if(isTalentRegisted){
                            SaveSharedPreference.setGiveTalentData(mContext, mt);
                        }else{
                            SaveSharedPreference.setTakeTalentData(mContext, mt);
                        }
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
                params.put("loc", location);
                params.put("level", String.valueOf(level));
                params.put("point", point);
                params.put("talentFlag", talentFlag);
                params.put("gpLat", String.valueOf(geoPoint.getLat()));
                params.put("gpLng", String.valueOf(geoPoint.getLng()));

                Log.d(String.valueOf(geoPoint.getLat()), String.valueOf(geoPoint.getLng()));
                MyTalent mt = new MyTalent();
                mt.setMyTalent(talent1, talent2, talent3, location, point, String.valueOf(level), geoPoint);
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

}
