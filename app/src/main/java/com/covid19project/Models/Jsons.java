package com.covid19project.Models;

public class Jsons {

    String image_slider, home_treatment_images, home_treatment_links, toll_numbers, lab_test, donate_drug, donate_relief;

    public Jsons() {
    }

    public Jsons(String image_slider, String home_treatment_images, String home_treatment_links, String toll_numbers, String lab_test, String donate_drug, String donate_relief) {
        this.image_slider = image_slider;
        this.home_treatment_images = home_treatment_images;
        this.home_treatment_links = home_treatment_links;
        this.toll_numbers = toll_numbers;
        this.lab_test = lab_test;
        this.donate_drug = donate_drug;
        this.donate_relief = donate_relief;
    }

    public String getImage_slider() {
        return image_slider;
    }

    public void setImage_slider(String image_slider) {
        this.image_slider = image_slider;
    }

    public String getHome_treatment_images() {
        return home_treatment_images;
    }

    public void setHome_treatment_images(String home_treatment_images) {
        this.home_treatment_images = home_treatment_images;
    }

    public String getHome_treatment_links() {
        return home_treatment_links;
    }

    public void setHome_treatment_links(String home_treatment_links) {
        this.home_treatment_links = home_treatment_links;
    }

    public String getToll_numbers() {
        return toll_numbers;
    }

    public void setToll_numbers(String toll_numbers) {
        this.toll_numbers = toll_numbers;
    }

    public String getLab_test() {
        return lab_test;
    }

    public void setLab_test(String lab_test) {
        this.lab_test = lab_test;
    }

    public String getDonate_drug() {
        return donate_drug;
    }

    public void setDonate_drug(String donate_drug) {
        this.donate_drug = donate_drug;
    }

    public String getDonate_relief() {
        return donate_relief;
    }

    public void setDonate_relief(String donate_relief) {
        this.donate_relief = donate_relief;
    }
}
