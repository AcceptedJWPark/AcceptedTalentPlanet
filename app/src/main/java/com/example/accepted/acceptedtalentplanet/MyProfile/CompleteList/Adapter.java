package com.example.accepted.acceptedtalentplanet.MyProfile.CompleteList;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class Adapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<ListItem> arrayList;

    Adapter(Context mContext, ArrayList<ListItem> arrayList)
    {
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {

        View view = convertView;
        final ViewHolder holder;
        view = null;

        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.myprofile_completelist_listviewbg, viewGroup, false);

            holder = new ViewHolder();

            holder.tv_Condition = view.findViewById(R.id.tv_Condition_CompleteList);
            holder.tv_Name = view.findViewById(R.id.tv_Name_CompleteList);
            holder.tv_Date = view.findViewById(R.id.tv_Date_CompleteList);
            holder.tv_Talent1 = view.findViewById(R.id.tv_Talent1_CompleteList);
            holder.tv_Talent2 = view.findViewById(R.id.tv_Talent2_CompleteList);
            holder.tv_Talent3 = view.findViewById(R.id.tv_Talent3_CompleteList);
            holder.tv_Point = view.findViewById(R.id.tv_Point_CompleteList);
            holder.iv_GiveTakeIcon = view.findViewById(R.id.iv_GiveTakeIcon_CompleteList);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels * 0.10);
            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            params1.height = Interesting_ListView_height;
            view.setLayoutParams(params1);

            view.setTag(holder);

        }else
        {
            holder=(ViewHolder) view.getTag();
        }


        holder.tv_Name.setText(arrayList.get(position).getname());
        holder.tv_Point.setText(arrayList.get(position).getpoint());
        holder.tv_Date.setText(arrayList.get(position).getDate());
        holder.tv_Talent1.setText(arrayList.get(position).getKeyword1());
        holder.tv_Talent2.setText(arrayList.get(position).getKeyword2());
        holder.tv_Talent3.setText(arrayList.get(position).getKeyword3());

        if(arrayList.get(position).TalentType_CODE() == 1)
        {
            holder.tv_Condition.setText("관심 재능");
            holder.tv_Point.setTextColor(Color.parseColor("#cc5e93"));
            holder.iv_GiveTakeIcon.setImageResource(R.drawable.icon_handshake_take);
        }
        else
        {
            holder.tv_Condition.setText("재능 드림");
            holder.tv_Point.setTextColor(Color.parseColor("#002c62"));
            holder.iv_GiveTakeIcon.setImageResource(R.drawable.icon_handshake_give);
        }


        return view;
    }

    static class ViewHolder
    {

        TextView tv_Condition;
        TextView tv_Name;
        TextView tv_Date;
        TextView tv_Talent1;
        TextView tv_Talent2;
        TextView tv_Talent3;
        TextView tv_Point;
        ImageView iv_GiveTakeIcon;
    }



}
