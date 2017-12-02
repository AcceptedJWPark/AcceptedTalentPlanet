package com.example.accepted.acceptedtalentplanet.InterestingList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.accepted.acceptedtalentplanet.CustomerService.CustomerService_MainActivity;
import com.example.accepted.acceptedtalentplanet.GeoPoint;
import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;
import com.example.accepted.acceptedtalentplanet.MyProfile.MyProfile_Activity;
import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.System.System_Activity;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentResister.TalentResister_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSearching.TalentSearching_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_ListAdapter;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_ListItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Accepted on 2017-10-24.
 */

public class InterestingList_Activity extends AppCompatActivity {

    Context mContext;
    ListView Interesting_List;

    ArrayList<InterestingList_ListItem> InterestingList_ArrayList;
    InterestingList_ListAdapter InterestingList_Adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.interestinglist_activity);

        mContext = getApplicationContext();
        Interesting_List = (ListView) findViewById(R.id.Interesting_List);
        InterestingList_ArrayList = new ArrayList<>();

        InterestingList_Adapter = new InterestingList_ListAdapter(mContext, InterestingList_ArrayList);

        InterestingList_ArrayList.add(new InterestingList_ListItem(R.drawable.textpicture,"박종우","기타","피아노","드럼","2017.11.30. 14:19 등록"));
        Interesting_List.setAdapter(InterestingList_Adapter);
    }

}



