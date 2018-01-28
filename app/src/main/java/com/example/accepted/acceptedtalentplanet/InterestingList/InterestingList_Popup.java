package com.example.accepted.acceptedtalentplanet.InterestingList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-11-30.
 */

public class InterestingList_Popup extends FragmentActivity {

    Context mContext;
    Button Popup_ProgressorCancel;
    ImageView talentSharing_popupclosebtn;
    private InterestingList_Dialog mInterestingList_Dialog;

    RelativeLayout Interesting_popup_container;
    ImageView Interesting_popup_picture;

    ImageView InteresteingList_addfriendList_on;
    ImageView InteresteingList_addfriendList_off;
    String talentID;
    String profileUserID;
    boolean addedFriend = false;
    boolean sendFlag = true;
    boolean talentFlag = true;
    boolean genderPBS, birthPBS, jobPBS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.interestinglist_popup);
        mContext = getApplicationContext();


        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mContext = getApplicationContext();
        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;


        Interesting_popup_container = findViewById(R.id.Interesting_popup_container);
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int picturewidth = (int) (metrics.heightPixels*0.164*0.8*0.85);
        ViewGroup.LayoutParams params = Interesting_popup_container.getLayoutParams();
        params.width = (int) picturewidth;
        params.height = (int) picturewidth;
        Interesting_popup_container.setLayoutParams(params);



        talentID = getIntent().getStringExtra("TalentID");
        sendFlag = (getIntent().getIntExtra("codeGiveTake", 1) == 2) ? true : false;


        talentSharing_popupclosebtn = (ImageView) findViewById(R.id.TalentSharing_pupupclosebtn);
        talentSharing_popupclosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterestingList_Popup.this.finish();
            }
        });

        InteresteingList_addfriendList_on = findViewById(R.id.InterestedPopup_addfriendList_on);
        InteresteingList_addfriendList_off = findViewById(R.id.InterestedPopup_addfriendList_off);

        if (addedFriend) {
            InteresteingList_addfriendList_on.setVisibility(View.VISIBLE);
            InteresteingList_addfriendList_off.setVisibility(View.GONE);
        } else {
            InteresteingList_addfriendList_on.setVisibility(View.GONE);
            InteresteingList_addfriendList_off.setVisibility(View.VISIBLE);
        }

        InteresteingList_addfriendList_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InteresteingList_addfriendList_on.setVisibility(View.GONE);
                InteresteingList_addfriendList_off.setVisibility(View.VISIBLE);
                Toast.makeText(mContext, "친구 목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        InteresteingList_addfriendList_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InteresteingList_addfriendList_off.setVisibility(View.GONE);
                InteresteingList_addfriendList_on.setVisibility(View.VISIBLE);
                Toast.makeText(mContext, "친구 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        Popup_ProgressorCancel = findViewById(R.id.Popup_ProgressorCancel);
        final AlertDialog.Builder ProgressorCancelPopup = new AlertDialog.Builder(this);

        if (sendFlag) {
            //Popup_ProgressorCancel.setTextColor(Color.parseColor("#d2d2d2"));
            Popup_ProgressorCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float textSize = getResources().getDimension(R.dimen.DialogTxtSize);
                    ProgressorCancelPopup.setMessage("관심을 취소하시겠습니까?")
                            .setPositiveButton("관심취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    removeInteresting(talentID);
                                    Toast.makeText(mContext, "관심 취소 완료", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    Intent i = new Intent(getBaseContext(), TalentCondition_Activity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "취소 하기 클릭", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = ProgressorCancelPopup.create();
                    alertDialog.show();
                    TextView msgView = (TextView) alertDialog.findViewById(android.R.id.message);
                    msgView.setTextSize(textSize);
                }
            });
            Popup_ProgressorCancel.setText("관심 취소");
        } else {

            Popup_ProgressorCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float textSize = getResources().getDimension(R.dimen.DialogTxtSize);
                    ProgressorCancelPopup.setMessage("재능 진행 또는 관심 취소를 진행해주세요!")
                            .setPositiveButton("진행하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    doSharingTalent(talentID);
                                    Toast.makeText(mContext, "진행 하기 클릭", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                    Intent i = new Intent(getBaseContext(), TalentCondition_Activity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(i);
                                }
                            })
                            .setNegativeButton("취소하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext, "취소 하기 클릭", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = ProgressorCancelPopup.create();
                    alertDialog.show();
                    TextView msgView = (TextView) alertDialog.findViewById(android.R.id.message);
                    msgView.setTextSize(textSize);
                }
            });
        }
        getProfileInfo(talentID);
    }
        private View.OnClickListener ProgressListener = new View.OnClickListener() {
            public void onClick(View v) {
                doSharingTalent(talentID);

            }
        };

        private View.OnClickListener CancelListener = new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "취소버튼 클릭",
                        Toast.LENGTH_SHORT).show();
                mInterestingList_Dialog.dismiss();
            }
        };

    public void getProfileInfo(final String talentID) {
        final String TalentID = talentID;
        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
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
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserName)).setText(obj.getString("USER_NAME"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserGender)).setText((genderPBS)?Gender:"비공개");
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserBirth)).setText((birthPBS)?obj.getString("USER_BIRTH"):"비공개");
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserJob)).setText((jobPBS)?obj.getString("JOB"):"비공개");
                    ((TextView)findViewById(R.id.TalentSharingPopup_Keyword1)).setText(obj.getString("TALENT_KEYWORD1"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Keyword2)).setText(obj.getString("TALENT_KEYWORD2"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Keyword3)).setText(obj.getString("TALENT_KEYWORD3"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Location1)).setText(obj.getString("LOCATION1"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Location2)).setText(obj.getString("LOCATION2").equals("")?"미등록":obj.getString("LOCATION2"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Location3)).setText(obj.getString("LOCATION3").equals("")?"미등록":obj.getString("LOCATION3"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Level)).setText(SaveSharedPreference.getLevel(obj.getString("LEVEL")));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Point)).setText(obj.getString("T_POINT")+"P");
                    profileUserID = obj.getString("USER_ID");
                    talentFlag = (obj.getString("TALENT_FLAG").equals("Y"))? true : false;
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

    public void doSharingTalent(final String talentID) {

        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/doSharingTalent.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

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
                String senderID;
                String masterID;
                if(sendFlag){
                    if(talentFlag){
                        senderID =  SaveSharedPreference.getTakeTalentData(mContext).getTalentID();


                    }else{
                        senderID = SaveSharedPreference.getGiveTalentData(mContext).getTalentID();
                    }
                }else{
                    senderID = talentID;
                }

                if(sendFlag){
                    masterID = talentID;
                }else{
                    if(talentFlag){
                        masterID =  SaveSharedPreference.getTakeTalentData(mContext).getTalentID();
                    }else{
                        masterID = SaveSharedPreference.getGiveTalentData(mContext).getTalentID();
                    }
                }
                params.put("senderID", senderID);
                params.put("masterID", masterID);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

    public void removeInteresting(final String talentID) {

        RequestQueue postRequestQueue = Volley.newRequestQueue(this);
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Interest/removeInteresting.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

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
                String senderID;
                String masterID;
                if(sendFlag){
                    if(talentFlag){
                        senderID =  SaveSharedPreference.getTakeTalentData(mContext).getTalentID();


                    }else{
                        senderID = SaveSharedPreference.getGiveTalentData(mContext).getTalentID();
                    }
                }else{
                    senderID = talentID;
                }

                if(sendFlag){
                    masterID = talentID;
                }else{
                    if(talentFlag){
                        masterID =  SaveSharedPreference.getTakeTalentData(mContext).getTalentID();
                    }else{
                        masterID = SaveSharedPreference.getGiveTalentData(mContext).getTalentID();
                    }
                }
                params.put("senderID", senderID);
                params.put("masterID", masterID);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }


}
