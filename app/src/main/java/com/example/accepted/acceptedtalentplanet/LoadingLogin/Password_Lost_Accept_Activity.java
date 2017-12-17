package com.example.accepted.acceptedtalentplanet.LoadingLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.R;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

/**
 * Created by Accepted on 2017-12-17.
 */

public class Password_Lost_Accept_Activity extends AppCompatActivity {

    LinearLayout accept_join_mail;
    Context mContext;
    EditText PasswordLost_Email;
    EditText PasswordLost_EmailCode;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_passwordlost_accept_activity);

        mContext = getApplicationContext();
        accept_join_mail = (LinearLayout) findViewById(R.id.accept_join_mail);

        PasswordLost_Email = (EditText) findViewById(R.id.PasswordLost_Email);
        PasswordLost_EmailCode = (EditText) findViewById(R.id.PasswordLost_EmailCode);

        PasswordLost_Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v,mContext);
                }
            }
        });
        PasswordLost_EmailCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }
            }
        });



    }

    public void PasswordLost_emailCheck(View v)
    {
        accept_join_mail.setVisibility(View.VISIBLE);
    }

    public void goNext(View v)
    {
        Intent i = new Intent(mContext, Password_Lost_Change_Activity.class);
        startActivity(i);
    }



}
