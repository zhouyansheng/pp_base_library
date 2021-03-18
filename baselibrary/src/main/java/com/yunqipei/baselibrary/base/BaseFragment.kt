package com.yunqipei.baselibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yunqipei.baselibrary.R


class BaseFragment : Fragment() {

    protected val TAG : String = this.javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(fetchLayoutId(), container, false)
        initView()
        return rootView
    }


    open fun fetchLayoutId(): Int{
        return 0
    }

    open fun initView(){}
}