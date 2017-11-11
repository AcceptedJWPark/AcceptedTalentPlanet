package com.example.accepted.acceptedtalentplanet.MyProfile;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_ClaimActivity;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.Home.HomeActivity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.LoginActivity;
import com.example.accepted.acceptedtalentplanet.MyProfileData;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentConditionActivity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResisterActivity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharingActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MyprofileActivity extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;
    Context mContext;
    MyProfileData myProfile;
    TextView ToolbarTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile_activity);
        mContext = getApplicationContext();
        slidingMenuDL = (DrawerLayout) findViewById(R.id.MyProfile_listboxDL);

        //ToolBar 적용하기
        drawerView = (View) findViewById(R.id.MyProfile_container);
        imgDLOpenMenu = (ImageView) findViewById(R.id.ActionBar_Listview);
        DrawerCloseImg = (ImageView) findViewById(R.id.DrawerCloseImg);

        myProfile = new MyProfileData();

        imgDLOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.openDrawer(drawerView);

            }
        });

        DrawerCloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.closeDrawer(drawerView);
            }
        });

        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));
        getMyProfile();

        ToolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        ToolbarTxt.setText("My Profile");

    }

    public void slideMenuHome(View v){
        Intent i = new Intent(mContext, HomeActivity.class);
        startActivity(i);
    }

    public void slideMenuProfile(View v){
        Intent i = new Intent(mContext, MyprofileActivity.class);
        startActivity(i);
    }

    public void slideMenuTalent(View v){
        Intent i = new Intent(mContext, TalentResisterActivity.class);
        startActivity(i);
    }

    public void slideMenuTS(View v){
        Intent i = new Intent(mContext, TalentSharingActivity.class);
        startActivity(i);
    }

    public void slideMenuMyTalent(View v){
        Intent i = new Intent(mContext, TalentConditionActivity.class);
        startActivity(i);
    }

    public void slideMenuLogout(View v){
        SaveSharedPreference.clearUserInfo(mContext);
        Intent i = new Intent(mContext, LoginActivity.class);
        startActivity(i);
        finish();
    }


    public void slideMenuCustomerService(View v){
        Intent i = new Intent(mContext, CustomerService_MainActivity.class);
        startActivity(i);
    }



    public void getMyProfile(){
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Profile/getMyProfileInfo.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String Gender = (obj.getString("GENDER").equals("남")) ? "남자" : "여자";
                    String[] TalentGive = {obj.getString("G_TALENT1"), obj.getString("G_TALENT2"), obj.getString("G_TALENT3")};
                    String[] TalentTake = {obj.getString("T_TALENT1"), obj.getString("T_TALENT2"), obj.getString("T_TALENT3")};
                    myProfile.setMyProfile(obj.getString("USER_NAME"), Gender, obj.getString("USER_BIRTH"), obj.getString("JOB"), TalentGive, TalentTake);
                    Log.d("result", obj.toString());
                    ((TextView)findViewById(R.id.MyProfile_Email)).setText(SaveSharedPreference.getUserId(mContext));
                    ((TextView)findViewById(R.id.MyProfile_Name)).setText(myProfile.getUserName());
                    ((TextView)findViewById(R.id.MyProfile_Gender)).setText(myProfile.getGender());
                    ((TextView)findViewById(R.id.MyProfile_Birthday)).setText(myProfile.getBirth());
                    ((TextView)findViewById(R.id.MyProfile_Job)).setText(myProfile.getJob());
                    ((TextView)findViewById(R.id.MyProfile_GiveTalent1)).setText(TalentGive[0]);
                    ((TextView)findViewById(R.id.MyProfile_GiveTalent2)).setText(TalentGive[1]);
                    ((TextView)findViewById(R.id.MyProfile_GiveTalent3)).setText(TalentGive[2]);
                    ((TextView)findViewById(R.id.MyProfile_TakeTalent1)).setText(TalentTake[0]);
                    ((TextView)findViewById(R.id.MyProfile_TakeTalent2)).setText(TalentTake[1]);
                    ((TextView)findViewById(R.id.MyProfile_TakeTalent3)).setText(TalentTake[2]);
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
