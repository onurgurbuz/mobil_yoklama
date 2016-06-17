package com.example.harun.yoklama;


import com.example.harun.yoklama.Model.Info;
import com.example.harun.yoklama.Model.InfoDers;
import com.example.harun.yoklama.Model.InfoOgrenci;
import com.example.harun.yoklama.Model.InfoYoklamaListesi;

import java.util.Date;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface Interfaces {

    // GET yada POST mu olduğunu belirliyoruz.
    @GET("/info.php")
    void getJsonValues(@Query("id") String id, @Query("table") String table, Callback<Info> response);

    @GET("/info.php")
    void getJsonDersler(@Query("id") String id, @Query("table") String table, Callback<InfoDers> response);

    @GET("/info.php")
    void getJsonYoklamaListesi(@Query("id") String id, @Query("table") String table, Callback<InfoYoklamaListesi> response);

    @GET("/info.php")
    void getJsonOgrenciler(@Query("id") String id, @Query("table") String table, Callback<InfoOgrenci> response);

    @FormUrlEncoded
    @POST("/signup.php")
    void getYoklama(@Field("ders") String ders, @Field("ogretmen") String ogretmen, @Field("yoklama") String yoklama,
                    @Field("ogrenci") String ogrenci, @Field("tarih") String tarih, Callback<InfoYoklamaListesi> callback);

    @FormUrlEncoded
    @POST("/update.php")
    void updateUser(@Field("id") String id, @Field("pass") String pass, Callback<Info> callback);

}