package com.galid.gallery.rest;

import com.galid.gallery.model.PhotoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhotoService {

    @GET("/animal/all")
    Call<List<PhotoModel>> getAllAnimals();

}
