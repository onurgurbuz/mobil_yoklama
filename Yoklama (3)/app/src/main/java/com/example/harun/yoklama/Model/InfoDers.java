package com.example.harun.yoklama.Model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class InfoDers {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("info")
    @Expose
    private List<Ders> info = new ArrayList<Ders>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Ders> getInfo() {
        return info;
    }

    public void setInfo(List<Ders> info) {
        this.info = info;
    }
}