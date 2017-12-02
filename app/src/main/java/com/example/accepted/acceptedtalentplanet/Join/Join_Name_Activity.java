package com.example.accepted.acceptedtalentplanet.Join;

/**
 * Created by kwonhong on 2017-10-01.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.accepted.acceptedtalentplanet.R;

public class Join_Name_Activity extends  AppCompatActivity {

    public String email;
    public String pw;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_name);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");

        EditText first_name = (EditText)findViewById(R.id.Join_FirstName) ;
        EditText last_name = (EditText)findViewById(R.id.Join_LastName) ;

        first_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v);
                }

            }
        });
        last_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v);
                }

            }
        });


    }

    public void goNext(View v){
        EditText first_name = (EditText)findViewById(R.id.Join_FirstName) ;
        EditText last_name = (EditText)findViewById(R.id.Join_LastName) ;

        Intent intent = new Intent(this, RegistGenderActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("pw", pw);
        intent.putExtra("name", first_name.getText().toString() + last_name.getText().toString());
        startActivity(intent);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
