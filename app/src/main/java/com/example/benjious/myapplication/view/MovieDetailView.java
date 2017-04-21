package com.example.benjious.myapplication.view;

import com.example.benjious.myapplication.bean.DouBanBean.MovieDetail;

/**
 * Created by Benjious on 2017/4/21.
 */

public interface MovieDetailView {
    void showDetilContent(MovieDetail movieDetail);
    void showProgress();
    void hideprogress();
    void showFailure(Exception e,String s);

}
