package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    EditText txt_correo, txt_contra;
    Button btn_login,bton_Registrarse;

    String correoG;
    String token;
    String mensaje="Erro";
    boolean estado=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_correo= findViewById(R.id.txt_correo);
        txt_contra=findViewById(R.id.txt_contra);
        btn_login= findViewById(R.id.btn_login);
        bton_Registrarse=findViewById(R.id.bton_Registrarse);
    btn_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //ObtenerUsuario("dinofiesta123@gmail.com");
            try {
                Login(txt_correo.getText().toString(),txt_contra.getText().toString());

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    });

        bton_Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ObtenerUsuario("dinofiesta123@gmail.com");

                    cambiarRegistro();


            }
        });



    }



    private void Login(final String correo, final String  contra) throws JSONException{
        estado=false;
        mensaje="Request fallido";
        String  url = "https://7jnsf5isq8.execute-api.us-east-1.amazonaws.com/usuario/iniciarSesión";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("correo",correo);
        jsonObject.put("Contraseña",contra);
        final String requestBody = jsonObject.toString();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @SuppressLint("NotConstructor")
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            mensaje=response.get("message").toString();
                            correoG=correo;
                            estado=response.getBoolean("success");
                            Toast.makeText(Login.this, mensaje, Toast.LENGTH_LONG).show();
                            if(estado){
                                JSONObject objeto =  response.getJSONObject("data");
                                token=objeto.getString("token");
                                cambiarNavBar(correoG);
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //handle the error
                Toast.makeText(Login.this, "An error occurred", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);
    }


    public void cambiarNavBar(final String correo){
        Intent i= new Intent(Login.this, NavBar.class);
        Bundle miBundle= new Bundle();
        miBundle.putString("correo",correo);
        i.putExtras(miBundle);
        startActivity(i);
    }


    public void cambiarRegistro(){
        Intent i= new Intent(Login.this,Registro.class);
        startActivity(i);
    }



}