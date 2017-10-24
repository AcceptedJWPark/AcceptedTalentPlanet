package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

public class TalentResisterActivity_Level extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_level);

        TextView LevelKey1= (TextView) findViewById(R.id.Level_key1);
        SpannableString Level_content1 = new SpannableString("기타");
        Level_content1.setSpan(new UnderlineSpan(), 0, Level_content1 .length(), 0);
        LevelKey1.setText(Level_content1);

        TextView LevelKey2 = (TextView) findViewById(R.id.Level_key2);
        SpannableString Level_content2 = new SpannableString("기타 연주");
        Level_content2.setSpan(new UnderlineSpan(), 0, Level_content2.length(), 0);
        LevelKey2.setText(Level_content2);

        TextView LevelKey3 = (TextView) findViewById(R.id.Level_key3);
        SpannableString Level_content3 = new SpannableString("Guitar");
        Level_content3.setSpan(new UnderlineSpan(), 0, Level_content3.length(), 0);
        LevelKey3.setText(Level_content3);

    }

}
