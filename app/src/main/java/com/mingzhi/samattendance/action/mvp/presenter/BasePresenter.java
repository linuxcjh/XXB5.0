package com.mingzhi.samattendance.action.mvp.presenter;

import com.mingzhi.samattendance.action.mvp.convert.RetrofitStringConverter;
import com.mingzhi.samattendance.action.mvp.biz.IUserBiz;
import com.mingzhi.samattendance.action.framework.network.HttpsClient;
import com.mingzhi.samattendance.action.framework.common.CommonUtils;


import retrofit.RestAdapter;
import retrofit.client.ApacheClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Chen on 2015/8/21.
 */
public class BasePresenter {

    /* HTTP REQUEST Request of json type */
    public RestAdapter restAdapterHttp =new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setConverter(new GsonConverter(CommonUtils.gson)).setEndpoint(IUserBiz.URL).build();
    public IUserBiz apiHttp=restAdapterHttp.create(IUserBiz.class);

    /* HTTP REQUEST Request of String Type */
    public RestAdapter restAdapterHS =new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setConverter(new RetrofitStringConverter(CommonUtils.gson)).setEndpoint(IUserBiz.URL_SCAN).build();
    public IUserBiz apiHS=restAdapterHS.create(IUserBiz.class);

    /* HTTPS REQUEST Request of json type */
    public RestAdapter restAdapterHttps =new RestAdapter.Builder().setClient(new ApacheClient(HttpsClient.newHttpsClient())).setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(IUserBiz.URL).build();
    public IUserBiz apiHttps=restAdapterHttps.create(IUserBiz.class);

}
