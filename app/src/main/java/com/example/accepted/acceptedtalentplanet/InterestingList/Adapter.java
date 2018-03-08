package com.example.accepted.acceptedtalentplanet.InterestingList;

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

import com.example.accepted.acceptedtalentplanet.InterestingList.Popup.MainActivity;
import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class Adapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ListItem> arrayList;
    private ImageView iv_Picture;
    private ImageView iv_GiveTakeIcon;
    private TextView tv_Name;
    private TextView tv_Talent1;
    private TextView tv_Talent2;
    private TextView tv_Talent3;
    private TextView tv_GiveTakeTxt;

    public Adapter(Context context, ArrayList<ListItem> arrayList) {
        this.mContext = context;
        this.arrayList = arrayList;
    }


    @Override
    public int getCount() {
        return this.arrayList.size();
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
        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.interestinglist_listviewbg, viewGroup,false);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.1);

            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            params1.height = Interesting_ListView_height;
            view.setLayoutParams(params1);

            iv_Picture = view.findViewById(R.id.iv_Picture_InterestingList);
            tv_Name = view.findViewById(R.id.tv_Name_InterestingList);
            tv_Talent1 = view.findViewById(R.id.tv_Talent1_InterestingList);
            tv_Talent2 = view.findViewById(R.id.tv_Talent2_InterestingList);
            tv_Talent3 = view.findViewById(R.id.tv_Talent3_InterestingList);
            iv_GiveTakeIcon = view.findViewById(R.id.iv_GiveTakeIcon_InterestingList);

            tv_GiveTakeTxt = view.findViewById(R.id.tv_GiveTakeTxt_InterestingList);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("TalentID", ((ListItem)getItem(index)).getTalentID());
                    intent.putExtra("codeGiveTake", arrayList.get(index).getGiveTake_Code());
                    mContext.startActivity(intent);
                }
            });

        }


        iv_Picture.setBackgroundResource(arrayList.get(position).getPicture());
        tv_Name.setText(arrayList.get(position).getName());
        tv_Talent1.setText(arrayList.get(position).getTalent1());
        tv_Talent2.setText(arrayList.get(position).getTalent2());
        tv_Talent3.setText(arrayList.get(position).getTalent3());

        if (arrayList.get(position).getGiveTake_Code()==2)
        {
            tv_GiveTakeTxt.setText("보낸 관심");
            iv_GiveTakeIcon.setImageResource(R.drawable.icon_inter_give);
        }
        else  {
            tv_GiveTakeTxt.setText("받은 관심");
            iv_GiveTakeIcon.setImageResource(R.drawable.icon_inter_take);
        }

        return view;
    }

}
