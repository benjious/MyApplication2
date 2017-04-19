package com.example.benjious.myapplication.bean.DouBanBean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Benjious on 2017/4/17.
 */

public class SubjectBean implements Parcelable {
    /**
     * rating : {}
     * genres : []
     * title : 肖申克的救赎
     * casts : []
     * collect_count : 1052434
     * original_title : The Shawshank Redemption
     * subtype : movie
     * directors : []
     * year : 1994
     * images : {}
     * alt : https://movie.douban.com/subject/1292052/
     * id : 1292052
     */

    private RatingBean rating;
    private String title;
    private int collect_count;
    private String original_title;
    private String subtype;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;

    //类型
    private List<String> genres;

    //主演
    private List<PersonBean> casts;

    //导演
    private List<PersonBean> directors;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<PersonBean> getCasts() {
        return casts;
    }

    public void setCasts(List<PersonBean> casts) {
        this.casts = casts;
    }

    public List<PersonBean> getDirectors() {
        return directors;
    }

    public void setDirectors(List<PersonBean> directors) {
        this.directors = directors;
    }

    @Override
    public String toString() {
        return "SubjectBean{" +
                "rating=" + rating +
                ", title='" + title + '\'' +
                ", collect_count=" + collect_count +
                ", original_title='" + original_title + '\'' +
                ", subtype='" + subtype + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", genres=" + genres +
                ", casts=" + casts +
                ", directors=" + directors +
                '}';
    }

    public SubjectBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.rating, flags);
        dest.writeString(this.title);
        dest.writeInt(this.collect_count);
        dest.writeString(this.original_title);
        dest.writeString(this.subtype);
        dest.writeString(this.year);
        dest.writeParcelable(this.images, flags);
        dest.writeString(this.alt);
        dest.writeString(this.id);
        dest.writeStringList(this.genres);
        dest.writeList(this.casts);
        dest.writeList(this.directors);
    }

    protected SubjectBean(Parcel in) {
        this.rating = in.readParcelable(RatingBean.class.getClassLoader());
        this.title = in.readString();
        this.collect_count = in.readInt();
        this.original_title = in.readString();
        this.subtype = in.readString();
        this.year = in.readString();
        this.images = in.readParcelable(ImagesBean.class.getClassLoader());
        this.alt = in.readString();
        this.id = in.readString();
        this.genres = in.createStringArrayList();
        this.casts = new ArrayList<PersonBean>();
        in.readList(this.casts, PersonBean.class.getClassLoader());
        this.directors = new ArrayList<PersonBean>();
        in.readList(this.directors, PersonBean.class.getClassLoader());
    }

    public static final Creator<SubjectBean> CREATOR = new Creator<SubjectBean>() {
        @Override
        public SubjectBean createFromParcel(Parcel source) {
            return new SubjectBean(source);
        }

        @Override
        public SubjectBean[] newArray(int size) {
            return new SubjectBean[size];
        }
    };
}
