package com.accepted.acceptedtalentplanet.LoadingLogin.Login;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.accepted.acceptedtalentplanet.GeoPoint;
import com.accepted.acceptedtalentplanet.MyTalent;
import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.VolleySingleton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-09-17.
 */

public class MainActivity extends AppCompatActivity {
    private InputMethodManager imm;
    private Context mContext;
    private String FcmToken;
    private EditText et_Email;
    private EditText et_Password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_activity);
        mContext = getApplicationContext();

        if(getIntent().hasExtra("dupFlag")){
            Toast.makeText(mContext, "다른 기기에서 로그인되어 접속이 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

        et_Email = (EditText)findViewById(R.id.Login_ID);
        et_Password = (EditText)findViewById(R.id.Login_Password);
        //FcmToken = SaveSharedPreference.getFcmToken(mContext);


        et_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }

            }
        });

        et_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }

            }
        });


//        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

    }

    public void loginClicked(View v){

//        imm.hideSoftInputFromWindow(et_Password.getWindowToken(), 0);
        final String userID = et_Email.getText().toString();
        final String userPW = et_Password.getText().toString();

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/checkLoginInfo.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String result = obj.getString("result");
                    if(result.equals("success")){
                        String userName = obj.getString("userName");
                        SaveSharedPreference.setPrefUsrName(mContext, userName);
                        SaveSharedPreference.setPrefUsrId(mContext, userID);
                        getMyTalent();
                        getMyTalentPoint();
                        getMyPicture();
                        getFriendList();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(getBaseContext(), com.accepted.acceptedtalentplanet.TalentSharing.MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.putExtra("Activity", true);
                                startActivity(intent);
                            }
                        },500);

                    }else if(result.equals("fail")){
                        Toast.makeText(getApplicationContext(), "비밀번호를 잘못 입력하셨습니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("userID", userID);
                params.put("userPW", userPW);

                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    public void goRegist(View v){
        Intent intent = new Intent(this, com.accepted.acceptedtalentplanet.Join.Phone.MainActivity.class);
        startActivity(intent);
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

                        talent.setMyTalent(obj.getString("TALENT_KEYWORD1"), obj.getString("TALENT_KEYWORD2"), obj.getString("TALENT_KEYWORD3"), obj.getString("LOCATION"), obj.getString("T_POINT"), obj.getString("LEVEL"), gpPoint);
                        talent.setStatus(status);
                        talent.setTalentID(obj.getString("seq"));
                        if(obj.getString("TALENT_FLAG").equals("Y")){
                            SaveSharedPreference.setGiveTalentData(mContext, talent);
                        }else{
                            SaveSharedPreference.setTakeTalentData(mContext, talent);
                        }
                    }

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
                    JSONObject obj = new JSONObject(response);
                    int talentPoint = Integer.parseInt(obj.getString("TALENT_POINT"));
                    SaveSharedPreference.setPrefTalentPoint(mContext, talentPoint);


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

    public void PasswordLost(View v)
    {
        Intent i = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.LoadingLogin.PasswordLost.Confirm.MainActivity.class);
        startActivity(i);
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

    public void getFriendList(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "FriendList/getFriendList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    SQLiteDatabase sqliteDatabase;
                    String dbName = "/accepted.db";
                    sqliteDatabase = SQLiteDatabase.openOrCreateDatabase(getFilesDir() + dbName, null);
                    for(int i = 0; i < obj.length(); i++) {
                        JSONObject o = obj.getJSONObject(i);
                        String sqlUpsert = "INSERT OR REPLACE INTO TB_FRIEND_LIST(MASTER_ID, FRIEND_ID, TALENT_TYPE) VALUES ('" + SaveSharedPreference.getUserId(mContext) + "', '" + o.getString("FRIEND_USER_ID") + "', '" + o.getString("PARTNER_TALENT_FLAG") + "')";
                        sqliteDatabase.execSQL(sqlUpsert);
                    }

                    sqliteDatabase.close();
                }
                catch(JSONException e){
                    e.printStackTrace();
                }catch (SQLiteException e) {
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
