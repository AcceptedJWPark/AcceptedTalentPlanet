package com.example.accepted.acceptedtalentplanet.Home;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class Home_NewTalent_ListView_Adapter extends RecyclerView.Adapter<Home_NewTalent_ListView_Adapter.ViewHolder> {

    private ArrayList<Home_NewTalent_ListItem> mItems;

    public Home_NewTalent_ListView_Adapter(ArrayList<Home_NewTalent_ListItem> items) {
        mItems = items;
    }


    @Override
    public Home_NewTalent_ListView_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_newtalent_listviewbg, parent, false);

        ViewHolder holder = new ViewHolder(v);
        Log.d("ViewHolder Called", String.valueOf(getItemCount()));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(mItems.get(position).image);
        holder.textName.setText(mItems.get(position).name);
        holder.textTalent.setText(mItems.get(position).talent);


  /*      GridLayoutManager.LayoutParams layoutParams =(GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        int viewWidth = layoutParams.width;*/
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView textName;
        public TextView textTalent;

        public ViewHolder(View view)
        {
            super(view);
            imageView = view.findViewById(R.id.Home_NewTalent_Picture);
            textName = view.findViewById(R.id.Home_NewTalent_Name);
            textTalent = view.findViewById(R.id.Home_NewTalent_Talent);
        }
    }

}
