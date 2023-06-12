package com.example.semana10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvMessage;
    private List<Integer> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        tvMessage = this.findViewById(R.id.txtregistropke);
        //TextView tvSum = this.findViewById(R.id.);

        Button btn1 = this.findViewById(R.id.Txtpoke);

        Button btnCalc = this.findViewById(R.id.txtregistropke);



        btnCalc.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), listadeAnime.class);
            startActivity(intent);

        });

    }


}