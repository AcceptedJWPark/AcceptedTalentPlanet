package com.accepted.acceptedtalentplanet.Join.Name;

/**
 * Created by kwonhong on 2017-10-01.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.accepted.acceptedtalentplanet.R;

import static com.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MainActivity extends  AppCompatActivity {

    public String email;
    public String pw;

    private Context mContext;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.join_name);
        mContext = getApplicationContext();



        ((LinearLayout)findViewById(R.id.ll_ProContainer_Name_Join)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");

        EditText firstName = (EditText)findViewById(R.id.et_Firstname_Name_Join) ;
        EditText lastName = (EditText)findViewById(R.id.et_Lastname_Name_Join) ;




        firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v,mContext);
                }

            }
        });
        lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        EditText et_Firstname = (EditText)findViewById(R.id.et_Firstname_Name_Join) ;
        EditText et_Lasttname = (EditText)findViewById(R.id.et_Lastname_Name_Join) ;

        String firstName = et_Firstname.getText().toString();
        String lastName = et_Lasttname.getText().toString();
        if(firstName.length() == 0 )
        {
            Toast.makeText(mContext,"성을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(lastName.length() == 0)
        {
            Toast.makeText(mContext,"이름을 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }

        else if(checkId(firstName))
        {
            Toast.makeText(mContext,"이름에 숫자 또는 특수문자가 들어갈 수 없습니다.",Toast.LENGTH_SHORT).show();
            et_Firstname.setText("");
        }

        else if(checkId(lastName))
        {
            Toast.makeText(mContext,"이름에 숫자 또는 특수문자가 들어갈 수 없습니다.",Toast.LENGTH_SHORT).show();
            et_Lasttname.setText("");
        }

        else
        {
            Intent intent = new Intent(this, com.accepted.acceptedtalentplanet.Join.Gender.MainActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("pw", pw);
            intent.putExtra("name", et_Firstname.getText().toString() + et_Lasttname.getText().toString());
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
