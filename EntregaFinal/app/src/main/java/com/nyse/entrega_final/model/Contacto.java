package com.nyse.entrega_final.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nyse.entrega_final.R;

import java.util.ArrayList;

public class Contacto extends BaseAdapter {

    private final Context context;
    private ContactosModel model;
    private ArrayList<ContactosModel> lista;

    public Contacto(Context context, ArrayList<ContactosModel> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemVIew = view;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemVIew = inflater.inflate(R.layout.itemcontacto, viewGroup, false);
        }
        TextView tv_item_Contacto = itemVIew.findViewById(R.id.tv_item_Contacto);
        model = lista.get(i);
        tv_item_Contacto.setText(model.getNombre());
        return itemVIew;
    }
}
