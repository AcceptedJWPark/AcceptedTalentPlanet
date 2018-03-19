package com.example.accepted.acceptedtalentplanet;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Accepted on 2018-03-19.
 */

public class pictureExpand extends AppCompatActivity {

    LinearLayout ll_CloseContainer;
    ImageView iv_Picture_pictureExpand;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        setContentView(R.layout.pictureexpand_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ((ImageView) findViewById(R.id.iv_Close_pictureExpand)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
}
