package com.covid19project.Models;

public class DonateRelief {
    String district, mobile;

    public DonateRelief() {
    }

    public DonateRelief(String district, String mobile) {
        this.district = district;
        this.mobile = mobile;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
