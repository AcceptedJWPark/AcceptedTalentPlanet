package com.example.accepted.acceptedtalentplanet.Join;

/**
 * Created by kwonhong on 2017-10-01.
 */
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.R;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class Join_Name_Activity extends  AppCompatActivity {

    public String email;
    public String pw;

    Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.join_name);
        mContext = getApplicationContext();

        ((LinearLayout)findViewById(R.id.pre_LL)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

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
                    hideKeyboard(v,mContext);
                }

            }
        });
        last_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        EditText first_name = (EditText)findViewById(R.id.Join_FirstName) ;
        EditText last_name = (EditText)findViewById(R.id.Join_LastName) ;

        String first_name_Txt = first_name.getText().toString();
        String last_name_Txt = last_name.getText().toString();
        if(first_name_Txt.length() == 0 )
        {
            Toast.makeText(mContext,"성을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(last_name_Txt.length() == 0)
        {
            Toast.makeText(mContext,"이름을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(checkId(first_name_Txt))
        {
            Toast.makeText(mContext,"이름에 숫자 또는 특수문자가 들어갈 수 없습니다.",Toast.LENGTH_SHORT).show();
            first_name.setText("");
        }

        else if(checkId(last_name_Txt))
        {
            Toast.makeText(mContext,"이름에 숫자 또는 특수문자가 들어갈 수 없습니다.",Toast.LENGTH_SHORT).show();
            last_name.setText("");
        }

        else
        {
            Intent intent = new Intent(this, Join_Gender_Activity.class);
            intent.putExtra("email", email);
            intent.putExtra("pw", pw);
            intent.putExtra("name", first_name.getText().toString() + last_name.getText().toString());
            startActivity(intent);
        }
    }

    public boolean checkId(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLetter(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }


}
