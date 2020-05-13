package com.covid19project.Models;

public class HealthCareList {

    String sno, district, hospital, type;

    public HealthCareList() {
    }

    public HealthCareList(String sno, String district, String hospital, String type) {
        this.sno = sno;
        this.district = district;
        this.hospital = hospital;
        this.type = type;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
