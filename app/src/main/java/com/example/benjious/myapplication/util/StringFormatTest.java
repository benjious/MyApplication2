package com.example.benjious.myapplication.util;

import android.graphics.Color;

import com.example.benjious.myapplication.bean.DouBanBean.PersonBean;

import java.util.List;
import java.util.Random;

/**
 * Created by Benjious on 2017/4/20.
 */

public class StringFormatTest {
    public static String revertPersonName(List<PersonBean> date) {
        StringBuilder stringBuffer = new StringBuilder();
        if (date == null) {
            return "匿名";
        }
        for (PersonBean person : date) {
            stringBuffer.append(person.getName()).append("/");
        }
        return stringBuffer.toString();

    }

    public static String revertType(List<String> types) {
        StringBuilder sb = new StringBuilder();
       if (types.isEmpty()){
           return "匿名";
       }else {
           for (String type : types) {
               sb.append(type).append("/");
           }
           return sb.toString();
       }
    }
    /**
     * 随机颜色
     */
    public static int randomColor() {
        Random random = new Random();
        int red = random.nextInt(150) + 50;//50-199
        int green = random.nextInt(150) + 50;//50-199
        int blue = random.nextInt(150) + 50;//50-199
        return Color.rgb(red, green, blue);
    }
}
