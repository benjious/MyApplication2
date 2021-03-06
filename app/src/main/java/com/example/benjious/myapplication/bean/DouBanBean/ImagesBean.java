package com.example.benjious.myapplication.bean.DouBanBean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Benjious on 2017/4/17.
 */
public class ImagesBean implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.small);
        dest.writeString(this.large);
        dest.writeString(this.medium);
    }

    public ImagesBean() {
    }

    protected ImagesBean(Parcel in) {
        this.small = in.readString();
        this.large = in.readString();
        this.medium = in.readString();
    }

    public static final Parcelable.Creator<ImagesBean> CREATOR = new Parcelable.Creator<ImagesBean>() {
        @Override
        public ImagesBean createFromParcel(Parcel source) {
            return new ImagesBean(source);
        }

        @Override
        public ImagesBean[] newArray(int size) {
            return new ImagesBean[size];
        }
    };
}
