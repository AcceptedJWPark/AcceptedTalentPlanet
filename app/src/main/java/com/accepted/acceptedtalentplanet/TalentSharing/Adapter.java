package com.accepted.acceptedtalentplanet.TalentSharing;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.TalentSharing.Popup.MainActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class Adapter extends BaseAdapter {

    Context mContext;
    ArrayList<ListItem> arrayList;

    public Adapter(Context mContext, ArrayList<ListItem> arrayList) {
        this.mContext = mContext;
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

        View view = convertView;
        final ViewHolder holder;
        view = null;
        final int index = position;

        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.talentsharing_listviewbg, viewGroup,false);

            holder = new ViewHolder();

            holder.iv_picture = view.findViewById(R.id.iv_picture_TalentSharing);
            holder.tv_Name = view.findViewById(R.id.tv_Name_TalentSharing);
            holder.tv_talent1 = view.findViewById(R.id.tv_talent1_TalentSharing);
            holder.tv_talent2 = view.findViewById(R.id.tv_talent2_TalentSharing);
            holder.tv_talent3 = view.findViewById(R.id.tv_talent3_TalentSharing);
            holder.tv_distance = view.findViewById(R.id.tv_distance_TalentSharing);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("TalentID", ((ListItem) getItem(index)).getTalentID());
                    String talentFlag = (((ListItem) getItem(index)).getTalentFlag()) ? "Give" : "Take";
                    intent.putExtra("TalentFlag", talentFlag);
                    mContext.startActivity(intent);
                }
            });

            if (arrayList.get(position).getImagePath().equals("NODATA")) {
                holder.iv_picture.setBackgroundResource(arrayList.get(position).getPicture());
            } else {
                Glide.with(mContext).load(SaveSharedPreference.getImageUri() + arrayList.get(position).getImagePath()).into(holder.iv_picture);
                Log.d("imagePath", SaveSharedPreference.getImageUri() + arrayList.get(position).getImagePath());
            }
            holder.tv_Name.setText(arrayList.get(position).getName());
            holder.tv_talent1.setText(arrayList.get(position).getTalent1());
            holder.tv_talent2.setText(arrayList.get(position).getTalent2());
            holder.tv_talent3.setText(arrayList.get(position).getTalent3());
            holder.tv_distance.setText(arrayList.get(position).getdistance());

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

        TextView tv_distance;
    }

}
