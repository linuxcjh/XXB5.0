package com.mingzhi.samattendance.action.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mingzhi.samattendance.action.mvp.view.IRotateCameraView;
import com.mingzhi.samattendance.action.framework.common.CommonUtils;

import java.io.File;

/**
 * Created by Chen on 2015/8/21.
 */
public class RotateCameraPresenter extends BasePresenter{

    public static final String CAMERA_PATH = "Camera_path";
    public IRotateCameraView mRotateCameraView;
    private String filePath;
    private Activity mContext;

    public RotateCameraPresenter(IRotateCameraView RotateCameraView, Activity context){
        this.mRotateCameraView = RotateCameraView;
        this.mContext=context;
    }

    public void justBundle( Bundle savedInstanceState){
        if (savedInstanceState != null) {
            filePath = savedInstanceState.getString(CAMERA_PATH);
            fileExists();
        } else {
           filePath = CommonUtils.getCameraCapturePath(mContext);
        }
    }

    public void saveBundle(Bundle outState){
        outState.putString(CAMERA_PATH, filePath);
    }

    public void fileExists() {
        File file = new File(filePath);
        if (file.exists()) {
            Intent intent = new Intent();
            intent.putExtra(CAMERA_PATH, filePath);
            mContext.setResult(0x12, intent);
        }
        mContext.finish();
    }

}
