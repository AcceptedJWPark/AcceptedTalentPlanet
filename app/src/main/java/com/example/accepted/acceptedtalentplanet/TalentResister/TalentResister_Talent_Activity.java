package com.example.accepted.acceptedtalentplanet.TalentResister;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import java.util.ArrayList;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class TalentResister_Talent_Activity extends AppCompatActivity {
    private String Talent1, Talent2, Talent3;
    private boolean TalentRegister_Flag;
    private boolean HavingDataFlag;
    private MyTalent Data;
    private String[] TalentArr;

    EditText Talent_autoEdit;

    Context mContext;
    ListView talent_ListView;
    ArrayList<String> talent_ArrayList = new ArrayList<>();
    TalentResister_Location_Adapter talentLocation_Adapter;
    Button talent_addBtn;

    LinearLayout TalentResister_TalentLL;
    LinearLayout TalentResister_Talent_Txt_LL;
    Button TalentResister_Talent_Btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_talent);

        mContext = getApplicationContext();


        Intent i = getIntent();

        TalentRegister_Flag = i.getBooleanExtra("talentFlag", true);
        HavingDataFlag = i.getBooleanExtra("HavingDataFlag", false);

        if(HavingDataFlag){
            if(TalentRegister_Flag)
                Data = SaveSharedPreference.getGiveTalentData(mContext);
            else
                Data = SaveSharedPreference.getTakeTalentData(mContext);

            TalentArr = Data.getKeywordArray();

            for(int index = 0; index < TalentArr.length; index++)
                talent_ArrayList.add(TalentArr[index]);
        }

        TalentResister_TalentLL = (LinearLayout) findViewById(R.id.TalentResister_TalentLL);
        TalentResister_Talent_Txt_LL = (LinearLayout) findViewById(R.id.TalentResister_Talent_Txt_LL);
        TalentResister_Talent_Btn = (Button) findViewById(R.id.TalentResister_Talent_Btn);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int TalentResister_TalentLL_height = metrics.heightPixels/16;
        int TalentResister_Talent_Txt_LL_height = metrics.heightPixels/12;
        int TalentResister_Talent_Btn_height = metrics.heightPixels/24;

        ViewGroup.LayoutParams params1 = TalentResister_TalentLL.getLayoutParams();
        ViewGroup.LayoutParams params2 = TalentResister_Talent_Txt_LL.getLayoutParams();
        ViewGroup.LayoutParams params3 = TalentResister_Talent_Btn.getLayoutParams();

        params1.height = TalentResister_TalentLL_height;
        params2.height = TalentResister_Talent_Txt_LL_height;
        params3.height = TalentResister_Talent_Btn_height;

        TalentResister_TalentLL.setLayoutParams(params1);
        TalentResister_Talent_Txt_LL.setLayoutParams(params2);
        TalentResister_Talent_Btn.setLayoutParams(params3);

        talent_ListView = (ListView) findViewById(R.id.talent_ListView);

        talentLocation_Adapter = new TalentResister_Location_Adapter(mContext, talent_ArrayList);
        talent_ListView.setAdapter(talentLocation_Adapter);

        talent_addBtn = (Button) findViewById(R.id.addTalentBtn);
        Talent_autoEdit = (EditText) findViewById(R.id.TalentResister_Talent);
        talent_addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Edittxt = Talent_autoEdit.getText().toString();
                talentLocation_Adapter = new TalentResister_Location_Adapter(getBaseContext(), talent_ArrayList);

                if (Edittxt.length()==0||talent_ArrayList.size()>=3)
                {
                    return;
                }

                    if (talent_ArrayList.size()==1)
                    {
                        if (talent_ArrayList.get(0).equals(Edittxt)) {
                            Toast.makeText(mContext, "키워드가 중복됩니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    else
                        {
                            talent_ArrayList.add(Edittxt);
                            Talent_autoEdit.setText("");
                            talent_ListView.setAdapter(talentLocation_Adapter);
                        }
                }

                else if (talent_ArrayList.size()==2) {
                    if (talent_ArrayList.get(1).equals(Edittxt)||talent_ArrayList.get(0).equals(Edittxt)) {
                        Toast.makeText(mContext, "키워드가 중복됩니다.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        talent_ArrayList.add(Edittxt);
                        Talent_autoEdit.setText("");
                        talent_ListView.setAdapter(talentLocation_Adapter);
                    }
                }
                else if (talent_ArrayList.size()==3) {
                    if (talent_ArrayList.get(0).equals(Edittxt)||talent_ArrayList.get(1).equals(Edittxt)||talent_ArrayList.get(2).equals(Edittxt)) {
                        Toast.makeText(mContext, "키워드가 중복됩니다.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        talent_ArrayList.add(Edittxt);
                        Talent_autoEdit.setText("");
                        talent_ListView.setAdapter(talentLocation_Adapter);
                    }
                }
                else
                {
                    talent_ArrayList.add(Edittxt);
                    Talent_autoEdit.setText("");
                    talent_ListView.setAdapter(talentLocation_Adapter);
                }

            }
        });

        Talent_autoEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    hideKeyboard(v, mContext);
                }

            }
        });


    }


    public void goNext(View v){

        if(talent_ArrayList.size() < 3){
            Toast.makeText(getApplicationContext(), "재능 3개 필수 입력입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Talent1 = talent_ArrayList.get(0);
        Talent2 = talent_ArrayList.get(1);
        Talent3 = talent_ArrayList.get(2);
        Intent i = new Intent(this, TalentResister_Location_Activity.class);
        i.putExtra("talentFlag", TalentRegister_Flag);
        i.putExtra("HavingDataFlag", HavingDataFlag);

        i.putExtra("talent1", Talent1);
        i.putExtra("talent2", Talent2);
        i.putExtra("talent3", Talent3);
        if(HavingDataFlag) {
            i.putExtra("Data", Data);
        }
        startActivity(i);

    }





}
