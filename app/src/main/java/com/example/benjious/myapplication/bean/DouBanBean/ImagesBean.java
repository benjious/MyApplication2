package com.example.benjious.myapplication.bean.DouBanBean;

/**
 * Created by Benjious on 2017/4/17.
 */
public class ImagesBean {

    /**
     * small : http://img7.doubanio.com/view/movie_poster_cover/ipst/public/p480747492.webp
     * large : http://img7.doubanio.com/view/movie_poster_cover/lpst/public/p480747492.webp
     * medium : http://img7.doubanio.com/view/movie_poster_cover/spst/public/p480747492.webp
     */

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @Override
    public String toString() {
        return "ImagesBean{" +
                "small='" + small + '\'' +
                ", large='" + large + '\'' +
                ", medium='" + medium + '\'' +
                '}';
    }
}
