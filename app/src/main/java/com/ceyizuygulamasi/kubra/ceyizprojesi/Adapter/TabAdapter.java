package com.ceyizuygulamasi.kubra.ceyizprojesi.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class TabAdapter extends FragmentStatePagerAdapter {


    List<Fragment> fragments;
    public TabAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragments=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
