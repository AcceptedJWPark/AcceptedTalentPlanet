package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_UpdateActivity extends AppCompatActivity {

    ExpandableListView ExpandableListViewTest;

    private ArrayList<CustomerService_UpdateListItem> arrayGroup = new ArrayList<CustomerService_UpdateListItem>();
    private HashMap<CustomerService_UpdateListItem, ArrayList<String>> arrayChild = new HashMap<>();

    LinearLayout CustomerService_Update_PreBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_updateactivity);

        ExpandableListViewTest = (ExpandableListView) this.findViewById(R.id.CustomerService_Update_ELV);
        setArrayData();
        ExpandableListViewTest.setAdapter(new CustomerService_UpdateExpandableLVAdapter(this, arrayGroup, arrayChild));

        CustomerService_Update_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_Update_PreBtn);
        CustomerService_Update_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void setArrayData()
    {

        arrayGroup.add(new CustomerService_UpdateListItem
                ("Application Service Open",
                "Talent Planet Service Open : Mar.2018 \n Version : 1.01 \n",
                "Mar.2018"));


        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList1.add("Talent Planet Service Open : Mar.2018 \n Version : 1.01 \n");


        arrayChild.put(arrayGroup.get(0),arrayList1);

    }

    }
