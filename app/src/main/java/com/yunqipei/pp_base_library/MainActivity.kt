package com.yunqipei.pp_base_library


import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.jph.takephoto.model.TResult
import com.yunqipei.baselibrary.base.TakePhotoActivity
import com.yunqipei.baselibrary.basedialog.CommentDialog
import com.yunqipei.baselibrary.basedialog.SelectPicDialog
import com.yunqipei.baselibrary.basedialog.ShareCommonDialog
import com.yunqipei.baselibrary.picloader.GlideEngine
import java.io.File


class MainActivity : TakePhotoActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val img = findViewById<ImageView>(R.id.img)

        findViewById<Button>(R.id.btn1).setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    CommentDialog(this@MainActivity, "取消", "确定", "我是lib的title", object :
                        CommentDialog.ButtonClickCallback {
                        override fun clickConfirm() {

                        }
                    }).show()
                }

            })
        findViewById<Button>(R.id.btn2).setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                SelectPicDialog(this@MainActivity,  object :
                    SelectPicDialog.ButtonClickCallback {
                    override fun clickTakePhoto() {
                        takePhoto.onPickFromCapture(configUri())
                    }

                    override fun clickAlbum() {
                        //打开自带的图片选择界面
                        takePhoto.onPickFromGallery()
                    }

                }).show()
            }

        })
        findViewById<Button>(R.id.btn3).setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                openPickImageDialog()
            }
        })

        findViewById<Button>(R.id.btn4).setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                ShareCommonDialog(this@MainActivity, object :
                    ShareCommonDialog.ButtonClickCallback {
                    override fun clickWX() {

                    }

                    override fun clickFriend() {

                    }

                }).show()
            }
        })

        findViewById<Button>(R.id.btn5).setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                GlideEngine.createGlideEngine()
                    .loadCircleImage(this@MainActivity,"https://lhscbigimgs.oss-cn-beijing.aliyuncs.com/1/0/1614927092787.jpg",img,R.mipmap.ic_launcher)
            }
        })

    }

    override fun takeSuccess(result: TResult) {
        super.takeSuccess(result)
        val path = result.image.originalPath
    }

    /**
     * 图片路径
     *
     * @return
     */
    override fun configUri(): Uri? {
        val file = File(
            Environment.getExternalStorageDirectory(),
            "/temp/" + System.currentTimeMillis() + ".jpg"
        )
        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
        }
        return Uri.fromFile(file)
    }

}