package com.example.harun.yoklama.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ders {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("dersadi")
    @Expose
    private String dersadi;
    @SerializedName("kid")
    @Expose
    private String kid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDersadi() {
        return dersadi;
    }

    public void setDersadi(String dersadi) {
        this.dersadi = dersadi;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }
}