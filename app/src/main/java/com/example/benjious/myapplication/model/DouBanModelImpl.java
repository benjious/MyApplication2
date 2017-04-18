package com.example.benjious.myapplication.model;

import android.util.Log;

import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;
import com.example.benjious.myapplication.interfaze.OnLoadDouBanDataListener;
import com.example.benjious.myapplication.util.MovieJsonUtil;
import com.example.benjious.myapplication.util.OkHttpUtils;

import java.util.List;

/**
 * Created by Benjious on 2017/4/17.
 */

public class DouBanModelImpl implements DouBanModel {
public static final String TAG="DouBanModelImpl xyz =";

    @Override
    public void loadData(final String url, final OnLoadDouBanDataListener dataListener) {
        OkHttpUtils.ResultCallback<String> resultCallback=new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {

                List<SubjectBean> beanList= MovieJsonUtil.readJsonDataBeans(response,"subjects");
                Log.d(TAG, "xyz  onSuccess: 返回了数据"+beanList);
                dataListener.onSuccess(beanList);
            }

            @Override
            public void onFailure(Exception e) {
                dataListener.onFailure("加载失败",e);
            }
        };
        Log.d(TAG, "xyz  loadData: 有没有执行到这里");
        OkHttpUtils.get(url,resultCallback);
    }


}
