package com.example.accepted.acceptedtalentplanet.TalentSharing;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-10-24.
 */

public class TalentSharingPopup extends FragmentActivity{

    ImageView talentSharing_pupupclosebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String talentID = getIntent().getStringExtra("TalentID");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.talentsharing_popup);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);

        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;



        talentSharing_pupupclosebtn = (ImageView) findViewById(R.id.TalentSharing_pupupclosebtn);
        talentSharing_pupupclosebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TalentSharingPopup.this.finish();
            }
        });

        getProfileInfo(talentID);
    }

    public void getProfileInfo(String talentID) {
        final String TalentID = talentID;
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/getProfileInfo.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("result", response);
                    String Gender = (obj.getString("GENDER").equals("남")) ? "남자" : "여자";
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserName)).setText(obj.getString("USER_NAME"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserGender)).setText(Gender);
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserBirth)).setText(obj.getString("USER_BIRTH"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserJob)).setText(obj.getString("JOB"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Keyword1)).setText(obj.getString("TALENT_KEYWORD1"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Keyword2)).setText(obj.getString("TALENT_KEYWORD2"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Keyword3)).setText(obj.getString("TALENT_KEYWORD3"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Location1)).setText(obj.getString("LOCATION1"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Location2)).setText(obj.getString("LOCATION2"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Location3)).setText(obj.getString("LOCATION3"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Level)).setText(SaveSharedPreference.getLevel(obj.getString("LEVEL")));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Point)).setText(obj.getString("T_POINT")+"P");
                } catch (JSONException e) {
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
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("talentID", TalentID);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }


}
