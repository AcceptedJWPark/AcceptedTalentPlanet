package com.example.accepted.acceptedtalentplanet.SharingList;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.InterestingList.InterestingList_Activity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.TalentCondition.TalentCondition_Activity;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_ListItem;
import com.example.accepted.acceptedtalentplanet.TalentSharing.TalentSharing_Popup_Activity;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class SharingList_Adapter extends BaseAdapter{

    Context context;

    ArrayList<SharingList_Item> list_ArrayList;




    public SharingList_Adapter(Context context, ArrayList<SharingList_Item> list_ArrayList) {
        this.context = context;
        this.list_ArrayList = list_ArrayList;
    }


    @Override
    public int getCount() {
        return list_ArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.sharinglist_listviewbg, viewGroup, false);
        }

        TextView SharingList_ConditionType;
        TextView SharingList_Name;
        TextView SharingList_RegistDate;
        TextView SharingList_Keyword1;
        TextView SharingList_Keyword2;
        TextView SharingList_Keyword3;
        TextView SharingList_Txt;
        final Spinner SharingList_Spinner;
        RelativeLayout SharingList_SpinnerRL;

        SharingList_ConditionType = view.findViewById(R.id.SharingList_ConditionType);
        SharingList_Name= view.findViewById(R.id.SharingList_Name);
        SharingList_RegistDate= view.findViewById(R.id.SharingList_RegistDate);
        SharingList_Keyword1= view.findViewById(R.id.SharingList_Keyword1);
        SharingList_Keyword2= view.findViewById(R.id.SharingList_Keyword2);
        SharingList_Keyword3= view.findViewById(R.id.SharingList_Keyword3);
        SharingList_Txt= view.findViewById(R.id.SharingList_Txt);
        SharingList_Spinner = view.findViewById(R.id.SharingList_Spinner);
        SharingList_SpinnerRL = view.findViewById(R.id.SharingList_SpinnerRL);




            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.10);
            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            params1.height = Interesting_ListView_height;
            view.setLayoutParams(params1);

            ArrayAdapter adapter = ArrayAdapter.createFromResource(context, R.array.SharingList_SpinnerList, R.layout.sharinglist_spinnertext);
            SharingList_Spinner.setAdapter(adapter);
            SharingList_SpinnerRL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharingList_Spinner.setFocusableInTouchMode(true);
                    SharingList_Spinner.performClick();
                }
            });



        SharingList_Name.setText(list_ArrayList.get(position).getname());
        SharingList_RegistDate.setText(list_ArrayList.get(position).getRegistDate());
        SharingList_Keyword1.setText(list_ArrayList.get(position).getKeyword1());
        SharingList_Keyword2.setText(list_ArrayList.get(position).getKeyword2());
        SharingList_Keyword3.setText(list_ArrayList.get(position).getKeyword3());

        switch (list_ArrayList.get(position).getTalentConditionType_CODE()){
            case 1: {
                SharingList_ConditionType.setText("[진행 중]");
                SharingList_Txt.setText("진행 중입니다.");
                break;
            }
                case 2:{
                    SharingList_ConditionType.setText("[공유 완료]");
                    SharingList_Txt.setText("완료 하였습니다.");
                    break;
                }

            case 3:{
                SharingList_ConditionType.setText("[진행 취소]");
                SharingList_Txt.setText("진행 취소하였습니다.");
                break;
            }
    }

        return view;
    }

}
