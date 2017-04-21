package com.example.benjious.myapplication.presenter;


import com.example.benjious.myapplication.bean.NewBean.DataBean;
import com.example.benjious.myapplication.api.Urls;
import com.example.benjious.myapplication.interfaze.OnloadFirstDataListener;
import com.example.benjious.myapplication.model.FirstModeImpl;
import com.example.benjious.myapplication.model.FirstModel;
import com.example.benjious.myapplication.view.FirstView;

import java.util.List;

/**
 * 这个类,实现Model的一个接口,当加载完数据方便通过presenter调用view的方法更新view,
 * Created by Benjious on 2016/12/31.
 */

public class NewsListPresenterImpl implements NewListPresenter,OnloadFirstDataListener {
    public FirstView mFirstView;
    public FirstModel mFirstModel;
    public static final String TAG="NewsListPresenterImpl  ";
    public NewsListPresenterImpl(FirstView view) {
        this.mFirstView=view;
        this.mFirstModel=new FirstModeImpl();

    }

    @Override
    public void onSuccess(List<DataBean> list) {
        mFirstView.hideProgress();
        mFirstView.addData(list);
    }

    @Override
    public void onFailure(String str, Exception e) {
        mFirstView.hideProgress();
        mFirstView.showLoadFail();
    }


    //presenter接口的方法
    //不走请求图中的第一步
    @Override
    public void loadData(Object type, int page) {
        String url = getUrl((Integer) type, page);
        System.out.println(TAG+url);
        if (page==0) {
            mFirstView.showProgress();
        }
        mFirstModel.loadData(url,(Integer) type,this);
    }

    private String getUrl(int type, int page) {
        StringBuilder stringBuilder = new StringBuilder();

                   stringBuilder.append(Urls.TOP_URL).append(Urls.TOP_ID);

        stringBuilder.append("/").append(page).append(Urls.END_URL);
        return stringBuilder.toString();
    }

}
