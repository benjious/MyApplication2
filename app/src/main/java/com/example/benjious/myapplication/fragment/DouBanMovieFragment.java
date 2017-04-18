package com.example.benjious.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.adapter.DouBanAdapter;
import com.example.benjious.myapplication.api.ConstantsImageUrl;
import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;
import com.example.benjious.myapplication.presenter.DouBanListPresenter;
import com.example.benjious.myapplication.presenter.DouBanPresenterImpl;
import com.example.benjious.myapplication.util.ImgLoadUtil;
import com.example.benjious.myapplication.view.DouBanView;
import com.example.benjious.myapplication.view.FirstView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjious on 2017/3/19.
 */

public class DouBanMovieFragment extends Fragment implements DouBanView, DouBanAdapter.OnMovieItemClickListener {
    private XRecyclerView mXRecyclerView;
    private View mHeadView;
    private DouBanAdapter mDouBanAdapter;
    private ArrayList<SubjectBean> mSubjectBeen;
    private DouBanListPresenter mDouBanListPresenter;
    public static DouBanMovieFragment newInstance() {
        Bundle args = new Bundle();
        DouBanMovieFragment fragment = new DouBanMovieFragment();
        fragment.setArguments(args);
        return fragment;
    }

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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXRecyclerView.setLayoutManager(layoutManager);
        mXRecyclerView.setPullRefreshEnabled(false);
        mXRecyclerView.setLoadingMoreEnabled(false);
        mXRecyclerView.setNestedScrollingEnabled(false);
        mXRecyclerView.setHasFixedSize(false);
        mDouBanAdapter = new DouBanAdapter(getActivity());
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

        if (list.size() == 0 | list == null) {
            mDouBanAdapter.isShowFooter(false);
        }
        mDouBanAdapter.setData(mSubjectBeen);
        mDouBanAdapter.notifyDataSetChanged();

    }

    @Override
    public void showLoadFail() {
        View view = getActivity() == null ? mXRecyclerView.getRootView() : getActivity().findViewById(R.id.frameLayout);
        Snackbar.make(view, "加载失败", Snackbar.LENGTH_LONG).show();
    }

    /**
     * Item 点击事件
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position) {

    }
}
