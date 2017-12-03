package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-11-02.
 */

public class CustomerService_UpdateExpandableLVAdapter extends BaseExpandableListAdapter{


    private Context context;
    private ArrayList<CustomerService_UpdateListItem> arrayGroup;
    private HashMap<CustomerService_UpdateListItem, ArrayList<String>> arrayChild;

    public CustomerService_UpdateExpandableLVAdapter (Context context, ArrayList<CustomerService_UpdateListItem> arrayGroup, HashMap<CustomerService_UpdateListItem, ArrayList<String>> arrayChild)
    {
        super();
        this.context = context;
        this.arrayGroup = arrayGroup;
        this.arrayChild = arrayChild;
    }


    @Override
    public int getGroupCount() {
        return arrayGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayChild.get(arrayGroup.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View v = convertView;

        String Title = arrayGroup.get(groupPosition).getUpdateTitle();
        String Summary = arrayGroup.get(groupPosition).getUpdateSummary();
        String Date = arrayGroup.get(groupPosition).getUpdateDate();

        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_update_parentbg, parent,false);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.08);
            ViewGroup.LayoutParams params1 = v.getLayoutParams();
            params1.height = Interesting_ListView_height;
            v.setLayoutParams(params1);

        }
        TextView textGroup1 = (TextView) v.findViewById(R.id.CustomerService_UpdateList_Title);
        TextView textGroup2 = (TextView) v.findViewById(R.id.CustomerService_UpdateList_Summary);
        TextView textGroup3 = (TextView) v.findViewById(R.id.CustomerService_UpdateList_Date);
        textGroup1.setText(Title);
        textGroup2.setText(Summary);
        textGroup3.setText(Date);
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childName = arrayChild.get(arrayGroup.get(groupPosition)).get(childPosition);
        View v = convertView;

        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_update_childbg, null);
        }
        TextView textChild = (TextView) v.findViewById(R.id.CustomerService_Update_child_text);
        textChild.setText(childName);
        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}


