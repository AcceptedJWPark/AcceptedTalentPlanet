package com.example.accepted.acceptedtalentplanet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);


    }

    public void clickStart(View view){
        Intent intent = new Intent(this, com.example.accepted.acceptedtalentplanet.LoadingLogin.Login.MainActivity.class);
        startActivity(intent);
    }



}


