package com.example.benjious.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.activity.NewsDetailActivity;
import com.example.benjious.myapplication.adapter.FirstAdapter;
import com.example.benjious.myapplication.bean.DataBean;
import com.example.benjious.myapplication.api.Urls;
import com.example.benjious.myapplication.presenter.NewsListFragmentImpl;
import com.example.benjious.myapplication.presenter.FirstPresenter;
import com.example.benjious.myapplication.view.FirstView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjious on 2017/3/19.
 */

public class NewsListFragment extends Fragment implements FirstView, SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;

    private int type = 0;
    private int pageIndex = 0;
    private ArrayList<DataBean> mData;
    private FirstPresenter mFirstPresenter;
    private LinearLayoutManager mLayoutManager;
    private FirstAdapter mFirstAdapter;


    //----------------------------------------------

    /**
     * 下面是两个Listener
     *
     */
    private FirstAdapter.OnItemClickListener mOnItemClickListener = new FirstAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            /**
             * 当我按下新闻条目,这个传递数据给DetailActivity
             * 1.获取基本信息,就是bean
             * 2.根据bean,去加载另外的界面
             *
             */
            DataBean data = mFirstAdapter.getItem(position);
            System.out.println("点击的数据======" + data.getTitle());
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", data);

            View intoView = view.findViewById(R.id.detail_img);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            intoView, getString(R.string.transition_news_img));
            ActivityCompat.startActivity(getActivity(),intent,options.toBundle());


        }
    };

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        //最后一个的条目的角标 0,1,2,3,4,5,6,7,
        private int mLastVisibleItemPostion;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mLastVisibleItemPostion = mLayoutManager.findLastVisibleItemPosition();

        }


        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //当没有滚动 并且 到达最后一个条目的时候,并且到了adapter存放的量
            if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisibleItemPostion + 1 == mFirstAdapter.getItemCount() && mFirstAdapter.isShowFooter()) {
                mFirstPresenter.loadData(type, pageIndex + Urls.PAZE_SIZE);
            }

        }



    };



    //-------------------------------------------------------------------
    public static NewsListFragment newInstance(int type) {
        Bundle args = new Bundle(type);
        NewsListFragment fragment = new NewsListFragment();
        args.putInt("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirstPresenter = new NewsListFragmentImpl(this);
        //保存数据
        type = getArguments().getInt("type");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_news_list, container, false);
        initView(view);
        onRefresh();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.my_toolbar_color);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mFloatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mFloatingActionButton.setRippleColor(getResources().getColor(R.color.colorAccent));

        mFirstAdapter = new FirstAdapter(getActivity());
        mRecyclerView.setAdapter(mFirstAdapter);

        mFirstAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void addData(List<DataBean> mList) {
        if (null == mData) {
            mData = new ArrayList<>();
        }

        //把数据放进去
        mData.addAll(mList);
        if (pageIndex == 0) {
            mFirstAdapter.setData(mData);
        } else {
            //如果服务器那个获取没有东西，设置标志到达底部
            if (mList.size() == 0 || mList == null) {
                mFirstAdapter.isShowFooter(false);
            }
            mFirstAdapter.notifyDataSetChanged();
        }
        // pageIndex : 0 -- 20  40-20
        pageIndex += Urls.PAZE_SIZE;
    }

    @Override
    public void showLoadFail() {
        if (pageIndex == 0) {
            mFirstAdapter.isShowFooter(false);
            mFirstAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecyclerView.getRootView() : getActivity().findViewById(R.id.main_content);
        Snackbar.make(view, "加载数据失败", Snackbar.LENGTH_SHORT).show();
    }


    //让页数定位为0,数据清空,重新加载
    @Override
    public void onRefresh() {
        pageIndex = 0;
        if (mData != null) {
            mData.clear();
        }
        //这个没有判断什么，直接进入加载通道
        mFirstPresenter.loadData(type, pageIndex);

    }

}
