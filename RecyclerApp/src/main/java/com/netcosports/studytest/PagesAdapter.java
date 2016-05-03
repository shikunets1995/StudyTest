package com.netcosports.studytest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Шикунец on 01.04.2016.
 */
public class PagesAdapter extends FragmentPagerAdapter {
    public PagesAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Fragment0.newInstance("text " + position);
            case 1:
                return ButtonFragment.newInstance();
            case 2:
                return FlagFragment.newInstance();
            case 3:
                return OnlineFragment.newInstance();
            case 4:
                return  TimeFragment.newInstance();
            case 5:
                return  JsonFragment.newInstance();
        }

        return Fragment0.newInstance("text " + position);
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 1:
                return "EditButton";
            case 2:
                return "Flags";
            case 3:
                return "Online";
            case 4:
                 return "Time";
            case 5:
                return "JSON";
        }
        return "List " ;
    }



}

