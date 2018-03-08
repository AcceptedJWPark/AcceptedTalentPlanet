package com.example.accepted.acceptedtalentplanet.TalentSharing;

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;
import com.example.accepted.acceptedtalentplanet.SaveSharedPreference;
import com.example.accepted.acceptedtalentplanet.SharingList.SharingList_Adapter;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-24.
 */

public class TalentSharing_ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<TalentSharing_ListItem> list_ArrayList;

    public TalentSharing_ListAdapter(Context context, ArrayList<TalentSharing_ListItem> list_ArrayList) {
        this.context = context;
        this.list_ArrayList = list_ArrayList;
    }


    @Override
    public int getCount() {
        return this.list_ArrayList.size();
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View view = convertView;
        final ViewHolder holder;
        view = null;
        final int index = position;

        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.talentsharing_listviewbg, viewGroup,false);

            holder = new ViewHolder();

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.1);

            ViewGroup.LayoutParams params1 = view.getLayoutParams();
            params1.height = Interesting_ListView_height;
            view.setLayoutParams(params1);
            String fileData = list_ArrayList.get(position).getFileData();
            Log.d("fileData = ", fileData);

            holder.listView_picture = view.findViewById(R.id.TalentSharing_Picture);
            holder.listView_name = view.findViewById(R.id.TalentSharing_Name);
            holder.listView_talent1 = view.findViewById(R.id.TalentSharing_Keyword1);
            holder.listView_talent2 = view.findViewById(R.id.TalentSharing_Keyword2);
            holder.listView_talent3 = view.findViewById(R.id.TalentSharing_Keyword3);
            holder.listView_distance = view.findViewById(R.id.TalentSharing_Distance);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TalentSharing_Popup_Activity.class);
                    intent.putExtra("TalentID", ((TalentSharing_ListItem)getItem(index)).getTalentID());
                    String talentFlag = (((TalentSharing_ListItem)getItem(index)).getTalentFlag()) ? "Give" : "Take";
                    intent.putExtra("TalentFlag", talentFlag);
                    context.startActivity(intent);
                }
            });

        if(fileData.equals("Tk9EQVRB")) {
            holder.listView_picture.setBackgroundResource(list_ArrayList.get(position).getPicture());
        }else{
            holder.listView_picture.setImageBitmap(SaveSharedPreference.StringToBitMap(fileData));
        }
            holder.listView_name.setText(list_ArrayList.get(position).getName());
            holder.listView_talent1.setText(list_ArrayList.get(position).getTalent1());
            holder.listView_talent2.setText(list_ArrayList.get(position).getTalent2());
            holder.listView_talent3.setText(list_ArrayList.get(position).getTalent3());
            holder.listView_distance.setText(list_ArrayList.get(position).getdistance());

        }



        return view;
    }

    static class ViewHolder
    {

        ImageView listView_picture;
        TextView listView_name;
        TextView listView_talent1;
        TextView listView_talent2;
        TextView listView_talent3;
        TextView listView_distance;
    }

}
