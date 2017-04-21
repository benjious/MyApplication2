package com.example.benjious.myapplication.interfaze;

import com.example.benjious.myapplication.bean.DouBanBean.MovieDetail;

/**
 * Created by Benjious on 2017/4/21.
 */

public interface OnLoadMovieDetailListener {
    void onSuccess(MovieDetail movieDetail);

    void onFailure(String str, Exception e);
}
