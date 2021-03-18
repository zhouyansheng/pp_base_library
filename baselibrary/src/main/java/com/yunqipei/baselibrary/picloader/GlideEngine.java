package com.yunqipei.baselibrary.picloader;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.NonNull;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;


public class GlideEngine {

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public void loadImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }


    /**
     * 加载gif
     *
     * @param context   上下文
     * @param url       图片路径
     * @param imageView 承载图片ImageView
     */
    public void loadAsGifImage(@NonNull Context context, @NonNull String url,
                               @NonNull ImageView imageView) {
        Glide.with(context)
                .asGif()
                .load(url)
                .into(imageView);
    }

    /**
     * 加载图片列表图片
     *
     * @param context   上下文
     * @param url       图片路径
     * @param imageView 承载图片ImageView
     */
    public void loadGridImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView) {
        Glide.with(context)
                .load(url)
                .override(200, 200)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加载圆型图片
     *
     *
     * @param context
     * @param url
     * @param imageView
     * @param defaultImage  默认图
     */
    public void loadCircleImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView, @NonNull int defaultImage){

        Glide.with(context).load(url).transform (new CircleCrop()).placeholder(defaultImage).into(imageView);

    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param defaultCorners 圆角角度
     */
    public void loadRoundedCornersImage(@NonNull Context context, @NonNull String url, @NonNull ImageView imageView, @NonNull int defaultCorners){
        Glide.with(context).load(url).apply(new RequestOptions().transform(new CenterCrop(),  new RoundedCorners(defaultCorners))).into(imageView);
    }


    private GlideEngine() {
    }

    private static GlideEngine instance;

    public static GlideEngine createGlideEngine() {
        if (null == instance) {
            synchronized (GlideEngine.class) {
                if (null == instance) {
                    instance = new GlideEngine();
                }
            }
        }
        return instance;
    }
}
