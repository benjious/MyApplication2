package com.example.benjious.myapplication.interfaze;

import com.example.benjious.myapplication.bean.DataBean;
import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;

import java.util.List;

/**
 * Created by Benjious on 2017/4/17.
 */

public interface OnLoadDouBanDataListener {
    void onSuccess(List<SubjectBean> list);

    void onFailure(String str, Exception e);
}
