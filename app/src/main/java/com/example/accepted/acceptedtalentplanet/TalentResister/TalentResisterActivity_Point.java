package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

public class TalentResisterActivity_Point extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_point);

        TextView PointKey1= (TextView) findViewById(R.id.Point_key1);
        SpannableString Point_content1 = new SpannableString("기타");
        Point_content1.setSpan(new UnderlineSpan(), 0, Point_content1 .length(), 0);
        PointKey1.setText(Point_content1);

        TextView PointKey2 = (TextView) findViewById(R.id.Point_key2);
        SpannableString Point_content2 = new SpannableString("기타 연주");
        Point_content2.setSpan(new UnderlineSpan(), 0, Point_content2.length(), 0);
        PointKey2.setText(Point_content2);

        TextView PointKey3 = (TextView) findViewById(R.id.Point_key3);
        SpannableString Point_content3 = new SpannableString("Guitar");
        Point_content3.setSpan(new UnderlineSpan(), 0, Point_content3.length(), 0);
        PointKey3.setText(Point_content3);

    }

}
