package com.example.shopbansach.model;

public class ThongBao {
    private String tentb;
    private String motatb;
    private int imgagetb;

    public ThongBao(String tentb, String motatb, int imgagetb) {
        this.tentb = tentb;
        this.motatb = motatb;
        this.imgagetb = imgagetb;
    }

    public String getTentb() {
        return tentb;
    }

    public void setTentb(String tentb) {
        this.tentb = tentb;
    }

    public String getMotatb() {
        return motatb;
    }

    public void setMotatb(String motatb) {
        this.motatb = motatb;
    }

    public int getImgagetb() {
        return imgagetb;
    }

    public void setImgagetb(int imgagetb) {
        this.imgagetb = imgagetb;
    }
}
