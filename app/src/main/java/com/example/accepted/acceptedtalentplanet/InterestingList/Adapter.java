package com.example.accepted.acceptedtalentplanet.InterestingList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.InterestingList.Popup.MainActivity;
import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class Adapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ListItem> arrayList;
    private String fileData;
    private Bitmap bitmap;
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

            holder.ll_pictureContainer = view.findViewById(R.id.ll_pictureContainer_InterestingList);
            holder.ll_txtContainer = view.findViewById(R.id.ll_txtContainer_InterestingList);
            holder.ll_iconContainer = view.findViewById(R.id.ll_iconContainer_InterestingList);
            holder.trashView1 = view.findViewById(R.id.trashView1_InterestingList);
            holder.trashView2 = view.findViewById(R.id.trashView2_InterestingList);
            holder.trashView3 = view.findViewById(R.id.trashView3_InterestingList);
            holder.trashView4 = view.findViewById(R.id.trashView4_InterestingList);


            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels * 0.1);
            int Interesting_ListView_width = metrics.widthPixels;

            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            ViewGroup.LayoutParams params2 = holder.ll_pictureContainer.getLayoutParams();
            ViewGroup.LayoutParams params3 = holder.ll_txtContainer.getLayoutParams();
            ViewGroup.LayoutParams params4 = holder.ll_iconContainer.getLayoutParams();
            ViewGroup.LayoutParams params5 = holder.trashView1.getLayoutParams();
            ViewGroup.LayoutParams params6 = holder.trashView2.getLayoutParams();
            ViewGroup.LayoutParams params7 = holder.trashView3.getLayoutParams();
            ViewGroup.LayoutParams params8 = holder.trashView4.getLayoutParams();

            params1.height = Interesting_ListView_height;
            params2.width = (int) (Interesting_ListView_width * 0.14);
            params2.height = (int) (Interesting_ListView_width * 0.14);
            params3.width = (int) (Interesting_ListView_width * 0.58);
            params4.width = (int) (Interesting_ListView_width * 0.14);
            params5.width = (int) (Interesting_ListView_width * 0.04);
            params6.width = (int) (Interesting_ListView_width * 0.04);
            params7.width = (int) (Interesting_ListView_width * 0.04);
            params8.width = (int) (Interesting_ListView_width * 0.04);


            view.setLayoutParams(params1);
            holder.ll_pictureContainer.setLayoutParams(params2);
            holder.ll_txtContainer.setLayoutParams(params3);
            holder.ll_iconContainer.setLayoutParams(params4);
            holder.trashView1.setLayoutParams(params5);
            holder.trashView2.setLayoutParams(params6);
            holder.trashView3.setLayoutParams(params7);
            holder.trashView4.setLayoutParams(params8);


            fileData = arrayList.get(position).getFileData();

            bitmap = SaveSharedPreference.StringToBitMap(fileData);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            bytes = stream.toByteArray();

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("TalentID", ((ListItem) getItem(index)).getTalentID());
                    intent.putExtra("codeGiveTake", arrayList.get(index).getGiveTake_Code());
                    if (!fileData.equals("Tk9EQVRB")) {
                        intent.putExtra("bitmapBytes", bytes);
                    }
                    mContext.startActivity(intent);
                }
            });

            if (fileData.equals("Tk9EQVRB")) {
                holder.iv_picture.setBackgroundResource(arrayList.get(position).getPicture());
            } else {
                holder.iv_picture.setImageBitmap(bitmap);
            }

            holder.tv_Name.setText(arrayList.get(position).getName());
            holder.tv_talent1.setText(arrayList.get(position).getTalent1());
            holder.tv_talent2.setText(arrayList.get(position).getTalent2());
            holder.tv_talent3.setText(arrayList.get(position).getTalent3());

            if (arrayList.get(position).getGiveTake_Code() == 2) {
                holder.tv_GiveTakeTxt.setText("보낸 관심");
                holder.iv_GiveTakeIcon.setImageResource(R.drawable.icon_inter_give);
            } else {
                holder.tv_GiveTakeTxt.setText("받은 관심");
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

        LinearLayout ll_pictureContainer;
        LinearLayout ll_txtContainer;
        LinearLayout ll_iconContainer;

        View trashView1;
        View trashView2;
        View trashView3;
        View trashView4;
    }


}
