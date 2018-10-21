package com.galid.gallery.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://172.30.1.33:8080/";
    private static RetrofitClient instance;

    private Retrofit retrofit;

    public static RetrofitClient getInstance(){
        if(instance == null)
            instance = new RetrofitClient();

        return instance;
    }

    // return service
    public <T> T create(Class<T> type){
        return (T)retrofit.create(type);
    }

    private RetrofitClient() {
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

}
