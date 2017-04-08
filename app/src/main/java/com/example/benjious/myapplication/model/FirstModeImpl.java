package com.example.benjious.myapplication.model;


import com.example.benjious.myapplication.bean.DataBean;
import com.example.benjious.myapplication.api.Urls;
import com.example.benjious.myapplication.interfaze.OnloadFirstDataListener;
import com.example.benjious.myapplication.util.NewsJsonUtils;
import com.example.benjious.myapplication.util.OkHttpUtils;

import java.util.List;

/**
 * 加载首页数据条目相关类
 * Created by Benjious on 2016/12/31.
 */
public class FirstModeImpl implements FirstModel {

    @Override
    public void loadData(String url, final int type, final OnloadFirstDataListener dataListener) {
        //这里定义CallBack类
        OkHttpUtils.ResultCallback<String> loadNewsCallback=new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<DataBean> dataBeens= NewsJsonUtils.readJsonDataBeans(response,getID(type));
                dataListener.onSuccess(dataBeens);
            }

            @Override
            public void onFailure(Exception e) {
                dataListener.onFailure("load news list failed ",e);
            }
        };
        //这个才是真正的操作
        OkHttpUtils.get(url,loadNewsCallback);
    }

    /**
     * 获取ID
     * @param type
     * @return
     */
    private String getID(int type) {
//        switch (type) {
//            case ONE:
//                id= Urls.TOP_ID;
//                break;
//            case TWO:
//                id=Urls.NBA_ID;
//                break;
//            case FirstFragment.THREE:
//                id = Urls.CAR_ID;
//                break;
//            default:
//                id = Urls.TOP_ID;
//                break;
//        }
        return   Urls.TOP_ID;
    }

    @Override
    public void loadDetailData(String url, OnloadFirstDataDetialListener detialListener) {

    }

    public interface OnloadFirstDataDetialListener{
        void onSuccess(List<DataBean> list);
        void onFailure(String str, Exception e);

    }
}