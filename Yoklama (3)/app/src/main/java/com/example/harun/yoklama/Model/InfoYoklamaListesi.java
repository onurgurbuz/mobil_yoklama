package com.example.harun.yoklama.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class InfoYoklamaListesi {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("info")
    @Expose
    private List<YoklamaListesi> info = new ArrayList<YoklamaListesi>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<YoklamaListesi> getInfo() {
        return info;
    }

    public void setInfo(List<YoklamaListesi> info) {
        this.info = info;
    }
}