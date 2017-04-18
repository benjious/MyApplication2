package com.example.benjious.myapplication.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.benjious.myapplication.fragment.DouBanMovieFragment;
import com.example.benjious.myapplication.fragment.NewsListFragment;

/**
 * 用这种方式添加tab的Fragment , 这个adapter中的getItem中返回的Fragment不能是之前已经实例过的mFirstListFragment
 * 和mGuolinFragment
 *
 * Created by Benjious on 2017/3/19.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private final Context context;
    private NewsListFragment mNewsListFragment;
    private DouBanMovieFragment mGuoKeFragment;

    public MainAdapter(FragmentManager fm, Context context, NewsListFragment newsListFragment, DouBanMovieFragment guoKeFragment) {
        super(fm);
        this.context = context;
        this.mGuoKeFragment = guoKeFragment;
        this.mNewsListFragment = newsListFragment;


        titles = new String[]{
                "网易新闻", "豆瓣电影", "未知"
        };


    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return mNewsListFragment;
        } else if (position == 1) {
            return mGuoKeFragment;

        } else {
            return new DouBanMovieFragment();
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
