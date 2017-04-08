package com.example.benjious.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.benjious.myapplication.fragment.GuolinFragment;
import com.example.benjious.myapplication.fragment.FirstListFragment;

/**
 * Created by Benjious on 2017/3/19.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private final Context context;
    private FirstListFragment mFirstListFragment;
    private GuolinFragment mGuolinFragment;
    public MainAdapter(FragmentManager fm, Context context, FirstListFragment firstListFragment, GuolinFragment guolinFragment) {
        super(fm);
        this.context = context;
        this.mGuolinFragment=guolinFragment;
        this.mFirstListFragment = firstListFragment;


        titles=new String[]{
                "a","b"
        };


    }

    @Override
    public Fragment getItem(int position) {
        if (position==0) {
            return mFirstListFragment;
        }else {
            return mGuolinFragment;
        }
    }


    @Override
    public int getCount() {
        return titles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}
