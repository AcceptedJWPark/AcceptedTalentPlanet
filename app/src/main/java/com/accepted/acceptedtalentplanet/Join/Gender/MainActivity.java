package com.accepted.acceptedtalentplanet.Join.Gender;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.accepted.acceptedtalentplanet.R;

public class MainActivity extends  AppCompatActivity {

    private Spinner spn_Gender;
    private String[] genderList = {"성별 선택", "남자", "여자"};

    public String email;
    public String pw;
    public String name;
    private Context mContext;

    private RelativeLayout rl_preContainer;
    private TextView tv_TitleContainer;
    private LinearLayout ll_genderContainer;
    private Button btn_Next;
    private View trashView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_gender);

        mContext = getApplicationContext();

        rl_preContainer = (RelativeLayout) findViewById(R.id.rl_preContainer_join_gender);
        tv_TitleContainer = (TextView) findViewById(R.id.tv_TitleContainer_join_gender);
        ll_genderContainer = (LinearLayout) findViewById(R.id.ll_genderContainer_join_gender);
        btn_Next = (Button) findViewById(R.id.btn_Next_join_gender);
        trashView = findViewById(R.id.trashView_join_gender);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);


        int preContainer_height = (int) (metrics.heightPixels*0.06);
        int titleContainer_height = (int) (metrics.heightPixels*0.1);
        int genderContainer_height= (int) (metrics.heightPixels*0.05);
        int btn_Next_height= (int) (metrics.heightPixels*0.04);
        int trashView_height= (int) (metrics.heightPixels*0.02);

        ViewGroup.LayoutParams params1 = rl_preContainer.getLayoutParams();
        ViewGroup.LayoutParams params2 = tv_TitleContainer.getLayoutParams();
        ViewGroup.LayoutParams params3 = ll_genderContainer.getLayoutParams();
        ViewGroup.LayoutParams params4 = btn_Next.getLayoutParams();
        ViewGroup.LayoutParams params5 = trashView.getLayoutParams();

        params1.height = preContainer_height;
        params2.height = titleContainer_height;
        params3.height = genderContainer_height;
        params4.height = btn_Next_height;
        params5.height = trashView_height;

        rl_preContainer.setLayoutParams(params1);
        tv_TitleContainer.setLayoutParams(params2);
        ll_genderContainer.setLayoutParams(params3);
        btn_Next.setLayoutParams(params4);
        trashView.setLayoutParams(params5);

        ((LinearLayout)findViewById(R.id.ll_preContainer_Birth_Join)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spn_Gender = (Spinner) findViewById(R.id.spn_Gender_Join);
        spn_Gender.setPrompt("성별 선택");
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this,R.layout.join_genderspinner, genderList);

        adapter.setDropDownViewResource(R.layout.customerservice_claim_spinnertext);
        spn_Gender.setAdapter(adapter);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        pw = intent.getStringExtra("pw");
        name = intent.getStringExtra("name");
    }

    public void goNext(View v){
        String genderTxt = spn_Gender.getSelectedItem().toString();
        if (genderTxt.equals("성별 선택"))
        {
            Toast.makeText(mContext, "성별을 선택해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            CheckBox cb_isShowGender = (CheckBox)findViewById(R.id.cb_isShowGender_Gender_Join);
            boolean genderPBS = !cb_isShowGender.isChecked();
            Intent intent = new Intent(this, com.accepted.acceptedtalentplanet.Join.Birth.MainActivity.class);
            intent.putExtra("email", email);
            intent.putExtra("pw", pw);
            intent.putExtra("name", name);
            intent.putExtra("gender", spn_Gender.getSelectedItem().toString());
            intent.putExtra("genderPBS", genderPBS);
            startActivity(intent);
        }
    }

}
