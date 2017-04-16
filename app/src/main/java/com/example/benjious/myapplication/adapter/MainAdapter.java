package com.example.benjious.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.benjious.myapplication.fragment.GuoKeFragment;
import com.example.benjious.myapplication.fragment.FirstListFragment;

/**
 * 用这种方式添加tab的Fragment , 这个adapter中的getItem中返回的Fragment不能是之前已经实例过的mFirstListFragment
 * 和mGuolinFragment
 *
 * Created by Benjious on 2017/3/19.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private final Context context;
    private FirstListFragment mFirstListFragment;
    private GuoKeFragment mGuoKeFragment;

    public MainAdapter(FragmentManager fm, Context context, FirstListFragment firstListFragment, GuoKeFragment guoKeFragment) {
        super(fm);
        this.context = context;
        this.mGuoKeFragment = guoKeFragment;
        this.mFirstListFragment = firstListFragment;


        titles = new String[]{
                "网易新闻", "果壳科技", "未知"
        };


    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return mFirstListFragment;
        } else if (position == 1) {
            return mGuoKeFragment;

        } else {
            return new GuoKeFragment();
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
