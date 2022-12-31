package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Registro extends AppCompatActivity {

    EditText txt_registro_sexo,txt_registro_contra,txt_registro_nombres,txt_registro_nombreUsuario,txt_registro_dni,txt_registro_correo,txt_registro_fecha,txt_registro_celular;
    Button bton_Registro_registrarse;
    ImageButton bton_registro_volver;
    String mensaje="Erro";
    boolean estado=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txt_registro_correo= findViewById(R.id.txt_registro_correo);
        txt_registro_contra=findViewById(R.id.txt_registro_contra);
        txt_registro_sexo=findViewById(R.id.txt_registro_sexo);
        txt_registro_nombreUsuario =findViewById(R.id.txt_registro_nombreUsuario);
        txt_registro_nombres =findViewById(R.id.txt_registro_nombres);
        txt_registro_dni  =findViewById(R.id.txt_registro_dni);
        txt_registro_fecha =findViewById(R.id.txt_registro_fecha);
        txt_registro_celular=findViewById(R.id.txt_registro_celular);
        bton_Registro_registrarse= findViewById(R.id.bton_Registro_registrarse);
        bton_registro_volver= findViewById(R.id.bton_registro_volver);
        bton_Registro_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Login(txt_registro_correo.getText().toString(),txt_registro_contra.getText().toString(),
                    txt_registro_fecha.getText().toString(),txt_registro_sexo.getText().toString(),
                    txt_registro_nombres.getText().toString(),txt_registro_nombreUsuario.getText().toString(),
                    txt_registro_celular.getText().toString(),txt_registro_dni.getText().toString());
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        bton_registro_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarLogin();
            }
        });

    }

    public void cambiarLogin(){
        Intent i= new Intent(Registro.this,Login.class);
        startActivity(i);
    }



    private void Login(final String correo, final String  contra,final String  fecha,final String  sexo,final String  nombre,final String  nombreUsuario,final String  celular,final String  dni) throws JSONException{
        estado=false;
        mensaje="Request fallido";
        String  url = "https://7jnsf5isq8.execute-api.us-east-1.amazonaws.com/usuario/registrar";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("correo",correo);
        jsonObject.put("nombre",nombre);
        jsonObject.put("nombre_usuario",nombreUsuario);
        jsonObject.put("fecha_de_nacimiento",fecha);
        jsonObject.put("dni",dni);
        jsonObject.put("genero",sexo);
        jsonObject.put("celular",celular);
        jsonObject.put("Contraseña",contra);
        final String requestBody = jsonObject.toString();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("NotConstructor")
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            mensaje=response.get("message").toString();
                            estado=response.getBoolean("success");
                            Toast.makeText(Registro.this, mensaje, Toast.LENGTH_LONG).show();
                            if(estado){

                                cambiarLogin();
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //handle the error
                Toast.makeText(Registro.this, "An error occurred", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);
    }




}