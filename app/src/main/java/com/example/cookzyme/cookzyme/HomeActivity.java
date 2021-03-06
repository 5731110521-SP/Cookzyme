package com.example.cookzyme.cookzyme;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import com.example.cookzyme.cookzyme.ref.EmptyActivity;


public class HomeActivity extends ActionBarActivity {
    int beforeCurrent =2;
    ViewPager mViewPager;
//    private static HomeSectionFragment homeSectionFragment = new HomeSectionFragment();
//    private static ProfileFragment profile = new ProfileFragment();
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Refrigerator:
//                    System.out.println("0 beforeCurrent "+ beforeCurrent);
                    beforeCurrent = mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem(0);
//                    System.out.println("After set 0");
                    return true;
                case R.id.navigation_Superstore:
//                    System.out.println("1 beforeCurrent "+ beforeCurrent);
                    beforeCurrent = mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem(1);
//                    System.out.println("After set 1");
                    return true;
                case R.id.navigation_home:
//                    System.out.println("2 beforeCurrent "+ beforeCurrent);
                    beforeCurrent = mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem(2);
//                    System.out.println("After set 2");
                    return true;
                case R.id.navigation_Profile:
//                    System.out.println("3 beforeCurrent "+ beforeCurrent);
                    if(beforeCurrent!=3){
//                        System.out.println("if not not refresh");
                    }else{
//                        Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.pager + ":" + mViewPager.getCurrentItem());
//                        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                        ft.detach(page);
//                        ft.attach(page);
//                        ft.commit();
                        getSupportFragmentManager().popBackStack();
//                        System.out.println("else 3  refresh");
                    }
                    beforeCurrent = mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem(3);
//                    System.out.println("After set 3");
                    return true;
                case R.id.navigation_Setting:
//                    System.out.println("4 beforeCurrent "+ beforeCurrent);
                    beforeCurrent = mViewPager.getCurrentItem();
                    mViewPager.setCurrentItem(4);
//                    System.out.println("After set 4");
//                    System.out.println(mViewPager.getCurrentItem());
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
        mViewPager.setOffscreenPageLimit(20);

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
                    return RefrigeratorSectionFragment.newInstance();
                case 1:
//                    return ShareFragment.newInstance();
                    return SuperstoreFragment.newInstance();
                case 2:
                    return HomeSectionFragment.newInstance();
                case 3:
                    return ProfileFragment.newInstance();
                case 4:
                    return new EmptyActivity();
                default:
                    return ProfileFragment.newInstance();
            }
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount() == 0) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to exit?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            HomeActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }else{
            super.onBackPressed();
        }
    }
}


