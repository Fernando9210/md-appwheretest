package com.fer.app.appwhere;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fer.app.appwhere.adapters.AdapterSucursales;
import com.fer.app.appwhere.model.SucursalesObj;
import com.fer.app.appwhere.remote.ApiUtils;
import com.fer.app.appwhere.remote.UserService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    UserService userService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        userService = ApiUtils.getUserService();
        getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(final GoogleMap map) {
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
                                double latitude = Double.parseDouble(objeto.getString("latitude"));
                                double longitude = Double.parseDouble(objeto.getString("longitude"));
                                LatLng point= new LatLng(latitude, longitude);
                                map.addMarker(new MarkerOptions()
                                        .position(point)
                                        .title("Prueba"));

                                CameraPosition cameraPosition = CameraPosition.builder()
                                        .target(point)
                                        .zoom(0)
                                        .build();

                                map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                //map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition1));
                            }
                            //Toast.makeText(getContext(),listaSucursales.toString(),Toast.LENGTH_SHORT).show();


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
        /*LatLng codhem = new LatLng(19.5012, -99.2205);
        LatLng codhem1 = new LatLng(19.2561426, -98.8959417);
        map.addMarker(new MarkerOptions()
                .position(codhem)
                .title("C1 CODHEM del Estado de México"));
        map.addMarker(new MarkerOptions()
                .position(codhem1)
                .title("C2 CODHEM del Estado de México"));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(codhem)
                .zoom(9)
                .build();
        CameraPosition cameraPosition1 = CameraPosition.builder()
                .target(codhem1)
                .zoom(9)
                .build();

        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition1));*/
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
                                String json = gson.toJson(sucursalesObj.getMerchants()[i]); //convert
                                String json_s = "["+String.valueOf(json)+"]";
                                JSONArray json_a = new JSONArray(json_s);
                                JSONObject objeto = json_a.getJSONObject(0);
                                String name = objeto.getString("merchantName");
                            }
                            //Toast.makeText(getContext(),listaSucursales.toString(),Toast.LENGTH_SHORT).show();


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
