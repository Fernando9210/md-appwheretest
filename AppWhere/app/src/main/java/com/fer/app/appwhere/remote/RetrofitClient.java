package com.fer.app.appwhere.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Fer on 23/06/2019.
 */

public class RetrofitClient {

    public static Retrofit retrofit =null;

    public static Retrofit getClient(String url){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                       .baseUrl(url)
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();
        }
        return retrofit;
    }
}
