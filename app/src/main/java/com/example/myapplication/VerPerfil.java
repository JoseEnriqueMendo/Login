package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class VerPerfil extends AppCompatActivity {



    TextView txt_correo1, txt_nombre,txt_creditos1,txt_fecha,txt_nombreUsuario,txt_celular,txt_dni,txt_genero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_ver_perfil);
        txt_nombre= findViewById(R.id.txt_nombre);
        txt_correo1= findViewById(R.id.txt_correo1);
        txt_celular= findViewById(R.id.txt_celular);
        txt_creditos1= findViewById(R.id.txt_creditos1);
        txt_fecha= findViewById(R.id.txt_fecha);
        txt_genero= findViewById(R.id.txt_genero);
        txt_nombreUsuario= findViewById(R.id.txt_nombreUsuario);
        txt_dni= findViewById(R.id.txt_dni);


        Bundle miBundle = this.getIntent().getExtras();
        if (miBundle != null) {
            String correo= miBundle.getString("correo");
            ObtenerUsuario(correo);
        }else {
            Toast.makeText(VerPerfil.this, "no hay parametros", Toast.LENGTH_LONG).show();
        }


    }


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



}