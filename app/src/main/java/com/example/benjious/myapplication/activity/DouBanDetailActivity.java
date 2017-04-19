package com.example.benjious.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;

/**
 * Created by Benjious on 2017/4/19.
 */

public class DouBanDetailActivity extends AppCompatActivity {
    private SubjectBean subjectsBean;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_one_movie_detail);
        if (getIntent() != null) {
            subjectsBean = (SubjectBean) getIntent().getSerializableExtra("subjectBean");
        }
    }
}
