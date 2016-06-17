package com.example.harun.yoklama.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.harun.yoklama.R;

public class EgitmenActivity extends AppCompatActivity {
    Button btnYoklamaListele;
    Button btnYeniYoklama;
    Button btnAyarlar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egitmen);
        yeniYoklama();
        YoklamaListele();
        ayarlar();
    }
    public void YoklamaListele(){
        btnYoklamaListele = (Button)findViewById(R.id.btnListele);
        btnYoklamaListele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EgitmenActivity.this,ListelemeActivity.class);
                startActivity(intent);
            }
        });
    }
    public void yeniYoklama(){
        btnYeniYoklama = (Button)findViewById(R.id.btnYeni);
        btnYeniYoklama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EgitmenActivity.this,YeniYoklamaActivity.class);
                startActivity(intent);
            }
        });
    }
    public void ayarlar(){
        btnAyarlar = (Button)findViewById(R.id.btnAyar);
        btnAyarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EgitmenActivity.this,AyarlarActivity.class);
                startActivity(intent);
            }
        });
    }
}
