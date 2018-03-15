package com.example.accepted.acceptedtalentplanet.LoadingLogin.Loading;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-09-20.
 */

public class MainActivity extends AppCompatActivity {

    Context mContext;
    int interval = 100;
    final int maxInterval = 500;

    boolean running = false;

    SQLiteDatabase sqliteDatabase;
    String lastMessageID;

    Thread thread1;

    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_start);


        mContext = getApplicationContext();

        String dbName = "/accepted.db";
        sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + dbName, null);
        Log.d("db path = ", getFilesDir() + dbName);


        String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS TB_CHAT_LOG (MESSAGE_ID INTEGER, ROOM_ID INTEGER, USER_ID TEXT, CONTENT TEXT, CREATION_DATE TEXT, READED_FLAG TEXT)";
        sqliteDatabase.execSQL(sqlCreateTbl);

        String sqlCreateTbl2 = "CREATE TABLE IF NOT EXISTS TB_CHAT_ROOM (ROOM_ID INTEGER PRIMARY KEY, USER_ID TEXT UNIQUE, USER_NAME TEXT, START_MESSAGE_ID INTEGER, CREATION_DATE TEXT, LAST_UPDATE_DATE TEXT, ACTIVATE_FLAG TEXT, PICTURE BLOB)";
        sqliteDatabase.execSQL(sqlCreateTbl2);

        startLoading();

    }

    private void startLoading()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                getMyTalent();
                if(SaveSharedPreference.getUserId(MainActivity.this).length() == 0) {
                    intent = new Intent(getBaseContext(), com.example.accepted.acceptedtalentplanet.LoadingLogin.Login.MainActivity.class);
                }else{
                    intent = new Intent(getBaseContext(), com.example.accepted.acceptedtalentplanet.TalentSharing.MainActivity.class);

                }
                    startActivity(intent);
                    finish();

            }
        },1000);
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

                        GeoPoint gpPoint = new GeoPoint(Double.parseDouble(obj.getString("GP_LAT")), Double.parseDouble(obj.getString("GP_LNG")));

                        MyTalent talent = new MyTalent();
                        talent.setMyTalent(obj.getString("TALENT_KEYWORD1"), obj.getString("TALENT_KEYWORD2"), obj.getString("TALENT_KEYWORD3"), obj.getString("LOCATION"),  obj.getString("T_POINT"), obj.getString("LEVEL"), gpPoint);
                        talent.setStatus(obj.getString("STATUS_FLAG"));
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
                    getMyPicture();

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

    public void getMyPicture(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/getMyPicture.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    if(response.length() != 0) {
                        JSONObject obj = new JSONObject(response);
                        if(!obj.getString("FILE_DATA").equals("Tk9EQVRB")){
                            SaveSharedPreference.setMyPicture(obj.getString("FILE_DATA"));
                        }
                    }
                    Log.d("getPicture", "completed");

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

    public void retrieveMessage(){


        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Chat/retrieveMessage.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray o = new JSONArray(response);
                    int i = 0;
                    for(i = 0; i < o.length(); i++) {
                        JSONObject obj = o.getJSONObject(i);
                        int roomID = SaveSharedPreference.makeChatRoom(mContext, obj.getString("USER_ID"), obj.getString("USER_NAME"));
                        sqliteDatabase.execSQL("INSERT INTO TB_CHAT_LOG(MESSAGE_ID, ROOM_ID, USER_ID, CONTENT, CREATION_DATE, READED_FLAG) VALUES (" + obj.getString("MESSAGE_ID") + ", "+roomID+", '"+obj.getString("USER_ID")+"','"+ obj.getString("CONTENT").replace("'", "''")+"','" + obj.getString("CREATION_DATE_STRING") + "', 'N')");

                        interval = 100;
                        lastMessageID = obj.getString("MESSAGE_ID");
                    }

                    if(i == 0)
                    {
                        if(interval < maxInterval){
                            interval += 50;
                        }
                    }

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
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
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", SaveSharedPreference.getUserId(mContext));
                params.put("lastMessageID", lastMessageID);

                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    protected void onResume(){
        super.onResume();

        running = true;

        String selectMaxData = "SELECT IFNULL(MAX(A.MESSAGE_ID), 0) AS MESSAGE_ID FROM TB_CHAT_LOG A WHERE A.USER_ID != '" + SaveSharedPreference.getUserId(mContext) + "'";
        Cursor cursor = sqliteDatabase.rawQuery(selectMaxData, null);
        cursor.moveToFirst();
        lastMessageID = String.valueOf(cursor.getInt(0));


        thread1 = new PollingThread();
        thread1.start();
    }

    protected void onPause(){
        super.onPause();

        running = true;
    }

    class PollingThread extends Thread {
        @Override
        public void run(){
            while(running){
                retrieveMessage();
                try {
                    Thread.sleep(interval);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        thread1.interrupt();
    }

}
