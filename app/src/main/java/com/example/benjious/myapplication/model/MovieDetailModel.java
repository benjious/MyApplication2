package com.example.benjious.myapplication.model;

import com.example.benjious.myapplication.interfaze.OnLoadMovieDetailListener;

/**
 * Created by Benjious on 2017/4/21.
 */

public interface MovieDetailModel {
    void  loadData(String url, OnLoadMovieDetailListener dataListener);

}
