package com.fer.app.appwhere.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fer.app.appwhere.R;

import java.util.ArrayList;

/**
 * Created by Fer on 25/06/2019.
 */

public class AdapterSucursales extends RecyclerView.Adapter<AdapterSucursales.ViewHolderSucursales> {
    ArrayList<String> direccionesSucursales;
    ArrayList<String> listaSucursales;
    ArrayList<String> telefonosSucursales;

    public AdapterSucursales(ArrayList<String> listaSucursales, ArrayList<String> direccionesSucursales, ArrayList<String> telefonosSucursales) {
        this.listaSucursales = listaSucursales;
        this.direccionesSucursales = direccionesSucursales;
        this.telefonosSucursales = telefonosSucursales;
    }

    @Override
    public ViewHolderSucursales onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
        return new ViewHolderSucursales(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderSucursales holder, int position) {
       holder.asignarSucursales(listaSucursales.get(position), direccionesSucursales.get(position), telefonosSucursales.get(position));

    }

    @Override
    public int getItemCount() {
        direccionesSucursales.size();
        telefonosSucursales.size();
        return listaSucursales.size();
    }

    public class ViewHolderSucursales extends RecyclerView.ViewHolder {
        TextView nombre, direccion, telefono;
        public ViewHolderSucursales(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.nombre);
            direccion = (TextView)itemView.findViewById(R.id.direccion);
            telefono = (TextView) itemView.findViewById(R.id.telefono);
        }

        public void asignarSucursales(String nombres, String dir, String telefonos) {
            nombre.setText(nombres);
            direccion.setText(dir);
            telefono.setText(telefonos);
        }
    }
}
