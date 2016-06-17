package com.example.harun.yoklama.Model;

/**
 * Created by onur on 28.05.2016.
 */
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Info {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("info")
    @Expose
    private List<Kullanici> info = new ArrayList<Kullanici>();

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Kullanici> getInfo() {
        return info;
    }

    public void setInfo(List<Kullanici> info) {
        this.info = info;
    }
}