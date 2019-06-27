package com.fer.app.appwhere;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fer.app.appwhere.adapters.AdapterSucursales;
import com.fer.app.appwhere.model.SucursalesObj;
import com.fer.app.appwhere.remote.ApiUtils;
import com.fer.app.appwhere.remote.UserService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SucursalesFragment extends Fragment {

    UserService userService;
    ArrayList<String> listaSucursales;
    ArrayList<String> direccionesSucursales;
    ArrayList<String> telefonosSucursales;
    RecyclerView recycler;
    public SucursalesFragment() {
        // Required empty public constructor
        userService = ApiUtils.getUserService();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sucursales, null);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getSucursales();
        super.onActivityCreated(savedInstanceState);
        recycler = (RecyclerView) getActivity().findViewById(R.id.reciclerId);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recycler.setHasFixedSize(true);
        listaSucursales = new ArrayList<>();
        direccionesSucursales = new ArrayList<>();
        telefonosSucursales = new ArrayList<>();

    }
    private void getSucursales(){
        Call<SucursalesObj> call = userService.onRegisterMerchant();
        call.enqueue(new Callback<SucursalesObj>(){
            @Override
            public void onResponse(Call<SucursalesObj> call, Response<SucursalesObj> response) {
                if(response.isSuccessful()){
                    SucursalesObj sucursalesObj = response.body();
                    if(sucursalesObj.getMerchants() != null){

                        try {
                            for(int i=0;i<sucursalesObj.getMerchants().length;i++){
                            Gson gson = new Gson();
                            String json = gson.toJson(sucursalesObj.getMerchants()[i]);
                            String json_s = "["+String.valueOf(json)+"]";
                            JSONArray json_a = new JSONArray(json_s);
                            JSONObject objeto = json_a.getJSONObject(0);
                            String name = objeto.getString("merchantName");
                                String dir = objeto.getString("merchantAddress");
                                String phone = objeto.getString("merchantTelephone");
                                listaSucursales.add(name);
                                direccionesSucursales.add(dir);
                                telefonosSucursales.add(phone);
                            }
                            //Toast.makeText(getContext(),listaSucursales.toString(),Toast.LENGTH_SHORT).show();
                            AdapterSucursales adapter =new AdapterSucursales(listaSucursales, direccionesSucursales, telefonosSucursales);
                            recycler.setAdapter(adapter);
                            recycler.getAdapter().notifyDataSetChanged();

                        }catch (Exception e){
                            Toast.makeText(getContext(),"El error es "+ e,Toast.LENGTH_SHORT).show();
                        }

                    }
                    else Toast.makeText(getContext(),"No existen sucursales ",Toast.LENGTH_SHORT).show();

                }
                else Toast.makeText(getContext(),"A ocurrido un error, intentelo de nuevo",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SucursalesObj> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

}