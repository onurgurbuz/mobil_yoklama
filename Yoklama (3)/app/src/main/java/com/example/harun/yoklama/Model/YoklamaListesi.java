package com.example.harun.yoklama.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by harun on 5/27/16.
 */
public class YoklamaListesi {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("ders")
    @Expose
    private String ders;
    @SerializedName("ogretmen")
    @Expose
    private String ogretmen;
    @SerializedName("yoklama")
    @Expose
    private String yoklama;
    @SerializedName("ogrenci")
    @Expose
    private String ogrenci;
    @SerializedName("tarih")
    @Expose
    private String tarih;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDers() {
        return ders;
    }

    public void setDers(String ders) {
        this.ders = ders;
    }

    public String getOgretmen() {
        return ogretmen;
    }

    public void setOgretmen(String ogretmen) {
        this.ogretmen = ogretmen;
    }

    public String getYoklama() {
        return yoklama;
    }

    public void setYoklama(String yoklama) {
        this.yoklama = yoklama;
    }

    public String getOgrenci() {
        return ogrenci;
    }

    public void setOgrenci(String ogrenci) {
        this.ogrenci = ogrenci;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
