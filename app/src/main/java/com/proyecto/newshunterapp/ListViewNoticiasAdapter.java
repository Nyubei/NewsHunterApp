package com.proyecto.newshunterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewNoticiasAdapter extends BaseAdapter {

    Context context;
    ArrayList<Noticia> noticiaData;
    LayoutInflater layoutInflater;
    Noticia noticaModel;

    public ListViewNoticiasAdapter(Context context, ArrayList<Noticia> noticiaData) {
        this.context = context;
        this.noticiaData = noticiaData;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return noticiaData.size();
    }

    @Override
    public Object getItem(int pos) {
        return noticiaData.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView==null){
            rowView = layoutInflater.inflate(R.layout.activity_news,
                    null,
                    true);
        }
        //enlazar vistas
        TextView titular = rowView.findViewById(R.id.titular);
        TextView cuerpo = rowView.findViewById(R.id.cuerpo);
        TextView fecha = rowView.findViewById(R.id.fecha);

        noticaModel = noticiaData.get(pos);
        titular.setText(noticaModel.getTitular());
        cuerpo.setText(noticaModel.getCuerpoNoticia());
        fecha.setText(noticaModel.getFecha());

        return rowView;
    }
}
