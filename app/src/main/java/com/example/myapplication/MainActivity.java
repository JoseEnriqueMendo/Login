package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText txt_correo, txt_contra;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_correo= findViewById(R.id.txt_correo);
        txt_contra=findViewById(R.id.txt_contra);
        btn_login= findViewById(R.id.btn_login);
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
    }

    private void ObtenerUsuario(String correo){
    String  url = "https://7jnsf5isq8.execute-api.us-east-1.amazonaws.com/usuario/obtener/"+correo;
        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    txt_correo.setText(jsonObject.getString("message"));
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

    private void Login(final String correo, final String  contra) throws JSONException{

        String  url = "https://7jnsf5isq8.execute-api.us-east-1.amazonaws.com/usuario/iniciarSesión";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("correo",correo);
        jsonObject.put("Contraseña",contra);
        final String requestBody = jsonObject.toString();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //realiza procedimiento
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //handle the error
                Toast.makeText(MainActivity.this, "An error occurred", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);
    }




}