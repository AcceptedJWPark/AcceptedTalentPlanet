package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-09-29.
 */

public class CustomerService_Claim_Sharing_Adapter extends BaseAdapter implements View.OnClickListener{

    Context context;
    private ArrayList<CustomerService_Claim_Sharing_Item> list_ArrayList;
    private LayoutInflater mInflater;
    private int mLayout;

    public interface ListBtnClickListener
    {
        void onListBtnClick(int position);
    }

    private ListBtnClickListener listBtnClickListener;

    public CustomerService_Claim_Sharing_Adapter(Context context, int layout, ArrayList<CustomerService_Claim_Sharing_Item> itemList, ListBtnClickListener clickListener){

        this.context = context;
        this.mLayout = layout;
        this.list_ArrayList = itemList;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listBtnClickListener = clickListener;

    }



    @Override
    public int getCount() {
        return list_ArrayList.size();
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolderItem viewHolderItem;

        if(view == null) {
            view = mInflater.inflate(mLayout, viewGroup, false);
            viewHolderItem = new ViewHolderItem();
            viewHolderItem.listView_name = (TextView) view.findViewById(R.id.CustomerService_Claim_SharingListName);
            viewHolderItem.listView_date = (TextView) view.findViewById(R.id.CustomerService_Claim_SharingListRegistDate);
            viewHolderItem.listView_keyword1 = (TextView) view.findViewById(R.id.CustomerService_Claim_SharingListKeyword1);
            viewHolderItem.listView_keyword2 = (TextView) view.findViewById(R.id.CustomerService_Claim_SharingListKeyword2);
            viewHolderItem.listView_keyword3 = (TextView) view.findViewById(R.id.CustomerService_Claim_SharingListKeyword3);
            viewHolderItem.listView_talentcondition = (TextView) view.findViewById(R.id.CustomerService_Claim_SharingListTalentCondition);
            view.setTag(viewHolderItem);

        }

        else
        {
            viewHolderItem = (ViewHolderItem) view.getTag();
        }

        viewHolderItem.listView_name.setText(list_ArrayList.get(position).getname());
        viewHolderItem.listView_keyword1.setText(list_ArrayList.get(position).getkeyword1());
        viewHolderItem.listView_keyword2.setText(list_ArrayList.get(position).getkeyword2());
        viewHolderItem.listView_keyword3.setText(list_ArrayList.get(position).getkeyword3());
        viewHolderItem.listView_date.setText(list_ArrayList.get(position).getdate());
        viewHolderItem.listView_talentcondition.setText(list_ArrayList.get(position).gettalentCondition());



        Button listView_ChoiceBtn = view.findViewById(R.id.CustomerService_Claim_SharingListBtn);
        listView_ChoiceBtn.setTag(position);
        listView_ChoiceBtn.setOnClickListener(this);

        return view;
    }

    class ViewHolderItem
    {
        public TextView listView_name;
        public TextView listView_date;
        public TextView listView_keyword1;
        public TextView listView_keyword2;
        public TextView listView_keyword3;
        public TextView listView_talentcondition;
    }


    @Override
    public void onClick(View v) {

        if(this.listBtnClickListener != null)
        {
            this.listBtnClickListener.onListBtnClick((int)v.getTag());
        }

    }



}
