package com.example.benjious.myapplication.model;

import com.example.benjious.myapplication.interfaze.OnLoadDouBanDataListener;

/**
 * Created by Benjious on 2017/4/17.
 */

public interface DouBanModel {
    void  loadData(String url, OnLoadDouBanDataListener dataListener);
}
