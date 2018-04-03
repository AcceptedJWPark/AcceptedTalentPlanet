package com.accepted.acceptedtalentplanet.CustomerService.Update;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-11-02.
 */

public class ELVAdapter extends BaseExpandableListAdapter{


    private Context mContext;
    private ArrayList<ListItem> arrayList_Parent;
    private HashMap<ListItem, ArrayList<String>> arrayList_Child;

    public ELVAdapter(Context context, ArrayList<ListItem> arrayList_Parent, HashMap<ListItem, ArrayList<String>> arrayList_Child)
    {
        super();
        this.mContext = context;
        this.arrayList_Parent = arrayList_Parent;
        this.arrayList_Child = arrayList_Child;
    }


    @Override
    public int getGroupCount() {
        return arrayList_Parent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayList_Child.get(arrayList_Parent.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayList_Parent.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayList_Child.get(arrayList_Parent.get(groupPosition)).get(childPosition);
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

        String title = arrayList_Parent.get(groupPosition).getTitle();
        String summary = arrayList_Parent.get(groupPosition).getSummary();
        String date = arrayList_Parent.get(groupPosition).getUpdateDate();

        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_update_parentbg, parent,false);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.1);
            ViewGroup.LayoutParams params1 = v.getLayoutParams();
            params1.height = Interesting_ListView_height;
            v.setLayoutParams(params1);

        }
        TextView tv_ParentTitle = (TextView) v.findViewById(R.id.tv_ParentTitle_Update);
        TextView tv_ParentSummary = (TextView) v.findViewById(R.id.tv_ParentSummary_Update);
        TextView tv_ParentDate = (TextView) v.findViewById(R.id.tv_ParentDate_Update);
        tv_ParentTitle.setText(title);
        tv_ParentSummary.setText(summary);
        tv_ParentDate.setText(date);
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childName = arrayList_Child.get(arrayList_Parent.get(groupPosition)).get(childPosition);
        View v = convertView;

        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_update_childbg, null);
        }
        TextView tv_ChildTitle = (TextView) v.findViewById(R.id.tv_ChildTitle_Update);
        tv_ChildTitle.setText(childName);
        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}


