package com.example.harun.yoklama.Controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harun.yoklama.Controller.YoklamaListesi1;
import com.example.harun.yoklama.R;

import java.util.List;

/**
 * Created by harun on 5/27/16.
 */
public class Liste_Adapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<YoklamaListesi1> mYoklamaListesi1;

    public Liste_Adapter(Activity activity, List<YoklamaListesi1> yoklamalar) {
        //XML'i alıp View'a çevirecek inflater'ı örnekleyelim
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        //gösterilecek listeyi de alalım
        mYoklamaListesi1 = yoklamalar;
    }

    @Override
    public int getCount() {
        return mYoklamaListesi1.size();
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

        satirView = mInflater.inflate(R.layout.liste_adapter, null);
        TextView txt_dersAdi =
                (TextView) satirView.findViewById(R.id.txt_ders);
        TextView txt_tarih =
                (TextView) satirView.findViewById(R.id.txt_tarih);
        ImageView imageView =
                (ImageView) satirView.findViewById(R.id.simge);

        YoklamaListesi1 yoklama = mYoklamaListesi1.get(position);

        txt_dersAdi.setText(yoklama.getDersAdi());
        txt_tarih.setText(yoklama.getTarih());
        imageView.setImageResource(R.mipmap.yoklamalar);

        return satirView;
    }

}
