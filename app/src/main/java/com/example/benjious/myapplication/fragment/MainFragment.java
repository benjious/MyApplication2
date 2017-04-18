package com.example.benjious.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.adapter.MainAdapter;

/**
 * Created by Benjious on 2017/3/19.
 */

public class MainFragment extends Fragment {
    private FloatingActionButton mFloatingActionButton;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MainAdapter adapter;
    private Context context;

    private NewsListFragment mNewsListFragment;
    private DouBanMovieFragment guokrFragment;
    public static final int ONE = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context=getActivity();
        mNewsListFragment = NewsListFragment.newInstance(ONE);
        guokrFragment = new DouBanMovieFragment();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container,false);
        initViews(view);

        setHasOptionsMenu(true);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        // 当tab layout位置为果壳精选时，隐藏fab
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
                if (tab.getPosition() == 1) {
                    fab.hide();
                } else {
                    //知乎的界面是就打开
                    fab.show();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        return view;


    }

    private  void initViews(View view){
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mViewPager.setOffscreenPageLimit(2);

        adapter = new MainAdapter(
                getChildFragmentManager(),
                context,
                mNewsListFragment,
                guokrFragment
                );

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
