package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.Date;

import static com.example.jhj0104.brainbeauty.R.menu.s_main_add;

/**
 * Created by jhj0104 on 2016-12-02.
 */

public class S_parallaxMain extends ParallaxViewPagerBaseActivity {

    MaterialCalendarView widget;
    Calendar calendar = Calendar.getInstance();
    Date today = calendar.getTime();
    String myDate;

    private SlidingTabLayout mNavigBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parallax_main);

        Intent intent = getIntent();
        final S_data data = (S_data) intent.getSerializableExtra("Date");
        final String myDate = (data.Date).toString();
        this.myDate = myDate;
        initValues();

        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mNavigBar = (SlidingTabLayout) findViewById(R.id.navig_tab);
        mHeader = findViewById(R.id.header);

        if (savedInstanceState != null) {
            mHeader.setTranslationY(savedInstanceState.getFloat(HEADER_TRANSLATION_Y));
        }
        setupAdapter();
    }

    @Override
    protected void initValues() {
        int tabHeight = getResources().getDimensionPixelSize(R.dimen.tab_height);
        mMinHeaderHeight = getResources().getDimensionPixelSize(R.dimen.min_header_height);
        mHeaderHeight = getResources().getDimensionPixelSize(R.dimen.header_height);
        mMinHeaderTranslation = - mMinHeaderHeight + tabHeight;
        mNumFragments = 1;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putFloat(HEADER_TRANSLATION_Y, mHeader.getTranslationY());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void setupAdapter() {
        if (mAdapter == null) {
            mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), mNumFragments);
        }
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(mNumFragments);
        mNavigBar.setOnPageChangeListener(getViewPagerChangeListener());
        mNavigBar.setViewPager(mViewPager);
    }

    @Override
    protected void scrollHeader(int scrollY) {
        float translationY = Math.max(-scrollY, mMinHeaderTranslation);
        mHeader.setTranslationY(translationY);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.toString()) {
            case "s_main_add_btn" :
                Toast.makeText(this, "Menu Item 'add' selected", Toast.LENGTH_SHORT).show();
                S_data data = new S_data(myDate, "");
                Intent intent = new Intent(getApplicationContext(), S_listAdd.class);
                intent.putExtra("Date",data);
                startActivity(intent);
                break;
            case "s_main_calendar":
                ClockFragment mDialog = new ClockFragment();
                mDialog.show(getFragmentManager(), "MYTAG");
//                Intent myIntent = new Intent(S_main.this, S_calendar.class);
//                S_main.this.startActivity(myIntent);
//                finish();
                break;
        }
        return true;
    }
    private static class ViewPagerAdapter extends ParallaxFragmentPagerAdapter {

        public ViewPagerAdapter(FragmentManager fm, int numFragments) {
            super(fm, numFragments);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch (position) {
                case 0:

                    fragment = FirstScrollViewFragment.newInstance(0);
                    break;

                default:
                    throw new IllegalArgumentException("Wrong page given " + position);
            }
            return fragment;
        }

    }

    @Override
    public void onBackPressed(){
        Intent myIntent = new Intent(S_parallaxMain.this, BB_menu.class);
        S_parallaxMain.this.startActivity(myIntent);
        finish();
    }
    protected void onResume(){ //다시 정상적으로 ㅅ ㅣㄹ행될 때
        super.onResume();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(s_main_add, menu);
        return true;
    }


}
