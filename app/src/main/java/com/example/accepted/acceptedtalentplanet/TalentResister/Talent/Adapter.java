package com.example.accepted.acceptedtalentplanet.TalentResister.Talent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-11-23.
 */

public class Adapter extends BaseAdapter {

    Context mContext;
    private ArrayList<String> arrayList;

    public Adapter(Context mContext, ArrayList<String> arrayList)
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
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.talentresister_talent_listviewbg, null);
        }
        TextView tv_LocationTxt = (TextView) convertView.findViewById(R.id.tv_LocationTxt_TalentRegister);
        tv_LocationTxt.setText(arrayList.get(position));


        ImageView iv_DeleteIcon = convertView.findViewById(R.id.iv_DeleteIcon_TalentRegister);
        iv_DeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
