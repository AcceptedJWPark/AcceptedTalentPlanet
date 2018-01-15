package com.example.accepted.acceptedtalentplanet.MyProfile;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MyProfile_Activity extends AppCompatActivity {

    MyProfileData myProfile;
    Context mContext;

    DrawerLayout slidingMenuDL;
    View drawerView;

    android.support.v7.widget.Toolbar myProfile_toolbar;
    RelativeLayout myProfile_PictureLL;
    Button myProfile_Button;
    TextView myProfile_Devider1;
    TextView myProfile_Devider2;
    LinearLayout myProfile_List_LL1;
    LinearLayout myProfile_List_LL2;
    LinearLayout myProfile_List_LL3;
    LinearLayout myProfile_List_LL4;
    LinearLayout myProfile_List_LL5;
    LinearLayout myProfile_List_LL6;
    LinearLayout myProfile_List_LL7;
    LinearLayout myProfile_List_LL8;
    RelativeLayout MyProfile_PictureContainer;

    ImageView MyProfile_Picture;

    EditText MyProfile_Job;

    Button MyProfile_Save;

    ImageView MyProfile_CompleteList_Open;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile_activity);
        mContext = getApplicationContext();

        myProfile = new MyProfileData();

        slidingMenuDL = (DrawerLayout) findViewById(R.id.MyProfile_listboxDL);
        drawerView = (View) findViewById(R.id.MyProfile_container);

        MyProfile_Job = (EditText) findViewById(R.id.MyProfile_Job);
        MyProfile_Job.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               ImageView ActionBar_Listview = (ImageView) findViewById(R.id.ActionBar_Listview);
               ImageView ActionBar_AlarmView = (ImageView) findViewById(R.id.ActionBar_AlarmView);
               if(!hasFocus)
               {
                   hideKeyboard(v,mContext);
                   ActionBar_Listview.setFocusable(false);
                   ActionBar_Listview.setFocusableInTouchMode(false);
                   ActionBar_AlarmView.setFocusable(false);
                   ActionBar_AlarmView.setFocusableInTouchMode(false);
               }
               else
               {
                   ActionBar_Listview.setFocusable(true);
                   ActionBar_Listview.setFocusableInTouchMode(true);
                   ActionBar_AlarmView.setFocusable(true);
                   ActionBar_AlarmView.setFocusableInTouchMode(true);
               }
           }
       });

        ((TextView) findViewById(R.id.toolbarTxt)).setText("My Profile");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                    DrawerLayout_Open(v,MyProfile_Activity.this,slidingMenuDL,drawerView);
                }
        };
        DrawerLayout_ClickEvent(MyProfile_Activity.this,mClicklistener);
        getMyProfile();

        myProfile_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.MyProfile_toolbar);
        myProfile_PictureLL = (RelativeLayout) findViewById(R.id.MyProfile_PictureLL);
        myProfile_Button= (Button) findViewById(R.id.MyProfile_Save);
        myProfile_Devider1 = (TextView) findViewById(R.id.MyProfile_Devider1); 
        myProfile_Devider2 = (TextView) findViewById(R.id.MyProfile_Devider2);
        myProfile_List_LL1 = (LinearLayout) findViewById(R.id.MyProfile_List_LL1);
        myProfile_List_LL2 = (LinearLayout) findViewById(R.id.MyProfile_List_LL2);
        myProfile_List_LL3 = (LinearLayout) findViewById(R.id.MyProfile_List_LL3);
        myProfile_List_LL4 = (LinearLayout) findViewById(R.id.MyProfile_List_LL4);
        myProfile_List_LL5 = (LinearLayout) findViewById(R.id.MyProfile_List_LL5);
        myProfile_List_LL6 = (LinearLayout) findViewById(R.id.MyProfile_List_LL6);
        myProfile_List_LL7 = (LinearLayout) findViewById(R.id.MyProfile_List_LL7);
        myProfile_List_LL8 = (LinearLayout) findViewById(R.id.MyProfile_List_LL8);
        MyProfile_PictureContainer = (RelativeLayout) findViewById(R.id.MyProfile_Picture_Container);

        
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int TalentResister_Toolbar_height = (int) (metrics.heightPixels*0.055);
        int TalentResister_Picture_LL_height = (int) (metrics.heightPixels*0.188);
        int TalentResister_Button_height = (int) (metrics.heightPixels*0.042);
        int TalentResister_Devider_height = (int) (metrics.heightPixels*0.045);
        int TalentResister_List_LL_height = (int) (metrics.heightPixels*0.0705);

        double TalentResister_Picture_width = TalentResister_Picture_LL_height*0.7;
        double TalentResister_Picture_height = TalentResister_Picture_LL_height*0.7;


        ViewGroup.LayoutParams params1 = myProfile_toolbar.getLayoutParams();
        ViewGroup.LayoutParams params2 = myProfile_PictureLL.getLayoutParams();
        ViewGroup.LayoutParams params3 = myProfile_Button.getLayoutParams();
        ViewGroup.LayoutParams params4 = myProfile_Devider1.getLayoutParams();
        ViewGroup.LayoutParams params5 = myProfile_Devider2.getLayoutParams();
        ViewGroup.LayoutParams params6 = myProfile_List_LL1.getLayoutParams();
        ViewGroup.LayoutParams params7 = myProfile_List_LL2.getLayoutParams();
        ViewGroup.LayoutParams params8 = myProfile_List_LL3.getLayoutParams();
        ViewGroup.LayoutParams params9 = myProfile_List_LL4.getLayoutParams();
        ViewGroup.LayoutParams params10 = myProfile_List_LL5.getLayoutParams();
        ViewGroup.LayoutParams params11 = myProfile_List_LL6.getLayoutParams();
        ViewGroup.LayoutParams params12 = myProfile_List_LL7.getLayoutParams();
        ViewGroup.LayoutParams params13 = myProfile_List_LL8.getLayoutParams();

        ViewGroup.LayoutParams params14 = MyProfile_PictureContainer.getLayoutParams();

        params1.height = TalentResister_Toolbar_height;
        params2.height = TalentResister_Picture_LL_height;
        params3.height = TalentResister_Button_height;
        params4.height = TalentResister_Devider_height;
        params5.height = TalentResister_Devider_height;
        params6.height = TalentResister_List_LL_height;
        params7.height = TalentResister_List_LL_height;
        params8.height = TalentResister_List_LL_height;
        params9.height = TalentResister_List_LL_height;
        params10.height = TalentResister_List_LL_height;
        params11.height = TalentResister_List_LL_height;
        params12.height = TalentResister_List_LL_height;
        params13.height = TalentResister_List_LL_height;

        params14.width = (int) TalentResister_Picture_width;
        params14.height = (int) TalentResister_Picture_height;

        myProfile_toolbar.setLayoutParams(params1);
        myProfile_PictureLL.setLayoutParams(params2);
        myProfile_Button.setLayoutParams(params3);
        myProfile_Devider1.setLayoutParams(params4);
        myProfile_Devider2.setLayoutParams(params5);
        myProfile_List_LL1.setLayoutParams(params6);
        myProfile_List_LL2.setLayoutParams(params7);
        myProfile_List_LL3.setLayoutParams(params8);
        myProfile_List_LL4.setLayoutParams(params9);
        myProfile_List_LL5.setLayoutParams(params10);
        myProfile_List_LL6.setLayoutParams(params11);
        myProfile_List_LL7.setLayoutParams(params12);
        myProfile_List_LL8.setLayoutParams(params13);

        MyProfile_PictureContainer.setLayoutParams(params14);


        MyProfile_CompleteList_Open = (ImageView) findViewById(R.id.MyProfile_CompleteList_Open);
        MyProfile_CompleteList_Open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, MyProfile_CompleteList_Activity.class);
                startActivity(i);
            }
        });

        MyProfile_Save = (Button)findViewById(R.id.MyProfile_Save);
        MyProfile_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMyProfile();
            }
        });




        View.OnClickListener listener_give = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, TalentResister_Activity.class);
                i.putExtra("GiveFlag", true);
                startActivity(i);
            }
        };

        View.OnClickListener listener_take = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, TalentResister_Activity.class);
                i.putExtra("GiveFlag", false);
                startActivity(i);
            }
        };

        ((TextView)findViewById(R.id.MyProfile_GiveTalent1)).setOnClickListener(listener_give);
        ((TextView)findViewById(R.id.MyProfile_GiveTalent2)).setOnClickListener(listener_give);
        ((TextView)findViewById(R.id.MyProfile_GiveTalent3)).setOnClickListener(listener_give);
        ((TextView)findViewById(R.id.MyProfile_TakeTalent1)).setOnClickListener(listener_take);
        ((TextView)findViewById(R.id.MyProfile_TakeTalent2)).setOnClickListener(listener_take);
        ((TextView)findViewById(R.id.MyProfile_TakeTalent3)).setOnClickListener(listener_take);
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
                    String point = obj.getString("TALENT_POINT") + "P";
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
                    ((TextView)findViewById(R.id.MyProfile_TalentPoint)).setText(point);
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

    public void saveMyProfile(){
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Profile/saveMyProfileInfo.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("result = ", obj.toString());

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
                MyProfile_Job = (EditText)findViewById(R.id.MyProfile_Job);

                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("job", MyProfile_Job.getText().toString());
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }




}
