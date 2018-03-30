package com.example.accepted.acceptedtalentplanet.Join.Name;

/**
 * Created by kwonhong on 2017-10-01.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.R;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MainActivity extends  AppCompatActivity {

    public String email;
    public String pw;

    private Context mContext;

    private RelativeLayout rl_preContainer;
    private TextView tv_TitleContainer;
    private LinearLayout ll_NameContainer;
    private View trashView;
    private Button btn_Accept;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.join_name);
        mContext = getApplicationContext();


        rl_preContainer = (RelativeLayout) findViewById(R.id.rl_preContainer_join_name);
        tv_TitleContainer = (TextView) findViewById(R.id.tv_TitleContainer_join_name);
        ll_NameContainer = (LinearLayout) findViewById(R.id.ll_nameContainer_join_name);
        trashView = findViewById(R.id.trashView_join_name);
        btn_Accept = (Button) findViewById(R.id.btn_Accept_name_join);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int preContainer_height = (int) (metrics.heightPixels*0.06);
        int titleContainer_height = (int) (metrics.heightPixels*0.1);
        int nameContainer_height= (int) (metrics.heightPixels*0.05);
        int trashView_height= (int) (metrics.heightPixels*0.02);
        int btn_Accept_height= (int) (metrics.heightPixels*0.04);

        ViewGroup.LayoutParams params1 = rl_preContainer.getLayoutParams();
        ViewGroup.LayoutParams params2 = tv_TitleContainer.getLayoutParams();
        ViewGroup.LayoutParams params3 = ll_NameContainer.getLayoutParams();
        ViewGroup.LayoutParams params4 = trashView.getLayoutParams();
        ViewGroup.LayoutParams params5 = btn_Accept.getLayoutParams();

        params1.height = preContainer_height;
        params2.height = titleContainer_height;
        params3.height = nameContainer_height;
        params4.height = trashView_height;
        params5.height = btn_Accept_height;

        rl_preContainer.setLayoutParams(params1);
        tv_TitleContainer.setLayoutParams(params2);
        ll_NameContainer.setLayoutParams(params3);
        trashView.setLayoutParams(params4);
        btn_Accept.setLayoutParams(params5);


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
            Intent intent = new Intent(this, com.example.accepted.acceptedtalentplanet.Join.Gender.MainActivity.class);
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
