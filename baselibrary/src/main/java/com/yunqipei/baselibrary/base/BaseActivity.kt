package com.yunqipei.baselibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.handarui.iqfun.util.AppManager

abstract class BaseActivity : AppCompatActivity() {


    protected val TAG : String = this.javaClass.name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (fetchLayoutId() != 0) {
            setContentView(fetchLayoutId())
        }
        AppManager.addActivity(this)
    }

    protected open fun fetchLayoutId(): Int {
        return 0
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.removeActivity(this)
    }


}