package com.accepted.acceptedtalentplanet.InterestingList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.InterestingList.Popup.MainActivity;
import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class Adapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ListItem> arrayList;
    byte[] bytes;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final int index = position;

        View view = convertView;
        final ViewHolder holder;
        view = null;


        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.interestinglist_listviewbg, viewGroup, false);

            holder = new ViewHolder();

            holder.iv_picture = view.findViewById(R.id.iv_Picture_InterestingList);
            holder.tv_Name = view.findViewById(R.id.tv_Name_InterestingList);
            holder.tv_talent1 = view.findViewById(R.id.tv_Talent1_InterestingList);
            holder.tv_talent2 = view.findViewById(R.id.tv_Talent2_InterestingList);
            holder.tv_talent3 = view.findViewById(R.id.tv_Talent3_InterestingList);
            holder.iv_GiveTakeIcon = view.findViewById(R.id.iv_GiveTakeIcon_InterestingList);
            holder.tv_GiveTakeTxt = view.findViewById(R.id.tv_GiveTakeTxt_InterestingList);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("TalentID", ((ListItem) getItem(index)).getTalentID());
                    intent.putExtra("codeGiveTake", arrayList.get(index).getGiveTake_Code());
                    mContext.startActivity(intent);
                }
            });

            if (arrayList.get(position).getFilePath().equals("NODATA")) {
                holder.iv_picture.setBackgroundResource(arrayList.get(position).getPicture());
            } else {
                Glide.with(mContext).load(SaveSharedPreference.getImageUri() + arrayList.get(position).getFilePath()).into(holder.iv_picture);
            }

            holder.tv_Name.setText(arrayList.get(position).getName());
            holder.tv_talent1.setText(arrayList.get(position).getTalent1());
            holder.tv_talent2.setText(arrayList.get(position).getTalent2());
            holder.tv_talent3.setText(arrayList.get(position).getTalent3());

            if (arrayList.get(position).getGiveTake_Code() == 2) {
                holder.tv_GiveTakeTxt.setText("보 냄");
                holder.iv_GiveTakeIcon.setImageResource(R.drawable.icon_inter_give);
            } else {
                holder.tv_GiveTakeTxt.setText("받 음");
                holder.iv_GiveTakeIcon.setImageResource(R.drawable.icon_inter_take);
            }
        }
            return view;

    }

    static class ViewHolder
    {

        ImageView iv_picture;
        TextView tv_Name;
        TextView tv_talent1;
        TextView tv_talent2;
        TextView tv_talent3;

        ImageView iv_GiveTakeIcon;
        TextView tv_GiveTakeTxt;
    }


}
