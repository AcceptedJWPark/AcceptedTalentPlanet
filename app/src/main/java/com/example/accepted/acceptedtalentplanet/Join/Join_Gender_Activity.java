package com.example.accepted.acceptedtalentplanet.Join;

/**
 * Created by kwonhong on 2017-10-01.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.R;

public class Join_Gender_Activity extends  AppCompatActivity {

    Spinner genderSpinner;
    String[] mobileNetworkTypes = {"성별 선택", "남자", "여자"};

    public String email;
    public String pw;
    public String name;
    Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_gender);

        mContext = getApplicationContext();

        ((LinearLayout)findViewById(R.id.pre_LL)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        genderSpinner = (Spinner) findViewById(R.id.Join_genderSpinner);
        genderSpinner.setPrompt("성별 선택");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,R.layout.join_genderspinner,mobileNetworkTypes);

        adapter.setDropDownViewResource(R.layout.customerservice_claim_spinnertext);
        genderSpinner.setAdapter(adapter);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
    }

    public void goNext(View v){
        Spinner gender = (Spinner)findViewById(R.id.Join_genderSpinner);
        String genderTxt = gender.getSelectedItem().toString();
        if (genderTxt.equals("성별 선택"))
        {
            Toast.makeText(mContext, "성별을 선택해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            CheckBox genderCheckbox = (CheckBox)findViewById(R.id.Join_GenderNoShowCheck);
            boolean genderPBS = !genderCheckbox.isChecked();
            Intent intent = new Intent(this, Join_Birth_Activity.class);
            intent.putExtra("email", email);
            intent.putExtra("pw", pw);
            intent.putExtra("name", name);
            intent.putExtra("gender", gender.getSelectedItem().toString());
            intent.putExtra("genderPBS", genderPBS);
            startActivity(intent);
        }
    }

}
