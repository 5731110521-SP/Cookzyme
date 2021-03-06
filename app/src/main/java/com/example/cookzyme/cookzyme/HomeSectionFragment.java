package com.example.cookzyme.cookzyme;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeSectionFragment extends Fragment {

    ViewPager mViewPager;

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    TabLayout mTab;


    public static HomeSectionFragment newInstance() {
        return new HomeSectionFragment();
    }

    public HomeSectionFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.home_section_fragment, container, false);

        mViewPager = (ViewPager) rootView.findViewById(R.id.pagerHome);
        mTab = (TabLayout)rootView.findViewById(R.id.tabs);
        //pager swipe focus
        mTab.setupWithViewPager(mViewPager);
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mViewPager.setCurrentItem(0);
                        break;
                    case 1:
                        mViewPager.setCurrentItem(1);
                        break;
                    default:
                        mViewPager.setCurrentItem(0);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
    }

    public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

        private String[] tabTitles = new String[]{"Recipe", "Feed"};

        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return HomeTabFragment.newInstance();
                case 1:
                    return HomeFeedFragment.newInstance();
                default:
                    return HomeTabFragment.newInstance();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
