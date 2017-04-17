package com.example.benjious.myapplication.model;


import android.util.Log;

import com.example.benjious.myapplication.bean.DataBean;
import com.example.benjious.myapplication.api.Urls;
import com.example.benjious.myapplication.bean.DataDetilBean;
import com.example.benjious.myapplication.interfaze.OnloadFirstDataListener;
import com.example.benjious.myapplication.util.NewsJsonUtils;
import com.example.benjious.myapplication.util.OkHttpUtils;

import java.util.List;

import static android.R.id.list;
import static android.R.string.ok;

/**
 * 加载首页数据条目相关类
 * Created by Benjious on 2016/12/31.
 */
public class FirstModeImpl implements FirstModel {
    public static final String TAG = " FirstModeImpl xyz=====";

    @Override
    public void loadData(String url, final int type, final OnloadFirstDataListener dataListener) {
        //这里定义CallBack类
        OkHttpUtils.ResultCallback<String> loadNewsCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<DataBean> dataBeens = NewsJsonUtils.readJsonDataBeans(response, getID(type));
                dataListener.onSuccess(dataBeens);
            }

            @Override
            public void onFailure(Exception e) {
                dataListener.onFailure("load news list failed ", e);
            }
        };
        //这个才是真正的操作
        Log.d(TAG, "xyz  loadData: " + url);
        OkHttpUtils.get(url, loadNewsCallback);
    }

    /**
     * 获取ID
     *
     * @param type
     * @return
     */
    private String getID(int type) {
        return Urls.TOP_ID;
    }

    @Override
    public void loadDetailData(final String dcoid, final OnloadFirstDataDetialListener detialListener) {
        String detailUrl = getDetailUrl(dcoid);
        OkHttpUtils.ResultCallback<String> resultCallback = new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                DataDetilBean newsDetilBean = NewsJsonUtils.readJsonNewsDetailBeans(response, dcoid);
                detialListener.onSuccess(newsDetilBean);

            }

            @Override
            public void onFailure(Exception e) {
                detialListener.onFailure("load news detail info failure.", e);
            }
        };
        OkHttpUtils.get(detailUrl, resultCallback);
    }

    public interface OnloadFirstDataDetialListener {
        void onSuccess(DataDetilBean detailDate);

        void onFailure(String str, Exception e);

    }

    private String getDetailUrl(String docId) {
        StringBuilder stringBuilder = new StringBuilder(Urls.NEW_DETAIL);
        stringBuilder.append(docId).append(Urls.END_DETAIL_URL);
        return stringBuilder.toString();
    }

}
