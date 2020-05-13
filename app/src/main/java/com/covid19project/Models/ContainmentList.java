package com.covid19project.Models;

public class ContainmentList {

    String district, containment_zones;

    public ContainmentList() {
    }

    public ContainmentList(String district, String containment_zones) {
        this.district = district;
        this.containment_zones = containment_zones;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getContainment_zones() {
        return containment_zones;
    }

    public void setContainment_zones(String containment_zones) {
        this.containment_zones = containment_zones;
    }
}
