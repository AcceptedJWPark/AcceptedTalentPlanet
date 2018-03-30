package com.example.accepted.acceptedtalentplanet.InterestingList.Popup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.accepted.acceptedtalentplanet.Friend;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;
import com.example.accepted.acceptedtalentplanet.pictureExpand;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-11-30.
 */

public class MainActivity extends FragmentActivity {

    private Context mContext;

    private Button btn_Progress;
    private Button btn_Message;

    private ImageView iv_CloseIcon;

    private RelativeLayout rl_FriendIconContainer;

    private ImageView iv_AddFriendOn;
    private ImageView iv_AddFriendOff;
    private boolean firstFriendFlag;

    String talentID;
    String profileUserID;
    String userName;
    boolean addedFriend = false;
    boolean sendFlag = true;
    boolean talentFlag = true;
    boolean genderPBS, birthPBS, jobPBS;

    private int targetPoint = 0;


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


        rl_FriendIconContainer = findViewById(R.id.Interesting_popup_container);
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int picturewidth = (int) (metrics.heightPixels*0.164*0.8*0.85);
        ViewGroup.LayoutParams params = rl_FriendIconContainer.getLayoutParams();
        params.width = (int) picturewidth;
        params.height = (int) picturewidth;
        rl_FriendIconContainer.setLayoutParams(params);



        talentID = getIntent().getStringExtra("TalentID");
        sendFlag = (getIntent().getIntExtra("codeGiveTake", 1) == 2) ? true : false;




        iv_CloseIcon = (ImageView) findViewById(R.id.iv_CloseIcon_InterestingPopup);
        iv_CloseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });

        iv_AddFriendOn = findViewById(R.id.iv_AddFriendOn_InterestingPopup);
        iv_AddFriendOff = findViewById(R.id.iv_AddFriendOff_InterestingPopup);

        iv_AddFriendOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_AddFriendOn.setVisibility(View.GONE);
                iv_AddFriendOff.setVisibility(View.VISIBLE);
                Toast.makeText(mContext, "친구 목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                Friend friend = new Friend(profileUserID, (talentFlag)?"Y":"N");
                SaveSharedPreference.removeFriend(mContext, friend);
                addedFriend = false;
            }
        });
        iv_AddFriendOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_AddFriendOff.setVisibility(View.GONE);
                iv_AddFriendOn.setVisibility(View.VISIBLE);
                Toast.makeText(mContext,"친구 목록에 추가되었습니다.",Toast.LENGTH_SHORT).show();
                Friend friend = new Friend(profileUserID, (talentFlag)?"Y":"N");
                SaveSharedPreference.putFriend(mContext, friend);
                addedFriend = true;
            }
        });

        btn_Message = (Button)findViewById(R.id.Popup_Message);
        btn_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int roomID = SaveSharedPreference.makeChatRoom(mContext, profileUserID, userName);
                if(roomID < 0){
                    return;
                }
                Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.Messanger.Chatting.MainActivity.class);
                i.putExtra("userID", profileUserID);
                i.putExtra("roomID", roomID);
                i.putExtra("userName", userName);
                startActivity(i);

                finish();
            }
        });


        btn_Progress = findViewById(R.id.btn_Progress_InterestingPopup);
        final AlertDialog.Builder ProgressorCancelPopup = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog));

        if (sendFlag) {
            btn_Progress.setText("진행하기");
            btn_Progress.setBackgroundResource(R.drawable.bgr_preinterested);
            btn_Progress.setOnClickListener(null);

            //btn_Progress.setTextColor(Color.parseColor("#d2d2d2"));
//            btn_Progress.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    float textSize = getResources().getDimension(R.dimen.DialogTxtSize);
//                    ProgressorCancelPopup.setMessage("관심을 취소하시겠습니까?")
//                            .setPositiveButton("관심취소", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    removeInteresting(talentID);
//                                    dialog.cancel();
//                                    Intent i = new Intent(getBaseContext(), com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
//                                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                    startActivity(i);
//                                    finish();
//                                }
//                            })
//                            .setNegativeButton("닫기", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    dialog.cancel();
//                                }
//                            });
//
//                    AlertDialog alertDialog = ProgressorCancelPopup.create();
//                    alertDialog.show();
//                    TextView msgView = (TextView) alertDialog.findViewById(android.R.id.message);
//                    msgView.setTextSize(textSize);
//                }
//            });

        } else
            {

            btn_Progress.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    float textSize = getResources().getDimension(R.dimen.DialogTxtSize);
                    ProgressorCancelPopup.setMessage("상대방의 포인트" + targetPoint + "P 로 진행됩니다.")
                            .setPositiveButton("진행하기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    doSharingTalent(talentID);
                                    dialog.cancel();
                                    Intent i = new Intent(getBaseContext(), com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                                    i.putExtra("TalentCondition_TalentFlag",(talentFlag)?"Take":"Give");
                                    startActivity(i);
                                    finish();
                                }
                            })
                            .setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });



                    AlertDialog alertDialog = ProgressorCancelPopup.create();
                    alertDialog.show();
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(getResources().getColor(R.color.loginPasswordLost));
                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(getResources().getColor(R.color.loginPasswordLost));

                    TextView msgView = (TextView) alertDialog.findViewById(android.R.id.message);
                    msgView.setTextSize(textSize);
                }
            });
        }
        getProfileInfo(talentID);
    }


    public void getProfileInfo(final String talentID) {
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
                    userName = obj.getString("USER_NAME");
                    String Gender = (obj.getString("GENDER").equals("남")) ? "남자" : "여자";
                    ((TextView)findViewById(R.id.name_InterestingPopup)).setText(userName);
                    ((TextView)findViewById(R.id.gender_InterestingPopup)).setText((genderPBS)?Gender:"비공개");
                    ((TextView)findViewById(R.id.birth_InterestingPopup)).setText((birthPBS)?obj.getString("USER_BIRTH"):"비공개");
                    ((TextView)findViewById(R.id.job_InterestingPopup)).setText((jobPBS)?obj.getString("JOB"):"비공개");
                    ((TextView)findViewById(R.id.talent1_InterestingPopup)).setText(obj.getString("TALENT_KEYWORD1"));
                    ((TextView)findViewById(R.id.talent2_InterestingPopup)).setText(obj.getString("TALENT_KEYWORD2"));
                    ((TextView)findViewById(R.id.talent3_InterestingPopup)).setText(obj.getString("TALENT_KEYWORD3"));
                    ((TextView)findViewById(R.id.location1_InterestingPopup)).setText(obj.getString("LOCATION"));
                    ((TextView)findViewById(R.id.level_InterestingPopup)).setText(SaveSharedPreference.getLevel(obj.getString("LEVEL")));
                    ((TextView)findViewById(R.id.point_InterestingPopup)).setText(obj.getString("T_POINT")+"P");
                    profileUserID = obj.getString("USER_ID");
                    talentFlag = (obj.getString("TALENT_FLAG").equals("Y"))? true : false;
                    targetPoint = Integer.parseInt(obj.getString("T_POINT"));

                    ArrayList<Friend> friendList = SaveSharedPreference.getFriendList(mContext);
                    addedFriend = false;
                    for(Friend f : friendList){
                        if(f.getUserID().equals(profileUserID) && f.getPartnerTalentType().equals(obj.getString("TALENT_FLAG"))){
                            addedFriend = true;
                            break;
                        }
                    }

                    firstFriendFlag = addedFriend;

                    if (addedFriend) {
                        iv_AddFriendOn.setVisibility(View.VISIBLE);
                        iv_AddFriendOff.setVisibility(View.GONE);
                    } else {
                        iv_AddFriendOn.setVisibility(View.GONE);
                        iv_AddFriendOff.setVisibility(View.VISIBLE);
                    }



                    if(!obj.getString("S_FILE_PATH").equals("NODATA")) {
                        Glide.with(mContext).load(SaveSharedPreference.getImageUri() + obj.getString("S_FILE_PATH")).into((ImageView) findViewById(R.id.Interesting_popup_picture));
                        ((ImageView) findViewById(R.id.Interesting_popup_picture)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(mContext, pictureExpand.class);
                                intent.putExtra("Activity", "Interest");
                                intent.putExtra("userID", profileUserID);
                                startActivity(intent);
                            }
                        });
                    }
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

    public void doSharingTalent(final String talentID) {

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/doSharingTalent.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    MyTalent mt = (talentFlag)?SaveSharedPreference.getTakeTalentData(mContext):SaveSharedPreference.getGiveTalentData(mContext);

                    mt.setStatus("M");
                    if(!talentFlag){
                        SaveSharedPreference.setGiveTalentData(mContext, mt);
                    }else{
                        SaveSharedPreference.setTakeTalentData(mContext, mt);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                String senderID;
                String masterID;
                String myTalentID = "";
                if(sendFlag){
                    if(talentFlag){
                        senderID =  SaveSharedPreference.getTakeTalentData(mContext).getTalentID();
                        myTalentID = senderID;
                    }else{
                        senderID = SaveSharedPreference.getGiveTalentData(mContext).getTalentID();
                        myTalentID = senderID;
                    }
                }else{
                    senderID = talentID;
                }

                if(sendFlag){
                    masterID = talentID;
                }else{
                    if(talentFlag){
                        masterID =  SaveSharedPreference.getTakeTalentData(mContext).getTalentID();
                        myTalentID = masterID;
                    }else{
                        masterID = SaveSharedPreference.getGiveTalentData(mContext).getTalentID();
                        myTalentID = masterID;
                    }
                }
                params.put("senderID", senderID);
                params.put("masterID", masterID);
                params.put("myTalentID", myTalentID);
                params.put("activityFlag", "Y");
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

    public void removeInteresting(final String talentID) {

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Interest/removeInteresting.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
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

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(firstFriendFlag != addedFriend){
            updateFriendList();
        }
    }

    public void updateFriendList() {

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "FriendList/updateFriendList.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("friendID", profileUserID);
                params.put("talentFlag", (talentFlag)?"Y" : "N");
                params.put("updateFlag", (addedFriend)?"I":"D");
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }


}
