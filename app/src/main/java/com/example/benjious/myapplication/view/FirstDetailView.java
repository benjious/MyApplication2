package com.example.benjious.myapplication.view;

/**
 * Created by Benjious on 2017/4/16.
 */

public interface FirstDetailView {
    void showDetilContent(String  s);
    void showProgress();
    void hideprogress();
    void showFailure(Exception e,String s);

}
