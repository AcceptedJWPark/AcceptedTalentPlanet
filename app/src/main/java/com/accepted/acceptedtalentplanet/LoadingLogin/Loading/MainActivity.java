package com.accepted.acceptedtalentplanet.LoadingLogin.Loading;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.accepted.acceptedtalentplanet.GeoPoint;
import com.accepted.acceptedtalentplanet.MyTalent;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.VolleySingleton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.accepted.acceptedtalentplanet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-09-20.
 */

public class MainActivity extends AppCompatActivity {

    Context mContext;

    SQLiteDatabase sqliteDatabase;


    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_start);

        mContext = getApplicationContext();

        String dbName = "/accepted.db";
        sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + dbName, null);
        Log.d("db path = ", getFilesDir() + dbName);


        String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS TB_CHAT_LOG (MESSAGE_ID INTEGER PRIMARY KEY, ROOM_ID INTEGER, MASTER_ID TEXT, USER_ID TEXT, CONTENT TEXT, CREATION_DATE TEXT, READED_FLAG TEXT)";
        sqliteDatabase.execSQL(sqlCreateTbl);

        String sqlCreateTbl2 = "CREATE TABLE IF NOT EXISTS TB_CHAT_ROOM (ROOM_ID INTEGER, USER_ID TEXT, USER_NAME TEXT, MASTER_ID TEXT, START_MESSAGE_ID INTEGER, CREATION_DATE TEXT, LAST_UPDATE_DATE TEXT, ACTIVATE_FLAG TEXT, FILE_PATH TEXT, PRIMARY KEY(ROOM_ID, USER_ID, MASTER_ID))";
        sqliteDatabase.execSQL(sqlCreateTbl2);

        String sqlCreateTbl3 = "CREATE TABLE IF NOT EXISTS TB_FRIEND_LIST (MASTER_ID TEXT, FRIEND_ID TEXT, TALENT_TYPE TEXT, PRIMARY KEY(MASTER_ID, FRIEND_ID, TALENT_TYPE))";
        sqliteDatabase.execSQL(sqlCreateTbl3);

        String sqlCreateTbl4 = "CREATE TABLE IF NOT EXISTS TB_FCM_TOKEN (TOKEN TEXT)";
        sqliteDatabase.execSQL(sqlCreateTbl4);

        String sqlCreateTbl6 = "CREATE TABLE IF NOT EXISTS TB_GRANT (USER_ID TEXT PRIMARY KEY, MESSAGE_GRANT INT, CONDITION_GRANT INT, ANSWER_GRANT INT)";
        sqliteDatabase.execSQL(sqlCreateTbl6);

        String sqlCreateTbl7 = "CREATE TABLE IF NOT EXISTS TB_READED_INTEREST (USER_ID TEXT, TALENT_ID INT, PRIMARY KEY(USER_ID, TALENT_ID))";
        sqliteDatabase.execSQL(sqlCreateTbl7);

        sqliteDatabase.close();

        if(SaveSharedPreference.getFirstLoadingFlag(mContext)){
            Intent intent = new Intent(getBaseContext(), com.accepted.acceptedtalentplanet.CustomerService.Introduction.MainActivity.class);
            intent.putExtra("FirstLoading", true);
            startActivity(intent);
            finish();
        }else {
            startLoading();
        }
    }

    private void startLoading()
    {
        getMyTalent();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;

                if (SaveSharedPreference.getUserId(MainActivity.this).length() == 0) {
                    intent = new Intent(getBaseContext(), com.accepted.acceptedtalentplanet.LoadingLogin.Login.MainActivity.class);
                } else {
                    intent = new Intent(getBaseContext(), com.accepted.acceptedtalentplanet.TalentSharing.MainActivity.class);
                    intent.putExtra("Activity", true);
                }

                startActivity(intent);
                finish();
            }
        },3000);
    }

    public void getMyTalent(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "TalentRegist/getMyTalent.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String status = obj.getString("STATUS_FLAG");

                        if(status.equals("C") && obj.getString("TALENT_FLAG").equals("Y")){
                            status = (obj.getString("TARGET_COMP_FLAG").equals("C")) ? status : "M";
                        }
                        GeoPoint gpPoint = new GeoPoint(Double.parseDouble(obj.getString("GP_LAT")), Double.parseDouble(obj.getString("GP_LNG")));

                        MyTalent talent = new MyTalent();
                        talent.setMyTalent(obj.getString("TALENT_KEYWORD1"), obj.getString("TALENT_KEYWORD2"), obj.getString("TALENT_KEYWORD3"), obj.getString("LOCATION"),  obj.getString("T_POINT"), obj.getString("LEVEL"), gpPoint);
                        talent.setStatus(status);
                        talent.setTalentID(obj.getString("seq"));
                        if(obj.getString("TALENT_FLAG").equals("Y")){
                            SaveSharedPreference.setGiveTalentData(mContext, talent);
                        }else{
                            SaveSharedPreference.setTakeTalentData(mContext, talent);
                        }
                    }
                    getMyTalentPoint();

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", SaveSharedPreference.getUserId(mContext));


                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void getMyTalentPoint(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/getMyTalentPoint.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    int talentPoint = 0;
                    if(response.length() != 0) {
                        JSONObject obj = new JSONObject(response);
                        talentPoint = Integer.parseInt(obj.getString("TALENT_POINT"));
                    }
                    SaveSharedPreference.setPrefTalentPoint(mContext, talentPoint);
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd hh:mm:ss");

                    getMyPicture();

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", SaveSharedPreference.getUserId(mContext));


                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void getMyPicture(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/getMyPicture.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    if(response.length() != 0) {

                        JSONObject obj = new JSONObject(response);
                        if(!obj.getString("S_FILE_PATH").equals("NODATA")){
                            SaveSharedPreference.setMyPicturePath(obj.getString("S_FILE_PATH"), obj.getString("FILE_PATH"));
                        }
                    }
                    Log.d("getPicture", "completed");

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", SaveSharedPreference.getUserId(mContext));
               return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }



}
