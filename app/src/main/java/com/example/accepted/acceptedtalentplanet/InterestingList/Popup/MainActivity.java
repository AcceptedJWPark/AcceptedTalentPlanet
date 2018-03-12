package com.example.accepted.acceptedtalentplanet.InterestingList.Popup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.accepted.acceptedtalentplanet.InterestingList.DialogActivity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

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
    private DialogActivity dialogActivity;

    private RelativeLayout rl_FriendIconContainer;

    private ImageView iv_AddFriendOn;
    private ImageView iv_AddFriendOff;

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


        rl_FriendIconContainer = findViewById(R.id.rl_FriendIconContainer_InterestingPopup);
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

        if (addedFriend) {
            iv_AddFriendOn.setVisibility(View.VISIBLE);
            iv_AddFriendOff.setVisibility(View.GONE);
        } else {
            iv_AddFriendOn.setVisibility(View.GONE);
            iv_AddFriendOff.setVisibility(View.VISIBLE);
        }

        iv_AddFriendOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_AddFriendOn.setVisibility(View.GONE);
                iv_AddFriendOff.setVisibility(View.VISIBLE);
                Toast.makeText(mContext, "친구 목록에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        iv_AddFriendOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_AddFriendOff.setVisibility(View.GONE);
                iv_AddFriendOn.setVisibility(View.VISIBLE);
                Toast.makeText(mContext, "친구 목록에 추가되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        btn_Progress = findViewById(R.id.btn_Progress_InterestingPopup);
        final AlertDialog.Builder ProgressorCancelPopup = new AlertDialog.Builder(this);

        if (sendFlag) {
            //btn_Progress.setTextColor(Color.parseColor("#d2d2d2"));
            btn_Progress.setOnClickListener(new View.OnClickListener() {
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
                                    Intent i = new Intent(getBaseContext(), com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
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
            btn_Progress.setText("관심 취소");
        } else {

            btn_Progress.setOnClickListener(new View.OnClickListener() {
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
                                    Intent i = new Intent(getBaseContext(), com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
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
                dialogActivity.dismiss();
            }
        };

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
                    String Gender = (obj.getString("GENDER").equals("남")) ? "남자" : "여자";
                    ((TextView)findViewById(R.id.name_InterestingPopup)).setText(obj.getString("USER_NAME"));
                    ((TextView)findViewById(R.id.gender_InterestingPopup)).setText((genderPBS)?Gender:"비공개");
                    ((TextView)findViewById(R.id.birth_InterestingPopup)).setText((birthPBS)?obj.getString("USER_BIRTH"):"비공개");
                    ((TextView)findViewById(R.id.job_InterestingPopup)).setText((jobPBS)?obj.getString("JOB"):"비공개");
                    ((TextView)findViewById(R.id.talent1_InterestingPopup)).setText(obj.getString("TALENT_KEYWORD1"));
                    ((TextView)findViewById(R.id.talent2_InterestingPopup)).setText(obj.getString("TALENT_KEYWORD2"));
                    ((TextView)findViewById(R.id.talent3_InterestingPopup)).setText(obj.getString("TALENT_KEYWORD3"));
                    ((TextView)findViewById(R.id.location1_InterestingPopup)).setText(obj.getString("LOCATION1"));
                    ((TextView)findViewById(R.id.location2_InterestingPopup)).setText(obj.getString("LOCATION2").equals("")?"미등록":obj.getString("LOCATION2"));
                    ((TextView)findViewById(R.id.location3_InterestingPopup)).setText(obj.getString("LOCATION3").equals("")?"미등록":obj.getString("LOCATION3"));
                    ((TextView)findViewById(R.id.level_InterestingPopup)).setText(SaveSharedPreference.getLevel(obj.getString("LEVEL")));
                    ((TextView)findViewById(R.id.point_InterestingPopup)).setText(obj.getString("T_POINT")+"P");
                    profileUserID = obj.getString("USER_ID");
                    talentFlag = (obj.getString("TALENT_FLAG").equals("Y"))? true : false;
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


}