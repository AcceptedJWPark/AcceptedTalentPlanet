package com.example.accepted.acceptedtalentplanet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.example.accepted.acceptedtalentplanet.Alarm.ListItem;
import com.example.accepted.acceptedtalentplanet.Messanger.List.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static android.text.TextUtils.isEmpty;

/**
 * Created by kwonhong on 2017-10-14.
 */

public class SaveSharedPreference{
    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_ID = "userid";
    static final String SERVER_IP = "https://13.124.141.242/Accepted/";
    static final String SERVER_IP2 = "https://221.162.94.43:8443/Accepted/";
    static final String PREF_GIVE_DATA = "giveData";
    static final String PREF_TAKE_DATA = "takeData";
    static final String PREF_GEO_POINT = "geoPoint";
    static final String PREF_FRIEND_ARRAY = "friendList";
    static final String PREF_TALENT_POINT = "talentPoint";
    static final String PREF_FCM_TOKEN = "fcmToken";
    static final String PREF_ALARM_ARRAY = "alarmArray";
    static final String PREF_MESSAGE_PUSH_GRANT = "messagePushGrant";
    static final String PREF_CONDITION_PUSH_GRANT = "conditionPushGrant";
    static final String PREF_ANSWER_PUSH_GRANT = "answerPushGrant";
    static Bitmap myPicture = null;

    static DrawerLayout slidingMenuDL;
    static View drawerView;


    static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefUsrName(Context ctx, String userName){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static void setPrefUsrId(Context ctx, String useId){
        Log.d("perf userid", useId);
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, useId);
        editor.commit();
    }

    public static void setPrefTalentPoint(Context ctx, int point){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_TALENT_POINT, point);
        editor.commit();
    }

    public static void setPrefFcmToken(Context ctx, String token){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";

        try{
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);
            String sqlUpsert = "INSERT OR REPLACE INTO TB_FCM_TOKEN(TOKEN) VALUES ('"+token+"')";
            sqliteDatabase.execSQL(sqlUpsert);

            Log.d("insert friend", sqlUpsert);
            sqliteDatabase.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    public static void removePrefFcmToken(Context ctx){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";

        try{
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);
            String sqlUpsert = "DELETE FROM TB_FCM_TOKEN";
            sqliteDatabase.execSQL(sqlUpsert);

            sqliteDatabase.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    public static void setPrefPushGrant(Context ctx, boolean messageGrant, boolean conditionGrant, boolean answerGrant){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";

        try{
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);
            String sqlUpsert = "INSERT OR REPLACE INTO TB_GRANT(USER_ID, MESSAGE_GRANT, CONDITION_GRANT, ANSWER_GRANT) VALUES ('"+getUserId(ctx)+"', "+ ((messageGrant) ? 1 : 0) + ", " + ((conditionGrant) ? 1 : 0)  + ", " + ((answerGrant) ? 1 : 0)  + ")";
            sqliteDatabase.execSQL(sqlUpsert);

            Log.d("grant upsert = ", sqlUpsert);

            sqliteDatabase.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    public static int getTalentPoint(Context ctx){
        return getSharedPreferences(ctx).getInt(PREF_TALENT_POINT, 0);
    }

    public static String getUserName(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserId(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }

    public static void clearUserInfo(Context ctx){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
        myPicture = null;
    }

    public static String getFcmToken(Context ctx){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";
        String token = null;
        try{
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);
            String sqlSelect = "SELECT * FROM TB_FCM_TOKEN";
            Cursor cursor = sqliteDatabase.rawQuery(sqlSelect, null);
            cursor.moveToFirst();

            Log.d("select TOKEN", sqlSelect);

            token = cursor.getString(0);

            cursor.close();

            sqliteDatabase.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return token;
    }

    public static boolean getMessagePushGrant(Context ctx){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";
        boolean pushGrant = true;
        try{
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);
            String sqlSelect = "SELECT MESSAGE_GRANT FROM TB_GRANT WHERE USER_ID = '" + getUserId(ctx) + "'";
            Cursor cursor = sqliteDatabase.rawQuery(sqlSelect, null);
            cursor.moveToFirst();
            Log.d("message grant query = ", sqlSelect);
            Log.d("message grant = ", cursor.getInt(0) + "");

            pushGrant = (cursor.getInt(0) > 0);

            cursor.close();

            sqliteDatabase.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return pushGrant;
    }

    public static boolean getConditionPushGrant(Context ctx){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";
        boolean pushGrant = true;
        try{
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);
            String sqlSelect = "SELECT CONDITION_GRANT FROM TB_GRANT WHERE USER_ID = '" + getUserId(ctx) + "'";
            Cursor cursor = sqliteDatabase.rawQuery(sqlSelect, null);
            cursor.moveToFirst();

            pushGrant = (cursor.getInt(0) > 0);

            cursor.close();

            sqliteDatabase.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return pushGrant;
    }

    public static boolean getAnswerPushGrant(Context ctx){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";
        boolean pushGrant = true;
        try{
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);
            String sqlSelect = "SELECT ANSWER_GRANT FROM TB_GRANT WHERE USER_ID = '" + getUserId(ctx) + "'";
            Cursor cursor = sqliteDatabase.rawQuery(sqlSelect, null);
            cursor.moveToFirst();

            pushGrant = (cursor.getInt(0) > 0);

            cursor.close();

            sqliteDatabase.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return pushGrant;
    }

    public static boolean checkSession(){
        return !isEmpty(PREF_USER_ID);
    }

    public static String getServerIp(){
        return SERVER_IP;
    }

    public static String getLevel(String Level) {
        switch (Level) {
            case "1":
                return "시작단계(Beginner)";
            case "2":
                return "초급(Elementary)";
            case "3":
                return "중급(Intermediate)";
            case "4":
                return "상급(Master)";
            default:
                return "전문가(Professional)";
        }
    }

    public static void setGiveTalentData(Context ctx, MyTalent Data){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(Data);
        editor.putString(PREF_GIVE_DATA, json);
        editor.commit();
    }

    public static MyTalent getGiveTalentData(Context ctx){
        Gson gson = new Gson();
        String json = getSharedPreferences(ctx).getString(PREF_GIVE_DATA, "");
        MyTalent data = gson.fromJson(json, MyTalent.class);
        return data;
    }

    public static void setTakeTalentData(Context ctx, MyTalent Data){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(Data);
        editor.putString(PREF_TAKE_DATA, json);
        editor.commit();
    }

    public static MyTalent getTakeTalentData(Context ctx){
        Gson gson = new Gson();
        String json = getSharedPreferences(ctx).getString(PREF_TAKE_DATA, "");
        MyTalent data = gson.fromJson(json, MyTalent.class);
        return data;
    }

    public static void setGeoPointArr(Context ctx, GeoPoint Data){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(Data);
        editor.putString(PREF_GEO_POINT, json);
        editor.commit();
    }

    public static GeoPoint getGeoPointArr(Context ctx){
        Gson gson = new Gson();

        String json = getSharedPreferences(ctx).getString(PREF_GEO_POINT, "");
        GeoPoint data = gson.fromJson(json, GeoPoint.class);

        return data;
    }

    public static void setPrefAlarmArray(Context ctx, ArrayList<ListItem> arrayList){
        SharedPreferences.Editor editor  = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(PREF_ALARM_ARRAY, json);
        editor.commit();
    }

    public static ArrayList<ListItem> getPrefAlarmArry(Context ctx){
        Gson gson = new Gson();
        ArrayList<ListItem> arrayList;
        String json = getSharedPreferences(ctx).getString(PREF_ALARM_ARRAY, "");
        arrayList = gson.fromJson(json, new TypeToken<ArrayList<ListItem>>(){}.getType());
        return arrayList;
    }

    public static void putFriend(Context ctx, Friend friend){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";

        try{
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);
            String sqlUpsert = "INSERT OR REPLACE INTO TB_FRIEND_LIST(MASTER_ID, FRIEND_ID, TALENT_TYPE) VALUES ('" + getUserId(ctx) + "', '"+ friend.getUserID() + "', '" + friend.getPartnerTalentType() +"')";
            sqliteDatabase.execSQL(sqlUpsert);

            Log.d("insert friend", sqlUpsert);
            sqliteDatabase.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    public static void removeFriend(Context ctx, Friend friend){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";

        try{
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);
            String sqlDelete = "DELETE FROM TB_FRIEND_LIST WHERE MASTER_ID ='" + getUserId(ctx) + "' AND FRIEND_ID = '"+ friend.getUserID() + "' AND TALENT_TYPE = '" + friend.getPartnerTalentType() +"'";
            Log.d("delete friend", sqlDelete);
            sqliteDatabase.execSQL(sqlDelete);

            sqliteDatabase.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Friend> getFriendList(Context ctx){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";
        ArrayList<Friend> list = new ArrayList<>();
        try{
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);
            String sqlSelect = "SELECT * FROM TB_FRIEND_LIST WHERE MASTER_ID = '" + getUserId(ctx) + "'";
            Cursor cursor = sqliteDatabase.rawQuery(sqlSelect, null);
            cursor.moveToFirst();

            Log.d("select friend", sqlSelect);

            while(!cursor.isAfterLast()){
                list.add(new Friend(cursor.getString(1), cursor.getString(2)));
                cursor.moveToNext();
            }

            cursor.close();

            sqliteDatabase.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        return list;
    }


    public static void DrawerLayout_ClickEvent(Context context, View.OnClickListener listener){



        ((LinearLayout) ((Activity)context).findViewById(R.id.DrawerLayout_OpenIcon)).setOnClickListener(listener);
        ((ImageView) ((Activity)context).findViewById(R.id.DrawerCloseImg)).setOnClickListener(listener);
        ((LinearLayout) ((Activity)context).findViewById(R.id.DrawerLayout_AlarmIcon)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_Profile)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_MyTalentResister)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_MyTalentProcess)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_TSharing)).setOnClickListener(listener);
//        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_TalentSearching)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_SharingList)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_MessageBox)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_FriendList)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_ServiceCenter)).setOnClickListener(listener);
        ((TextView) ((Activity)context).findViewById(R.id.SlidingMenu_System)).setOnClickListener(listener);
        ((LinearLayout) ((Activity)context).findViewById(R.id.SlidingMenu_LogOut)).setOnClickListener(listener);

    }
    public static void DrawerLayout_Open(View view, final Context mContext, DrawerLayout drawerLayout, View drawerView) {

        Intent i;

        switch (view.getId())
        {

            case R.id.DrawerLayout_OpenIcon:
            {
                drawerLayout.openDrawer(drawerView);
                break;
            }

            case R.id.DrawerCloseImg :
            {
                drawerLayout.closeDrawer(drawerView);
                break;
            }

            case R.id.DrawerLayout_AlarmIcon :
            {
                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.Alarm.MainActivity.class);
                mContext.startActivity(i);

                break;
            }

            case R.id.SlidingMenu_Profile : {
                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.MyProfile.MainActivity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_MyTalentResister : {
                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentResister.MainActivity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_MyTalentProcess : {
                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentCondition.MainActivity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_TSharing : {
                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentSharing.MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mContext.startActivity(i);
                break;
            }
//            case R.id.SlidingMenu_TalentSearching : {
//                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.TalentSearching.SearchingPage.MainActivity.class);
//                mContext.startActivity(i);
//                break;
//            }

            case R.id.SlidingMenu_SharingList : {
                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.SharingList.MainActivity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_MessageBox : {
                i = new Intent(mContext, MainActivity.class);
                mContext.startActivity(i);
                break;
            }
            case R.id.SlidingMenu_FriendList : {
                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.FriendList.MainActivity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_ServiceCenter : {
                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.CustomerService.MainActivity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_System : {
                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.System.MainActivity.class);
                mContext.startActivity(i);
                break;
            }

            case R.id.SlidingMenu_LogOut : {
                removePrefFcmToken(mContext);
                final String userID = getUserId(mContext);
                RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
                StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/saveFCMToken.do", new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        try {
                            JSONObject obj = new JSONObject(response);
                            if(obj.getString("result").equals("success")){
                                Log.d("saveToken", "토큰 저장 성공");
                            }else{
                                Log.d("saveToken", "토큰 저장 실패");
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
                        params.put("userID", userID);
                        params.put("fcmToken", "");


                        return params;
                    }
                };

                postRequestQueue.add(postJsonRequest);
                clearUserInfo(mContext);
                i = new Intent(mContext, com.example.accepted.acceptedtalentplanet.LoadingLogin.Login.MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
                ((Activity)mContext).finish();
                break;

            }
        }
    }

    public static void hideKeyboard(View view, Context context) {
        InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    public static Response.ErrorListener getErrorListener(){
        Response.ErrorListener errorListener = new Response.ErrorListener(){
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
        };

        return errorListener;
    }

    public static void setMyPicture(String picture){
        myPicture = StringToBitMap(picture);
    }

    public static void setMyPicture(Bitmap bitmap){
        myPicture = bitmap;
    }

    public static Bitmap getMyPicture(){
        return myPicture;
    }

    public static int makeChatRoom(Context ctx, String userID, String userName){
        SQLiteDatabase sqliteDatabase;
        String dbName = "/accepted.db";

        final Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd,a hh:mm:ss");
        final String nowDateStr = simpleDateFormat.format(date);

        try {
            int roomID;
            int startMessageID;
            String creationDate;
            String picture;
            sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);

            String test = "SELECT IFNULL(MAX(A.START_MESSAGE_ID), 0) AS START_MESSAGE_ID FROM TB_CHAT_ROOM A WHERE A.USER_ID = '" + userID+ "'";
            Cursor cursort = sqliteDatabase.rawQuery(test, null);
            cursort.moveToFirst();
            startMessageID = cursort.getInt(0);
            Log.d("start_message_id", "" + startMessageID);

            test = "SELECT IFNULL(MAX(B.ROOM_ID), (SELECT IFNULL(MAX(C.ROOM_ID) + 1, 1) FROM TB_CHAT_ROOM C)) AS MESSAGE_ID FROM TB_CHAT_ROOM B WHERE B.USER_ID = '" + userID + "' AND B.ACTIVATE_FLAG = 'Y'";
            cursort = sqliteDatabase.rawQuery(test, null);
            cursort.moveToFirst();
            roomID = cursort.getInt(0);
            Log.d("room_id", "" + roomID);

            test = "SELECT IFNULL(MAX(D.CREATION_DATE), '"+nowDateStr+"') AS CREATION_DATE FROM TB_CHAT_ROOM D WHERE D.USER_ID = '" + userID+ "'";
            cursort = sqliteDatabase.rawQuery(test, null);
            cursort.moveToFirst();
            creationDate = cursort.getString(0);
            Log.d("creation_date", "" + creationDate );

            test = "SELECT IFNULL(MAX(PICTURE), 'NODATA') FROM TB_CHAT_ROOM WHERE USER_ID = '" + userID + "'";
            cursort = sqliteDatabase.rawQuery(test, null);
            cursort.moveToFirst();
            try {
                picture = cursort.getString(0);
            }catch (CursorIndexOutOfBoundsException e){
                e.printStackTrace();
                picture = "NODATA";
            }


            String sqlUpsert = "INSERT OR REPLACE INTO TB_CHAT_ROOM(ROOM_ID, USER_ID, USER_NAME, MASTER_ID, START_MESSAGE_ID, CREATION_DATE, LAST_UPDATE_DATE, ACTIVATE_FLAG, PICTURE) VALUES ("+roomID+", '" + userID + "', '"+userName+"', '"+getUserId(ctx)+"', "+startMessageID+", '"+creationDate+"', '"+nowDateStr+"', 'Y', '"+ picture + "')";
            sqliteDatabase.execSQL(sqlUpsert);

            sqliteDatabase.close();


            return roomID;


        } catch (SQLiteException e) {
            e.printStackTrace();
        }

        return -1;
    }

    static public Bitmap getPictureFromDB(Context ctx, String UserID){
        String fileData = "Tk9EQVRB";
        try {
            String dbName = "/accepted.db";
            SQLiteDatabase sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(ctx.getFilesDir() + dbName, null);

            String selectPicture = "SELECT PICTURE FROM TB_IMAGES WHERE MASTER_ID = '" + getUserId(ctx) + "' AND USER_ID = '" + UserID + "'";
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

        if(!fileData.equals("Tk9EQVRB"))
            return StringToBitMap(fileData);

        return null;
    }

    public static void checkDuplicatedLogin(final Context ctx, final Activity activity){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(ctx).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/checkDuplicatedLogin.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("checkDup", obj.toString());
                    if(obj.getString("DUP_FLAG").equals("N")){
                        removePrefFcmToken(ctx);
                        Toast.makeText(ctx, "다른 기기에서 로그인되어 접속이 종료됩니다.", Toast.LENGTH_SHORT).show();
                        clearUserInfo(ctx);
                        Intent i = new Intent(ctx, com.example.accepted.acceptedtalentplanet.LoadingLogin.Login.MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        ctx.startActivity(i);
                        activity.finish();
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
                params.put("userID", getUserId(ctx));
                params.put("token", getFcmToken(ctx));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }


}
