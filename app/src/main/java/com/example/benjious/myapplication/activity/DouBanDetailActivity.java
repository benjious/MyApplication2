package com.example.benjious.myapplication.activity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;


/**
 * Created by Benjious on 2017/4/19.
 */

public class DouBanDetailActivity extends AppCompatActivity {
    private SubjectBean subjectsBean;
    public static final String TAG = "DouBanDetailActivity ";
    public static final String SUB ="subjects";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_movie_detail);
        if (getIntent() != null) {
             subjectsBean =  getIntent().getParcelableExtra(SUB);
        }
    }
}
