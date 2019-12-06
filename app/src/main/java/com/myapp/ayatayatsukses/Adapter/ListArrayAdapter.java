package com.myapp.ayatayatsukses.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.myapp.ayatayatsukses.Model.ModelData;
import com.myapp.ayatayatsukses.R;

import java.util.ArrayList;

public class ListArrayAdapter extends ArrayAdapter<ModelData> {

    private ArrayList<ModelData> list;
    private LayoutInflater inflater;
    private int res;

    public ListArrayAdapter(Context context, int resource, ArrayList<ModelData> list) {
        super(context, resource, list);
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        MyHolder holder = null;


        if (convertView == null) {

            convertView = inflater.inflate(res, parent, false);

            holder = new MyHolder();

            holder.Nama = (TextView) convertView.findViewById(R.id.tvNamaAyat);
            holder.ID = (TextView) convertView.findViewById(R.id.tvID);

            convertView.setTag(holder);

        } else {

            holder = (MyHolder) convertView.getTag();
        }

        holder.ID.setText(list.get(position).getId());
        holder.Nama.setText(list.get(position).getNm_ayat());
       /* holder.Isi.setText("ISI_AYAT : "+list.get(position).getIsi_ayat());
        holder.Arti.setText("ARTI_AYAT : "+list.get(position).getArti_ayat());*/

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void remove(ModelData object) {
        super.remove(object);
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    static class MyHolder {

        TextView ID;
        TextView Nama;
        TextView Isi;
        TextView Arti;


    }
}
