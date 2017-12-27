package com.dlwx.repast.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 历史纪录的adapter
 */

public class RecordAdapter extends FragmentPagerAdapter {
    List<String> tab_titles;
    List<Fragment> fragments;
    public RecordAdapter(FragmentManager fm,List<String> tab_titles,List<Fragment> fragments) {
        super(fm);
        this.tab_titles = tab_titles;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tab_titles.get(position);
    }
}
