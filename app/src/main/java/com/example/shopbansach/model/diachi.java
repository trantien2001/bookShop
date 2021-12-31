package com.example.shopbansach.model;

import java.io.Serializable;

public class diachi implements Serializable {
    private int idDc;
    private String tenDiaChi;
    private String diaChi;
    private String tenNguoiDung;
    private String soDienThoai;

    public diachi(int idDc, String tenDiaChi, String diaChi, String tenNguoiDung, String soDienThoai) {
        this.idDc = idDc;
        this.tenDiaChi = tenDiaChi;
        this.diaChi = diaChi;
        this.tenNguoiDung = tenNguoiDung;
        this.soDienThoai = soDienThoai;
    }

    public int getIdDc() {
        return idDc;
    }

    public void setIdDc(int idDc) {
        this.idDc = idDc;
    }

    public String getTenDiaChi() {
        return tenDiaChi;
    }

    public void setTenDiaChi(String tenDiaChi) {
        this.tenDiaChi = tenDiaChi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }
}
