package com.mingzhi.samattendance.action.mvp.view;

import com.mingzhi.samattendance.action.mvp.bean.ScanInfoModel;
import com.mingzhi.samattendance.action.mvp.bean.User;

import retrofit.mime.TypedInput;

/**
 * Created by Chen on 2015/8/21.
 */
public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showLoading();

    void hideLoading();

    void toMainActivity(User user);

    void toMainDisplayScanData(ScanInfoModel scanInfoModel);

    void showFailedError();


}
