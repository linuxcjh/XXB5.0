package com.mingzhi.samattendance.action.mvp.presenter;

import com.mingzhi.samattendance.action.framework.TransIntoAccoutAuditModel;
import com.mingzhi.samattendance.action.mvp.bean.User;
import com.mingzhi.samattendance.action.framework.common.CommonUtils;
import com.mingzhi.samattendance.action.mvp.view.IUserLoginView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

/**
 * Created by Chen on 2015/8/21.
 */
public class UserLoginPresenter extends BasePresenter{

    public IUserLoginView userLoginView;

    public UserLoginPresenter (IUserLoginView userLoginView){

        this.userLoginView = userLoginView;
    }

    public void clear(){
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }

    public TypedInput getToServiceData() {
        TransIntoAccoutAuditModel model = new TransIntoAccoutAuditModel();
        model.setImei("358108066527747");
        model.setNum("chenjianhui");
        model.setPassword("753789");
        model.setVersions("SM-G9200 , 22 , 5.1.1 , 3.0.9");
        TypedInput in = new TypedByteArray("application/json", CommonUtils.gson.toJson(model).getBytes());
        return in;
    }
    /**
     * init login
     */
    public void login(){
        userLoginView.showLoading();
        apiHttp.login(getToServiceData(), new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                userLoginView.toMainActivity(user);
                userLoginView.hideLoading();
            }

            @Override
            public void failure(RetrofitError error) {
                userLoginView.showFailedError();
                userLoginView.hideLoading();
            }
        });

    }
    public TypedInput getToServicePictureData() {
        TransIntoAccoutAuditModel model = new TransIntoAccoutAuditModel();
        model.setUserId("0cfa1fe7a9954095b1a12ad7e6b8da1e");
        model.setFilePath(CommonUtils.getFileByte("/storage/emulated/0/XXB/profession/dcim/20150826115713.jpg"));
        TypedInput in = new TypedByteArray("application/json", CommonUtils.gson.toJson(model).getBytes());
        return in;
    }
    /**
     * Upload picture
     */
    public void upImage(){
        userLoginView.showLoading();
        apiHttp.updateUserimage(getToServicePictureData(), new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                userLoginView.toMainActivity(user);
                userLoginView.hideLoading();
            }

            @Override
            public void failure(RetrofitError error) {
                userLoginView.showFailedError();
                userLoginView.hideLoading();
            }
        });
    }

}
