package com.example.accepted.acceptedtalentplanet.TalentCondition;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

public class TalentConditionActivity_1 extends AppCompatActivity {

    DrawerLayout slidingMenuDL;
    View drawerView;
    ImageView imgDLOpenMenu;

    Button TalentCondition_ShowGiveBtn;
    Button TalentCondition_ShowTakeBtn;
    TextView TalentCondition;
    TextView TalentCondition_Keyword1;
    TextView TalentCondition_Keyword2;
    TextView TalentCondition_Keyword3;
    TextView TalentCondition_Text1;
    TextView TalentCondition_Text2;
    Button TalentCondition_btn01;
    Button TalentCondition_btn02;
    Button TalentCondition_btn03;
    LinearLayout TalentCondition_KeywordBox;
    boolean TalentCondition_Give;
    boolean TalentCondition_Take;
    String TalentCondition_Give_Keyword[] = {"Guitar", "Piano", "Drum"};
    String TalentCondition_Take_Keyword[] = {"영어", "영어 회화", "영어 독학"};
    int GiveTalentConditionCode = 1;
    int TakeTalentConditionCode = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentcondition_1);

        TalentCondition_ShowGiveBtn = (Button) findViewById(R.id.TalentCondition_ShowGive);
        TalentCondition_ShowTakeBtn = (Button) findViewById(R.id.TalentCondition_ShowTake);
        TalentCondition = (TextView) findViewById(R.id.TalentCondition_Condition);
        TalentCondition_Keyword1 = (TextView) findViewById(R.id.TalentCondition_Keyword1);
        TalentCondition_Keyword2 = (TextView) findViewById(R.id.TalentCondition_Keyword2);
        TalentCondition_Keyword3 = (TextView) findViewById(R.id.TalentCondition_Keyword3);
        TalentCondition_Text1 = (TextView) findViewById(R.id.TalentCondition_TextView1);
        TalentCondition_Text2 = (TextView) findViewById(R.id.TalentCondition_TextView2);
        TalentCondition_btn01 = (Button) findViewById(R.id.TalentCondition_Button1);
        TalentCondition_btn02 = (Button) findViewById(R.id.TalentCondition_Button2);
        TalentCondition_btn03 = (Button) findViewById(R.id.TalentCondition_Button3);
        TalentCondition_KeywordBox = (LinearLayout) findViewById(R.id.TalentCondition_KeywordBox);

        TalentCondition_Give = true;
        TalentCondition_Take = true;

        ShowGiveBtnClicked();

        TalentCondition_ShowGiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowGiveBtnClicked();
            }
        });

        TalentCondition_ShowTakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowTakeBtnClicked();
            }
        });




        //ToolBar 적용하기
        slidingMenuDL = (DrawerLayout) findViewById(R.id.TalentCondition1_listboxDL);
        drawerView = (View) findViewById(R.id.TalentCondition_container1);
        imgDLOpenMenu = (ImageView) findViewById(R.id.DrawerOpenImg);
        imgDLOpenMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingMenuDL.openDrawer(drawerView);

            }
        });

    }

    public void ShowGiveBtnClicked() {
        if (TalentCondition_Give == false) {
            TalentCondition_KeywordBox.setVisibility(View.GONE);
            TalentCondition_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_whitebg));
            TalentCondition_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_bluebg));
            TalentCondition_ShowTakeBtn.setTextColor(Color.parseColor("#bebebe"));
            TalentCondition_ShowGiveBtn.setTextColor(Color.parseColor("#ffffff"));
            TalentCondition_Text1.setText("\"재능드림이 등록되지 않았습니다.");
            TalentCondition_Text2.setText("재능드림을 등록하여 회원님의 재능을 공유해주세요!\"");
            TalentCondition_btn01.setVisibility(View.GONE);
            TalentCondition_btn02.setVisibility(View.GONE);
            TalentCondition_btn03.setVisibility(View.VISIBLE);
            TalentCondition_btn03.setText("재능드림 등록하기");
        } else {
            TalentCondition_KeywordBox.setVisibility(View.VISIBLE);
            TalentCondition_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_whitebg));
            TalentCondition_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_bluebg));
            TalentCondition_ShowTakeBtn.setTextColor(Color.parseColor("#bebebe"));
            TalentCondition_ShowGiveBtn.setTextColor(Color.parseColor("#ffffff"));
            TalentCondition_btn01.setVisibility(View.VISIBLE);
            TalentCondition_btn02.setVisibility(View.VISIBLE);
            TalentCondition_btn03.setVisibility(View.GONE);
            TalentCondition_btn03.setText("재능드림 등록하기");
            TalentCondition_Keyword1.setText(TalentCondition_Give_Keyword[0]);
            TalentCondition_Keyword2.setText(TalentCondition_Give_Keyword[1]);
            TalentCondition_Keyword3.setText(TalentCondition_Give_Keyword[2]);

            if (GiveTalentConditionCode == 1) {
                TalentCondition_Text1.setText("\"현재 회원님의 재능드림 상태는 대기중 입니다.");
                TalentCondition_Text2.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!\"");
                TalentCondition_btn01.setText("관심목록 확인");
                TalentCondition_btn02.setText("T.Sharing");
                TalentCondition.setText("대기중...");


            } else if (GiveTalentConditionCode == 2) {
                TalentCondition_Text1.setText("\"현재 회원님의 재능드림 상태는 진행중 입니다.");
                TalentCondition_Text2.setText("다음 단계를 진행해주세요!\"");
                TalentCondition_btn01.setText("완료하기");
                TalentCondition_btn02.setText("취소하기");
                TalentCondition.setText("진행중...");
            } else if (GiveTalentConditionCode == 3) {
                TalentCondition_Text1.setText("\"현재 회원님의 재능드림 상태는 완료 입니다.");
                TalentCondition_Text2.setText("재능 재등록 또는 재능 수정하기를 진행해주세요!\"");
                TalentCondition_btn01.setText("재능드림 재등록");
                TalentCondition_btn02.setText("재능드림 수정하기");
                TalentCondition.setText("완료");
            }
        }
    }

    public void ShowTakeBtnClicked() {
        if (TalentCondition_Take == false) {
            TalentCondition_KeywordBox.setVisibility(View.GONE);
            TalentCondition_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_whitebg));
            TalentCondition_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_bluebg));
            TalentCondition_ShowGiveBtn.setTextColor(Color.parseColor("#bebebe"));
            TalentCondition_ShowTakeBtn.setTextColor(Color.parseColor("#ffffff"));
            TalentCondition_Text1.setText("\"관심재능이 등록되지 않았습니다.");
            TalentCondition_Text2.setText("관심재능을 등록하여 회원님의 관심사를 시작해보세요!\"");
            TalentCondition_btn01.setVisibility(View.GONE);
            TalentCondition_btn02.setVisibility(View.GONE);
            TalentCondition_btn03.setVisibility(View.VISIBLE);
            TalentCondition_btn03.setText("관심재능 등록하기");
        } else {
            TalentCondition_KeywordBox.setVisibility(View.VISIBLE);
            TalentCondition_ShowGiveBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_whitebg));
            TalentCondition_ShowTakeBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.textbox_bluebg));
            TalentCondition_ShowGiveBtn.setTextColor(Color.parseColor("#bebebe"));
            TalentCondition_ShowTakeBtn.setTextColor(Color.parseColor("#ffffff"));
            TalentCondition_btn01.setVisibility(View.VISIBLE);
            TalentCondition_btn02.setVisibility(View.VISIBLE);
            TalentCondition_btn03.setVisibility(View.GONE);
            TalentCondition_btn03.setText("관심재능 등록하기");
            TalentCondition_Keyword1.setText(TalentCondition_Take_Keyword[0]);
            TalentCondition_Keyword2.setText(TalentCondition_Take_Keyword[1]);
            TalentCondition_Keyword3.setText(TalentCondition_Take_Keyword[2]);

            if (TakeTalentConditionCode == 1) {
                TalentCondition_Text1.setText("\"현재 회원님의 관심재능 상태는 대기중 입니다.");
                TalentCondition_Text2.setText("관심목록 확인 또는 T.Sharing을 확인해보세요!\"");
                TalentCondition_btn01.setText("관심목록 확인");
                TalentCondition_btn02.setText("T.Sharing");
                TalentCondition.setText("대기중...");
            } else if (TakeTalentConditionCode  == 2) {
                TalentCondition_Text1.setText("\"현재 회원님의 관심재능 상태는 진행중 입니다.");
                TalentCondition_Text2.setText("다음 단계를 진행해주세요!\"");
                TalentCondition_btn01.setText("완료하기");
                TalentCondition_btn02.setText("취소하기");
                TalentCondition.setText("진행중...");
            } else if (TakeTalentConditionCode  == 3) {
                TalentCondition_Text1.setText("\"현재 회원님의 관심재능 상태는 완료 입니다.");
                TalentCondition_Text2.setText("재능 재등록 또는 재능 수정하기를 진행해주세요!\"");
                TalentCondition_btn01.setText("관심재능 재등록");
                TalentCondition_btn02.setText("관심재능 수정하기");
                TalentCondition.setText("완료");
            }

        }


    }

}
