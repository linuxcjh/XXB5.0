package com.mingzhi.samattendance.action.mvp.presenter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.mingzhi.samattendance.action.framework.XxbConstants;
import com.mingzhi.samattendance.action.mvp.bean.ScanInfoModel;
import com.mingzhi.samattendance.action.mvp.view.IUserLoginView;
import com.mingzhi.samattendance.action.framework.common.CommonUtils;
import com.mingzhi.samattendance.action.scancard.RotateCameraActivity;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import a_vcard.android.syncml.pim.vcard.VCardException;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Created by Chen on 2015/8/21.
 */
public class ScanCardPresenter extends UserLoginPresenter{

    private String filePath;
    public ScanCardPresenter(IUserLoginView userLoginView){
            super(userLoginView);
    }

    /**
     * Scan business card  Demo
     */
    public void getScan(){
        userLoginView.showLoading();
//        File upFile = new File(Environment.getExternalStorageDirectory(),
//                "mingpian.jpg");
        if(TextUtils.isEmpty(filePath)){
            return;
        }
        File upFile = new File(filePath);
        TypedFile file = new TypedFile("image/jpg",upFile);
        Map<String, String> map = new HashMap<>();
        map.put("user", "e.cjh.cn@gmail.com");
        map.put("pass", "X4F5Q5466DQ63PFE");
        map.put("lang", "524287");
        apiHS.getCard(map,file,new Callback<String>(){
            @Override
            public void success(String s, Response response) {
                try {
                    ScanInfoModel model = CommonUtils.parser(s);
                    userLoginView.toMainDisplayScanData(model);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (VCardException e) {
                    e.printStackTrace();
                }
                userLoginView.hideLoading();
            }
            @Override
            public void failure(RetrofitError error) {
                userLoginView.hideLoading();
            }
        });
    }
    /**
     * get camera picture
     * @param context
     */
    public void getCamera(Activity context){
        Intent intentC = new Intent(context,
                RotateCameraActivity.class);
        context.startActivityForResult(intentC, XxbConstants.CAMERA_REQUEST_CODE);
    }
    /**
     * Compress of camera of picture
     * @param data
     */
    public void getFilePath(Intent data) {
        filePath= data.getStringExtra(RotateCameraPresenter.CAMERA_PATH);
        CommonUtils.getImage(filePath);
    }


}
