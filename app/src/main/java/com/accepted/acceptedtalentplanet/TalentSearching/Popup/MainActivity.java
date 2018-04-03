package com.accepted.acceptedtalentplanet.TalentSearching.Popup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.VolleySingleton;
import com.accepted.acceptedtalentplanet.pictureExpand;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-10-24.
 */

public class MainActivity extends FragmentActivity{

    private ImageView popupClosebtn;
    private boolean genderPBS, birthPBS, jobPBS;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String talentID = getIntent().getStringExtra("TalentID");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.talentsharing_popup);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        mContext = getApplicationContext();

        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);

        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;


        ((ImageView)findViewById(R.id.TalentSharing_popup_picture)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, pictureExpand.class);
                startActivity(intent);
            }
        });


        popupClosebtn = (ImageView) findViewById(R.id.iv_CloseIcon_InterestingPopup);
        popupClosebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });

        getProfileInfo(talentID);
    }

    public void getProfileInfo(String talentID) {
        final String TalentID = talentID;
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/getProfileInfo.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("result", response);

                    genderPBS = (obj.getString("GENDER_FLAG").equals("Y"))?true:false;
                    birthPBS = (obj.getString("BIRTH_FLAG").equals("Y"))?true:false;
                    jobPBS = (obj.getString("JOB_FLAG").equals("Y"))?true:false;

                    String Gender = (obj.getString("GENDER").equals("남")) ? "남자" : "여자";
                    ((TextView)findViewById(R.id.name_InterestingPopup)).setText(obj.getString("USER_NAME"));
                    ((TextView)findViewById(R.id.gender_InterestingPopup)).setText((genderPBS)?Gender:"비공개");
                    ((TextView)findViewById(R.id.birth_InterestingPopup)).setText((birthPBS)?obj.getString("USER_BIRTH"):"비공개");
                    ((TextView)findViewById(R.id.job_InterestingPopup)).setText((jobPBS)?obj.getString("JOB"):"비공개");
                    ((TextView)findViewById(R.id.talent1_InterestingPopup)).setText(obj.getString("TALENT_KEYWORD1"));
                    ((TextView)findViewById(R.id.talent2_InterestingPopup)).setText(obj.getString("TALENT_KEYWORD2"));
                    ((TextView)findViewById(R.id.talent3_InterestingPopup)).setText(obj.getString("TALENT_KEYWORD3"));
                    ((TextView)findViewById(R.id.location1_InterestingPopup)).setText(obj.getString("LOCATION1"));
                    ((TextView)findViewById(R.id.level_InterestingPopup)).setText(SaveSharedPreference.getLevel(obj.getString("LEVEL")));
                    ((TextView)findViewById(R.id.point_InterestingPopup)).setText(obj.getString("T_POINT")+"P");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
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
