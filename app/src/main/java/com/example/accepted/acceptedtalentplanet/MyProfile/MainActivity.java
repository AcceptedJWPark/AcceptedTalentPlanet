package com.example.accepted.acceptedtalentplanet.MyProfile;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.example.accepted.acceptedtalentplanet.BuildConfig;
import com.example.accepted.acceptedtalentplanet.MyProfileData;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleyMultipartRequest;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;
import com.example.accepted.acceptedtalentplanet.pictureExpand;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MainActivity extends AppCompatActivity {

    public MyProfileData myProfileData;
    private Context mContext;

    private DrawerLayout drawerLayout;
    private View view_DrawerLayout;

    private android.support.v7.widget.Toolbar ll_Toolbar;
    private ImageView iv_DrawerLayoutIcon;
    private ImageView iv_AlarmIcon;

    private Button btn_Save;
    private TextView tv_Divider1;
    private TextView tv_Divider2;

    private RelativeLayout rl_Container;
    private LinearLayout ll_Container1;
    private LinearLayout ll_Container2;
    private LinearLayout ll_Container3;
    private LinearLayout ll_Container4;
    private LinearLayout ll_Container5;
    private LinearLayout ll_Container6;
    private LinearLayout ll_Container7;
    private LinearLayout ll_Container8;
    private LinearLayout ll_SaveContainer;

    private RelativeLayout rl_PictureContainer;

    private boolean genderFlag;
    private boolean birthFlag;
    private boolean jobFlag;
    private CheckBox cb_isShowGender;
    private CheckBox cb_isShowBirth;
    private CheckBox cb_isShowJob;

    private ImageView iv_Picture;
    private ImageView iv_PictureIcon;
    private ImageView iv_CompleteListIcon;

    private EditText et_Job;

    private Uri photoUri;
    private String currentPhotoPath;
    private String mImageCaptureName;
    private final int CAMERA_CODE = 1111;
    private final int GALLERY_CODE = 1112;

    private static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".fileprovider";


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile_activity);
        mContext = getApplicationContext();

        myProfileData = new MyProfileData();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_MyProfile);
        view_DrawerLayout = (View) findViewById(R.id.view_DrawerLayout_MyProfile);

        et_Job = (EditText) findViewById(R.id.et_Job_MyProfile);
        et_Job.setOnFocusChangeListener(new View.OnFocusChangeListener() {
           @Override
           public void onFocusChange(View v, boolean hasFocus) {
             iv_DrawerLayoutIcon = (ImageView) findViewById(R.id.DrawerLayout_OpenIcon);
             iv_AlarmIcon = (ImageView) findViewById(R.id.DrawerLayout_AlarmIcon);
               if(!hasFocus)
               {
                   hideKeyboard(v,mContext);
                   iv_DrawerLayoutIcon.setFocusable(false);
                   iv_DrawerLayoutIcon.setFocusableInTouchMode(false);
                   iv_AlarmIcon.setFocusable(false);
                   iv_AlarmIcon.setFocusableInTouchMode(false);
               }
               else
               {
                   iv_DrawerLayoutIcon.setFocusable(true);
                   iv_DrawerLayoutIcon.setFocusableInTouchMode(true);
                   iv_AlarmIcon.setFocusable(true);
                   iv_AlarmIcon.setFocusableInTouchMode(true);
               }
           }
       });

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("My Profile");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                    DrawerLayout_Open(v,MainActivity.this, drawerLayout, view_DrawerLayout);
                }
        };
        DrawerLayout_ClickEvent(MainActivity.this,mClicklistener);
        getMyProfile();

        ll_Toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.ll_Toolbar_MyProfile);
        rl_Container = (RelativeLayout) findViewById(R.id.rl_Container_MyProfile);
        btn_Save = (Button) findViewById(R.id.btn_Save_MyProfile);
        tv_Divider1 = (TextView) findViewById(R.id.tv_Divider1_MyProfile);
        tv_Divider2 = (TextView) findViewById(R.id.tv_Divider2_MyProfile);
        ll_Container1 = (LinearLayout) findViewById(R.id.ll_Container1_MyProfile);
        ll_Container2 = (LinearLayout) findViewById(R.id.ll_Container2_MyProfile);
        ll_Container3 = (LinearLayout) findViewById(R.id.ll_Container3_MyProfile);
        ll_Container4 = (LinearLayout) findViewById(R.id.ll_Container4_MyProfile);
        ll_Container5 = (LinearLayout) findViewById(R.id.ll_Container5_MyProfile);
        ll_Container6 = (LinearLayout) findViewById(R.id.ll_Container6_MyProfile);
        ll_Container7 = (LinearLayout) findViewById(R.id.ll_Container7_MyProfile);
        ll_Container8 = (LinearLayout) findViewById(R.id.ll_Container8_MyProfile);
        rl_PictureContainer = (RelativeLayout) findViewById(R.id.rl_PictureContainer_MyProfile);

        ll_SaveContainer = (LinearLayout) findViewById(R.id.ll_SaveContainer_MyProfile);

        cb_isShowGender = (CheckBox)findViewById(R.id.cb_isShowGender_MyProfile);
        cb_isShowBirth = (CheckBox)findViewById(R.id.cb_isShowBirth_MyProfile);
        cb_isShowJob = (CheckBox)findViewById(R.id.cb_isShowJob_MyProfile);

        
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int statusBarHeight = getStatusBarHeight();
        int displayHeight_NoStatus = metrics.heightPixels - statusBarHeight;

        int toolbar_height = (int) (displayHeight_NoStatus*0.055);
        int picture_LL_height = (int) (displayHeight_NoStatus*0.188);
        int btn_height = (int) (displayHeight_NoStatus*0.042);
        int Devider_height = (int) (displayHeight_NoStatus*0.045);
        int List_height = (int) (displayHeight_NoStatus*0.075);
        int btnContainer_height =  (displayHeight_NoStatus-(toolbar_height+picture_LL_height+Devider_height*2+List_height*8));

        int TalentResister_Picture_width = (int) (picture_LL_height*0.7);
        int TalentResister_Picture_height = (int) (picture_LL_height*0.7);


        ViewGroup.LayoutParams params1 = ll_Toolbar.getLayoutParams();
        ViewGroup.LayoutParams params2 = rl_Container.getLayoutParams();
        ViewGroup.LayoutParams params3 = btn_Save.getLayoutParams();
        ViewGroup.LayoutParams params4 = tv_Divider1.getLayoutParams();
        ViewGroup.LayoutParams params5 = tv_Divider2.getLayoutParams();
        ViewGroup.LayoutParams params6 = ll_Container1.getLayoutParams();
        ViewGroup.LayoutParams params7 = ll_Container2.getLayoutParams();
        ViewGroup.LayoutParams params8 = ll_Container3.getLayoutParams();
        ViewGroup.LayoutParams params9 = ll_Container4.getLayoutParams();
        ViewGroup.LayoutParams params10 = ll_Container5.getLayoutParams();
        ViewGroup.LayoutParams params11 = ll_Container6.getLayoutParams();
        ViewGroup.LayoutParams params12 = ll_Container7.getLayoutParams();
        ViewGroup.LayoutParams params13 = ll_Container8.getLayoutParams();

        ViewGroup.LayoutParams params14 = rl_PictureContainer.getLayoutParams();
        ViewGroup.LayoutParams params15 = ll_SaveContainer.getLayoutParams();

        params1.height = toolbar_height;
        params2.height = picture_LL_height;
        params3.height = btn_height;
        params4.height = Devider_height;
        params5.height = Devider_height;
        params6.height = List_height;
        params7.height = List_height;
        params8.height = List_height;
        params9.height = List_height;
        params10.height = List_height;
        params11.height = List_height;
        params12.height = List_height;
        params13.height = List_height;

        params14.width = TalentResister_Picture_width;
        params14.height = TalentResister_Picture_height;

        params15.height = btnContainer_height;

        ll_Toolbar.setLayoutParams(params1);
        rl_Container.setLayoutParams(params2);
        btn_Save.setLayoutParams(params3);
        tv_Divider1.setLayoutParams(params4);
        tv_Divider2.setLayoutParams(params5);
        ll_Container1.setLayoutParams(params6);
        ll_Container2.setLayoutParams(params7);
        ll_Container3.setLayoutParams(params8);
        ll_Container4.setLayoutParams(params9);
        ll_Container5.setLayoutParams(params10);
        ll_Container6.setLayoutParams(params11);
        ll_Container7.setLayoutParams(params12);
        ll_Container8.setLayoutParams(params13);

        rl_PictureContainer.setLayoutParams(params14);
        ll_SaveContainer.setLayoutParams(params15);


        iv_CompleteListIcon = (ImageView) findViewById(R.id.iv_CompleteListIcon_MyProfile);
        iv_CompleteListIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.MyProfile.CompleteList.MainActivity.class);
                startActivity(i);
            }
        });

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMyProfile();
            }
        });

        final AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(MainActivity.this);

        iv_Picture = (ImageView) findViewById(R.id.iv_Picture_MyProfile);
        iv_Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, pictureExpand.class);
                intent.putExtra("Activity", "Profile");
                startActivity(intent);
            }
        });


        iv_PictureIcon = (ImageView) findViewById(R.id.iv_PictureIcon_MyProfile);
        iv_PictureIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT > 22){
                    requestPermissions(new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA}, 1);
                }
                AlarmDeleteDialog.setMessage("사진을 가져올 곳을 선택해주세요.")
                        .setPositiveButton("카메라", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectPhoto();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("앨범", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectGallery();
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = AlarmDeleteDialog.create();
                alertDialog.show();
            }
        });




        View.OnClickListener listener_give = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                i.putExtra("GiveFlag", true);
                startActivity(i);
            }
        };

        View.OnClickListener listener_take = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                i.putExtra("GiveFlag", false);
                startActivity(i);
            }
        };

        ((TextView)findViewById(R.id.tv_GiveTalent1_MyProfile)).setOnClickListener(listener_give);
        ((TextView)findViewById(R.id.tv_GiveTalent2_MyProfile)).setOnClickListener(listener_give);
        ((TextView)findViewById(R.id.tv_GiveTalent3_MyProfile)).setOnClickListener(listener_give);
        ((TextView)findViewById(R.id.tv_TakeTalent1_MyProfile)).setOnClickListener(listener_take);
        ((TextView)findViewById(R.id.tv_TakeTalent2_MyProfile)).setOnClickListener(listener_take);
        ((TextView)findViewById(R.id.tv_TakeTalent3_MyProfile)).setOnClickListener(listener_take);
    }



    public void getMyProfile(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Profile/getMyProfileInfo.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String Gender = (obj.getString("GENDER").equals("남")) ? "남자" : "여자";
                    String[] TalentGive = {obj.getString("G_TALENT1"), obj.getString("G_TALENT2"), obj.getString("G_TALENT3")};
                    String[] TalentTake = {obj.getString("T_TALENT1"), obj.getString("T_TALENT2"), obj.getString("T_TALENT3")};
                    genderFlag = (obj.getString("GENDER_FLAG").equals("Y"))?true:false;
                    birthFlag = (obj.getString("BIRTH_FLAG").equals("Y"))?true:false;
                    jobFlag = (obj.getString("JOB_FLAG").equals("Y"))?true:false;
                    myProfileData.setMyProfile(obj.getString("USER_NAME"), Gender, obj.getString("USER_BIRTH"), obj.getString("JOB"), TalentGive, TalentTake, genderFlag, birthFlag, jobFlag);
                    Log.d("result", obj.toString());
                    String point = obj.getString("TALENT_POINT") + "P";
                    ((TextView)findViewById(R.id.tv_Email_MyProfile)).setText(SaveSharedPreference.getUserId(mContext));
                    ((TextView)findViewById(R.id.tv_Name_MyProfile)).setText(myProfileData.getUserName());
                    ((TextView)findViewById(R.id.tv_Gender_MyProfile)).setText(myProfileData.getGender());
                    ((TextView)findViewById(R.id.tv_Birthday_MyProfile)).setText(myProfileData.getBirth());
                    ((TextView)findViewById(R.id.et_Job_MyProfile)).setText(myProfileData.getJob());
                    ((TextView)findViewById(R.id.tv_GiveTalent1_MyProfile)).setText(TalentGive[0]);
                    ((TextView)findViewById(R.id.tv_GiveTalent2_MyProfile)).setText(TalentGive[1]);
                    ((TextView)findViewById(R.id.tv_GiveTalent3_MyProfile)).setText(TalentGive[2]);
                    ((TextView)findViewById(R.id.tv_TakeTalent1_MyProfile)).setText(TalentTake[0]);
                    ((TextView)findViewById(R.id.tv_TakeTalent2_MyProfile)).setText(TalentTake[1]);
                    ((TextView)findViewById(R.id.tv_TakeTalent3_MyProfile)).setText(TalentTake[2]);
                    ((TextView)findViewById(R.id.tv_Point_MyProfile)).setText(point);

                    cb_isShowGender.setChecked(!genderFlag);
                    cb_isShowBirth.setChecked(!birthFlag);
                    cb_isShowJob.setChecked(!jobFlag);
                    Bitmap bitmap = SaveSharedPreference.getMyPicture();
                    if(bitmap != null){
                        iv_Picture.setImageBitmap(bitmap);
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void saveMyProfile(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Profile/saveMyProfileInfo.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("result = ", obj.toString());
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "프로필 저장이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    }

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener()) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                et_Job = (EditText)findViewById(R.id.et_Job_MyProfile);

                genderFlag = !cb_isShowGender.isChecked();
                birthFlag = !cb_isShowBirth.isChecked();
                jobFlag = !cb_isShowJob.isChecked();

                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("job", et_Job.getText().toString());
                params.put("genderFlag", (genderFlag)?"Y":"N");
                params.put("birthFlag", (birthFlag)?"Y":"N");
                params.put("jobFlag", (jobFlag)?"Y":"N");
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    private void selectPhoto(){
        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state)){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {

                File photoFile = null;
                try{
                    photoFile = createImageFile();
                }catch (IOException e){
                    e.printStackTrace();
                }

                if(photoFile != null){
                    photoUri = FileProvider.getUriForFile(mContext, AUTHORITY, photoFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    startActivityForResult(intent, CAMERA_CODE);
                }
            }
        }
    }

    private File createImageFile() throws IOException {
        File dir = new File(Environment.getExternalStorageDirectory() + "/path/");
        if(!dir.exists()){
            dir.mkdirs();
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        mImageCaptureName = timeStamp + ".png";

        File storageDir = new File(Environment.getExternalStorageDirectory() + "/path/" + mImageCaptureName);
        currentPhotoPath = storageDir.getAbsolutePath();

        return storageDir;
    }

    private void selectGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            switch(requestCode){
                case GALLERY_CODE:
                    sendPicture(data.getData());
                    break;

                case CAMERA_CODE:
                    getPictureForPhoto();
                    break;
                default:
                    break;
            }
        }
    }

    private void sendPicture(Uri imgUri){
        String imagePath = getRealPathFromURI(imgUri);
        Log.d("image Path = ", imagePath);
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(imagePath);
        }catch (IOException e){
            e.printStackTrace();
        }

        int exifOrientation;
        int exifDegree;

        if(exif != null){
            exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            exifDegree = exifOrientationToDegrees(exifOrientation);
        }else{
            exifDegree = 0;
        }

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imagePath, options); //MediaStore.Images.Media.getBitmap(this.getContentResolver(), imgUri);

            options.inSampleSize = setSimpleSize(options, 960, 720);

            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath, options);
            bitmap = rotate(bitmap, exifDegree);
            if(bitmap == null){
                Log.d("bitmap = ", "null");
            }
            iv_Picture.setImageBitmap(bitmap);
            SaveSharedPreference.setMyPicture(bitmap);

            uploadBitmap(bitmap);
        }catch (Exception e){
                    e.printStackTrace();//bitmap = rotate(bitmap, exifDegree);
        }

    }

    private void getPictureForPhoto(){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(currentPhotoPath, options);

        options.inSampleSize = setSimpleSize(options, 960, 720);

        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, options);

        ExifInterface exif = null;
        try{
            exif = new ExifInterface(currentPhotoPath);
        }catch (IOException e){
            e.printStackTrace();
        }

        int exifOrientation;
        int exifDegree;

        if(exif != null){
            exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            exifDegree = exifOrientationToDegrees(exifOrientation);
        }else{
            exifDegree = 0;
        }

        bitmap = rotate(bitmap, exifDegree);

        iv_Picture.setImageBitmap(bitmap);
        SaveSharedPreference.setMyPicture(bitmap);
        uploadBitmap(bitmap);
    }

    private int exifOrientationToDegrees(int exifOrientation){
        if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_90){
            return 90;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_180){
            return 180;
        }else if(exifOrientation == ExifInterface.ORIENTATION_ROTATE_270){
            return 270;
        }

        return 0;
    }

    private Bitmap rotate(Bitmap src, float degree){
        Matrix matrix = new Matrix();

        matrix.postRotate(degree);

        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    private String getRealPathFromURI(Uri contentUri){
        int column_index = 0;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        }

        return cursor.getString(column_index);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults){
        switch(requestCode){
            case 1:
            {
                if(!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED))
                {
                    Toast.makeText(mContext, "permission denied", Toast.LENGTH_SHORT);
                }


            }
        }
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private void uploadBitmap(final Bitmap bitmap){
        final String tags = "UserProfilePicture";

        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Profile/savePicture.do", new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                try {
                    JSONObject obj = new JSONObject(new String(response.data));
                    Toast.makeText(mContext, obj.getString("result"), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("tags", tags);
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData(){
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("pic", new DataPart(imagename + ".png", getFileDataFromDrawable(bitmap)));
                return params;
            }
        };

        try {
            for(Map.Entry<String, String> elem : volleyMultipartRequest.getHeaders().entrySet()){
                Log.d("header = " , String.format("키 : %s, 값 : %s", elem.getKey(), elem.getValue()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        VolleySingleton.getInstance(mContext).getRequestQueue().add(volleyMultipartRequest);

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

    private int setSimpleSize(BitmapFactory.Options options, int requestWidth, int requestHeight){
        // 이미지 사이즈를 체크할 원본 이미지 가로/세로 사이즈를 임시 변수에 대입.
        int originalWidth = options.outWidth;
        int originalHeight = options.outHeight;

        // 원본 이미지 비율인 1로 초기화
        int size = 1;

        // 해상도가 깨지지 않을만한 요구되는 사이즈까지 2의 배수의 값으로 원본 이미지를 나눈다.
        while(requestWidth < originalWidth || requestHeight < originalHeight){
            originalWidth = originalWidth / 2;
            originalHeight = originalHeight / 2;

            size = size * 2;
        }
        return size;
    }


}
