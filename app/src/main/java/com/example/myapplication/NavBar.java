package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavBar extends AppCompatActivity {
    FirstFragment firstFragment= new FirstFragment();
    SecondFragment secondFragment= new SecondFragment();
    ThirdFragment thirdFragment= new ThirdFragment();
    VerPerfil verPerfil=new VerPerfil();



   // TextView txt_correo1, txt_nombre,txt_creditos1,txt_fecha,txt_nombreUsuario,txt_celular,txt_dni,txt_genero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_nav_bar);
   /*     txt_nombre= findViewById(R.id.txt_nombre);
        txt_correo1= findViewById(R.id.txt_correo1);
        txt_celular= findViewById(R.id.txt_celular);
        txt_creditos1= findViewById(R.id.txt_creditos1);
        txt_fecha= findViewById(R.id.txt_fecha);
        txt_genero= findViewById(R.id.txt_genero);
        txt_nombreUsuario= findViewById(R.id.txt_nombreUsuario);
        txt_dni= findViewById(R.id.txt_dni);*/


        Bundle miBundle = this.getIntent().getExtras();
        if (miBundle != null) {
            String correo= miBundle.getString("correo");
         //  ObtenerUsuario(correo);
        }else {
            Toast.makeText(NavBar.this, "no hay parametros", Toast.LENGTH_LONG).show();
        }

        BottomNavigationView navigation=findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(firstFragment);

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener= new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                loadFragment(firstFragment);
                return true;
                case R.id.secondFragment:
                    loadFragment(secondFragment);
                    return true;
                case R.id.thirdFragment:
                    loadFragment(thirdFragment);
                    return true;
                case R.id.verPerfil:
                    loadFragment(verPerfil);
                    return  true;
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer,fragment);
        transaction.commit();
    }
/*
    private void ObtenerUsuario(String correo){
        String  url = "https://7jnsf5isq8.execute-api.us-east-1.amazonaws.com/usuario/obtener/"+correo;
        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    try {
                        JSONObject objeto =  jsonObject.getJSONObject("data");
                        txt_nombre.setText(objeto.getString("nombre"));
                        txt_correo1.setText(objeto.getString("correo"));
                        txt_celular.setText(objeto.getString("celular"));
                        txt_genero.setText(objeto.getString("genero"));
                        txt_dni.setText(objeto.getString("dni"));
                        txt_nombreUsuario.setText(objeto.getString("nombre_usuario"));
                        txt_fecha.setText(objeto.getString("fecha_de_nacimiento"));
                        txt_creditos1.setText(objeto.getString("creditos"));

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.getMessage());
            }
        });
        Volley.newRequestQueue(this).add(postResquest);
    }
*/


}