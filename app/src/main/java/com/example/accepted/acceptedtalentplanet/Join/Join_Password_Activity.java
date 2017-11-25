package com.example.accepted.acceptedtalentplanet.Join;

/**
 * Created by kwonhong on 2017-10-01.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.accepted.acceptedtalentplanet.R;

public class Join_Password_Activity extends  AppCompatActivity {

    public String email;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_password);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
    }

    public void goNext(View v){
        EditText pw = (EditText)findViewById(R.id.et_join_pw) ;
        Intent intent = new Intent(this, Join_Name_Activity.class);
        intent.putExtra("email", email);
        intent.putExtra("pw", pw.getText().toString());
        startActivity(intent);
    }

}
