package com.example.semana10;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.semana10.Clases.Pokemon;
import com.example.semana10.service.pokemonService;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonRegistro extends AppCompatActivity {

    private static final int OPEN_CAMERA_REQUEST = 1001;
    private static final int OPEN_GALLERY_REQUEST = 1002;

    private String fotoEnBase64;
    private Bitmap photo;


    private String imagen;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == OPEN_CAMERA_REQUEST && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            fotoEnBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

            Retrofit imgRetro = new Retrofit.Builder()
                    .baseUrl("https://demo-upn.bit2bittest.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            pokemonService.ImageToSave imageToSave = new pokemonService.ImageToSave(fotoEnBase64);

            pokemonService imageService = imgRetro.create(pokemonService.class);

            Call<pokemonService.ImageResponse> imgC = imageService.saveImage(imageToSave);

            imgC.enqueue(new Callback<pokemonService.ImageResponse>() {
                @Override
                public void onResponse(Call<pokemonService.ImageResponse> call, Response<pokemonService.ImageResponse> response) {
                    if(response.isSuccessful()){
                        System.out.println(response.body().getUrl());
                        imagen = "https://demo-upn.bit2bittest.com" + response.body().getUrl();
                    }
                    else
                        Log.i("MAIN_APP", "No se subiÃ³");
                }

                @Override
                public void onFailure(Call<pokemonService.ImageResponse> call, Throwable t) {

                }
            });

        }

        if(requestCode == OPEN_GALLERY_REQUEST && resultCode == RESULT_OK) {
            Uri selectedImage = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);


                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                fotoEnBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                Retrofit imgRetro = new Retrofit.Builder()
                        .baseUrl("https://demo-upn.bit2bittest.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                pokemonService.ImageToSave imageToSave = new pokemonService.ImageToSave(fotoEnBase64);

                pokemonService imageService = imgRetro.create(pokemonService.class);

                Call<pokemonService.ImageResponse> imgC = imageService.saveImage(imageToSave);

                imgC.enqueue(new Callback<pokemonService.ImageResponse>() {
                    @Override
                    public void onResponse(Call<pokemonService.ImageResponse> call, Response<pokemonService.ImageResponse> response) {
                        if(response.isSuccessful()){
                            System.out.println(response.body().getUrl());
                            imagen = "https://demo-upn.bit2bittest.com" + response.body().getUrl();
                        }
                        else
                            System.out.println("No subio");
                    }

                    @Override
                    public void onFailure(Call<pokemonService.ImageResponse> call, Throwable t) {

                    }
                });


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_registro);

        Button registrarP = findViewById(R.id.btnRegistrarP);
        Button volverP = findViewById(R.id.btnVolverP);
        Button camaraP = findViewById(R.id.btncamara);
        Button galeriaP = findViewById(R.id.btngaleria);


        EditText regNom = findViewById(R.id.etNombreP);
        EditText regTipo = findViewById(R.id.etTipoP);
        EditText regFot = findViewById(R.id.etPhotoP);

        volverP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(PokemonRegistro.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        camaraP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOpenCamera();
            }
        });
        galeriaP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
                else {
                    String[] permissions = new String[] {Manifest.permission.READ_EXTERNAL_STORAGE};
                    requestPermissions(permissions, 2000);
                }
            }
        });
        registrarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = regNom.getText().toString();
                String tipo = regTipo.getText().toString();
                String pokedex = regFot.getText().toString();

               // String imagen = "https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + regFot.getText().toString() + ".png";

                if (!nombre.isEmpty() && !imagen.isEmpty() && !tipo.isEmpty()) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://6477e051362560649a2d045f.mockapi.io/Lisa/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
                    pokemonService service = retrofit.create(pokemonService.class);

                    Pokemon pok = new Pokemon();
                    pok.setNombre(nombre);
                    pok.setPokedex(pokedex);
                    pok.setTipo(tipo);
                    pok.setImagen(imagen);

                    Call<Pokemon> call = service.create(pok);

                    call.enqueue(new Callback<Pokemon>() {
                        @Override
                        public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                            Intent intent =  new Intent(PokemonRegistro.this, MenuActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Pokemon> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }



    private void handleOpenCamera() {
        if(checkSelfPermission(Manifest.permission.CAMERA)  == PackageManager.PERMISSION_GRANTED)
        {
            // abrir camara
            Log.i("MAIN_APP", "Tiene permisos para abrir la camara");
            abrirCamara();
        } else {
            // solicitar el permiso
            Log.i("MAIN_APP", "No tiene permisos para abrir la camara, solicitando");
            String[] permissions = new String[] {Manifest.permission.CAMERA};
            requestPermissions(permissions, 1000);
        }
    }

    private void abrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, OPEN_CAMERA_REQUEST);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_GALLERY_REQUEST);
    }
}