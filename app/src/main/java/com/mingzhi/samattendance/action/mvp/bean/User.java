package com.mingzhi.samattendance.action.mvp.bean;

/**
 * Created by Chen on 2015/8/21.
 */
public class User {

    private String userName;
    private String password;
    private String userId;// 用户记录ID
    private String name;// 用户姓名
    private String result;// 0:异常 1:用户不存在 2:密码错误 3:该用户已经离职
    private String saasFlag; // 公司ID
    private String isManager; // 0：管理员 1：员工
    private String userImage;//
    private String saasFlagName;// 公司
    private String departmentname;
    private String num;// 工号
    private String androidUserId;// push userid
    private String androidid;// channelid.
    private String isAgency;//0 工厂 1 代理商
    private String iosId;


    public String getIosId() {
        return iosId;
    }

    public void setIosId(String iosId) {
        this.iosId = iosId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaasFlag() {
        return saasFlag;
    }

    public void setSaasFlag(String saasFlag) {
        this.saasFlag = saasFlag;
    }

    public String getIsManager() {
        return isManager;
    }

    public void setIsManager(String isManager) {
        this.isManager = isManager;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getSaasFlagName() {
        return saasFlagName;
    }

    public void setSaasFlagName(String saasFlagName) {
        this.saasFlagName = saasFlagName;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAndroidUserId() {
        return androidUserId;
    }

    public void setAndroidUserId(String androidUserId) {
        this.androidUserId = androidUserId;
    }

    public String getAndroidid() {
        return androidid;
    }

    public void setAndroidid(String androidid) {
        this.androidid = androidid;
    }

    public String getIsAgency() {
        return isAgency;
    }

    public void setIsAgency(String isAgency) {
        this.isAgency = isAgency;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
