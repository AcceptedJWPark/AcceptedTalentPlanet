package com.example.accepted.acceptedtalentplanet;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import static android.text.TextUtils.isEmpty;

/**
 * Created by kwonhong on 2017-10-14.
 */

public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_ID = "userid";
    static final String SERVER_IP = "http://13.124.141.242/Accepted/";
    static final String SERVER_IP2 = "http://192.168.123.3:8080/Accepted/";
    static final String PREF_GIVE_DATA = "giveData";
    static final String PREF_TAKE_DATA = "takeData";
    static final String PREF_GEO_POINT1 = "geoPoint1";
    static final String PREF_GEO_POINT2 = "geoPoint2";
    static final String PREF_GEO_POINT3 = "geoPoint3";

    static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefUsrName(Context ctx, String userName){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static void setPrefUsrId(Context ctx, String useId){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, useId);
        editor.commit();
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
    }

    public static boolean checkSession(){
        return !isEmpty(PREF_USER_ID);
    }

    public static String getServerIp(){
        return SERVER_IP2;
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

    public static void setGeoPointArr(Context ctx, GeoPoint[] Data){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String json = gson.toJson(Data[0]);
        editor.putString(PREF_GEO_POINT1, json);
        json = gson.toJson(Data[1]);
        editor.putString(PREF_GEO_POINT2, json);
        json = gson.toJson(Data[2]);
        editor.putString(PREF_GEO_POINT3, json);
        editor.commit();
    }

    public static GeoPoint[] getGeoPointArr(Context ctx){
        Gson gson = new Gson();
        GeoPoint[] data = new GeoPoint[3];

        String json = getSharedPreferences(ctx).getString(PREF_GEO_POINT1, "");
        data[0] = gson.fromJson(json, GeoPoint.class);
        json = getSharedPreferences(ctx).getString(PREF_GEO_POINT2, "");
        data[1] = gson.fromJson(json, GeoPoint.class);
        json = getSharedPreferences(ctx).getString(PREF_GEO_POINT3, "");
        data[2] = gson.fromJson(json, GeoPoint.class);

        return data;
    }

    public static byte[] getFileDataFromDrawable(Context ctx, int id){
        Drawable drawable = ContextCompat.getDrawable(ctx, id);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] getFileDataFromDrawable(Context ctx, Drawable drawable){
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
