package com.example.benjious.myapplication.presenter;

import android.content.Context;

import com.example.benjious.myapplication.bean.NewBean.DataDetilBean;
import com.example.benjious.myapplication.interfaze.OnloadFirstDataDetialListener;
import com.example.benjious.myapplication.model.FirstModeImpl;
import com.example.benjious.myapplication.model.FirstModel;
import com.example.benjious.myapplication.view.FirstDetailView;

/**
 * Created by Benjious on 2017/4/16.
 */

public class FirstDetailPresenterImpl implements FirstDetailPresenter,OnloadFirstDataDetialListener {
    private Context mcontext;
    private FirstDetailView mfirstDetilView;
    private FirstModel mFirstModel;

    public FirstDetailPresenterImpl(Context mcontext, FirstDetailView mfirstDetilView) {
        this.mcontext = mcontext;
        this.mfirstDetilView = mfirstDetilView;
        mFirstModel = new FirstModeImpl();
    }

    @Override
    public void loadContent(String url) {
        mfirstDetilView.showProgress();
        mFirstModel.loadDetailData(url,this);
    }

    @Override
    public void onSuccess(DataDetilBean detailDate) {
        if (detailDate!=null) {
            mfirstDetilView.showDetilContent(detailDate.getBody());
        }
        mfirstDetilView.hideprogress();
    }

    @Override
    public void onFailure(String str, Exception e) {
        mfirstDetilView.hideprogress();
        mfirstDetilView.showFailure(e,str);
    }
}
