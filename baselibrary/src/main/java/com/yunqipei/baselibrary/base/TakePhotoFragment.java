package com.yunqipei.baselibrary.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.yunqipei.baselibrary.R;

import java.io.File;

public abstract class TakePhotoFragment extends BaseFragment implements TakePhoto.TakeResultListener, InvokeListener {
    private static final String TAG = com.jph.takephoto.app.TakePhotoFragment.class.getName();
    private InvokeParam invokeParam;
    private TakePhoto takePhoto;
    private BottomSheetDialog mPickAvatarDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type= PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(getActivity(),type,invokeParam,this);
    }
    /**
     *  ??????TakePhoto??????
     * @return
     */
    public TakePhoto getTakePhoto(){
        if (takePhoto==null){
            takePhoto= (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
        }
        return takePhoto;
    }
    @Override
    public void takeSuccess(TResult result) {
        Log.i(TAG,"takeSuccess???" + result.getImage().getCompressPath());
    }
    @Override
    public void takeFail(TResult result, String msg) {
        Log.i(TAG, "takeFail:" + msg);
    }
    @Override
    public void takeCancel() {
        Log.i(TAG, getResources().getString(R.string.msg_operation_canceled));
    }
    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type= PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam=invokeParam;
        }
        return type;
    }


    /**
     * ???????????????????????? ?????????
     */
    public void openPickImageDialog() {
        //?????? ????????????dialog
        if (mPickAvatarDialog == null) {
            mPickAvatarDialog = new BottomSheetDialog(getContext());
            View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_picture, null);
            view.findViewById(R.id.take_photo).setOnClickListener(v -> {
                //?????????????????????
                getTakePhoto().onPickFromCapture(configUri());
                dismissDialog();
            });
            view.findViewById(R.id.select_from_album).setOnClickListener(v -> {
                //?????????????????????????????????
                getTakePhoto().onPickFromGallery();
                dismissDialog();
            });
            view.findViewById(R.id.cancel).setOnClickListener(v -> dismissDialog());
            mPickAvatarDialog.setContentView(view);
            if (mPickAvatarDialog.getWindow() != null) {
                //????????????
                mPickAvatarDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                        .setBackgroundResource(android.R.color.transparent);
            }
            mPickAvatarDialog.setCancelable(true);

        }
        mPickAvatarDialog.show();
    }

    /**
     * ???????????????????????? ??????
     */
    public void openPickImageWithCropDialog() {
        //?????? ????????????dialog
        if (mPickAvatarDialog == null) {
            mPickAvatarDialog = new BottomSheetDialog(getContext());
            View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_picture, null);
            view.findViewById(R.id.take_photo).setOnClickListener(v -> {
                //?????????????????????
                getTakePhoto().onPickFromCaptureWithCrop(configUri(), configCrop());
                dismissDialog();
            });
            view.findViewById(R.id.select_from_album).setOnClickListener(v -> {
                //?????????????????????????????????
                getTakePhoto().onPickFromGalleryWithCrop(configUri(), configCrop());
                dismissDialog();
            });
            view.findViewById(R.id.cancel).setOnClickListener(v -> dismissDialog());
            mPickAvatarDialog.setContentView(view);
            if (mPickAvatarDialog.getWindow() != null) {
                //????????????
                mPickAvatarDialog.getWindow().findViewById(R.id.design_bottom_sheet)
                        .setBackgroundResource(android.R.color.transparent);
            }
            mPickAvatarDialog.setCancelable(true);

        }
        mPickAvatarDialog.show();
    }


    protected void dismissDialog() {
        if (mPickAvatarDialog != null && mPickAvatarDialog.isShowing()) {
            mPickAvatarDialog.dismiss();
        }
    }

    /**
     * ????????????
     *
     * @return
     */
    protected Uri configUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return Uri.fromFile(file);
    }

    /**
     * ??????????????????
     *
     * @return
     */
    protected CropOptions configCrop() {
        return new CropOptions.Builder()
                .setAspectX(1)
                .setAspectY(1)
                .create();
    }
}
