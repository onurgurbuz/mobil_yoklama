package com.example.harun.yoklama.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Kullanici {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("kadi")
    @Expose
    private String kadi;
    @SerializedName("pass")
    @Expose
    private String pass;
    @SerializedName("isim")
    @Expose
    private String isim;
    @SerializedName("numara")
    @Expose
    private String numara;
    @SerializedName("yetki")
    @Expose
    private Object yetki;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKadi() {
        return kadi;
    }

    public void setKadi(String kadi) {
        this.kadi = kadi;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }

    public Object getYetki() {
        return yetki;
    }

    public void setYetki(Object yetki) {
        this.yetki = yetki;
    }
}