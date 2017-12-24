package com.example.accepted.acceptedtalentplanet.Join;

/**
 * Created by kwonhong on 2017-10-01.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.accepted.acceptedtalentplanet.R;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class Join_Password_Activity extends  AppCompatActivity {

    public String email;
    Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_password);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        EditText pw = (EditText)findViewById(R.id.et_join_pw) ;
        pw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v,mContext);
                }

            }
        });

    }

    public void goNext(View v){
        EditText pw = (EditText)findViewById(R.id.et_join_pw) ;
        Intent intent = new Intent(this, Join_Name_Activity.class);
        intent.putExtra("email", email);
        intent.putExtra("pw", pw.getText().toString());
        startActivity(intent);
    }


}