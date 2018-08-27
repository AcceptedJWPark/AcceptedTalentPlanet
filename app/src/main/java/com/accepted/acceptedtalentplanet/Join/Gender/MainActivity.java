package com.accepted.acceptedtalentplanet.Join.Gender;

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

import com.accepted.acceptedtalentplanet.R;

public class MainActivity extends  AppCompatActivity {

    private Spinner spn_Gender;
    private String[] genderList = {"성별 선택", "남자", "여자"};

    public String email;
    public String pw;
    public String name;
    private Context mContext;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_gender);

        mContext = getApplicationContext();



        ((LinearLayout)findViewById(R.id.ll_preContainer_Birth_Join)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spn_Gender = (Spinner) findViewById(R.id.spn_Gender_Join);
        spn_Gender.setPrompt("성별 선택");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,R.layout.join_genderspinner, genderList);

        adapter.setDropDownViewResource(R.layout.customerservice_claim_spinnertext);
        spn_Gender.setAdapter(adapter);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
    }

    public void goNext(View v){
        String genderTxt = spn_Gender.getSelectedItem().toString();
        if (genderTxt.equals("성별 선택"))
        {
            Toast.makeText(mContext, "성별을 선택해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            CheckBox cb_isShowGender = (CheckBox)findViewById(R.id.cb_isShowGender_Gender_Join);
            boolean genderPBS = !cb_isShowGender.isChecked();
            Intent intent = new Intent(this, com.accepted.acceptedtalentplanet.Join.Birth.MainActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("pw", pw);
            intent.putExtra("name", name);
            intent.putExtra("gender", spn_Gender.getSelectedItem().toString());
            intent.putExtra("genderPBS", genderPBS);
            startActivity(intent);
        }
    }

}
