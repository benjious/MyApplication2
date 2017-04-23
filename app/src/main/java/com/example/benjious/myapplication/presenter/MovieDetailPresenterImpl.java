package com.example.benjious.myapplication.presenter;

import android.content.Context;

import com.example.benjious.myapplication.bean.DouBanBean.MovieDetail;
import com.example.benjious.myapplication.interfaze.OnLoadMovieDetailListener;
import com.example.benjious.myapplication.model.MovieDetailModel;
import com.example.benjious.myapplication.model.MovieDetailModelImpl;
import com.example.benjious.myapplication.view.MovieDetailView;


/**
 * Created by Benjious on 2017/4/21.
 */

public class MovieDetailPresenterImpl implements MovieDetailPresenter ,OnLoadMovieDetailListener{
    private MovieDetailView mMovieDetailView;
    private MovieDetailModel mMovieDetailModel;

    public MovieDetailPresenterImpl(Context context,MovieDetailView movieDetailView) {
        mMovieDetailView = movieDetailView;
        mMovieDetailModel = new MovieDetailModelImpl();

    }

    @Override
    public void load(String url) {
        mMovieDetailModel.loadData(url,this);
    }

    @Override
    public void onSuccess(MovieDetail movieDetail) {
        mMovieDetailView.hideprogress();
        mMovieDetailView.showDetilContent(movieDetail);

    }

    @Override
    public void onFailure(String str, Exception e) {
        mMovieDetailView.hideprogress();
        mMovieDetailView.showFailure(e,"加载失败");
    }
}
