package com.example.accepted.acceptedtalentplanet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Accepted on 2017-09-27.
 */

public class JoinGenderActivity extends AppCompatActivity {

    Spinner genderSpinner;
    String[] mobileNetworkTypes = {"성별 선택", "남자", "여자"};

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_gender);

        genderSpinner = (Spinner) findViewById(R.id.genderSpinner);
        genderSpinner.setPrompt("성별 선택");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,R.layout.genderspinner,mobileNetworkTypes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
    }
}


