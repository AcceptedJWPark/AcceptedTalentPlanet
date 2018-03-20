package com.example.accepted.acceptedtalentplanet.TalentResister.Talent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.MyTalent;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentResister.Location.Adapter;

import java.util.ArrayList;

import static com.example.accepted.acceptedtalentplanet.SaveSharedPreference.hideKeyboard;

public class MainActivity extends AppCompatActivity {
    private String talent1, talent2, talent3;
    private String[] talentList;
    private boolean isTalentRegisted;
    private boolean isHavingData;
    private MyTalent data;

    private EditText et_Talent;

    private Context mContext;
    private ListView listView;
    private ArrayList<String> arrayList = new ArrayList<>();
    private Adapter adapter;
    private Button btn_Add;

    private LinearLayout ll_InputContainer;
    private LinearLayout ll_TxtContainer;
    private Button btn_Next;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talentresister_talent);

        mContext = getApplicationContext();


        Intent i = getIntent();

        isTalentRegisted = i.getBooleanExtra("talentFlag", true);
        isHavingData = i.getBooleanExtra("HavingDataFlag", false);

        if(isHavingData){
            if(isTalentRegisted)
                data = SaveSharedPreference.getGiveTalentData(mContext);
            else
                data = SaveSharedPreference.getTakeTalentData(mContext);

            talentList = data.getKeywordArray();
            Log.d("isHaving", "data");
            for(int index = 0; index < talentList.length; index++) {
                arrayList.add(talentList[index]);
                Log.d("talent", "talent: " + talentList[index]);
            }
        }

        ll_InputContainer = (LinearLayout) findViewById(R.id.ll_InputContainer_Talent_TalentRegister);
        ll_TxtContainer = (LinearLayout) findViewById(R.id.ll_TxtContainer_TalentResister);
        btn_Next = (Button) findViewById(R.id.btn_Next_Talent_TalentResister);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        int TalentResister_Talent_Btn_height = (int) (metrics.heightPixels*0.04);

        ViewGroup.LayoutParams params3 = btn_Next.getLayoutParams();
        params3.height = TalentResister_Talent_Btn_height;
        btn_Next.setLayoutParams(params3);

        listView = (ListView) findViewById(R.id.listView_Talent_TalentRegister);

        adapter = new Adapter(mContext, arrayList);
        listView.setAdapter(adapter);

        btn_Add = (Button) findViewById(R.id.btn_Add_Talent_TalentRegister);
        et_Talent = (EditText) findViewById(R.id.et_Talent_TalentRegister);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Edittxt = et_Talent.getText().toString();
                adapter = new Adapter(getBaseContext(), arrayList);

                if (Edittxt.length()==0|| arrayList.size()>=3)
                {
                    return;
                }

                    if (arrayList.size()==1)
                    {
                        if (arrayList.get(0).equals(Edittxt)) {
                            Toast.makeText(mContext, "키워드가 중복됩니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    else
                        {
                            arrayList.add(Edittxt);
                            et_Talent.setText("");
                            listView.setAdapter(adapter);
                        }
                }

                else if (arrayList.size()==2) {
                    if (arrayList.get(1).equals(Edittxt)|| arrayList.get(0).equals(Edittxt)) {
                        Toast.makeText(mContext, "키워드가 중복됩니다.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        arrayList.add(Edittxt);
                        et_Talent.setText("");
                        listView.setAdapter(adapter);
                    }
                }
                else if (arrayList.size()==3) {
                    if (arrayList.get(0).equals(Edittxt)|| arrayList.get(1).equals(Edittxt)|| arrayList.get(2).equals(Edittxt)) {
                        Toast.makeText(mContext, "키워드가 중복됩니다.", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        arrayList.add(Edittxt);
                        et_Talent.setText("");
                        listView.setAdapter(adapter);
                    }
                }
                else
                {
                    arrayList.add(Edittxt);
                    et_Talent.setText("");
                    listView.setAdapter(adapter);
                }

            }
        });

        et_Talent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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

        if(arrayList.size() < 3){
            Toast.makeText(getApplicationContext(), "재능 3개 필수 입력입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        talent1 = arrayList.get(0);
        talent2 = arrayList.get(1);
        talent3 = arrayList.get(2);
        Intent i = new Intent(this, com.example.accepted.acceptedtalentplanet.TalentResister.Location.MainActivity.class);
        i.putExtra("talentFlag", isTalentRegisted);
        i.putExtra("isHavingData", isHavingData);

        i.putExtra("talent1", talent1);
        i.putExtra("talent2", talent2);
        i.putExtra("talent3", talent3);
        if(isHavingData) {
            i.putExtra("data", data);
        }
        startActivity(i);

    }





}
