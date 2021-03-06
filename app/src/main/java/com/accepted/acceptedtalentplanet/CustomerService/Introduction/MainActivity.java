package com.accepted.acceptedtalentplanet.CustomerService.Introduction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.accepted.acceptedtalentplanet.R;
import com.accepted.acceptedtalentplanet.SaveSharedPreference;


public class MainActivity extends AppCompatActivity {

    private ViewPager vp_Intro1;
    private TextView text_Intro;
    private boolean isFirstLoading = false;
    private TextView btn_intro;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customerservice_introductionmain);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mContext = getApplicationContext();

        isFirstLoading = getIntent().getBooleanExtra("FirstLoading", false);

        text_Intro = findViewById(R.id.text_Intro);
        btn_intro = findViewById(R.id.btn_intro);
        vp_Intro1 = (ViewPager) findViewById(R.id.vp_Intro1_Introduction);
        vp_Intro1.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0) {
                    text_Intro.setText("1");
                    Log.d(String.valueOf(position),"position");
                    btn_intro.setVisibility(View.GONE);
                }

                else if(position == 1){
                    text_Intro.setText("2");
                    Log.d(String.valueOf(position),"position");
                    btn_intro.setVisibility(View.GONE);
                }
                else{
                    Log.d(String.valueOf(position),"position");
                    text_Intro.setText("3");
                    btn_intro.setVisibility(View.VISIBLE);
                    btn_intro.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(isFirstLoading){
                                SaveSharedPreference.setFirstLoadingFlag(mContext, false);
                                Intent intent = new Intent(mContext, com.accepted.acceptedtalentplanet.LoadingLogin.Login.MainActivity.class);
                                startActivity(intent);
                            }
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        vp_Intro1.setAdapter(new pagerAdapter(getApplicationContext()));
        vp_Intro1.setCurrentItem(0);

    }


    View.OnClickListener movePageListener = new View.OnClickListener()
    {

        @Override
        public void onClick(View view) {
            int tag = (int) view.getTag();
            vp_Intro1.setCurrentItem(tag);
        }
    };

    public void setCurrentInflateItem(int position)
    {
        if(position == 0)
            vp_Intro1.setCurrentItem(0);
        else if(position == 1)
            vp_Intro1.setCurrentItem(1);
        else
            vp_Intro1.setCurrentItem(2);
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
                    view = mInflate.inflate(R.layout.loading_start, null);
                }

            else if(position == 1){
                    view = mInflate.inflate(R.layout.loading_second, null);
                }
                else{
                view = mInflate.inflate(R.layout.loading_third, null);
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
            return 3;
        }
    }
}
