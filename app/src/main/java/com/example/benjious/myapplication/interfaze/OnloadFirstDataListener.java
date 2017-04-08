package com.example.benjious.myapplication.interfaze;


import com.example.benjious.myapplication.bean.DataBean;

import java.util.List;

/**
 * Created by Benjious on 2017/3/21.
 */
public interface OnloadFirstDataListener {
    void onSuccess(List<DataBean> list);

    void onFailure(String str, Exception e);

}
