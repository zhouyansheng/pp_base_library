package com.handarui.iqfun.util

import android.app.Activity
import java.util.*

/**
 * Created by zx on 2018/2/5 0005.
 */

/**
 * Activity管理类
 */
object AppManager {
    private var activityStack: Stack<Activity>? = null

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 移除Activity
     */
    fun removeActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.remove(activity)
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity {
        return activityStack!!.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var act = activity
        if (act != null) {
            if(!act.isFinishing)
            {
                act.finish()
            }
            activityStack!!.remove(act)
        }
        act = null
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        if (activityStack == null)
            return
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i].finish()
            }
            i++
        }
        activityStack!!.clear()
    }

    fun cantainActivity(activity: Activity): Boolean {
        return if (activityStack != null) {
            activityStack!!.contains(activity)
        } else false
    }
    fun  doClearTop(act: Activity?):Boolean
    {
        //Log.e("Test", "CommonUtil.doClearTop start:");
        if(act == null || activityStack == null)
        {
            return false
        }
        if(!activityStack!!.contains(act))
        {
            return false
        }

        while (!activityStack!!.isEmpty())
        {
            val activity = activityStack!!.lastElement()
            if(activity == act)
            {
                break
            }
            finishActivity(activity)
        }
        //Log.e("Test", "CommonUtil.doClearTop end:");
        return false

    }

}