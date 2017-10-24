package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

public class TalentResisterActivity_Talent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_talent);

        TextView TalentKey1= (TextView) findViewById(R.id.Talent_key1);
        SpannableString content1 = new SpannableString("기타");
        content1.setSpan(new UnderlineSpan(), 0, content1.length(), 0);
        TalentKey1.setText(content1);

        TextView TalentKey2 = (TextView) findViewById(R.id.Talent_key2);
        SpannableString content2 = new SpannableString("기타연주");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        TalentKey2.setText(content2);

        TextView TalentKey3 = (TextView) findViewById(R.id.Talent_key3);
        SpannableString content3 = new SpannableString("Guitar");
        content3.setSpan(new UnderlineSpan(), 0, content3.length(), 0);
        TalentKey3.setText(content3);

    }

}
