package com.mingzhi.samattendance.action.framework;

import com.mingzhi.samattendance.action.mvp.bean.User;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.POST;
import retrofit.mime.TypedInput;

/**
 * Created by Chen on 2015/7/30.
 */
public interface XXBAPI {
    public static  final  String URL = "http://www.xxb001.cn/restful/xxbService/";
    public static final String URLSCAN="http://bcr2.intsig.net"; //?user=e.cjh.cn@gmail.com&pass=X4F5Q5466DQ63PFE&lang=524287

    @POST("/login")
    public  void listTrasn(@Body TypedInput user , Callback<User> cb);

    @POST("/insertCracked")
    public  void insertC(@Body TypedInput Crackd , Callback<RecievCC> cb);

    @POST("/BCRService/BCR_VCF2")
    public  void getCard(@Field("user") String user ,@Field("pass") String pass,@Field("lang") String lang,@Field("upfile") File upfile, Callback<String> cb);


}
