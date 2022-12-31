package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerPerfil#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerPerfil extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VerPerfil() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerPerfil.
     */
    // TODO: Rename and change types and number of parameters
    public static VerPerfil newInstance(String param1, String param2) {
        VerPerfil fragment = new VerPerfil();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
TextView txt_correo1, txt_nombre,txt_creditos1,txt_fecha,txt_nombreUsuario,txt_celular,txt_dni,txt_genero;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_ver_perfil, container, false);
        String correo = getArguments().getString("correo");
        txt_nombre= root.findViewById(R.id.txt_nombre);
        txt_correo1= root.findViewById(R.id.txt_correo1);
        txt_celular= root.findViewById(R.id.txt_celular);
        txt_creditos1= root.findViewById(R.id.txt_creditos1);
        txt_fecha= root.findViewById(R.id.txt_fecha);
        txt_genero= root.findViewById(R.id.txt_genero);
        txt_nombreUsuario= root.findViewById(R.id.txt_nombreUsuario);
        txt_dni= root.findViewById(R.id.txt_dni);
        ObtenerUsuario(correo);
        return  root;
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
        Volley.newRequestQueue(getContext()).add(postResquest);
    }


}