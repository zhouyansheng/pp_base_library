package com.yunqipei.baselibrary.basedialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.yunqipei.baselibrary.R
import com.yunqipei.baselibrary.databinding.SelectPicDialogBinding
import com.yunqipei.baselibrary.utils.UiUtils

class SelectPicDialog@JvmOverloads constructor(context: Context, private val buttonClickCallback: ButtonClickCallback? = null) : Dialog(context, R.style.CustomDialogStyle)  {

    private lateinit var binding: SelectPicDialogBinding

    init {
        window?.setGravity(Gravity.CENTER)
        window?.decorView?.setPadding(UiUtils.dp2px(30f,context), 0, UiUtils.dp2px(30f,context), 0)
        val lp = window?.attributes
        lp?.height = ViewGroup.LayoutParams.WRAP_CONTENT
        lp?.width = ViewGroup.LayoutParams.MATCH_PARENT
        window?.attributes = lp
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.select_pic_dialog, null, false)
        setContentView(binding.root)
        binding.dialog = this
        setCancelable(true)

    }


    fun cancelDialog(){
        dismiss()
    }


    fun takePhoto(){
        buttonClickCallback?.clickTakePhoto()
    }

    fun album(){
        buttonClickCallback?.clickAlbum()
    }

    interface ButtonClickCallback {
        fun clickTakePhoto()
        fun clickAlbum()
    }

}