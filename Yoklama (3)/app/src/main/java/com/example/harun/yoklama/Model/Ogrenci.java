package com.example.harun.yoklama.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ogrenci {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ogrno")
    @Expose
    private String ogrno;
    @SerializedName("ad")
    @Expose
    private String ad;
    @SerializedName("soyad")
    @Expose
    private String soyad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOgrno() {
        return ogrno;
    }

    public void setOgrno(String ogrno) {
        this.ogrno = ogrno;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
}
