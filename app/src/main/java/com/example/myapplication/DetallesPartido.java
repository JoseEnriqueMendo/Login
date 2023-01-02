package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetallesPartido#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetallesPartido extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetallesPartido() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetallesPartido.
     */
    // TODO: Rename and change types and number of parameters
    public static DetallesPartido newInstance(String param1, String param2) {
        DetallesPartido fragment = new DetallesPartido();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    TextView txt_titulo,txt_detalles_nombreliga,txt_detalles_paisliga,txt_detalles_estado,txt_detalles_nombreLocal,txt_detalles_nombrevisitante
    ,txt_detalles_golesLocal,txt_detalles_golesVisitante,txt_detalles_time,txt_detalles_referee,txt_detalles_estadionombre,txt_detalles_ciudad;
    ImageView img_detalles_local,img_detalles_visitante;
    Button btn_detalles_apostar;

    String url_Base = "https://v3.football.api-sports.io";
    String token="f988362117244bebd630ad07d347e028";
    Cuotas c;
    ArrayList<estadistica> e;
    ArrayList<Estadisticas_Detalles> EstadisticaLocal= new ArrayList<>();
    ArrayList<Estadisticas_Detalles> EstadisticaVisitante= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle datosRecuperados = getArguments();

        Partido p= datosRecuperados.getParcelable("partido");

        View root = inflater.inflate(R.layout.fragment_detalles_partido, container, false);

        txt_titulo=root.findViewById(R.id.txt_titulo);
        txt_detalles_nombreliga=root.findViewById(R.id.txt_detalles_nombreliga);
        txt_detalles_paisliga=root.findViewById(R.id.txt_detalles_paisliga);
        txt_detalles_estado=root.findViewById(R.id.txt_detalles_estado);
        txt_detalles_nombreLocal=root.findViewById(R.id.txt_detalles_nombreLocal);
        txt_detalles_nombrevisitante=root.findViewById(R.id.txt_detalles_nombrevisitante);
        txt_detalles_golesLocal=root.findViewById(R.id.txt_detalles_golesLocal);
        txt_detalles_golesVisitante=root.findViewById(R.id.txt_detalles_golesVisitante);
        img_detalles_local =root.findViewById(R.id.img_detalles_local);
        img_detalles_visitante=root.findViewById(R.id.img_detalles_visitante);
        btn_detalles_apostar=root.findViewById(R.id.btn_detalles_apostar);
        txt_detalles_time=root.findViewById(R.id.txt_detalles_time);
        txt_detalles_referee=root.findViewById(R.id.txt_detalles_referee);
        txt_detalles_estadionombre=root.findViewById(R.id.txt_detalles_estadionombre);
        txt_detalles_ciudad=root.findViewById(R.id.txt_detalles_ciudad);

        txt_detalles_nombreLocal.setText(p.getHome_name());
        txt_detalles_nombreliga.setText(p.getName_league());
        txt_detalles_golesLocal.setText(String.valueOf(p.getHome_goals()));
        txt_detalles_golesVisitante.setText(String.valueOf(p.getAway_goals()));
        String t= String.valueOf(p.getTime_fixture());
          txt_detalles_time.setText(t+"'");
        txt_detalles_paisliga.setText(p.getPais_league());
        txt_detalles_nombrevisitante.setText(p.getAway_name());
       txt_detalles_estado.setText(p.getStatus_long());
       txt_detalles_ciudad.setText("Ciudad: "+p.getCiudad());
        txt_detalles_referee.setText("Arbitro: "+p.getReferee());
        txt_detalles_estadionombre.setText("Estadio: "+p.getVenue_name());
        recuperarImagen(p.getHome_logo(),img_detalles_local);
        recuperarImagen(p.getAway_logo(),img_detalles_visitante);
        try {
            ObtenerPredicciones(String.valueOf(p.getId_fixture()));
            if(Objects.equals(p.getStatus_long(), "Not Started")  ){
                Toast.makeText(getContext(), "No hay estadisticas", Toast.LENGTH_LONG).show();
            }else
            ObtenerEstadisticas(String.valueOf(p.getId_fixture()));

        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
       // Toast.makeText(getContext(), c.toString(), Toast.LENGTH_LONG).show();

        return root;
    }

    private void ObtenerEstadisticas(final String id_fixture) throws JSONException {



        String url = url_Base+"/fixtures/statistics?"+"fixture="+id_fixture;

        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=  new JSONObject(response);
                    String v = jsonObject.get("response").toString();
                    JSONArray arreglo = new JSONArray(v);

                    JSONObject objetoGeneral = new JSONObject(arreglo.get(0).toString());

                    String objetoEstadistica = objetoGeneral.get("statistics").toString();
                    JSONArray arregloEstadistica = new JSONArray(objetoEstadistica);

                    for(int i=0;i<arregloEstadistica.length();i++){

                        JSONObject objetoGeneralEstadistica = new JSONObject(arregloEstadistica.get(i).toString());
                        String type = objetoGeneralEstadistica.getString("type");
                        String value="";
                        try {
                             value=objetoGeneralEstadistica.getString("value");
                        }catch ( JSONException e){
                            value="0";

                        }
                        Estadisticas_Detalles d=new Estadisticas_Detalles(type,value);
                        EstadisticaLocal.add(d);
                    }

                     Toast.makeText(getContext(), EstadisticaLocal.get(0).type, Toast.LENGTH_SHORT).show();

                     objetoGeneral = new JSONObject(arreglo.get(1).toString());

                     objetoEstadistica = objetoGeneral.get("statistics").toString();
                     arregloEstadistica = new JSONArray(objetoEstadistica);

                    for(int i=0;i<arregloEstadistica.length();i++){

                        JSONObject objetoGeneralEstadistica = new JSONObject(arregloEstadistica.get(i).toString());
                        String type = objetoGeneralEstadistica.getString("type");
                        String value="";
                        try {
                            value=objetoGeneralEstadistica.getString("value");
                        }catch ( JSONException e){
                            value="0";

                        }
                        Estadisticas_Detalles d=new Estadisticas_Detalles(type,value);
                        EstadisticaVisitante.add(d);
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
        }){

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-apisports-key", token);
                return headers;
            }};

        Volley.newRequestQueue(getContext()).add(postResquest);


    }

    private void ObtenerPredicciones(final String id_fixture) throws JSONException {



        String url = url_Base+"/predictions?"+"fixture="+id_fixture;

        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                        double cuotaLocal=0;
                        double cuotavisitante=0;
                        try {
                            String v = jsonObject.get("response").toString();
                            JSONArray arreglo = new JSONArray(v);
                            JSONObject objetoGeneral = new JSONObject(arreglo.get(0).toString());
                            JSONObject objetoPrediciones = new JSONObject(objetoGeneral.getString("predictions"));
                            JSONObject objetoCuota = new JSONObject(objetoPrediciones.getString("goals"));
                            cuotaLocal= Double.parseDouble(objetoCuota.getString("home")) * -1;
                             cuotavisitante = Double.parseDouble(objetoCuota.getString("away")) * -1;
                        }catch ( NumberFormatException e){
                            cuotaLocal=0;
                           cuotavisitante=0;
                        }



                        c = new Cuotas(cuotaLocal, cuotavisitante);

                        Toast.makeText(getContext(),c.toString(), Toast.LENGTH_SHORT).show();



                    } catch(JSONException e){
                        throw new RuntimeException(e);
                    }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.getMessage());
            }
        }){

            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-apisports-key", token);
                return headers;
            }};

        Volley.newRequestQueue(getContext()).add(postResquest);


    }

    private void recuperarImagen(String foto, ImageView iv) {

        ImageRequest peticion= new ImageRequest(foto, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                iv.setImageBitmap(response);
            }

        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(getContext()).add(peticion);
    }



}