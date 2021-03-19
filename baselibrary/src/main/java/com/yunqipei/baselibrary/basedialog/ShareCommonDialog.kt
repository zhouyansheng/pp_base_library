package com.yunqipei.baselibrary.basedialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.yunqipei.baselibrary.R
import com.yunqipei.baselibrary.databinding.ShareCommonDialogBinding

class ShareCommonDialog@JvmOverloads constructor(context: Context, private val buttonClickCallback: ButtonClickCallback? = null) : Dialog(context, R.style.CustomDialogStyle) {

    private lateinit var binding: ShareCommonDialogBinding

    init {
        window?.setGravity(Gravity.BOTTOM)
        window?.decorView?.setPadding(0, 0, 0, 0)
        val lp = window?.attributes
        lp?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        lp?.width = ViewGroup.LayoutParams.MATCH_PARENT
        window?.attributes = lp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.share_common_dialog, null, false)
        setContentView(binding.root)
        binding.dialog = this
        setCancelable(true)
    }

    fun weixin(){
        buttonClickCallback?.clickWX()
        dismiss()
    }

    fun friend(){
        buttonClickCallback?.clickFriend()
        dismiss()
    }

    interface ButtonClickCallback {
        fun clickWX()
        fun clickFriend()
    }

}