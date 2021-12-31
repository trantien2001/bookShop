package com.example.shopbansach.model;

import java.io.Serializable;

public class TaiKhoan implements Serializable {
    public int idtk;
    public String tenKh;
    public String email;

    public TaiKhoan(String tenKh, String email) {
        this.idtk = idtk;
        this.tenKh = tenKh;
        this.email = email;
    }

    public int getIdtk() {
        return idtk;
    }

    public void setIdtk(int idtk) {
        this.idtk = idtk;
    }

    public String getTenKh() {
        return tenKh;
    }

    public void setTenKh(String tenKh) {
        this.tenKh = tenKh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
