package com.example.accepted.acceptedtalentplanet.Join;

/**
 * Created by kwonhong on 2017-10-01.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.accepted.acceptedtalentplanet.R;

public class RegistGenderActivity extends  AppCompatActivity {

    Spinner genderSpinner;
    String[] mobileNetworkTypes = {"성별 선택", "남자", "여자"};

    public String email;
    public String pw;
    public String name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_gender);

        genderSpinner = (Spinner) findViewById(R.id.Join_genderSpinner);
        genderSpinner.setPrompt("성별 선택");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,R.layout.join_genderspinner,mobileNetworkTypes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
    }

    public void goNext(View v){
        Spinner gender = (Spinner)findViewById(R.id.Join_genderSpinner);

        Intent intent = new Intent(this, Join_Birth_Activity.class);
        intent.putExtra("email", email);
        intent.putExtra("pw", pw);
        intent.putExtra("name", name);
        intent.putExtra("gender", gender.getSelectedItem().toString());

        startActivity(intent);
    }

}
