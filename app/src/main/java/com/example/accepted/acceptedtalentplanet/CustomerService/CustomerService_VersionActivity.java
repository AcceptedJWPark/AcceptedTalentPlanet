package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_VersionActivity extends AppCompatActivity {


    //TODO:버전 정보 최신 VS 사용자 버전 다르면 활성화, 같으면 비활성화
    LinearLayout CustomerService_Version_PreBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_versionactivity);


        CustomerService_Version_PreBtn = (LinearLayout) findViewById(R.id.CustomerService_Version_PreBtn);
        CustomerService_Version_PreBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
