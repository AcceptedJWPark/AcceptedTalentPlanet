package com.example.accepted.acceptedtalentplanet.InterestingList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

public class Interesting_Activity extends AppCompatActivity {

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
        Interesting_List.setAdapter(InterestingList_Adapter);


    }

}
