package com.example.accepted.acceptedtalentplanet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.accepted.acceptedtalentplanet.LoadingLogin.Login_Activity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);


    }

    public void clickStart(View view){
        Intent intent = new Intent(this, Login_Activity.class);
        startActivity(intent);
    }



}


