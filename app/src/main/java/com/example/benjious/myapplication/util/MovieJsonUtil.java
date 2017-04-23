package com.example.benjious.myapplication.util;

import android.util.Log;

import com.example.benjious.myapplication.bean.DouBanBean.MovieDetail;
import com.example.benjious.myapplication.bean.DouBanBean.SubjectBean;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjious on 2017/4/18.
 */

public class MovieJsonUtil {
    private final static String TAG = "NewsJsonUtils";
    public static final String JSON_DATA = "...";

    /**
     * 将获取到的json转换为电影列表对象
     *
     * @param res
     * @param value
     * @return
     */
    public static List<SubjectBean> readJsonDataBeans(String res, String value) {
        List<SubjectBean> beans = new ArrayList<SubjectBean>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(value);
            if (jsonElement == null) {
                return null;
            }
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
                    continue;
                }
                if (jo.has("TAGS") && !jo.has("TAG")) {
                    continue;
                }

                if (!jo.has("imgextra")) {
                    SubjectBean news = JsonUtils.deserialize(jo, SubjectBean.class);
                    beans.add(news);
                }
            }
        } catch (Exception e) {
        }

        return beans;
    }

    public static MovieDetail readJsonMovieDetailBeans(String res) {
        MovieDetail movieDetail = null;
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
//            JsonElement jsonElement = jsonObj.get(docId);
//            if (jsonElement == null) {
//                return null;
//            }
//            subjectBean = JsonUtils.deserialize(jsonElement.getAsJsonObject(), SubjectBean.class);
            movieDetail = JsonUtils.deserialize(jsonObj, MovieDetail.class);
        } catch (Exception e) {
            // LogUtils.e(TAG, "readJsonNewsBeans error" , e);
            Log.d(TAG, "xyz  readJsonMovieDetailBeans: "+e);
        }
        return movieDetail;
    }


}
