package com.example.harun.yoklama.Controller;

/**
 * Created by harun on 5/27/16.
 */
public class YoklamaListesi1 {
    private String dersAdi;
    private String tarih;
    public YoklamaListesi1(String dersAdi, String tarih){
        this.dersAdi = dersAdi;
        this.tarih = tarih;
    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}