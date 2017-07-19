package com.example.cookzyme.cookzyme;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends FragmentActivity {

    ViewPager mViewPager;
//    private static HomeSectionFragment homeSectionFragment = new HomeSectionFragment();
//    private static Profile profile = new Profile();
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Refrigerator:
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_Superstore:
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_home:
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_Profile:
                    mViewPager.setCurrentItem(3);
                    return true;
                case R.id.navigation_Setting:
                    mViewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }

    };

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            navigation.setSelectedItemId(navigation.getMenu().getItem(position).getItemId());
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(mOnPageChangeListener);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mViewPager.setCurrentItem(2);

    }

    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new RefrigeratorSectionFragment();
                case 1:
                    return new HomeFeedFragment();
                case 2:
                    return new HomeSectionFragment();
                case 3:
                    return new Profile();
                case 4:
                    return new HomeSectionFragment();
                default:
                    return new Profile();
            }
        }

        @Override
        public int getCount() {
            return 5;
        }
    }

}


