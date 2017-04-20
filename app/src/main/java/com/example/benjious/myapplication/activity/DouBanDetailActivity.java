package com.example.benjious.myapplication.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.benjious.myapplication.R;
import com.example.benjious.myapplication.adapter.DouBanAdapter;
import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;
import com.example.benjious.myapplication.util.CommonUtils;
import com.example.benjious.myapplication.util.ImageLoaderUtils;
import com.example.benjious.myapplication.util.StringFormatUtil;
import com.example.benjious.myapplication.util.test.StatusBarUtils;
import com.example.benjious.myapplication.view.custom.MyNestedScrollView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.example.benjious.myapplication.view.statusbar.StatusBarUtil.getStatusBarHeight;


/**
 * Created by Benjious on 2017/4/19.
 */

public class DouBanDetailActivity extends AppCompatActivity {
    @Bind(R.id.img_item_bg)
    ImageView mImgItemBg;
    @Bind(R.id.iv_one_photo)
    ImageView mIvOnePhoto;
    @Bind(R.id.tv_one_rating_rate)
    TextView mTvOneRatingRate;
    @Bind(R.id.tv_one_rating_number)
    TextView mTvOneRatingNumber;
    @Bind(R.id.tv_one_directors)
    TextView mTvOneDirectors;
    @Bind(R.id.tv_one_casts)
    TextView mTvOneCasts;
    @Bind(R.id.tv_one_genres)
    TextView mTvOneGenres;
    @Bind(R.id.tv_one_day)
    TextView mTvOneDay;
    @Bind(R.id.tv_one_origin_title)
    TextView getTvOneTitle;
    @Bind(R.id.ll_one_item)
    LinearLayout mLlOneItem;
    @Bind(R.id.ll_Header_view)
    LinearLayout mLlHeaderView;
    @Bind(R.id.tv_one_title)
    TextView mTvOneTitle;
    @Bind(R.id.xrv_cast)
    XRecyclerView mXrvCast;
    @Bind(R.id.nsv_scrollview)
    MyNestedScrollView mNsvScrollview;
    @Bind(R.id.iv_title_head_bg)
    ImageView mIvTitleHeadBg;
    @Bind(R.id.title_tool_bar)
    Toolbar mTitleToolBar;
    @Bind(R.id.rl_title_head)
    RelativeLayout mRlTitleHead;

    public static final String TAG = "DBDA_xyz";
    public static final String SUB = "subjects";

    private int slidingDistance;
    private SubjectBean subjectsBean;


    // 这个是高斯图背景的高度
    private int imageBgHeight;
    // 更多信息url
    private String mMoreUrl;
    // 影片name
    private String mMovieName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_text);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            subjectsBean = getIntent().getParcelableExtra(SUB);
        }

        initSlideShapeTheme();

        // 数据配置
        setTitleBar();
        setHeaderData(subjectsBean);

        loadMovieDetail();
    }

    private void loadMovieDetail() {

    }

    private void setTitleBar() {
        setSupportActionBar(mTitleToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.icon_back);
        }
        // 手动设置才有效果
        mTitleToolBar.setTitleTextAppearance(this, R.style.ToolBar_Title);
        mTitleToolBar.setSubtitleTextAppearance(this, R.style.Toolbar_SubTitle);

        mTitleToolBar.setTitle(subjectsBean.getTitle());
        mTitleToolBar.setSubtitle(String.format("主演：%s", StringFormatUtil.formatName(subjectsBean.getCasts())));

        mTitleToolBar.inflateMenu(R.menu.movie_detail);
        mTitleToolBar.setOverflowIcon(ContextCompat.getDrawable(this, R.drawable.actionbar_more));
        mTitleToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mTitleToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionbar_more:// 更多信息
//                        WebViewActivity.loadUrl(MovieDetailActivity.this,mMoreUrl,mMovieName);

                        break;
                }
                return false;
            }
        });
    }

    /**
     * 初始化滑动渐变
     */
    private void initSlideShapeTheme() {

        setImgHeaderBg();

        // toolbar 的高
        int toolbarHeight = mTitleToolBar.getLayoutParams().height;
        Log.i(TAG, "toolbar_height:" + toolbarHeight);
        final int headerBgHeight = toolbarHeight + getStatusBarHeight(this);
        Log.i(TAG, "headerBgHeight:" + headerBgHeight);

        // 使背景图向上移动到图片的最低端，保留（titlebar+statusbar）的高度
        ViewGroup.LayoutParams params = mIvTitleHeadBg.getLayoutParams();
        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams) mIvTitleHeadBg.getLayoutParams();
        int marginTop = params.height - headerBgHeight;
        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);

        mIvTitleHeadBg.setImageAlpha(0);
        StatusBarUtils.setTranslucentImageHeader(this, 0, mTitleToolBar);

        // 上移背景图片，使空白状态栏消失(这样下方就空了状态栏的高度)
        if (mImgItemBg != null) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) mImgItemBg.getLayoutParams();
            layoutParams.setMargins(0, -getStatusBarHeight(this), 0, 0);
        }

        ViewGroup.LayoutParams imgItemBgparams = mImgItemBg.getLayoutParams();
        // 获得高斯图背景的高度
        imageBgHeight = imgItemBgparams.height;

        // 变色
        initScrollViewListener();

        initNewSlidingParams();
    }

    private void initNewSlidingParams() {
        int titleBarAndStatusHeight = (int) (CommonUtils.getDimens(R.dimen.nav_bar_height) + getStatusBarHeight(this));
        slidingDistance = imageBgHeight - titleBarAndStatusHeight - (int) (CommonUtils.getDimens(R.dimen.nav_bar_height_more));
    }

    private void initScrollViewListener() {
        // 为了兼容23以下
        mNsvScrollview.setOnScrollChangeListener(new MyNestedScrollView.ScrollInterface() {
            @Override
            public void onScrollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollChangeHeader(scrollY);
            }
        });
    }

    /**
     * 根据页面滑动距离改变Header方法
     */
    private void scrollChangeHeader(int scrolledY) {

        Log.d(TAG, "xyz  scrollChangeHeader: " + scrolledY);
        Log.d(TAG, "xyz  scrollChangeHeader: " + slidingDistance);

        if (scrolledY < 0) {
            scrolledY = 0;
        }
        float alpha = Math.abs(scrolledY) * 1.0f / (slidingDistance);

        Log.d(TAG, "xyz  scrollChangeHeader: " + alpha);
        Drawable drawable = mIvTitleHeadBg.getDrawable();


        if (scrolledY <= slidingDistance) {
            // title部分的渐变
            drawable.mutate().setAlpha((int) (alpha * 255));
            mIvTitleHeadBg.setImageDrawable(drawable);
        } else {
            drawable.mutate().setAlpha(255);
            mIvTitleHeadBg.setImageDrawable(drawable);
        }
    }

    /**
     * 加载titlebar背景
     */
    private void setImgHeaderBg() {
        if (subjectsBean != null) {
//           final String url=subjectsBean.getImages().getMedium();
            final String url= "http://img3.doubanio.com/view/movie_poster_cover/spst/public/p2443742358.jpg";
            // 高斯模糊背景 原来 参数：12,5  23,4
            Glide.with(this).load(url)
                    .error(R.drawable.stackblur_default)
                    .bitmapTransform(new BlurTransformation(this, 23, 4)).listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                    mTitleToolBar.setBackgroundColor(Color.TRANSPARENT);
//                    mIvTitleHeadBg.setImageAlpha(0);
//                    mIvTitleHeadBg.setVisibility(View.VISIBLE);
//                    Log.d(TAG, "xyz  onResourceReady: "+url);
                    return false;
                }
            }).into(mIvTitleHeadBg);
        }
    }

    public void setHeaderData(SubjectBean headerData) {
//        binding.include.setSubjectsBean(headerData);
//        // 立即改变UI，防止闪屏
//        binding.include.executePendingBindings();

        //里面小的，之前加载过的
// ImageLoaderUtils.display(this, mImgItemBg, subjectsBean.getImages().getSmall());
        //
        ImageLoaderUtils.display(this, mIvOnePhoto, headerData.getImages().getLarge());

        mTvOneRatingRate.setText(headerData.getRating().getStars());
        mTvOneRatingNumber.setText(String.valueOf(headerData.getCollect_count()));
        mTvOneDirectors.setText(StringFormatUtil.formatName(headerData.getDirectors()));
        mTvOneCasts.setText(StringFormatUtil.formatName(headerData.getCasts()));
        mTvOneGenres.setText(StringFormatUtil.formatGenres(headerData.getGenres()));
        mTvOneDay.setText(headerData.getYear());
        getTvOneTitle.setText(headerData.getOriginal_title());
    }
}
