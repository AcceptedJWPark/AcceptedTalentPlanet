package com.example.accepted.acceptedtalentplanet.Home;

/**
 * Created by kwonhong on 2017-10-05.
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    Context mContext;
    ArrayList<Item> mItems;
    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_recycler);

        mContext = getApplicationContext();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        mItems = new ArrayList<>();
        mAdapter = new MyAdapter(mItems);
        recyclerView.setAdapter(mAdapter);

        mItems.add(new Item(R.drawable.textpicture, "정우성", "#연기"));
        mItems.add(new Item(R.drawable.textpicture, "정우성", "#연기"));
        mItems.add(new Item(R.drawable.textpicture, "정우성", "#연기"));
        mItems.add(new Item(R.drawable.textpicture, "정우성", "#연기"));
        mItems.add(new Item(R.drawable.textpicture, "정우성", "#연기"));

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private ArrayList<Item> mItems;

        public MyAdapter(ArrayList<Item> items) {
            mItems = items;
        }


        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_newtalent_listviewbg, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.imageView.setImageResource(mItems.get(position).image);
            holder.textName.setText(mItems.get(position).name);
            holder.textTalent.setText(mItems.get(position).talent);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView textName;
            public TextView textTalent;

            public ViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.Home_NewTalent_Picture);
                textName = view.findViewById(R.id.Home_NewTalent_Name);
                textTalent = view.findViewById(R.id.Home_NewTalent_Talent);
            }
        }

    }
}

