package com.example.accepted.acceptedtalentplanet.FriendList;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.TalentSharing.Popup.MainActivity;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class Adapter extends BaseAdapter{

    private Context mContext;

    private ArrayList<ListItem> arrayList;

    private ImageView im_Picture;
    private TextView tv_Condition;
    private TextView tv_Name;
    private TextView tv_Keyword1;
    private TextView tv_Keyword2;
    private TextView tv_Keyword3;


    public Adapter(Context mContext, ArrayList<ListItem> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }



    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final int index = position;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.friendlist_listviewbg, viewGroup, false);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels * 0.10);
            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            params1.height = Interesting_ListView_height;
            view.setLayoutParams(params1);

            im_Picture = view.findViewById(R.id.im_Picture_FriendList);
            tv_Name = view.findViewById(R.id.tv_Name_FriendList);
            tv_Keyword1 = view.findViewById(R.id.tv_Keyword1_FriendList);
            tv_Keyword2 = view.findViewById(R.id.tv_Keyword2_FriendList);
            tv_Keyword3 = view.findViewById(R.id.tv_Keyword3_FriendList);
            tv_Condition = view.findViewById(R.id.tv_Condition_FriendList);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("TalentID", ((ListItem)getItem(index)).getTalentID());
                    String talentFlag = (((ListItem)getItem(index)).getTalentType() == 1) ? "Give" : "Take";
                    intent.putExtra("TalentFlag", talentFlag);
                    mContext.startActivity(intent);
                }
            });
        }

        im_Picture.setBackgroundResource(arrayList.get(position).getpicture());
        tv_Name.setText(arrayList.get(position).getname());
        tv_Keyword1.setText(arrayList.get(position).getTalent1());
        tv_Keyword2.setText(arrayList.get(position).getTalent2());
        tv_Keyword3.setText(arrayList.get(position).getTalent3());

        if(arrayList.get(position).getTalentType()==1) {
            switch (arrayList.get(position).getConditionType()) {
                case 1: {
                    tv_Condition.setText(" 님의 관심 재능은 대기 중입니다.");
                    break;
                }
                case 2: {
                    tv_Condition.setText(" 님의 관심 재능은 진행 중입니다.");
                    break;
                }

            }
        }
        else
        {
            switch (arrayList.get(position).getConditionType()) {
                case 1: {
                    tv_Condition.setText(" 님의 재능 드림은 대기 중입니다.");
                    break;
                }
                case 2: {
                    tv_Condition.setText(" 님의 재능 드림은 진행 중입니다.");
                    break;
                }

            }
        }


        return view;


    }

}