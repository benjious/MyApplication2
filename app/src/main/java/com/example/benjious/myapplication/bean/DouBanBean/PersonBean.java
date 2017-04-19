package com.example.benjious.myapplication.bean.DouBanBean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Benjious on 2017/4/17.
 */

public class PersonBean implements Parcelable {
    /**
     * alt : https://movie.douban.com/celebrity/1054521/
     * avatars : {"small":"http://img7.doubanio.com/img/celebrity/small/17525.jpg","large":"http://img7.doubanio.com/img/celebrity/large/17525.jpg","medium":"http://img7.doubanio.com/img/celebrity/medium/17525.jpg"}
     * name : 蒂姆·罗宾斯
     * id : 1054521
     */

    private String alt;
    private AvatarsBean avatars;
    private String name;
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public AvatarsBean getAvatars() {
        return avatars;
    }

    public void setAvatars(AvatarsBean avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "PersonBean{" +
                "alt='" + alt + '\'' +
                ", avatars=" + avatars +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public PersonBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.alt);
        dest.writeParcelable(this.avatars, flags);
        dest.writeString(this.name);
        dest.writeString(this.id);
    }

    protected PersonBean(Parcel in) {
        this.alt = in.readString();
        this.avatars = in.readParcelable(AvatarsBean.class.getClassLoader());
        this.name = in.readString();
        this.id = in.readString();
    }

    public static final Creator<PersonBean> CREATOR = new Creator<PersonBean>() {
        @Override
        public PersonBean createFromParcel(Parcel source) {
            return new PersonBean(source);
        }

        @Override
        public PersonBean[] newArray(int size) {
            return new PersonBean[size];
        }
    };
}
