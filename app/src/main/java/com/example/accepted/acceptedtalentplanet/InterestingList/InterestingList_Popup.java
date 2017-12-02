package com.example.accepted.acceptedtalentplanet.InterestingList;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-11-30.
 */

public class InterestingList_Popup extends FragmentActivity {

    Context mContext;
    Button Popup_ProgressorCancel;
    ImageView talentSharing_popupclosebtn;
    private InterestingList_Dialog mInterestingList_Dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.interested_popup);

        mContext = getApplicationContext();
        talentSharing_popupclosebtn = (ImageView) findViewById(R.id.TalentSharing_pupupclosebtn);
        talentSharing_popupclosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterestingList_Popup.this.finish();
            }
        });


        Popup_ProgressorCancel = findViewById(R.id.Popup_ProgressorCancel);
        final AlertDialog.Builder ProgressorCancelPopup = new AlertDialog.Builder(this);

        Popup_ProgressorCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

               /* mInterestingList_Dialog = new InterestingList_Dialog(InterestingList_Popup.this, "재능 진행 또는 관심 취소를 진행해주세요!", ProgressListener, CancelListener);
                mInterestingList_Dialog.show();
*/

              ProgressorCancelPopup.setMessage("재능 진행 또는 관심 취소를 진행해주세요!")
                        .setPositiveButton("진행하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"진행 하기 클릭",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("취소하기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(mContext,"취소 하기 클릭",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = ProgressorCancelPopup.create();
                alertDialog.show();
            }
        });

    }
    /*
    public void btnClicked(View v){
        switch (v.getId()) {
            case R.id.Popup_ProgressorCancel:
                mInterestingList_Dialog = new InterestingList_Dialog(getApplicationContext(), "재능 진행 또는 관심 취소를 진행해주세요!", ProgressListener, CancelListener);
                mInterestingList_Dialog.show();
                break;
        }
    }*/

        private View.OnClickListener ProgressListener = new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "진행버튼 클릭",
                        Toast.LENGTH_SHORT).show();

            }
        };

        private View.OnClickListener CancelListener = new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "취소버튼 클릭",
                        Toast.LENGTH_SHORT).show();
                mInterestingList_Dialog.dismiss();
            }
        };



}
