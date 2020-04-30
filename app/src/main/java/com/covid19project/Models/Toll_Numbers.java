package com.covid19project.Models;

public class Toll_Numbers {

    String district, emergency_number, landline_number;

    public Toll_Numbers() {
    }

    public Toll_Numbers(String district, String emergency_number, String landline_number) {
        this.district = district;
        this.emergency_number = emergency_number;
        this.landline_number = landline_number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmergency_number() {
        return emergency_number;
    }

    public void setEmergency_number(String emergency_number) {
        this.emergency_number = emergency_number;
    }

    public String getLandline_number() {
        return landline_number;
    }

    public void setLandline_number(String landline_number) {
        this.landline_number = landline_number;
    }
}
