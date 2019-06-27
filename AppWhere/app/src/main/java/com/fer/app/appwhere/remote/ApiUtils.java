package com.fer.app.appwhere.remote;

/**
 * Created by Fer on 23/06/2019.
 */

public class ApiUtils {

    public static final String BASE_URL="http://166.62.33.53:4590/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);

    }
}
