package com.example.harun.yoklama.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.harun.yoklama.Interfaces;
import com.example.harun.yoklama.Model.Info;
import com.example.harun.yoklama.R;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by onur on 13.06.2016.
 */
public class AyarlarActivity  extends AppCompatActivity {
    EditText sfrYeni, sfrTekrar;
    Button btnKaydet;
    Intent intent;
    private RestAdapter restAdapter;
    private Interfaces updateInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayarlar);
        sfrYeni = (EditText) findViewById(R.id.sfrYeni);
        sfrTekrar = (EditText) findViewById(R.id.sfrTekrar);
        btnKaydet = (Button) findViewById(R.id.btnKaydet);
        intent = new Intent(AyarlarActivity.this, EgitmenActivity.class);
        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://snncnaydn.com/yoklama")
                        .build();
                updateInterface = restAdapter.create(Interfaces.class);
                if(sfrYeni.getText().toString().equals(sfrTekrar.getText().toString())) {
                    updateInterface.updateUser(String.valueOf(Statikler.kid), sfrYeni.getText().toString(), new Callback<Info>() { // json array döneceği için modelimizi array tipinde belirledik
                        @Override
                        public void success(Info model, Response response) {
                            Info info = new Info();
                            info.setStatus(model.getStatus());
                            info.setInfo(model.getInfo());
                            startActivity(intent);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            String merror = error.getMessage();
                            Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
                        }
                    });
                }else
                    Toast.makeText(null,"Şifreler birbiriyle uyuşmuyor",Toast.LENGTH_LONG).show();
            }
        });
    }

}
