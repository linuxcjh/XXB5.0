package com.mingzhi.samattendance.action.mvp.biz;

import com.mingzhi.samattendance.action.mvp.bean.User;

import java.io.File;
import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.QueryMap;
import retrofit.mime.TypedFile;
import retrofit.mime.TypedInput;

/**
 * Created by Chen on 2015/8/21.
 */
public interface IUserBiz {

    //public static  final  String URL = "https://scm.kcs.kubota.com.cn/restful/scmService/";
    //public static  final  String URL = "http://192.168.0.252:8085/restful/xxbService/";
    public static final String URL = "http://123.57.35.34:8912/restful/xxbService/";
    public static final String URL_SCAN = "http://bcr2.intsig.net"; //?user=e.cjh.cn@gmail.com&pass=X4F5Q5466DQ63PFE&lang=524287

    @POST("/login")
    public void login(@Body TypedInput user, Callback<User> cb);

    @POST("/updateUserimage")
    public void updateUserimage(@Body TypedInput model, Callback<User> cb);

    @Multipart
    @POST("/BCRService/BCR_VCF2")
    public void getCard(@QueryMap Map<String, String> options, @Part("upfile") TypedFile upfile, Callback<String> cb);

}
