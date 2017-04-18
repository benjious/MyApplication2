package com.example.benjious.myapplication.view;

import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;

import java.util.List;

/**
 * Created by Benjious on 2017/4/18.
 */

public interface DouBanView {
    void showProgress();
    void hideProgress();
    void addData(List<SubjectBean> list);
    void showLoadFail();

}
