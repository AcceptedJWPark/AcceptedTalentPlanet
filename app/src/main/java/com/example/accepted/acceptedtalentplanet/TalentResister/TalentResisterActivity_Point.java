package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
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
import com.example.accepted.acceptedtalentplanet.LoadingLogin.LoginActivity;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TalentResisterActivity_Point extends AppCompatActivity {
    private Context mContext;
    private String Talent1, Talent2, Talent3;
    private String Location1, Location2, Location3;
    private boolean TalentRegister_Flag;
    private boolean HavingDataFlag;
    private MyTalent Data;
    private int Point;
    private int level;

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
        level = i.getIntExtra("level", 1);
        HavingDataFlag = i.getBooleanExtra("HavingDataFlag", false);
        if(HavingDataFlag)
        {
            Data = (MyTalent)i.getSerializableExtra("Data");
            Point = Data.getPoint();
            ((EditText)findViewById(R.id.TalentResister_Talent_Point)).setText(String.valueOf(Point));
        }

        TextView PointKey1= (TextView) findViewById(R.id.Point_key1);
        SpannableString Point_content1 = new SpannableString(Talent1);
        Point_content1.setSpan(new UnderlineSpan(), 0, Point_content1 .length(), 0);
        PointKey1.setText(Point_content1);

        TextView PointKey2 = (TextView) findViewById(R.id.Point_key2);
        SpannableString Point_content2 = new SpannableString(Talent2);
        Point_content2.setSpan(new UnderlineSpan(), 0, Point_content2.length(), 0);
        PointKey2.setText(Point_content2);

        TextView PointKey3 = (TextView) findViewById(R.id.Point_key3);
        SpannableString Point_content3 = new SpannableString(Talent3);
        Point_content3.setSpan(new UnderlineSpan(), 0, Point_content3.length(), 0);
        PointKey3.setText(Point_content3);

    }

    public void goSave(View v){


        final String point = ((EditText)findViewById(R.id.TalentResister_Talent_Point)).getText().toString();


        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentRegist/goRegist.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    if(result.equals("success")){
                        Toast.makeText(getApplicationContext(), "등록이 완료되었습니다..", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "등록이 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(getBaseContext(), TalentResisterActivity.class);
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
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

}
