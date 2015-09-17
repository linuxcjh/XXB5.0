package com.mingzhi.samattendance.action.mvp.bean;

/**
 * Created by Chen on 2015/9/16.
 */
public class ScanInfoModel {

    private String name;
    private String phone;
    private String tel;
    private String address;
    private String position;
    private String company;
    private String email;

    @Override
    public String toString() {
        return "NAME:" + name +"\nPHONE:"+phone+"\nTEL:"+tel+"\nADDRESS:"+address+"\nPOSITION:"+position+"\nCOMPANY:"+company+"\nEMAIL:"+email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


}
