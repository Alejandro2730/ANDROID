package com.example.semana10.service;

import com.example.semana10.Clases.Anime;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface animeservice {
    @GET("Anime")
    Call<List<Anime>> getAllUser();

    @GET("Anime/{id}")
    Call<Anime> findUser(@Path("id") int id);

    @POST("Anime ")
    Call<Anime> create(@Body Anime user);

    @PUT("Anime/{id}")
    Call<Anime> update(@Path("id") int id, @Body Anime anime);

    @DELETE("Anime/{id}")
    Call<Void> delete(@Path("id") int id);
}
