package com.example.accepted.acceptedtalentplanet.InterestingList;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-11-30.
 */

public class InterestingList_Dialog extends Dialog{

    private TextView ContentTxt;
    private TextView ProgressBtn;
    private TextView CancelBtn;

    private String mContentTxt;



    private View.OnClickListener mLeftClickListener;
    private View.OnClickListener mRightClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.interestinglist_dialog);

        ContentTxt = findViewById(R.id.interestinglist_dialog_ContentTxt);
        ProgressBtn = findViewById(R.id.interestinglist_dialog_ProceedBtn);
        CancelBtn = findViewById(R.id.interestinglist_dialog_CancelBtn);
        ContentTxt.setText(mContentTxt);

        if (mLeftClickListener != null && mRightClickListener != null) {
            ProgressBtn.setOnClickListener(mLeftClickListener);
            CancelBtn.setOnClickListener(mRightClickListener);

        } else if (mLeftClickListener != null
                && mRightClickListener == null) {

            ProgressBtn.setOnClickListener(mLeftClickListener);

        } else {


        }

    }

    public InterestingList_Dialog(Context context, String content,
                        View.OnClickListener singleListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContentTxt = content;
        this.mLeftClickListener = singleListener;
    }



    public InterestingList_Dialog(Context context,
                        String content, View.OnClickListener leftListener,
                        View.OnClickListener rightListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContentTxt = content;
        this.mLeftClickListener = leftListener;
        this.mRightClickListener = rightListener;

    }


}

