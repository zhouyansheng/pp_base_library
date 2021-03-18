package com.yunqipei.baselibrary.basedialog

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.yunqipei.baselibrary.R
import com.yunqipei.baselibrary.databinding.ShareWebDialogBinding
import java.io.ByteArrayOutputStream


class ShareWebDialog @JvmOverloads constructor(context: Context,val appId : String ,val url:String,val der:String,val title:String,val logo:String) : Dialog(context, R.style.CustomDialogStyle) {

    private lateinit var binding: ShareWebDialogBinding

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
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.share_web_dialog, null, false)
        setContentView(binding.root)
        binding.dialog = this
        setCancelable(true)
    }

    fun weixin(){
        var api = WXAPIFactory.createWXAPI(context, appId)
        val webpage = WXWebpageObject()
        webpage.webpageUrl = url

        val msg = WXMediaMessage(webpage)
        msg.title = title
        msg.description = der
        val thumbBmp = Glide.with(context).asBitmap().load(logo).submit().get()
        msg.thumbData = bitmapToByte(thumbBmp)

        //构造一个Req
        val req: SendMessageToWX.Req = SendMessageToWX.Req()
        req.message = msg
        req.scene = SendMessageToWX.Req.WXSceneSession
        api.sendReq(req)
        dismiss()
    }



    fun friend(){
        var api = WXAPIFactory.createWXAPI(context, appId)
        val webpage = WXWebpageObject()
        webpage.webpageUrl = url

        val msg = WXMediaMessage(webpage)
        msg.title = title
        msg.description = der
        val thumbBmp = Glide.with(context).asBitmap().load(logo).submit().get()
        msg.thumbData = bitmapToByte(thumbBmp)

        //构造一个Req
        val req: SendMessageToWX.Req = SendMessageToWX.Req()
        req.message = msg
        req.scene = SendMessageToWX.Req.WXSceneTimeline
        api.sendReq(req)

        dismiss()
    }


    fun bitmapToByte(bm:Bitmap):ByteArray {
        val baos =  ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray()
    }



}