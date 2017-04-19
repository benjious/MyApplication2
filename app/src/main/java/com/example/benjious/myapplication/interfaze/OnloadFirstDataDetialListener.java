package com.example.benjious.myapplication.interfaze;

import com.example.benjious.myapplication.bean.NewBean.DataDetilBean;

/**
 * Created by Benjious on 2017/4/17.
 */
public interface OnloadFirstDataDetialListener {
    void onSuccess(DataDetilBean detailDate);

    void onFailure(String str, Exception e);

}
