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

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity {
    EditText user, pass;
    Button btnLogin;
    private RestAdapter restAdapter;
    private Interfaces loginInterface;
    List<Info> liste;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        intent = new Intent(LoginActivity.this, EgitmenActivity.class);
        liste = new ArrayList<>();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://snncnaydn.com/yoklama")
                        .build();
                loginInterface = restAdapter.create(Interfaces.class);
                loginInterface.getJsonValues("all", "kullanici", new Callback<Info>() { // json array döneceği için modelimizi array tipinde belirledik
                    @Override
                    public void success(Info model, Response response) {
                        Info info = new Info();
                        info.setStatus(model.getStatus());
                        info.setInfo(model.getInfo());
                        liste.add(model);
                        if(Login()){
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Kullanıcı Adı veya Parola Hatalı", Toast.LENGTH_LONG).show();
                        }
                        liste.clear();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        String merror = error.getMessage();
                        Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    public boolean Login() {
        boolean a = false;
        for(int i = 0; i <=liste.size()+1;i++){
            if(user.getText().toString().equals(liste.get(0).getInfo().get(i).getKadi().toString())
                    && pass.getText().toString().equals(liste.get(0).getInfo().get(i).getPass().toString())){
                Statikler.kid=Integer.parseInt(liste.get(0).getInfo().get(i).getId());
                return true;

            }
        }
        return a;

    }
}
