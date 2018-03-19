package com.example.accepted.acceptedtalentplanet;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;

/**
 * Created by Accepted on 2018-03-19.
 */

public class pictureExpand extends AppCompatActivity {

    LinearLayout ll_CloseContainer;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        String type = getIntent().getStringExtra("Activity");
        String UserID = getIntent().getStringExtra("userID");
        setContentView(R.layout.pictureexpand_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ((ImageView) findViewById(R.id.iv_Close_pictureExpand)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if(type.equals("Profile")){
            Bitmap bitmap = SaveSharedPreference.getMyPicture();
            if(bitmap != null){
                ((ImageView) findViewById(R.id.iv_Picture_pictureExpand)).setImageBitmap(bitmap);
            }
        }else {

            String fileData = "Tk9EQVRB";
            try {
                String dbName = "/accepted.db";
                SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(mContext.getFilesDir() + dbName, null);

                String selectPicture = "SELECT PICTURE FROM TB_IMAGES WHERE MASTER_ID = '" + SaveSharedPreference.getUserId(mContext) + "' AND USER_ID = '" + UserID + "'";
                Log.d("image query", selectPicture);
                Cursor cursor = sqLiteDatabase.rawQuery(selectPicture, null);

                cursor.moveToFirst();

                fileData = cursor.getString(0);

                cursor.close();
                sqLiteDatabase.close();
            } catch (CursorIndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (!fileData.equals("Tk9EQVRB")) {
                ((ImageView) findViewById(R.id.iv_Picture_pictureExpand)).setImageBitmap(SaveSharedPreference.StringToBitMap(fileData));
            }
        }

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
