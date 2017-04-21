package com.example.benjious.myapplication.presenter;

/**
 * Created by Benjious on 2016/12/31.
 */

public interface NewListPresenter<E> {
    void loadData(E type, int page);
}
