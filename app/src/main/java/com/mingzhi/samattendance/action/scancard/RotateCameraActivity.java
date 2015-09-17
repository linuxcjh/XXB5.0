package com.mingzhi.samattendance.action.scancard;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import com.mingzhi.samattendance.action.mvp.presenter.RotateCameraPresenter;
import com.mingzhi.samattendance.action.mvp.view.IRotateCameraView;

/**
 * 
 * @author 陈建辉 <br/>
 * @time 2013-6-18 上午11:25:31
 * @note 功能说明：拍照页面
 */
public class RotateCameraActivity extends Activity implements IRotateCameraView {

   private RotateCameraPresenter mRotateCameraPresenter=new RotateCameraPresenter(this,this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        mRotateCameraPresenter.justBundle(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
        mRotateCameraPresenter.saveBundle(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
        mRotateCameraPresenter.fileExists();
	}

}
