package com.example.accepted.acceptedtalentplanet.MyProfile;

import android.content.Context;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class MyProfile_CompleteList_Adapter extends BaseAdapter{

    Context context;
    ArrayList<MyProfile_CompleteList_Item> list_ArrayList;

    MyProfile_CompleteList_Adapter(Context context, ArrayList<MyProfile_CompleteList_Item> list_ArrayList)
    {
        this.context = context;
        this.list_ArrayList = list_ArrayList;
    }

    @Override
    public int getCount() {
        return list_ArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return list_ArrayList.get(position);
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
            view = LayoutInflater.from(context).inflate(R.layout.myprofile_completelist_listviewbg, viewGroup, false);

            holder = new ViewHolder();

            holder.CompleteList_ConditionType = view.findViewById(R.id.CompleteList_ConditionType);
            holder.CompleteList_Name = view.findViewById(R.id.CompleteList_Name);
            holder.CompleteList_RegistDate = view.findViewById(R.id.CompleteList_RegistDate);
            holder.CompleteList_Keyword1 = view.findViewById(R.id.CompleteList_Keyword1);
            holder.CompleteList_Keyword2 = view.findViewById(R.id.CompleteList_Keyword2);
            holder.CompleteList_Keyword3 = view.findViewById(R.id.CompleteList_Keyword3);
            holder.CompleteList_Point = view.findViewById(R.id.CompleteList_Point);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
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


        holder.CompleteList_Name.setText(list_ArrayList.get(position).getname());
        holder.CompleteList_Point.setText(list_ArrayList.get(position).getpoint());
        holder.CompleteList_RegistDate.setText(list_ArrayList.get(position).getRegistDate());
        holder.CompleteList_Keyword1.setText(list_ArrayList.get(position).getKeyword1());
        holder.CompleteList_Keyword2.setText(list_ArrayList.get(position).getKeyword2());
        holder.CompleteList_Keyword3.setText(list_ArrayList.get(position).getKeyword3());

        if(list_ArrayList.get(position).TalentType_CODE() == 1)
        {
            holder.CompleteList_ConditionType.setText("[관심 재능]");
            holder.CompleteList_Point.setTextColor(Color.parseColor("#ff006a"));
        }
        else
        {
            holder.CompleteList_ConditionType.setText("[재능 드림]");
            holder.CompleteList_Point.setTextColor(Color.parseColor("#0077ff"));
        }


        return view;
    }

    static class ViewHolder
    {

        TextView CompleteList_ConditionType;
        TextView CompleteList_Name;
        TextView CompleteList_RegistDate;
        TextView CompleteList_Keyword1;
        TextView CompleteList_Keyword2;
        TextView CompleteList_Keyword3;
        TextView CompleteList_Point;
    }



}
