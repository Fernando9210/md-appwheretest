package com.fer.app.appwhere.remote;

import com.fer.app.appwhere.model.MerchantsObj;
import com.fer.app.appwhere.model.ResponseObj;
import com.fer.app.appwhere.model.SucursalesObj;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Fer on 23/06/2019.
 */

public interface UserService {
   @GET("api/session/login")
    Call<ResponseObj> onLogIn(@Query("email") String email, @Query("password") String password);

    @GET("get-merchants")
    Call<SucursalesObj> onRegisterMerchant();

    @POST("register-merchant")
    Call<MerchantsObj> createMerchants(@Body MerchantsObj merchantsObj);
}


