package com.example.semana10.service;

import com.example.semana10.Clases.Pokemon;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface pokemonService {

    @GET("Pokemon")
    Call<List<Pokemon>> getAllUser();

    @GET("Pokemon/{id}")
    Call<Pokemon> findUser(@Path("id") int id);

    @POST("Pokemon")
    Call<Pokemon> create(@Body Pokemon user);

    @PUT("Pokemon/{id}")
    Call<Pokemon> update(@Path("id") int id, @Body Pokemon pokemon);

    @DELETE("Pokemon/{id}")
    Call<Void> delete(@Path("id") int id);

    @POST("image")
    Call<ImageResponse> saveImage(@Body ImageToSave image);

    class ImageResponse {
        @SerializedName("url")
        private String url;

        public String getUrl(){
            return url;
        }
    }
    class ImageToSave {
        String base64Image;

        public ImageToSave(String base64Image){
            this.base64Image = base64Image;
        }
    }
}
