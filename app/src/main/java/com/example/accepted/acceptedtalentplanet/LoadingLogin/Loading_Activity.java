package com.example.accepted.acceptedtalentplanet.LoadingLogin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.accepted.acceptedtalentplanet.Home.Home_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Activity;

/**
 * Created by Accepted on 2017-09-20.
 */

public class Loading_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState)
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_start);
       startLoading();
    }

    private void startLoading()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(SaveSharedPreference.getUserId(Loading_Activity.this).length() == 0) {
                    intent = new Intent(getBaseContext(), Login_Activity.class);
                }else{
                    intent = new Intent(getBaseContext(), TalentSharing_Activity.class);
                }
                    startActivity(intent);
                    finish();

            }
        },3000);
    }


}
