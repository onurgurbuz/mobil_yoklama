package com.example.harun.yoklama.Controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harun.yoklama.Controller.YoklamaOgrenci;
import com.example.harun.yoklama.R;

import java.util.List;

/**
 * Created by harun on 5/28/16.
 */
public class YoklamaDetayAdapter extends BaseAdapter {




    private LayoutInflater mInflater;
    private List<YoklamaOgrenci> mYoklamaDetay;

    public YoklamaDetayAdapter(Activity activity, List<YoklamaOgrenci> ogrenci) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        mYoklamaDetay = ogrenci;
    }


    @Override
    public int getCount() {
        return mYoklamaDetay.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View satirView;

        satirView = mInflater.inflate(R.layout.yoklama_detay_adapter, null);
        TextView txt_ad =
                (TextView) satirView.findViewById(R.id.txt_ad);
        TextView txt_numara =
                (TextView) satirView.findViewById(R.id.txt_numara);
        ImageView imageView =
                (ImageView) satirView.findViewById(R.id.imgOgrenci);

        YoklamaOgrenci ogr = mYoklamaDetay.get(position);

        txt_ad.setText(ogr.getAd());
        txt_numara.setText(ogr.getNumara());
        imageView.setImageResource(R.mipmap.ogrenci);

        return satirView;
    }
}
