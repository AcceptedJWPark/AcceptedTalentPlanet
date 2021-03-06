package com.accepted.acceptedtalentplanet.CustomerService;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.MyFirebaseMessagingService;
import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.bumptech.glide.Glide;

import static com.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.accepted.acceptedtalentplanet.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2017-10-31.
 */

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private LinearLayout ll_Introduction;
    private LinearLayout ll_Update;
    private LinearLayout ll_Version;
    private LinearLayout ll_Notice;
    private LinearLayout ll_Faq;
    private LinearLayout ll_Question;
    private LinearLayout ll_QuestionList;
    private LinearLayout ll_Claim;
    private LinearLayout ll_ClaimList;
    private LinearLayout ll_Manual;
    private LinearLayout ll_Private;

    private DrawerLayout drawerLayout;
    private View v_drawerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("고객센터");
        ((TextView) findViewById(R.id.DrawerUserID)).setText(SaveSharedPreference.getUserId(mContext));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout_CustomerService);
        v_drawerView = (View) findViewById(R.id.v_drawerView_CustomerService);
        if(SaveSharedPreference.getMyThumbPicturePath() != null)
            Glide.with(mContext).load(SaveSharedPreference.getImageUri() + SaveSharedPreference.getMyThumbPicturePath()).into((ImageView) findViewById(R.id.DrawerPicture));

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v,MainActivity.this, drawerLayout, v_drawerView);
            }
        };
        DrawerLayout_ClickEvent(MainActivity.this,mClicklistener);


        ll_Introduction = (LinearLayout) findViewById(R.id.ll_Introduction_CustomerService);
        ll_Introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Introduction.MainActivity.class);
                startActivity(intent);
            }
        });

        ll_Update = (LinearLayout) findViewById(R.id.ll_Update_CustomerService);
        ll_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Update.MainActivity.class);
                startActivity(intent);
            }
        });

        ll_Version = (LinearLayout) findViewById(R.id.ll_Version_CustomerService);
        ll_Version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Version.MainActivity.class);
                startActivity(intent);
            }
        });

        ll_Notice = (LinearLayout) findViewById(R.id.ll_Notice_CustomerService);
        ll_Notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Notice.MainActivity.class);
                startActivity(intent);
            }
        });


        ll_Faq = (LinearLayout) findViewById(R.id.ll_Faq_CustomerService);
        ll_Faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Faq.MainActivity.class);
                startActivity(intent);
            }
        });

        ll_Question = (LinearLayout) findViewById(R.id.ll_Question_CustomerService);
        ll_Question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Question.MainActivity.class);
                startActivity(intent);
            }
        });


        ll_QuestionList = (LinearLayout) findViewById(R.id.ll_QuestionList_CustomerService);
        ll_QuestionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Question.QuestionList.MainActivity.class);
                startActivity(intent);
            }
        });

        ll_Claim = (LinearLayout) findViewById(R.id.ll_Claim_CustomerService);
        ll_Claim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Claim.MainActivity.class);
                startActivity(intent);
            }
        });

        ll_ClaimList = (LinearLayout) findViewById(R.id.ll_ClaimList_CustomerService);
        ll_ClaimList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Claim.ClaimList.MainActivity.class);
        startActivity(intent);
    }
});

        ll_Manual = (LinearLayout) findViewById(R.id.ll_Manual_CustomerService);
        ll_Manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.accepted.acceptedtalentplanet.CustomerService.Manual.MainActivity.class);
                startActivity(intent);
            }
        });

        ll_Private = findViewById(R.id.ll_Private_CustomerService);
        ll_Private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog));
                AlarmDeleteDialog.setMessage("개인정보 처리방침 Website로 이동합니다.")
                        .setPositiveButton("이동하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Uri uri = Uri.parse("http://13.209.191.97/Accepted/Privacy");
                                Intent it  = new Intent(Intent.ACTION_VIEW,uri);
                                startActivity(it);
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = AlarmDeleteDialog.create();
                alertDialog.show();
                alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(getResources().getColor(R.color.loginPasswordLost));
                alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(getResources().getColor(R.color.loginPasswordLost));
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        drawerLayout.closeDrawers();
        if(MyFirebaseMessagingService.isNewMessageArrive){
            findViewById(R.id.Icon_NewMessage).setVisibility(View.VISIBLE);
        }else{
            findViewById(R.id.Icon_NewMessage).setVisibility(View.GONE);
        }
    }

}
