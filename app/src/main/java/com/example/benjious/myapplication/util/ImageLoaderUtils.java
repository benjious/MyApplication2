package com.example.benjious.myapplication.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.benjious.myapplication.R;

/**
 * Created by Benjious on 2017/2/1.
 */

public class ImageLoaderUtils {
    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(R.mipmap.ic_image_loading)
                .error(R.mipmap.ic_image_loadfail).crossFade().into(imageView);

    }
}
