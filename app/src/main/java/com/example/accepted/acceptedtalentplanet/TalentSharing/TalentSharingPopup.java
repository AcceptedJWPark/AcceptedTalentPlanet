package com.example.accepted.acceptedtalentplanet.TalentSharing;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.accepted.acceptedtalentplanet.R;

/**
 * Created by Accepted on 2017-10-24.
 */

public class TalentSharingPopup extends FragmentActivity{

    ImageView talentSharing_pupupclosebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.talentsharing_popup);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);

        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;



        talentSharing_pupupclosebtn = (ImageView) findViewById(R.id.TalentSharing_pupupclosebtn);
        talentSharing_pupupclosebtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TalentSharingPopup.this.finish();
            }
        });
    }


}
