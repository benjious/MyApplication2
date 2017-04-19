package com.example.benjious.myapplication.view;


import com.example.benjious.myapplication.bean.NewBean.DataBean;

import java.util.List;

/**
 * Created by Benjious on 2016/12/31.
 */

public interface FirstView {
    void showProgress();
    void hideProgress();
    void addData(List<DataBean> list);
    void showLoadFail();

}
