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

/**
 * Created by Accepted on 2017-10-31.
 */

public class CustomerService_ClaimListExpandableLVAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<CustomerService_ClaimListQuestionItem> arrayGroup;
    private ArrayList<ArrayList<CustomerService_ClaimListAnswerItem>> arrayChild;

    public CustomerService_ClaimListExpandableLVAdapter(Context context, ArrayList<CustomerService_ClaimListQuestionItem> arrayGroup, ArrayList<ArrayList<CustomerService_ClaimListAnswerItem>> arrayChild)
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
        return arrayChild.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayChild.get(groupPosition).get(childPosition);
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



        String QuestionTitle = arrayGroup.get(groupPosition).getClaimTitle();
        String AnswerorNot = arrayGroup.get(groupPosition).getAnswerorNot();

        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_claimlist_parentbg,  parent,false);

            DisplayMetrics metrics = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(metrics);
            int Interesting_ListView_height = (int) (metrics.heightPixels*0.08);
            ViewGroup.LayoutParams params1 = v.getLayoutParams();
            params1.height = Interesting_ListView_height;
            v.setLayoutParams(params1);

        }
        TextView textGroup1 = (TextView) v.findViewById(R.id.CustomerService_ClaimList_Title);
        TextView textGroup3 = (TextView) v.findViewById(R.id.CustomerService_ClaimList_Condition);
        textGroup1.setText(QuestionTitle);
        textGroup3.setText(AnswerorNot);
        return v;


    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        String Claim_Name = arrayChild.get(groupPosition).get(childPosition).getClaim_Name();
        String Claim_Type = arrayChild.get(groupPosition).get(childPosition).getClaim_Type();
        String Claim_RegistDate = arrayChild.get(groupPosition).get(childPosition).getClaim_RegistDate();
        String Claim = arrayChild.get(groupPosition).get(childPosition).getClaim();
        String Claim_Answer = arrayChild.get(groupPosition).get(childPosition).getClaim_Answer();

        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_claimlist_childbg, null);
        }
        TextView CustomerService_ClaimListName = (TextView) v.findViewById(R.id.CustomerService_ClaimListName);
        TextView CustomerService_ClaimType = (TextView) v.findViewById(R.id.CustomerService_ClaimType);
        TextView CustomerService_Date = (TextView) v.findViewById(R.id.CustomerService_Date);
        TextView CustomerService_ClaimListClaim = (TextView) v.findViewById(R.id.CustomerService_ClaimListClaim);
        TextView CustomerService_ClaimListAnswer = v.findViewById(R.id.CustomerService_ClaimListAnswer);

        CustomerService_ClaimListName.setText(Claim_Name);
        CustomerService_ClaimType.setText(Claim_Type);
        CustomerService_Date.setText(Claim_RegistDate);
        CustomerService_ClaimListClaim.setText(Claim);
        CustomerService_ClaimListAnswer.setText(Claim_Answer);

        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}

