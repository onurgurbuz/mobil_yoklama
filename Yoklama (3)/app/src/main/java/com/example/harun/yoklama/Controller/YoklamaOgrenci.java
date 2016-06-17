package com.example.harun.yoklama.Controller;

/**
 * Created by harun on 5/28/16.
 */
public class YoklamaOgrenci {
    private String ad;
    private String numara;

    public YoklamaOgrenci(String ad, String numara) {
        this.ad = ad;
        this.numara = numara;
    }

    public String getAd() {

        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getNumara() {
        return numara;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }
}
