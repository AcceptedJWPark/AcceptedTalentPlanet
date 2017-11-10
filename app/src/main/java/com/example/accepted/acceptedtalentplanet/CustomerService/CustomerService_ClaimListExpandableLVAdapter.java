package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        String ResistDate = arrayGroup.get(groupPosition).getRegistDate();
        String AnswerorNot = arrayGroup.get(groupPosition).getAnswerorNot();

        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_claimlist_parentbg, null);

        }
        TextView textGroup1 = (TextView) v.findViewById(R.id.CustomerService_ClaimList_Title);
        TextView textGroup2 = (TextView) v.findViewById(R.id.CustomerService_ClaimList_RegistDate);
        TextView textGroup3 = (TextView) v.findViewById(R.id.CustomerService_ClaimList_Condition);
        textGroup1.setText(QuestionTitle);
        textGroup2.setText(ResistDate);
        textGroup3.setText(AnswerorNot);
        return v;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View v = convertView;

        String Question = arrayChild.get(groupPosition).get(childPosition).getQuestion();
        String Answer = arrayChild.get(groupPosition).get(childPosition).getAnswer();

        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.customerservice_claimlist_childbg, null);
        }
        TextView textQuestion = (TextView) v.findViewById(R.id.CustomerService_ClaimListQuestion);
        TextView textAnswer = (TextView) v.findViewById(R.id.CustomerService_ClaimListAnswer);
        textQuestion.setText(Question);
        textAnswer.setText(Answer);
        return v;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}

