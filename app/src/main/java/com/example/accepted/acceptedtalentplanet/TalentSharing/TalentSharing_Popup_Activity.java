package com.example.accepted.acceptedtalentplanet.TalentSharing;

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
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.accepted.acceptedtalentplanet.Friend;
import com.example.accepted.acceptedtalentplanet.Messanger.Messanger_Chatting;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Accepted on 2017-10-24.
 */

public class TalentSharing_Popup_Activity extends FragmentActivity{

    ImageView TalentSharing_popup_picture;
    ImageView talentSharing_pupupclosebtn;
    ImageView TalentSharingPopup_addfriendList_on;
    ImageView TalentSharingPopup_addfriendList_off;
    String UserID;
    String UserName;
    String talentFlag;
    String statusFlag;
    boolean hasFlag = false;
    Context mContext;
    Button interestBtn;
    Button sendMessageBtn;
    int point;

    boolean genderPBS, birthPBS, jobPBS;

    RelativeLayout TalentSharing_popup_container;

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

        TalentSharing_popup_container = findViewById(R.id.TalentSharing_popup_container);
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int picturewidth = (int) (metrics.heightPixels*0.164*0.8*0.85);
        ViewGroup.LayoutParams params = TalentSharing_popup_container.getLayoutParams();
        params.width = (int) picturewidth;
        params.height = (int) picturewidth;
        TalentSharing_popup_container.setLayoutParams(params);




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
                Friend friend = new Friend(UserID, talentFlag);
                SaveSharedPreference.removeFriend(mContext, friend);
                addedFriend = false;
                }
        });

        TalentSharingPopup_addfriendList_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TalentSharingPopup_addfriendList_off.setVisibility(View.GONE);
                TalentSharingPopup_addfriendList_on.setVisibility(View.VISIBLE);
                Toast.makeText(mContext,"친구 목록에 추가되었습니다.",Toast.LENGTH_SHORT).show();
                Friend friend = new Friend(UserID, talentFlag);
                SaveSharedPreference.putFriend(mContext, friend);
                addedFriend = true;
            }
        });

        sendMessageBtn = (Button)findViewById(R.id.TalentSharing_Send_Message_Button);
        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int roomID = SaveSharedPreference.makeChatRoom(mContext, UserID, UserName);
                if(roomID < 0){
                    Toast.makeText(mContext, "채팅방 생성 실패", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent i = new Intent(mContext, Messanger_Chatting.class);
                i.putExtra("userID", UserID);
                i.putExtra("roomID", roomID);
                i.putExtra("userName", UserName);
                startActivity(i);
            }
        });

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
                    Log.d("getProfile", obj.toString());
                    talentFlag = obj.getString("TALENT_FLAG");
                    point = Integer.parseInt(obj.getString("T_POINT"));

                    genderPBS = (obj.getString("GENDER_FLAG").equals("Y"))?true:false;
                    birthPBS = (obj.getString("BIRTH_FLAG").equals("Y"))?true:false;
                    jobPBS = (obj.getString("JOB_FLAG").equals("Y"))?true:false;
                    UserName = obj.getString("USER_NAME");

                    Log.d("result", response);
                    String Gender = (obj.getString("GENDER").equals("남")) ? "남자" : "여자";
                    String TalentText = talentFlag.equals("Y") ? "재능드림" : "관심재능";
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserName)).setText(UserName);
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserGender)).setText((genderPBS)?Gender:"비공개");
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserBirth)).setText((birthPBS)?obj.getString("USER_BIRTH"):"비공개");
                    ((TextView)findViewById(R.id.TalentSharingPopup_UserJob)).setText((jobPBS)?obj.getString("JOB"):"비공개");
                    ((TextView)findViewById(R.id.TalentSharingPopup_Keyword1)).setText(obj.getString("TALENT_KEYWORD1"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Keyword2)).setText(obj.getString("TALENT_KEYWORD2"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Keyword3)).setText(obj.getString("TALENT_KEYWORD3"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Location1)).setText(obj.getString("LOCATION1"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Location2)).setText((obj.getString("LOCATION2").length()==0)?"미등록":obj.getString("LOCATION2"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Location3)).setText((obj.getString("LOCATION3").length()==0)?"미등록":obj.getString("LOCATION3"));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Level)).setText(SaveSharedPreference.getLevel(obj.getString("LEVEL")));
                    ((TextView)findViewById(R.id.TalentSharingPopup_Point)).setText(point+"P");
                    ((TextView)findViewById(R.id.TalentSharing_TypeText)).setText(TalentText);
                    statusFlag = obj.getString("STATUS_FLAG");

                    if(!obj.getString("FILE_DATA").equals("Tk9EQVRB")){
                        ((ImageView)findViewById(R.id.TalentSharing_popup_picture)).setImageBitmap(SaveSharedPreference.StringToBitMap(obj.getString("FILE_DATA")));
                    }

                    UserID = obj.getString("USER_ID");
                    ArrayList<Friend> friendList = SaveSharedPreference.getFriendList(mContext);
                    addedFriend = false;
                    for(Friend f : friendList){
                        if(f.getUserID().equals(UserID)){
                            addedFriend = true;
                            break;
                        }
                    }

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
                    }else{
                        interestBtn.setText("관심 취소");
                        final AlertDialog.Builder ProgressorCancelPopup = new AlertDialog.Builder(TalentSharing_Popup_Activity.this);
                        interestBtn.setOnClickListener(new View.OnClickListener() {
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
                                                Intent i = new Intent(getBaseContext(), TalentSharing_Activity.class);
                                                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                                startActivity(i);
                                                finish();
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
        }, SaveSharedPreference.getErrorListener()) {
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
        if(talentFlag.equals("Y")){
            if(point > SaveSharedPreference.getTalentPoint(mContext)){
                Toast.makeText(mContext, "현재 사용 가능한 포인트가 재능드림 포인트보다 적습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if(!statusFlag.equals("P")){
            Toast.makeText(mContext, "현재 상대방이 대기중 상태가 아닙니다.", Toast.LENGTH_SHORT).show();
            return;
        }


        final String TalentID = talentID;
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentSharing/sendInterest.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    if(result.equals("success")){
                        Toast.makeText(getApplicationContext(), "관심을 보냈습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TalentSharing_Popup_Activity.this, TalentSharing_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "관심보내기에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
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

    public void removeInteresting(final String talentID) {

        RequestQueue postRequestQueue = Volley.newRequestQueue(mContext, new HurlStack(null, getSocketFactory()));
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

                if(talentFlag.equals("Y")){
                    senderID =  SaveSharedPreference.getTakeTalentData(mContext).getTalentID();
                }else{
                    senderID = SaveSharedPreference.getGiveTalentData(mContext).getTalentID();
                }

                masterID = talentID;

                params.put("senderID", senderID);
                params.put("masterID", masterID);
                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }

    private SSLSocketFactory getSocketFactory(){
        CertificateFactory cf = null;
        try{
            cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = getResources().openRawResource(R.raw.accepted);
            Certificate ca;
            try{
                ca= cf.generateCertificate(caInput);
                Log.e("CERT", "ca="+((X509Certificate)ca).getSubjectDN());
            }finally {
                caInput.close();
            }

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String s, SSLSession sslSession) {
                    Log.e("CipherUsed", sslSession.getCipherSuite());
                    return s.compareTo(SaveSharedPreference.getServerIp())==0;
                }
            };

            HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);
            SSLContext context = null;
            context = SSLContext.getInstance("TLS");

            context.init(null, tmf.getTrustManagers(), null);
            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());

            SSLSocketFactory sf = context.getSocketFactory();

            return sf;

        }catch (CertificateException e){
            e.printStackTrace();
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch(KeyStoreException e){
            e.printStackTrace();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }catch(KeyManagementException e){
            e.printStackTrace();
        }

        return null;
    }

}
