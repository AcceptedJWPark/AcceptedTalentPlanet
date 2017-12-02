package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_Manual_AnswerList_Activity extends AppCompatActivity {


    ExpandableListView ExpandableListViewTest;

    private ArrayList<String> arrayGroup = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> arrayChild = new HashMap<String, ArrayList<String>>();

    Button CustomerService_manual_answerList_PreBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_manual_answerlist);

        Intent intent = getIntent();
        String valueTypes = intent.getStringExtra("Value");
        Log.d(valueTypes,"전달 값");
        ExpandableListViewTest = (ExpandableListView) this.findViewById(R.id.CustomerService_Manual_ELV);
        ExpandableListViewTest.setAdapter(new CustomerService_Manual_ExpandableLVAdapter(this, arrayGroup, arrayChild));
        setArrayData(valueTypes);
        CustomerService_manual_answerList_PreBtn = (Button) findViewById(R.id.CustomerService_manual_answerList_PreBtn);
        CustomerService_manual_answerList_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setArrayData(String value)
    {
        if (value.equals("MyProfile"))
        {
            arrayGroup.add("My Profile");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("My Profile의 TextView");
            arrayChild.put(arrayGroup.get(0),arrayList);
        }
        else if (value.equals("MyTalent"))
        {
        arrayGroup.add("My Talent");
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("My Talent의 TextView");
        arrayChild.put(arrayGroup.get(0),arrayList);
        }
        else if(value.equals("TSharing"))
        {
            arrayGroup.add("TSharing");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("TSharing TextView");
            arrayChild.put(arrayGroup.get(0),arrayList);
        }
        else if(value.equals("TCondition"))
        {
            arrayGroup.add("TCondition");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("TCondition TextView");
            arrayChild.put(arrayGroup.get(0),arrayList);
        }
        else if(value.equals("TSearching"))
        {
            arrayGroup.add("TSearching");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("TSearching TextView");
            arrayChild.put(arrayGroup.get(0),arrayList);
        }
        else if(value.equals("CustomerService"))
        {
            arrayGroup.add("CustomerService");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("CustomerService TextView");
            arrayChild.put(arrayGroup.get(0),arrayList);
        }
        else if(value.equals("System"))
        {
            arrayGroup.add("System");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("System TextView");
            arrayChild.put(arrayGroup.get(0),arrayList);
        }
        else if(value.equals("Message"))
        {
            arrayGroup.add("Message");
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("Message TextView");
            arrayChild.put(arrayGroup.get(0),arrayList);
        }

    }



}



