package com.example.harun.yoklama.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.harun.yoklama.Interfaces;
import com.example.harun.yoklama.Model.InfoDers;
import com.example.harun.yoklama.R;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class YeniYoklamaActivity extends AppCompatActivity {

    Spinner spnDers;
    Button btnYeniYoklama;
    ArrayAdapter dersAdapter;
    private RestAdapter restAdapter;
    private Interfaces derslerInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni_yoklama);
        btnYeniYoklama = (Button) findViewById(R.id.btnyoklamaBaslat);
        spnDers = (Spinner) findViewById(R.id.spnDers);

        dersleriGetir();
        yoklamaBaslat();

        spnDers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Statikler.yoklamaDersAdi=spnDers.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    public void yoklamaBaslat() {
        btnYeniYoklama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YeniYoklamaActivity.this, KameraActivity.class);
                startActivity(intent);
            }
        });
    }
    ArrayList<String> liste;
    public void dersleriGetir() {
        liste = new ArrayList<String>();
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
                    if (model.getInfo().get(i).getKid().equals(String.valueOf(Statikler.kid))) {
                        liste.add(model.getInfo().get(i).getDersadi().toString());
                    }
                }
                dersAdapter = new ArrayAdapter(YeniYoklamaActivity.this, android.R.layout.simple_spinner_item, liste);
                dersAdapter.setDropDownViewResource(R.layout.spinner);
                spnDers.setAdapter(dersAdapter);
            }
            @Override
            public void failure(RetrofitError error) {
                String merror = error.getMessage();
                Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
            }

        });
    }
}
