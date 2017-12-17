package com.example.accepted.acceptedtalentplanet.LoadingLogin;

import android.app.Activity;
import android.content.Context;
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

public class Password_Lost_Change_Activity extends AppCompatActivity {

    EditText PasswordLost_newPassword;
    Context mContext;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_passwordlost_change_activity);

        PasswordLost_newPassword = (EditText) findViewById(R.id.PasswordLost_newPassword);

        PasswordLost_newPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }
            }
        });

    }


}
