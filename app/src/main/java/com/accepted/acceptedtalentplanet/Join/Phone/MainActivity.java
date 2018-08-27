package com.accepted.acceptedtalentplanet.Join.Phone;

/**
 * Created by kwonhong on 2017-10-01.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.accepted.acceptedtalentplanet.R;

import static com.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MainActivity extends  AppCompatActivity {


    private Context mContext;
    EditText phoneNo;
    EditText confirmNo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_phone);

        mContext = getApplicationContext();
        phoneNo = findViewById(R.id.et_Phone_Join);
        confirmNo = findViewById(R.id.et_PhoneCode_Join);
        phoneNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {hideKeyboard(v,mContext);}}});
        confirmNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {hideKeyboard(v,mContext);}}});
    }

    public void goNext(View v){
            Intent intent = new Intent(this, com.accepted.acceptedtalentplanet.Join.Email.MainActivity.class);
            startActivity(intent);
        }
    }



