package com.accepted.acceptedtalentplanet.FriendList;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.accepted.acceptedtalentplanet.TalentSharing.Popup.MainActivity;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class Adapter extends BaseAdapter{

    private Context mContext;

    private ArrayList<ListItem> arrayList;


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
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = convertView;
        final ViewHolder holder;
        view = null;

        final int index = position;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.friendlist_listviewbg, viewGroup, false);

            holder = new ViewHolder();

            holder.iv_Picture = view.findViewById(R.id.im_Picture_FriendList);
            holder.tv_Name = view.findViewById(R.id.tv_Name_FriendList);
            holder.tv_Talent1 = view.findViewById(R.id.tv_Talent1_FriendList);
            holder.tv_Talent2 = view.findViewById(R.id.tv_Talent2_FriendList);
            holder.tv_Talent3 = view.findViewById(R.id.tv_Talent3_FriendList);
            holder.tv_Condition = view.findViewById(R.id.tv_Condition_FriendList);

            holder.trashView1 = view.findViewById(R.id.trashView1_FriendList);
            holder.trashView2 = view.findViewById(R.id.trashView2_FriendList);
            holder.trashView3 = view.findViewById(R.id.trashView3_FriendList);

            holder.ll_pictureContainer = view.findViewById(R.id.ll_pictureContainer_FriendList);
            holder.ll_txtContainer = view.findViewById(R.id.ll_txtContainer_FriendList);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Friendlist_ListView_height = (int) (metrics.heightPixels * 0.10);
            int FriendList_ListView_width = metrics.widthPixels;

            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            ViewGroup.LayoutParams params2 = holder.ll_pictureContainer.getLayoutParams();
            ViewGroup.LayoutParams params3 = holder.ll_txtContainer.getLayoutParams();
            ViewGroup.LayoutParams params4 = holder.trashView1.getLayoutParams();
            ViewGroup.LayoutParams params5 = holder.trashView2.getLayoutParams();
            ViewGroup.LayoutParams params6 = holder.trashView3.getLayoutParams();

            params1.height = Friendlist_ListView_height;
            params2.width = (int) (FriendList_ListView_width * 0.13);
            params2.height = (int) (FriendList_ListView_width * 0.13);
            params3.width = (int) (FriendList_ListView_width * 0.77);
            params4.width = (int) (FriendList_ListView_width * 0.04);
            params5.width = (int) (FriendList_ListView_width * 0.04);
            params6.width = (int) (FriendList_ListView_width * 0.04);

            view.setLayoutParams(params1);
            holder.ll_pictureContainer.setLayoutParams(params2);
            holder.ll_txtContainer.setLayoutParams(params3);
            holder.trashView1.setLayoutParams(params4);
            holder.trashView2.setLayoutParams(params5);
            holder.trashView3.setLayoutParams(params6);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("TalentID", ((ListItem) getItem(index)).getTalentID());
                    String talentFlag = (((ListItem) getItem(index)).getTalentType() == 1) ? "Give" : "Take";
                    intent.putExtra("TalentFlag", talentFlag);
                    mContext.startActivity(intent);
                }
            });


            if (arrayList.get(position).getFilePath().equals("NODATA")) {
                holder.iv_Picture.setBackgroundResource(arrayList.get(position).getpicture());
            } else {
                Glide.with(mContext).load(SaveSharedPreference.getImageUri() + arrayList.get(position).getFilePath()).into(holder.iv_Picture);
            }

            holder.tv_Name.setText(arrayList.get(position).getname());
            holder.tv_Talent1.setText(arrayList.get(position).getTalent1());
            holder.tv_Talent2.setText(arrayList.get(position).getTalent2());
            holder.tv_Talent3.setText(arrayList.get(position).getTalent3());

            if (arrayList.get(position).getTalentType() == 1) {
                switch (arrayList.get(position).getConditionType()) {
                    case 1: {
                        holder.tv_Condition.setText(" 님의 관심 재능은 대기 중입니다.");
                        break;
                    }
                    case 2: {
                        holder.tv_Condition.setText(" 님의 관심 재능은 진행 중입니다.");
                        break;
                    }

                }
            } else {
                switch (arrayList.get(position).getConditionType()) {
                    case 1: {
                        holder.tv_Condition.setText(" 님의 재능 드림은 대기 중입니다.");
                        break;
                    }
                    case 2: {
                        holder.tv_Condition.setText(" 님의 재능 드림은 진행 중입니다.");
                        break;
                    }

                }
            }

        }
        return view;


    }

    static class ViewHolder
    {

        ImageView iv_Picture;
        TextView tv_Name;
        TextView tv_Talent1;
        TextView tv_Talent2;
        TextView tv_Talent3;
        TextView tv_Condition;


        LinearLayout ll_pictureContainer;
        LinearLayout ll_txtContainer;

        View trashView1;
        View trashView2;
        View trashView3;
    }

}