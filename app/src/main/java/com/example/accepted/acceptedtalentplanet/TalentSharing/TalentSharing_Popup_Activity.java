package com.example.accepted.acceptedtalentplanet.TalentSharing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-10-24.
 */

public class TalentSharing_Popup_Activity extends FragmentActivity{

    ImageView talentSharing_pupupclosebtn;
    ImageView TalentSharingPopup_addfriendList_on;
    ImageView TalentSharingPopup_addfriendList_off;
    String UserID;

    boolean hasFlag = false;
    Context mContext;
    Button interestBtn;

    boolean addedFriend = false;
    boolean sendFlag = true;
    String talentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        talentID = getIntent().getStringExtra("TalentID");
        sendFlag = (getIntent().getStringExtra("TalentFlag").equals("Give"))?true:false;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.talentsharing_popup);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mContext = getApplicationContext();
        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        talentSharing_pupupclosebtn = (ImageView) findViewById(R.id.TalentSharing_pupupclosebtn);
        talentSharing_pupupclosebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TalentSharing_Popup_Activity.this.finish();
            }
        });


        TalentSharingPopup_addfriendList_on = findViewById(R.id.TalentSharingPopup_addfriendList_on);
        TalentSharingPopup_addfriendList_off = findViewById(R.id.TalentSharingPopup_addfriendList_off);


        TalentSharingPopup_addfriendList_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentSharingPopup_addfriendList_on.setVisibility(View.GONE);
                TalentSharingPopup_addfriendList_off.setVisibility(View.VISIBLE);
                Toast.makeText(mContext,"친구 목록에서 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                SaveSharedPreference.removeFriend(mContext, UserID);
                addedFriend = false;
                }
        });

        TalentSharingPopup_addfriendList_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentSharingPopup_addfriendList_off.setVisibility(View.GONE);
                TalentSharingPopup_addfriendList_on.setVisibility(View.VISIBLE);
                Toast.makeText(mContext,"친구 목록에 추가되었습니다.",Toast.LENGTH_SHORT).show();
                SaveSharedPreference.putFriend(mContext, UserID);
                addedFriend = true;
            }
        });

        getProfileInfo(talentID);

    }

    public void getProfileInfo(final String talentID) {
        final String TalentID = talentID;
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/getProfileInfo.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("result", response);
                    String Gender = (obj.getString("GENDER").equals("남")) ? "남자" : "여자";
                    String TalentText = obj.getString("TALENT_FLAG").equals("Y") ? "재능드림" : "관심재능";
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
                    ((TextView)findViewById(R.id.TalentSharing_TypeText)).setText(TalentText);
                    UserID = obj.getString("USER_ID");
                    ArrayList<String> friendList = SaveSharedPreference.getFriendList(mContext);

                    addedFriend = (friendList.contains(UserID))?true:false;

                    hasFlag = (obj.getString("HAS_FLAG").equals("N"))? false : true;
                    Log.d("hasFlag", String.valueOf(hasFlag));
                    interestBtn = (Button)findViewById(R.id.TalentSharing_Interest_Button);
                    if(!hasFlag){
                        interestBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sendInterest(talentID);
                            }
                        });
                    }

                    if (addedFriend) {
                        TalentSharingPopup_addfriendList_on.setVisibility(View.VISIBLE);
                        TalentSharingPopup_addfriendList_off.setVisibility(View.GONE);
                    }
                    else {
                        TalentSharingPopup_addfriendList_on.setVisibility(View.GONE);
                        TalentSharingPopup_addfriendList_off.setVisibility(View.VISIBLE);
                    }
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
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("talentID", TalentID);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }


    public void sendInterest(final String talentID) {
        final String TalentID = talentID;
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/sendInterest.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    if(result.equals("success")){
                        Toast.makeText(getApplicationContext(), "관심을 보냈습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TalentSharing_Popup_Activity.this, TalentSharing_Popup_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "관심보내기에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }

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
                params.put("masterID", talentID);
                String senderID = (sendFlag)? SaveSharedPreference.getTakeTalentData(mContext).getTalentID() : SaveSharedPreference.getGiveTalentData(mContext).getTalentID();
                params.put("senderID", senderID);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

}
