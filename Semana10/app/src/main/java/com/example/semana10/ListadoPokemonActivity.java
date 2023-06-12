package com.example.semana10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.semana10.Clases.Pokemon;
import com.example.semana10.adapters.pokemonAdapter;
import com.example.semana10.service.pokemonService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListadoPokemonActivity extends AppCompatActivity {

    private List<Pokemon> pokemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_pokemon);
        Button regresarP = findViewById(R.id.btnRegresarP);

      regresarP.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(ListadoPokemonActivity.this, MenuActivity.class);
              startActivity(intent);
          }
      });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6477e051362560649a2d045f.mockapi.io/Lisa/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

        pokemonService service = retrofit.create(pokemonService.class);

        Call<List<Pokemon>> call = service.getAllUser();

       call.enqueue(new Callback<List<Pokemon>>() {
           @Override
           public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
               if (response.isSuccessful()) {

                   pokemons = response.body();
                   pokemonAdapter adapter = new pokemonAdapter(pokemons,ListadoPokemonActivity.this);



                   RecyclerView rvLista =  findViewById(R.id.rvListaPokemon);
                   rvLista.setLayoutManager(new LinearLayoutManager(ListadoPokemonActivity.this));
                   rvLista.setAdapter(adapter);
               }else {
                   int statusCode = response.code();
                   String errorMessage = "Error: " + statusCode;

                   Toast.makeText(ListadoPokemonActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<List<Pokemon>> call, Throwable t) {

           }
       });
    }
}