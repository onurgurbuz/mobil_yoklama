package com.example.harun.yoklama.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ListView;
import android.widget.Toast;

import com.example.harun.yoklama.Interfaces;
import com.example.harun.yoklama.Model.InfoDers;
import com.example.harun.yoklama.Model.InfoOgrenci;
import com.example.harun.yoklama.Model.InfoYoklamaListesi;
import com.example.harun.yoklama.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class YoklamaDetayActivity extends AppCompatActivity {
    final List<YoklamaOgrenci> liste = new ArrayList<YoklamaOgrenci>();
    private ListView lsDetay;
    private RestAdapter restAdapter;
    private Interfaces derslerInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoklama_detay);
        lsDetay = (ListView) findViewById(R.id.lstyoklamaDetay);





        yoklamaListesi = new ArrayList<String>();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://snncnaydn.com/yoklama")
                .build();
        derslerInterface = restAdapter.create(Interfaces.class);
        derslerInterface.getJsonYoklamaListesi("all", "yoklama", new Callback<InfoYoklamaListesi>() { // json array döneceği için modelimizi array tipinde belirledik
            @Override
            public void success(InfoYoklamaListesi model, Response response) {
                InfoYoklamaListesi info = new InfoYoklamaListesi();
                info.setStatus(model.getStatus());
                info.setInfo(model.getInfo());
                for (int i = 0; i < model.getInfo().size(); i++) {
                    if (model.getInfo().get(i).getOgretmen().equals(String.valueOf(Statikler.kid))) {
                        if(model.getInfo().get(i).getDers().equals(Statikler.secilenDersId)){
                            if (model.getInfo().get(i).getYoklama().equals("var"))
                                yoklamaListesi.add(model.getInfo().get(i).getOgrenci().toString());
                        }
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                String merror = error.getMessage();
                Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
            }
        });


        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://snncnaydn.com/yoklama")
                .build();
        derslerInterface = restAdapter.create(Interfaces.class);
        derslerInterface.getJsonOgrenciler("all", "ogrenci", new Callback<InfoOgrenci>() { // json array döneceği için modelimizi array tipinde belirledik
            @Override
            public void success(InfoOgrenci model, Response response) {
                InfoOgrenci info = new InfoOgrenci();
                info.setStatus(model.getStatus());
                info.setInfo(model.getInfo());
                for (int i = 0; i < model.getInfo().size(); i++) {
                    for (int j = 0; j < yoklamaListesi.size(); j++) {
                        if (model.getInfo().get(i).getId().equals(Statikler.secilenDersId)) {
                            liste.add(new YoklamaOgrenci(model.getInfo().get(i).getAd() +" " + model.getInfo().get(i).getSoyad(), model.getInfo().get(i).getOgrno()));
                        }
                    }
                }
                final ListView listemiz = (ListView) findViewById(R.id.lstyoklamaDetay);
                YoklamaDetayAdapter adaptorumuz = new YoklamaDetayAdapter(YoklamaDetayActivity.this, liste);
                listemiz.setAdapter(adaptorumuz);
            }

            @Override
            public void failure(RetrofitError error) {
                String merror = error.getMessage();
                Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
            }
        });
    }

    ArrayList<String> yoklamaListesi;

    public void yoklamaGetir() {
        yoklamaListesi = new ArrayList<String>();
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://snncnaydn.com/yoklama")
                .build();
        derslerInterface = restAdapter.create(Interfaces.class);
        derslerInterface.getJsonYoklamaListesi("all", "yoklama", new Callback<InfoYoklamaListesi>() { // json array döneceği için modelimizi array tipinde belirledik
            @Override
            public void success(InfoYoklamaListesi model, Response response) {
                InfoYoklamaListesi info = new InfoYoklamaListesi();
                info.setStatus(model.getStatus());
                info.setInfo(model.getInfo());
                for (int i = 0; i < model.getInfo().size(); i++) {
                    if (model.getInfo().get(i).getOgretmen().equals(String.valueOf(Statikler.kid))) {
                        if(model.getInfo().get(i).getDers().equals(Statikler.secilenDersId)){
                            if (model.getInfo().get(i).getYoklama() == "var")
                                yoklamaListesi.add(model.getInfo().get(i).getOgrenci().toString());
                        }
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                String merror = error.getMessage();
                Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
            }
        });
    }


    public void dersGetir() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://snncnaydn.com/yoklama")
                .build();
        derslerInterface = restAdapter.create(Interfaces.class);
        derslerInterface.getJsonDersler("all", "ders", new Callback<InfoDers>() { // json array döneceği için modelimizi array tipinde belirledik
            @Override
            public void success(InfoDers model, Response response) {
                InfoDers info = new InfoDers();
                info.setStatus(model.getStatus());
                info.setInfo(model.getInfo());
                for (int i = 0; i < model.getInfo().size(); i++) {
                    if (model.getInfo().get(i).getDersadi().equals(Statikler.secilenDers)) {
                        Statikler.secilenDersId=model.getInfo().get(i).getId();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                String merror = error.getMessage();
                Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
            }
        });
    }
}
