package com.example.semana10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.semana10.Clases.Anime;
import com.example.semana10.adapters.Animeadpter;
import com.example.semana10.service.animeservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listadeAnime extends AppCompatActivity {

    private List<Anime> animes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadeconttactos);

        Button regr = findViewById(R.id.Yaval);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6477e051362560649a2d045f.mockapi.io/Lisa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        animeservice service = retrofit.create(animeservice.class);

        Call<List <Anime> > call =service.getAllUser();
        call.enqueue(new Callback<List<com.example.semana10.Clases.Anime>>() {
            @Override
            public void onResponse(Call<List<com.example.semana10.Clases.Anime>> call, Response<List<com.example.semana10.Clases.Anime>> response) {
                animes = response.body();
                Animeadpter adapter = new Animeadpter(animes);
                RecyclerView rvLista =  findViewById(R.id.rvListaSimple);
                rvLista.setLayoutManager(new LinearLayoutManager(listadeAnime.this));
                rvLista.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<com.example.semana10.Clases.Anime>> call, Throwable t) {

            }
        });

        regr .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(listadeAnime.this, Registroactivity.class);
                startActivity(intent);
            }
        });
    }




    }