package com.fer.app.appwhere;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fer.app.appwhere.model.MerchantsObj;
import com.fer.app.appwhere.remote.ApiUtils;
import com.fer.app.appwhere.remote.UserService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnadirFragment extends Fragment {

    EditText nombre, direccion, telefono, latitud, longitud;
    Button crear;
    UserService userService;

    public AnadirFragment() {
        // Required empty public constructor
        userService = ApiUtils.getUserService();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anadir, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        nombre = (EditText) getActivity().findViewById(R.id.nombre);
        direccion = (EditText) getActivity().findViewById(R.id.direccion);
        telefono = (EditText) getActivity().findViewById(R.id.telefono);
        latitud = (EditText) getActivity().findViewById(R.id.latitud);
        longitud = (EditText) getActivity().findViewById(R.id.longitud);
        crear = (Button) getActivity().findViewById(R.id.agregar);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMerchants();
            }
        });

    }

    private void createMerchants(){
        MerchantsObj merchantsObj = new MerchantsObj(longitud.getText().toString(),latitud.getText().toString(),direccion.getText().toString(),nombre.getText().toString(),telefono.getText().toString());
        Call<MerchantsObj> call = userService.createMerchants(merchantsObj);
        call.enqueue(new Callback<MerchantsObj>() {
            @Override
            public void onResponse(Call<MerchantsObj> call, Response<MerchantsObj> response) {
                if(response.isSuccessful()){
                    nombre.setText("");
                    direccion.setText("");
                    telefono.setText("");
                    latitud.setText("");
                    longitud.setText("");
                    Toast.makeText(getContext(),"Agregado con exito!",Toast.LENGTH_SHORT).show();
                }
                else  Toast.makeText(getContext(),"El error es "+ response.code(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MerchantsObj> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}