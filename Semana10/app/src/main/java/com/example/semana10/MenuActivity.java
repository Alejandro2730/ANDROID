package com.example.semana10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button reg = findViewById(R.id.bttnRegistrarP);
        Button mos = findViewById(R.id.bttnMostrarP);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MenuActivity.this, PokemonRegistro.class);
                startActivity(intent);
                finish();
            }
        });
        mos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(MenuActivity.this, ListadoPokemonActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}