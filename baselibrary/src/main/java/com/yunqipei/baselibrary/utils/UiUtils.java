package com.yunqipei.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class UiUtils {

    public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    public static int dp2px(float value,Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }


    public static int getStatusHeight(Context context) {
        int statusHeight = 0;
        Class<?> localClass;
        try {
            localClass = Class.forName("com.android.internal.R$dimen");
            Object localObject = localClass.newInstance();
            int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject)
                    .toString());
            statusHeight = context.getResources().getDimensionPixelSize(i5);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return statusHeight;
    }

    public static void slideToUp(View view) {
        Animation slide = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        slide.setDuration(400);
        slide.setFillAfter(true);
        slide.setFillEnabled(true);
        view.startAnimation(slide);
        slide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }





}
