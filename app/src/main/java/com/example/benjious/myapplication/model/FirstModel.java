package com.example.benjious.myapplication.model;


import com.example.benjious.myapplication.interfaze.OnloadFirstDataListener;

/**
 * Created by Benjious on 2016/12/31.
 */
public interface FirstModel {
    void  loadData(String url, int type, OnloadFirstDataListener dataListener);
    void  loadDetailData(String url, FirstModeImpl.OnloadFirstDataDetialListener detialListener);

}
