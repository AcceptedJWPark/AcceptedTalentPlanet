package com.example.accepted.acceptedtalentplanet.CustomerService.Version;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.R;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.checkDuplicatedLogin;

/**
 * Created by Accepted on 2017-10-31.
 */

public class MainActivity extends AppCompatActivity {


    //TODO:버전 정보 최신 VS 사용자 버전 다르면 활성화, 같으면 비활성화
    private LinearLayout ll_preContainer;
    private Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_versionactivity);
        mContext = getApplicationContext();


        ll_preContainer = (LinearLayout) findViewById(R.id.ll_preContainer_Version);
        ll_preContainer.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        checkDuplicatedLogin(mContext, this);
    }
}
