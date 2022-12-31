package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

public class PantallaCarga extends AppCompatActivity {

    final Handler handler = new Handler();
    Button bton_carga_continuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        bton_carga_continuar=findViewById(R.id.bton_carga_continuar);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                bton_carga_continuar.setVisibility(View.VISIBLE);
                cambiarLogin();
            }
        }, 3000);

        bton_carga_continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    cambiarLogin();


            }
        });

    }



    public void cambiarLogin(){
        Intent i= new Intent(PantallaCarga.this,Login.class);
        startActivity(i);
    }

}