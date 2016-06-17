package com.example.harun.yoklama.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.harun.yoklama.Interfaces;
import com.example.harun.yoklama.Model.InfoDers;
import com.example.harun.yoklama.Model.InfoYoklamaListesi;
import com.example.harun.yoklama.R;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListelemeActivity extends AppCompatActivity {
    final List<YoklamaListesi1> liste = new ArrayList<YoklamaListesi1>();
    private RestAdapter restAdapter;
    private Interfaces derslerInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeleme);

        yoklamaGetir();

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
                    for (int j = 0; j < yoklamaListesi.size(); j++) {
                        if (model.getInfo().get(i).getId().equals(yoklamaListesi.get(j))) {
                            liste.add(new YoklamaListesi1(model.getInfo().get(i).getDersadi(), tarihListesi.get(j)));
                        }
                    }
                }

                final ListView listemiz = (ListView) findViewById(R.id.liste);
                Liste_Adapter adaptorumuz = new Liste_Adapter(ListelemeActivity.this, liste);
                listemiz.setAdapter(adaptorumuz);

                listemiz.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ListelemeActivity.this, YoklamaDetayActivity.class);
                        Statikler.secilenDers=liste.get(position).getDersAdi();
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
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                String merror = error.getMessage();
                Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
            }

        });
    }


    ArrayList<String> yoklamaListesi;
    ArrayList<String> tarihListesi;

    public void yoklamaGetir() {
        yoklamaListesi = new ArrayList<String>();
        tarihListesi = new ArrayList<String>();
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
                        yoklamaListesi.add(model.getInfo().get(i).getDers().toString());
                        tarihListesi.add(model.getInfo().get(i).getTarih().toString());
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