package com.example.wizte.ik_test_app.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.wizte.ik_test_app.ui.fragments.ViewOnMapFragment;
import com.example.wizte.ik_test_app.ui.fragments.ViewPhotoFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"View photo", "View on map"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return ViewPhotoFragment.newInstance();
        } else {
            return ViewOnMapFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return 2;
    }
}
