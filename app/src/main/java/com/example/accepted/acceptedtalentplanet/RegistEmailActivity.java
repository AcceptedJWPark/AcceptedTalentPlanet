package com.example.accepted.acceptedtalentplanet;

/**
 * Created by kwonhong on 2017-10-01.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class RegistEmailActivity extends  AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_email);

    }

    public void goNext(View v){
        EditText email = (EditText)findViewById(R.id.et_join_mail) ;
        Intent intent = new Intent(this, RegistPasswordActivity.class);
        intent.putExtra("email", email.getText().toString());

        startActivity(intent);
    }

}
