package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-11-03.
 */

public class CustomerService_OnebyOneQuestionActivity extends AppCompatActivity {

LinearLayout CustomerService_OnebyOneQuesition_PreBtn;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_onebyonequestionactivity);

        CustomerService_OnebyOneQuesition_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_OnebyOneQuesition_PreBtn);
        CustomerService_OnebyOneQuesition_PreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



}


