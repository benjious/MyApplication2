package com.example.benjious.myapplication.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.bean.NewBean.DataBean;
import com.example.benjious.myapplication.presenter.NewDetailPresenterImpl;
import com.example.benjious.myapplication.util.ImageLoaderUtils;
import com.example.benjious.myapplication.view.FirstDetailView;
import com.example.benjious.myapplication.view.statusbar.StatusBarUtil;

import org.sufficientlysecure.htmltextview.HtmlTextView;

/**
 * "count": 20,
 "start": 0,
 "total": 250,
 "subjects": [],
 "title": "豆瓣电影Top250"
 *
 *
 * Created by Benjious on 2017/4/16.
 */

public class NewsDetailActivity extends BaseActivity implements FirstDetailView{
    private ImageView ivImage;
    // private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toolbar;
    private ProgressBar progress;
    private HtmlTextView htNewsContent;
    private NewDetailPresenterImpl mfirstPresenter;
    private DataBean mData;//详情数据
    // private Toolbar toolbar;
    // 定义一个变量，来标识是否退出


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.fragment_news_detail);
    }


    @Override
    protected void findViewById() {
        ivImage = (ImageView) findViewById(R.id.detail_img);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        StatusBarUtil.setTranslucentForImageView(this, 0, toolbar);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        progress = (ProgressBar) findViewById(R.id.progress);
        htNewsContent = (HtmlTextView) findViewById(R.id.htNewsContent);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
       setSupportActionBar(toolbar);
        // 给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //通过 NavigationDrawer 打开关闭 抽屉---返回
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();//返回上一级
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {
        mData = (DataBean) getIntent().getSerializableExtra("news");
        collapsing_toolbar.setTitle(mData.getTitle());
        ImageLoaderUtils.display(getApplicationContext(), (ImageView) findViewById(R.id.detail_img),mData.getImgsrc());
        //下面初始化 Presenter 时，调用方法,实际上就通知model去获取数据
        mfirstPresenter=new NewDetailPresenterImpl(NewsDetailActivity.this,this);
        mfirstPresenter.loadContent(mData.getDocid());
    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void showDetilContent(String s) {
        htNewsContent.setHtmlFromString(s,new HtmlTextView.LocalImageGetter());
    }

    @Override
    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideprogress() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void showFailure(Exception e, String s) {

    }
}
