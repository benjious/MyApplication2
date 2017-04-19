package com.example.benjious.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.activity.DouBanDetailActivity;
import com.example.benjious.myapplication.adapter.DouBanAdapter;
import com.example.benjious.myapplication.api.ConstantsImageUrl;
import com.example.benjious.myapplication.api.Urls;
import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;
import com.example.benjious.myapplication.presenter.DouBanListPresenter;
import com.example.benjious.myapplication.presenter.DouBanPresenterImpl;
import com.example.benjious.myapplication.util.ImgLoadUtil;
import com.example.benjious.myapplication.view.DouBanView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.type;
import static android.media.CamcorderProfile.get;

/**
 * Created by Benjious on 2017/3/19.
 */

public class DouBanMovieFragment extends Fragment implements DouBanView, DouBanAdapter.OnMovieItemClickListener {
    private XRecyclerView mXRecyclerView;
    private View mHeadView;
    private DouBanAdapter mDouBanAdapter;
    private ArrayList<SubjectBean> mSubjectBeen;
    private DouBanListPresenter mDouBanListPresenter;
    private LinearLayoutManager mLayoutManager;
    private boolean isFirstTime = true;

    public static DouBanMovieFragment newInstance() {
        Bundle args = new Bundle();
        DouBanMovieFragment fragment = new DouBanMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int mLastVisibleItemPosition;//最后一个角标位置

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mLastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //正在滚动
            if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisibleItemPosition + 1 == mDouBanAdapter.getItemCount()
                    && mDouBanAdapter.isShowFooter()) {
                mDouBanListPresenter.loadData(0);
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, null);
        initView(view);
        mDouBanListPresenter.loadData(0);
        return view;
    }


    private void initView(View view) {
        mXRecyclerView = (XRecyclerView) view.findViewById(R.id.list_one);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(mLayoutManager);
        mXRecyclerView.setPullRefreshEnabled(false);
        mXRecyclerView.setLoadingMoreEnabled(false);
        mXRecyclerView.setNestedScrollingEnabled(false);
        mXRecyclerView.setHasFixedSize(false);
        mXRecyclerView.addOnScrollListener(mOnScrollListener);
        mDouBanAdapter = new DouBanAdapter(getActivity());
        mDouBanAdapter.setOnItemClickListener(this);
        mXRecyclerView.setAdapter(mDouBanAdapter);

        if (mHeadView == null) {
            mHeadView = View.inflate(getContext(), R.layout.header_item_one, null);
            View llMovieTop = mHeadView.findViewById(R.id.ll_movie_top);
            ImageView imageView = (ImageView) mHeadView.findViewById(R.id.iv_img);
            ImgLoadUtil.displayRandom(3, ConstantsImageUrl.ONE_URL_01, imageView);
            llMovieTop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "点击电影250", Toast.LENGTH_SHORT).show();
                }
            });
        }
        mXRecyclerView.addHeaderView(mHeadView);


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDouBanListPresenter = new DouBanPresenterImpl(this);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void addData(List<SubjectBean> list) {
        if (mSubjectBeen == null) {
            mSubjectBeen = new ArrayList<>();
        }
        mSubjectBeen.addAll(list);
        if (isFirstTime) {
            mDouBanAdapter.setData(mSubjectBeen);
            isFirstTime = false;
        } else {
            if (list.size() == 0) {
                mDouBanAdapter.isShowFooter(false);
            }
        }
        mDouBanAdapter.notifyDataSetChanged();

    }

    @Override
    public void showLoadFail() {
        if (isFirstTime) {
            mDouBanAdapter.isShowFooter(false);
            mDouBanAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mXRecyclerView.getRootView() : getActivity().findViewById(R.id.frameLayout);
        Snackbar.make(view, "加载失败", Snackbar.LENGTH_SHORT).show();
    }

    /**
     * Item 点击事件
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {
        SubjectBean subjectBean = mDouBanAdapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable(DouBanDetailActivity.SUB,subjectBean);
        Intent intent = new Intent(getActivity(), DouBanDetailActivity.class);
        intent.putExtras(bundle);
        //这里记得加上这一句，不然传不过去
        intent.setExtrasClassLoader(getActivity().getClassLoader());
//        intent.putExtra("subjectBean", subjectBean);
//
//        View intoView = view.findViewById(R.id.ll_one_item);
//        ActivityOptionsCompat options =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
//                        intoView, getString(R.string.transition_news_img));
//        ActivityCompat.startActivity(getActivity(),intent,options.toBundle());
        //Intent intent = new Intent(getActivity(),DouBanDetailActivity.class);
        startActivity(intent);
    }
}
