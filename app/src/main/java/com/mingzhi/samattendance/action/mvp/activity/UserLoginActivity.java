package com.mingzhi.samattendance.action.mvp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mingzhi.samattendance.action.ActivityBase;
import com.mingzhi.samattendance.action.R;
import com.mingzhi.samattendance.action.mvp.bean.ScanInfoModel;
import com.mingzhi.samattendance.action.mvp.bean.User;
import com.mingzhi.samattendance.action.mvp.presenter.ScanCardPresenter;
import com.mingzhi.samattendance.action.mvp.presenter.UserLoginPresenter;
import com.mingzhi.samattendance.action.mvp.view.IUserLoginView;

/**
 * Created by Chen on 2015/8/21.
 */
public class UserLoginActivity extends ActivityBase implements IUserLoginView, View.OnClickListener {

    private EditText mEtUserName, mEtPassword;
    private Button mBtnLogin, mBtnClear,mBtnScanCard,mBtnPic;
    private TextView mTvContent;
    private ProgressBar mPbLoading;
    private UserLoginPresenter mUserLoginPresenter = new UserLoginPresenter(this);
    private ScanCardPresenter mScanCardPresenter= new ScanCardPresenter(this);


    @Override
    public int setContentViewResId() {
        return R.layout.activity_user_login;
    }

    @Override
    public void findWigetAndListener() {
        mPbLoading = (ProgressBar) findViewById(R.id.id_pb_loading);
        mEtUserName = (EditText) findViewById(R.id.id_et_username);
        mEtPassword = (EditText) findViewById(R.id.id_et_password);
        mBtnLogin = (Button) findViewById(R.id.id_btn_login);
        mBtnLogin.setOnClickListener(this);
        mBtnClear = (Button) findViewById(R.id.id_btn_clear);
        mBtnClear.setOnClickListener(this);
        mBtnScanCard= (Button) findViewById(R.id.id_btn_scan);
        mBtnScanCard.setOnClickListener(this);
        mTvContent =(TextView)findViewById(R.id.id_tv_content);
        mBtnPic= (Button) findViewById(R.id.id_btn_pic);
        mBtnPic.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn_login:
                mUserLoginPresenter.login();
                break;
            case R.id.id_btn_clear:
                mUserLoginPresenter.clear();
                break;
            case R.id.id_btn_scan:
                mScanCardPresenter.getScan();
                break;
            case R.id.id_btn_pic:
                mScanCardPresenter.getCamera(this);
                break;
            default:
                break;
        }
    }

    @Override
    public String getUserName() {
        return mEtUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void clearUserName() {
        mEtUserName.setText("");
    }

    @Override
    public void clearPassword() {
        mEtPassword.setText("");
    }

    @Override
    public void showLoading() {
        mPbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mPbLoading.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity(User user) {
        Toast.makeText(this, user.getResult() + "login success,to MainActivity", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toMainDisplayScanData(ScanInfoModel scanInfoModel) {
        mTvContent.setText(scanInfoModel.toString());
        Toast.makeText(this,scanInfoModel.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mScanCardPresenter.getFilePath(data);
        Toast.makeText(this,"onActivityResult",Toast.LENGTH_SHORT).show();
    }
}
