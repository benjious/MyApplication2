package com.example.benjious.myapplication.bean.DouBanBean;

import java.util.List;

/**
 * Created by Benjious on 2017/4/21.
 */

public class MovieDetail {
    /**
     * rating : {}
     * reviews_count : 883
     * wish_count : 27005
     * douban_site :
     * year : 2017
     * images : {}
     * alt : https://movie.douban.com/subject/26260853/
     * id : 26260853
     * mobile_url : https://movie.douban.com/subject/26260853/mobile
     * title : 速度与激情8
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/26260853
     * seasons_count : null
     * schedule_url : https://movie.douban.com/subject/26260853/cinema/
     * episodes_count : null
     * countries : []
     * genres : ["动作","犯罪"]
     * collect_count : 85370
     * casts : []
     * current_season : null
     * original_title : The Fate of the Furious
     * summary : 多米尼克（范·迪塞尔 Vin Diesel 饰）与莱蒂（米歇尔·罗德里格兹 Michelle Rodriguez 饰）共度蜜月，布莱恩与米娅退出了赛车界，这支曾环游世界的顶级飞车家族队伍的生活正渐趋平淡。然而，一位神秘女子Cipher（查理兹·塞隆 Charlize T heron 饰）的出现，令整个队伍卷入信任与背叛的危机，面临前所未有的考验。
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1009396/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/4451.jpg","large":"https://img3.doubanio.com/img/celebrity/large/4451.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/4451.jpg"},"name":"F·加里·格雷","id":"1009396"}]
     * comments_count : 40164
     * ratings_count : 80360
     * aka : ["狂野时速8(港)","玩命关头8(台)","速激8","速8","速度8","The Fast and the Furious 8","Fast & Furious 8","Fast Eight","Furious Eight","Furious 8","Fast 8"]
     */

    private RatingBean rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesBean images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private int do_count;
    private String share_url;
    private int seasons_count;
    private String schedule_url;
    private int episodes_count;
    private int collect_count;
    private int current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private int ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<PersonBean> casts;
    private List<PersonBean> directors;
    private List<String> aka;

    public RatingBean getRating() {
        return rating;
    }

    public void setRating(RatingBean rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
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

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDo_count() {
        return do_count;
    }

    public void setDo_count(int do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(int seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public Object getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(int episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public Object getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(int current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public int getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(int ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
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

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }



    @Override
    public String toString() {
        return "MovieDetail{" +
                "rating=" + rating +
                ", reviews_count=" + reviews_count +
                ", wish_count=" + wish_count +
                ", douban_site='" + douban_site + '\'' +
                ", year='" + year + '\'' +
                ", images=" + images +
                ", alt='" + alt + '\'' +
                ", id='" + id + '\'' +
                ", mobile_url='" + mobile_url + '\'' +
                ", title='" + title + '\'' +
                ", do_count=" + do_count +
                ", share_url='" + share_url + '\'' +
                ", seasons_count=" + seasons_count +
                ", schedule_url='" + schedule_url + '\'' +
                ", episodes_count=" + episodes_count +
                ", collect_count=" + collect_count +
                ", current_season=" + current_season +
                ", original_title='" + original_title + '\'' +
                ", summary='" + summary + '\'' +
                ", subtype='" + subtype + '\'' +
                ", comments_count=" + comments_count +
                ", ratings_count=" + ratings_count +
                ", countries=" + countries +
                ", genres=" + genres +
                ", casts=" + casts +
                ", directors=" + directors +
                ", aka=" + aka +
                '}';
    }
}
