package com.example.harun.yoklama.Model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InfoOgrenci {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("info")
    @Expose
    private List<Ogrenci> info = new ArrayList<Ogrenci>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Ogrenci> getInfo() {
        return info;
    }

    public void setInfo(List<Ogrenci> info) {
        this.info = info;
    }
}