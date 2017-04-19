package com.example.benjious.myapplication.bean.DouBanBean;

import java.io.Serializable;
import java.util.List;

import static android.R.attr.tag;

/**
 * Created by Benjious on 2017/4/17.
 */

public class HotMovieBean implements Serializable{
    /**
     * count : 20
     * start : 0
     * total : 250
     * subjects : []
     * title : 豆瓣电影Top250
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectBean> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "HotMovieBean{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}
