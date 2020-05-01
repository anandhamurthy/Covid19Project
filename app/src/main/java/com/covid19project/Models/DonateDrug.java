package com.covid19project.Models;

public class DonateDrug {
    String code, ware_house, mobile;

    public DonateDrug() {
    }

    public DonateDrug(String code, String ware_house, String mobile) {
        this.code = code;
        this.ware_house = ware_house;
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWare_house() {
        return ware_house;
    }

    public void setWare_house(String ware_house) {
        this.ware_house = ware_house;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
