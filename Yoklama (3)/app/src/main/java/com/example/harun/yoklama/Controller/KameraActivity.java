package com.example.harun.yoklama.Controller;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harun.yoklama.CameraPreview;
import com.example.harun.yoklama.Controller.Statikler;
import com.example.harun.yoklama.Interfaces;
import com.example.harun.yoklama.Model.InfoDers;
import com.example.harun.yoklama.Model.InfoOgrenci;
import com.example.harun.yoklama.Model.InfoYoklamaListesi;
import com.example.harun.yoklama.R;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class KameraActivity extends Activity {

    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;
    TextView scanText;
    Button scanButton;
    ImageScanner scanner;
    private boolean barcodeScannned = false;
    private boolean previewing = true;
    private RestAdapter restAdapter;
    private Interfaces yoklamaInterface;

    static {
        System.loadLibrary("iconv");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamera);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();
        mCamera = getCameraInstance();

        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        dersleriGetir();


        mPreview = new CameraPreview(this, mCamera, previeCb, autoFocusCB);
        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
        preview.addView(mPreview);
        scanText = (TextView) findViewById(R.id.scanText);
    }

    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();

        } catch (Exception e) {

        }
        return c;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    PreviewCallback previeCb = new PreviewCallback() {
        @Override
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Parameters parameters = camera.getParameters();
            Size size = parameters.getPreviewSize();

            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);

            int result = scanner.scanImage(barcode);

            if (result != 0) {

                SymbolSet syms = scanner.getResults();
                for (Symbol sym : syms) {

                    Statikler.ogrNo = sym.getData();
                    ogrIdGetir();
                    yoklamaAl();
                    scanText.setText(sym.getData() + " Burada ");
                    scanText.setTextColor(Color.parseColor("#00AF03"));
                    barcodeScannned = true;

                    Intent intent = new Intent();
                    intent.putExtra("SCAN_RESULT", sym.getData());
                    setResult(RESULT_OK, intent);
                    releaseCamera();
                }
            }
        }

    };
    AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
        @Override
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };


    public void ogrIdGetir() {


        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://snncnaydn.com/yoklama")
                .build();
        yoklamaInterface = restAdapter.create(Interfaces.class);
        yoklamaInterface.getJsonOgrenciler("all", "ogrenci", new Callback<InfoOgrenci>() { // json array döneceği için modelimizi array tipinde belirledik
            @Override
            public void success(InfoOgrenci model, Response response) {
                InfoOgrenci info = new InfoOgrenci();
                info.setStatus(model.getStatus());
                info.setInfo(model.getInfo());
                for (int i = 0; i < model.getInfo().size(); i++) {
                    if (model.getInfo().get(i).getOgrno().equals(Statikler.ogrNo)) {
                        Statikler.yoklamaOgrenciID = model.getInfo().get(i).getId();
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

    public void dersleriGetir() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://snncnaydn.com/yoklama")
                .build();
        yoklamaInterface = restAdapter.create(Interfaces.class);
        yoklamaInterface.getJsonDersler("all", "ders", new Callback<InfoDers>() { // json array döneceği için modelimizi array tipinde belirledik
            @Override
            public void success(InfoDers model, Response response) {
                InfoDers info = new InfoDers();
                info.setStatus(model.getStatus());
                info.setInfo(model.getInfo());
                for (int i = 0; i < model.getInfo().size(); i++) {
                    if (model.getInfo().get(i).getDersadi().equals(String.valueOf(Statikler.yoklamaDersAdi))) {
                        Statikler.yoklamaDersId = model.getInfo().get(i).getId().toString();
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

    public void yoklamaAl() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date simdikiZaman = new Date();
        String tarih = df.format(simdikiZaman);
        restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://snncnaydn.com/yoklama")
                .build();
        yoklamaInterface = restAdapter.create(Interfaces.class);
        yoklamaInterface.getYoklama(Statikler.yoklamaDersId, String.valueOf(Statikler.kid), "var",
                Statikler.yoklamaOgrenciID, tarih, new Callback<InfoYoklamaListesi>() {
                    @Override
                    public void success(InfoYoklamaListesi infoYoklamaListesi, Response response) {
                        InfoYoklamaListesi info = new InfoYoklamaListesi();
                        info.setStatus(infoYoklamaListesi.getStatus());
                        info.setInfo(infoYoklamaListesi.getInfo());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        String merror = error.getMessage();
                        Toast.makeText(getApplicationContext(), merror, Toast.LENGTH_LONG).show();
                    }
                });
    }
}
