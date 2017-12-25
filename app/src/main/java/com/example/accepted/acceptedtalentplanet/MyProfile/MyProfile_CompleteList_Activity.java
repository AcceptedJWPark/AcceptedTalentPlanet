package com.example.accepted.acceptedtalentplanet.MyProfile;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.accepted.acceptedtalentplanet.MyProfileData;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Activity;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Adapter;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Item;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;
import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MyProfile_CompleteList_Activity extends AppCompatActivity {

    Context mContext;
    ListView CompleteList_ListView;
    ArrayList<MyProfile_CompleteList_Item> CompleteList_arrayList;
    MyProfile_CompleteList_Adapter CompleteList_Adapter;
    LinearLayout CompleteList_PreBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofile_completelist_activity);

        mContext = getApplicationContext();


        CompleteList_ListView = (ListView) findViewById(R.id.CompleteList_ListView);


        CompleteList_arrayList = new ArrayList<>();
        CompleteList_Adapter = new MyProfile_CompleteList_Adapter(MyProfile_CompleteList_Activity.this, CompleteList_arrayList);
        CompleteList_ListView.setAdapter(CompleteList_Adapter);

        //TODO:한 개의 어레이 리스트와 어뎁터로 두 개의 리스트뷰에 적용 시킬 수는 없나?
        CompleteList_arrayList.add(new MyProfile_CompleteList_Item(1,"박종우" +"님과","2017.12.03 11:22","기타 독학","통기타", "기타 연습","-200p"));
        CompleteList_arrayList.add(new MyProfile_CompleteList_Item(2,"우승제" +"님과","2017.12.07 13:12","스타크래프트","스타크래프트 리마스터", "스타1","+100p"));
        CompleteList_arrayList.add(new MyProfile_CompleteList_Item(1,"김용인" +"님과","2017.12.20 19:17","영어","영어 독해", "영어 듣기","-100p"));

        CompleteList_PreBtn = (LinearLayout) findViewById(R.id.CompleteList_PreBtn);
        CompleteList_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
