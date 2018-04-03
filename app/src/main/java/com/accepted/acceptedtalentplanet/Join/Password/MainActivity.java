package com.accepted.acceptedtalentplanet.Join.Password;

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
import android.widget.Toast;

import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.R;

public class MainActivity extends  AppCompatActivity {

    public String email;
    private Context mContext;
    private EditText et_Password;

    private RelativeLayout rl_preContainer;
    private LinearLayout ll_TitleContainer;
    private LinearLayout ll_pswContainer;
    private View trashView1;
    private View trashView2;
    private Button btn_Accept;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_password);

        mContext = getApplicationContext();

        rl_preContainer = (RelativeLayout) findViewById(R.id.rl_preContainer_join_psw);
        ll_TitleContainer = (LinearLayout) findViewById(R.id.ll_TitleContainer_join_psw);
        ll_pswContainer = (LinearLayout) findViewById(R.id.ll_pswContainer_join_psw);
        trashView1 = findViewById(R.id.trashView1_join_psw);
        trashView2 = findViewById(R.id.trashView2_join_psw);
        btn_Accept = (Button) findViewById(R.id.btn_Accept_join_psw);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int preContainer_height = (int) (metrics.heightPixels*0.06);
        int titleContainer_height = (int) (metrics.heightPixels*0.1);
        int pswContainer_height= (int) (metrics.heightPixels*0.05);
        int trashView1_height= (int) (metrics.heightPixels*0.02);
        int trashView2_height= (int) (metrics.heightPixels*0.02);
        int btn_Accept_height= (int) (metrics.heightPixels*0.04);

        ViewGroup.LayoutParams params1 = rl_preContainer.getLayoutParams();
        ViewGroup.LayoutParams params2 = ll_TitleContainer.getLayoutParams();
        ViewGroup.LayoutParams params3 = ll_pswContainer.getLayoutParams();
        ViewGroup.LayoutParams params4 = trashView1.getLayoutParams();
        ViewGroup.LayoutParams params5 = trashView2.getLayoutParams();
        ViewGroup.LayoutParams params6 = btn_Accept.getLayoutParams();

        params1.height = preContainer_height;
        params2.height = titleContainer_height;
        params3.height = pswContainer_height;
        params4.height = trashView1_height;
        params5.height = trashView2_height;
        params6.height = btn_Accept_height;

        rl_preContainer.setLayoutParams(params1);
        ll_TitleContainer.setLayoutParams(params2);
        ll_pswContainer.setLayoutParams(params3);
        trashView1.setLayoutParams(params4);
        trashView2.setLayoutParams(params5);
        btn_Accept.setLayoutParams(params6);



        ((LinearLayout)findViewById(R.id.ll_PreContainer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        et_Password = (EditText)findViewById(R.id.et_Password_pw_join) ;
        et_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }

            }
        });
    }

    public void goNext(View v){

        String pwTxt = et_Password.getText().toString();
        if(pwTxt.length() == 0)
        {
            Toast.makeText(mContext,"비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(pwTxt.length() < 6)
        {
            Toast.makeText(mContext, "비밀번호는 6자 이상이어야 합니다.", Toast.LENGTH_SHORT).show();
        }

        else if (checkPw(et_Password))
        {
            Toast.makeText(mContext, "비밀번호는 영문, 숫자, 특수문자 중 두 개 이상의 조합으로 이루어져야 합니다.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this, com.accepted.acceptedtalentplanet.Join.Name.MainActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("pw", et_Password.getText().toString());
            startActivity(intent);
        }
    }

    public boolean checkPw(EditText edt)
    {
        int engNum=0;
        int numNum=0;
        int speNum=0;
        int totalNum=0;

        for(int i =0; i<edt.getText().length(); i++)
        {
            if(Character.isLetter(edt.getText().charAt(i)))
            {
                engNum++;
            }
            if(Character.isDigit(edt.getText().charAt(i)))
            {
                numNum++;
            }
            if(!Character.isLetterOrDigit(edt.getText().charAt(i)))
            {
                speNum++;
            }
        }

        if (engNum == 0)
        {
            totalNum++;
        }
        if(numNum == 0)
        {
            totalNum++;
        }
        if(speNum == 0)
        {
            totalNum++;
        }
        if (totalNum < 2)
        {
            return false;
        }
        return  true;
    }

}
