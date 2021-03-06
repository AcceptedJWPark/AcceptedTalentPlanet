package com.accepted.acceptedtalentplanet;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.accepted.acceptedtalentplanet.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-03-19.
 */

public class pictureExpand extends AppCompatActivity {

    LinearLayout ll_CloseContainer;
    Context mContext;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        String type = getIntent().getStringExtra("Activity");
        userID = getIntent().getStringExtra("userID");
        setContentView(R.layout.pictureexpand_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ((ImageView) findViewById(R.id.iv_Close_pictureExpand)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getPicture();

        ll_CloseContainer = (LinearLayout) findViewById(R.id.ll_CloseContainer_pictureExpand);
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int statusBarHeight = getStatusBarHeight();
        int displayHeight_NoStatus = metrics.heightPixels - statusBarHeight;
        int ll_CloseContainer_height = (int) (displayHeight_NoStatus*0.04);

        ViewGroup.LayoutParams params1 = ll_CloseContainer.getLayoutParams();
        params1.height = ll_CloseContainer_height;
        ll_CloseContainer.setLayoutParams(params1);
    }

    public int getStatusBarHeight(){
        int statusBarHeight = 0;
        int screenSizeType = ((mContext).getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK);
        if(screenSizeType != Configuration.SCREENLAYOUT_SIZE_XLARGE){
            int resourceId = mContext.getResources().getIdentifier("status_bar_height","dimen","android");
            if(resourceId>0)
            {
                statusBarHeight = mContext.getResources().getDimensionPixelOffset(resourceId);
            }
        }
        return statusBarHeight;
    }

    public void getPicture() {
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/getMyPicture.do", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("image path2", SaveSharedPreference.getImageUri() + obj.getString("FILE_PATH"));
                    if(!obj.getString("FILE_PATH").equals("NODATA")){
                        Log.d("image path", SaveSharedPreference.getImageUri() + obj.getString("FILE_PATH"));
                        Glide.with(mContext).load(SaveSharedPreference.getImageUri() + obj.getString("FILE_PATH")).into((ImageView) findViewById(R.id.iv_Picture_pictureExpand));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("userID", userID);

                return params;
            }
        };


        postRequestQueue.add(postJsonRequest);

    }
}
