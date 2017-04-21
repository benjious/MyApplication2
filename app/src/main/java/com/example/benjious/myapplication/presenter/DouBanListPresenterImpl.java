package com.example.benjious.myapplication.presenter;

import android.util.Log;

import com.example.benjious.myapplication.api.Urls;
import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;
import com.example.benjious.myapplication.interfaze.OnLoadDouBanDataListener;
import com.example.benjious.myapplication.model.DouBanModel;
import com.example.benjious.myapplication.model.DouBanModelImpl;
import com.example.benjious.myapplication.view.DouBanView;
import com.example.benjious.myapplication.view.FirstView;

import java.util.List;

/**
 * Created by Benjious on 2017/4/17.
 */

public class DouBanListPresenterImpl implements DouBanListPresenter, OnLoadDouBanDataListener {
    private DouBanView mDouBanView;
    private DouBanModel mDouBanModel;
    public static final String TAG = "DouBanListPresenterImpl xyz";

    public DouBanListPresenterImpl(DouBanView douBanView) {
        mDouBanView = douBanView;
        mDouBanModel = new DouBanModelImpl();

    }

    @Override
    public void loadData( int page) {
        String url = Urls.DOUBAN_HOT_MOVIE;
        Log.d(TAG, " loadData:" + url);
        if (page == 0) {
            mDouBanView.showProgress();
        }
        mDouBanModel.loadData(url, this);
    }

    @Override
    public void onSuccess(List<SubjectBean> list) {
        mDouBanView.hideProgress();
        mDouBanView.addData(list);
    }

    @Override
    public void onFailure(String str, Exception e) {
        mDouBanView.hideProgress();
        mDouBanView.showLoadFail();
    }
}
