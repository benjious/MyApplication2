package com.example.benjious.myapplication.app;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;



/**
 * Created by jingbin on 2016/11/22.
 */

public class MyAppApplication extends Application {

    private static MyAppApplication sMyAppApplication;

    public static MyAppApplication getInstance() {
        return sMyAppApplication;
    }

    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        super.onCreate();
        sMyAppApplication = this;
       // HttpUtils.getInstance().init(this, DebugUtil.DEBUG);

        initTextSize();
    }

    /**
     * 使其系统更改字体大小无效
     */
    private void initTextSize() {
        Resources res = getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

}
