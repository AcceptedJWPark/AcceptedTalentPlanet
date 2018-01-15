package com.example.accepted.acceptedtalentplanet.CustomerService;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.accepted.acceptedtalentplanet.R;

public class CustomerService_IntroductionActivity extends AppCompatActivity {

    ViewPager TalentPlanet_VP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_introductionmain);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TalentPlanet_VP = (ViewPager) findViewById(R.id.introductionVP);
        TalentPlanet_VP.setAdapter(new pagerAdapter(getApplicationContext()));
        TalentPlanet_VP.setCurrentItem(0);
    }


    View.OnClickListener movePageListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View view) {
            int tag = (int) view.getTag();
            TalentPlanet_VP.setCurrentItem(tag);
        }
    };

    public void setCurrentInflateItem(int position)
    {
        if(position == 0)
            TalentPlanet_VP.setCurrentItem(0);
        else if(position == 1)
            TalentPlanet_VP.setCurrentItem(1);
    }

    private class pagerAdapter extends PagerAdapter
    {

        private LayoutInflater mInflate;

        public pagerAdapter(Context context) {
            super();
            mInflate = LayoutInflater.from(context);
        }

        @Override
        public Object instantiateItem(View pager, final int position)
        {
            View view = null;
            if(position == 0) {
                    view = mInflate.inflate(R.layout.customerservice_introduction2, null);
                }

            else{
                    view = mInflate.inflate(R.layout.customerservice_introduction3, null);
                }


            ((ViewPager) pager).addView(view, 0);
            return view;
        }

            public void destroyItem(View pager, int position, Object view)
            {
                ((ViewPager)pager).removeView((View)view);
            }

            public boolean isViewFromObject(View pager, Object obj)
            {
                return pager == obj;
            }

        public void restoreState(Parcelable arg0, ClassLoader arg1) {}
        public Parcelable saveState() { return null; }
        public void startUpdate(View arg0) {}
        public void finishUpdate(View arg0) {}

        @Override
        public int getCount() {
            return 2;
        }
    }
}
