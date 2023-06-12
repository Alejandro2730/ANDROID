package com.example.semana10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.semana10.Clases.Anime;
import com.example.semana10.service.animeservice;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registroactivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*

        setContentView(R.layout.activity_registroactivity);
        EditText txtnombre = findViewById(R.id.textnombre);
        EditText txtdesc = findViewById(R.id.textdesc);
        EditText txtfot = findViewById(R.id.textofoto);

        Button txt = findViewById(R.id.ingresar);


        //Anime am = new Anime(nombre,desc,foto);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6477e051362560649a2d045f.mockapi.io/Lisa/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        animeservice service = retrofit.create(animeservice.class);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtnombre.getText().toString();
                String desc = txtdesc.getText().toString();
                String foto = txtfot.getText().toString();
                Anime am = new Anime();

                am.setNombre(nombre);
                am.setFoto(foto);
                am.setDescripcion(desc);
                System.out.println(nombre);

                Call<Anime> call =service.create(am);
                call.enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, Response<Anime> response) {
                        Intent intent =  new Intent(Registroactivity.this, listadeAnime.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {


                    }
                });
            }
        });

         */


    }
}