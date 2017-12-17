package com.example.accepted.acceptedtalentplanet.MyProfile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.accepted.acceptedtalentplanet.Alarm.Alarm_Activity;
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.FriendList.FriendList_Activity;
import com.example.accepted.acceptedtalentplanet.Home.Home_Activity;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfileData;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.System.System_Activity;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class MyProfile_Activity extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;
    ImageView DrawerCloseImg;
    ImageView ActionBar_AlarmView;
    Context mContext;
    MyProfileData myProfile;
    TextView ToolbarTxt;
    
    android.support.v7.widget.Toolbar myProfile_toolbar;
    LinearLayout myProfile_PictureLL;
    LinearLayout myProfile_ButtonLL;
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

    EditText MyProfile_Job;

    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile_activity);
        mContext = getApplicationContext();
        slidingMenuDL = (DrawerLayout) findViewById(R.id.MyProfile_listboxDL);

        MyProfile_Job = (EditText) findViewById(R.id.MyProfile_Job);
        MyProfile_Job.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
               if(!hasFocus)
               {
                   hideKeyboard(v);
               }

           }
       });


        drawerView = (View) findViewById(R.id.MyProfile_container);
        imgDLOpenMenu = (ImageView) findViewById(R.id.ActionBar_Listview);
        DrawerCloseImg = (ImageView) findViewById(R.id.DrawerCloseImg);
        ActionBar_AlarmView = (ImageView) findViewById(R.id.ActionBar_AlarmView);

        myProfile = new MyProfileData();
        imgDLOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgDLOpenMenu.setClickable(true);
                slidingMenuDL.openDrawer(drawerView);

            }
        });

        DrawerCloseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.closeDrawer(drawerView);
            }
        });

        ActionBar_AlarmView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Alarm_Activity.class);
                startActivity(intent);
            }
        });


        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));
        getMyProfile();

        ToolbarTxt = (TextView) findViewById(R.id.toolbarTxt);
        ToolbarTxt.setText("My Profile");


        myProfile_toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.MyProfile_toolbar);
        myProfile_PictureLL = (LinearLayout) findViewById(R.id.MyProfile_PictureLL);
        myProfile_ButtonLL= (LinearLayout) findViewById(R.id.MyProfile_ButtonLL);
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
        
        
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int TalentResister_Toolbar_height = (int) (metrics.heightPixels*0.075);
        int TalentResister_Picture_LL_height = (int) (metrics.heightPixels*0.18);
        int TalentResister_Button_LL_height = (int) (metrics.heightPixels*0.083);
        int TalentResister_Devider_height = (int) (metrics.heightPixels*0.03);
        int TalentResister_List_LL_height = (int) (metrics.heightPixels*0.07);

        ViewGroup.LayoutParams params1 = myProfile_toolbar.getLayoutParams();
        ViewGroup.LayoutParams params2 = myProfile_PictureLL.getLayoutParams();
        ViewGroup.LayoutParams params3 = myProfile_ButtonLL.getLayoutParams();
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

        params1.height = TalentResister_Toolbar_height;
        params2.height = TalentResister_Picture_LL_height;
        params3.height = TalentResister_Button_LL_height;
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


        myProfile_toolbar.setLayoutParams(params1);
        myProfile_PictureLL.setLayoutParams(params2);
        myProfile_ButtonLL.setLayoutParams(params3);
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

    }

    public void slideMenuTalentSearching(View v){
        Intent i = new Intent(mContext, TalentSearching_Activity.class);
        startActivity(i);
    }

    public void slideMenuProfile(View v){
        Intent i = new Intent(mContext, MyProfile_Activity.class);
        startActivity(i);
    }

    public void slideMenuTalent(View v){
        Intent i = new Intent(mContext, TalentResister_Activity.class);
        startActivity(i);
    }

    public void slideMenuTS(View v){
        Intent i = new Intent(mContext, TalentSharing_Activity.class);
        startActivity(i);
    }

    public void slideMenuMyTalent(View v){
        Intent i = new Intent(mContext, TalentCondition_Activity.class);
        startActivity(i);
    }

    public void slideMenuLogout(View v){
        SaveSharedPreference.clearUserInfo(mContext);
        Intent i = new Intent(mContext, Login_Activity.class);
        startActivity(i);
        finish();
    }

    public void slideMenuCustomerService(View v){
        Intent i = new Intent(mContext, CustomerService_MainActivity.class);
        startActivity(i);
    }

    public void slideMenuSystem(View v){
        Intent i = new Intent(mContext, System_Activity.class);
        startActivity(i);
    }

    public void slideMenuTalentSharingList(View v){
        Intent i = new Intent(mContext, SharingList_Activity.class);
        startActivity(i);
    }

    public void slideFriendList(View v){
        Intent i = new Intent(mContext, FriendList_Activity.class);
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

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}
