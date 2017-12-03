package com.example.accepted.acceptedtalentplanet.InterestingList;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.interested_popup);
        String talentID = getIntent().getStringExtra("TalentID");
        mContext = getApplicationContext();
        talentSharing_popupclosebtn = (ImageView) findViewById(R.id.TalentSharing_pupupclosebtn);
        talentSharing_popupclosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterestingList_Popup.this.finish();
            }
        });


        Popup_ProgressorCancel = findViewById(R.id.Popup_ProgressorCancel);
        final AlertDialog.Builder ProgressorCancelPopup = new AlertDialog.Builder(this);

        Popup_ProgressorCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

               /* mInterestingList_Dialog = new InterestingList_Dialog(InterestingList_Popup.this, "재능 진행 또는 관심 취소를 진행해주세요!", ProgressListener, CancelListener);
                mInterestingList_Dialog.show();
*/

              ProgressorCancelPopup.setMessage("재능 진행 또는 관심 취소를 진행해주세요!")
                        .setPositiveButton("진행하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"진행 하기 클릭",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("취소하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"취소 하기 클릭",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = ProgressorCancelPopup.create();
                alertDialog.show();
            }
        });

        getProfileInfo(talentID);

    }
    /*
    public void btnClicked(View v){
        switch (v.getId()) {
            case R.id.Popup_ProgressorCancel:
                mInterestingList_Dialog = new InterestingList_Dialog(getApplicationContext(), "재능 진행 또는 관심 취소를 진행해주세요!", ProgressListener, CancelListener);
                mInterestingList_Dialog.show();
                break;
        }
    }*/

        private View.OnClickListener ProgressListener = new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "진행버튼 클릭",
                        Toast.LENGTH_SHORT).show();

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
