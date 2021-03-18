package com.yunqipei.baselibrary.picloader;

public interface OnImageCompleteCallback {
    /**
     * Start loading
     */
    void onShowLoading();

    /**
     * Stop loading
     */
    void onHideLoading();
}
