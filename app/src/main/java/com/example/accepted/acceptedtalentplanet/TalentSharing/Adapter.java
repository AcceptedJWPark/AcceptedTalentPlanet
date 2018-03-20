package com.example.accepted.acceptedtalentplanet.TalentSharing;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.TalentSharing.Popup.MainActivity;

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


            Bitmap bitmap = SaveSharedPreference.getPictureFromDB(mContext, arrayList.get(position).getUserID());

            holder.iv_picture = view.findViewById(R.id.iv_picture_TalentSharing);
            holder.tv_Name = view.findViewById(R.id.tv_Name_TalentSharing);
            holder.tv_talent1 = view.findViewById(R.id.tv_talent1_TalentSharing);
            holder.tv_talent2 = view.findViewById(R.id.tv_talent2_TalentSharing);
            holder.tv_talent3 = view.findViewById(R.id.tv_talent3_TalentSharing);

            holder.tv_distance = view.findViewById(R.id.tv_distance_TalentSharing);
            holder.ll_pictureContainer = view.findViewById(R.id.ll_pictureContainer_TalentSharing);
            holder.ll_txtContainer = view.findViewById(R.id.ll_txtContainer_TalentSharing);
            holder.trashView1 = view.findViewById(R.id.trashView1_TalentSharing);
            holder.trashView2 = view.findViewById(R.id.trashView2_TalentSharing);
            holder.trashView3 = view.findViewById(R.id.trashView3_TalentSharing);
            holder.trashView4 = view.findViewById(R.id.trashView4_TalentSharing);


            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int TalentSharing_ListView_height = (int) (metrics.heightPixels*0.1);
            int TalentSharing_ListView_width = metrics.widthPixels;
            Log.d(String.valueOf(TalentSharing_ListView_width), "width");

            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            ViewGroup.LayoutParams params2 = holder.tv_distance.getLayoutParams();
            ViewGroup.LayoutParams params3 = holder.ll_pictureContainer.getLayoutParams();
            ViewGroup.LayoutParams params4 = holder.ll_txtContainer.getLayoutParams();
            ViewGroup.LayoutParams params5 = holder.trashView1.getLayoutParams();
            ViewGroup.LayoutParams params6 = holder.trashView2.getLayoutParams();
            ViewGroup.LayoutParams params7 = holder.trashView3.getLayoutParams();
            ViewGroup.LayoutParams params8 = holder.trashView4.getLayoutParams();


            params1.height = TalentSharing_ListView_height;
            params2.width = (int) (TalentSharing_ListView_width*0.14);
            params2.height = (int) (TalentSharing_ListView_width*0.14);
            params3.width = (int) (TalentSharing_ListView_width*0.56);
            params4.width = (int) (TalentSharing_ListView_width*0.16);
            params5.width = (int) (TalentSharing_ListView_width*0.04);
            params6.width = (int) (TalentSharing_ListView_width*0.04);
            params7.width = (int) (TalentSharing_ListView_width*0.04);
            params8.width = (int) (TalentSharing_ListView_width*0.04);

            view.setLayoutParams(params1);
            holder.ll_pictureContainer.setLayoutParams(params2);
            holder.ll_txtContainer.setLayoutParams(params3);
            holder.tv_distance.setLayoutParams(params4);
            holder.trashView1.setLayoutParams(params5);
            holder.trashView2.setLayoutParams(params6);
            holder.trashView3.setLayoutParams(params7);
            holder.trashView4.setLayoutParams(params8);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("TalentID", ((ListItem) getItem(index)).getTalentID());
                    String talentFlag = (((ListItem) getItem(index)).getTalentFlag()) ? "Give" : "Take";
                    intent.putExtra("TalentFlag", talentFlag);
                    mContext.startActivity(intent);
                }
            });

            if (bitmap == null) {
                holder.iv_picture.setBackgroundResource(arrayList.get(position).getPicture());
            } else {
                holder.iv_picture.setImageBitmap(bitmap);
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
        LinearLayout ll_pictureContainer;
        LinearLayout ll_txtContainer;
        View trashView1;
        View trashView2;
        View trashView3;
        View trashView4;
    }

}
