package com.example.myapplication;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    TextView txt_home_creditos,txt_home_nombreUsuario;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    String url_Base="https://v3.football.api-sports.io";
    String token="f988362117244bebd630ad07d347e028";
    ArrayList<Partido> listaPartidos;
    RecyclerView rv1;
    AdaptadorUsuario adaptadorUsuario;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_home, container, false);
        String correo = getArguments().getString("correo");
        txt_home_creditos= root.findViewById(R.id.txt_home_creditos);
        txt_home_nombreUsuario= root.findViewById(R.id.txt_home_nombreUsuario);
           listaPartidos=new ArrayList<>();
        rv1=root.findViewById(R.id.recicle_home_partidos);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getContext());
        rv1.setLayoutManager(linearLayoutManager);
        adaptadorUsuario= new AdaptadorUsuario();
        rv1.setAdapter(adaptadorUsuario);
        try {
            ObtenerUsuario(correo);
            MostrarPartidos("140","2022-12-30","2022-12-31","2022");

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return  root;
    }

    private void MostrarPartidos(final String id_league, final String date_min, final String date_max, final String season) throws JSONException {


        String  url = url_Base+"/fixtures?"+"season="+season+"&league="+id_league+"&from="+date_min+"&to="+date_max;

        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=  new JSONObject(response);
                    String v = jsonObject.get("response").toString();

                        JSONArray arreglo = new JSONArray(v);
                   // for (int i=0;i<4;i++) {
                        JSONObject objetoGeneral = new JSONObject(arreglo.get(1).toString());
                        JSONObject objetoFixture = new JSONObject(objetoGeneral.getString("fixture"));
                        JSONObject objetoLiga = new JSONObject(objetoGeneral.getString("league"));
                        JSONObject objetoTeams = new JSONObject(objetoGeneral.getString("teams"));
                        JSONObject objetoGoals = new JSONObject(objetoGeneral.getString("goals"));

                        JSONObject objetoVenue = new JSONObject(objetoFixture.getString("venue"));
                        JSONObject objetoStatus = new JSONObject(objetoFixture.getString("status"));
                        JSONObject objetoLocal = new JSONObject(objetoTeams.getString("home"));
                        JSONObject objetoVisitante = new JSONObject(objetoTeams.getString("away"));




                        int idFixture = objetoFixture.getInt("id");
                        String timeStamp = objetoFixture.getString("timestamp");
                        int idVenue = objetoVenue.getInt("id");
                        String nameVenue = objetoVenue.getString("name");
                        String longStatus = objetoStatus.getString("long");
                        int idLiga = objetoLiga.getInt("id");
                        String nameLiga = objetoLiga.getString("name");
                        int idLocal = objetoLocal.getInt("id");
                        String nameLocal = objetoLocal.getString("name");
                        String logoLocal = objetoLocal.getString("logo");
                        boolean estadoLocal=false ;
                            try {
                                estadoLocal = objetoLocal.getBoolean("winner");
                            }catch ( JSONException e){
                                estadoLocal =false;

                            }


                            int idVisitante = objetoVisitante.getInt("id");
                            String nameVisitante = objetoVisitante.getString("name");
                            String logoVisitante = objetoVisitante.getString("logo");

                            boolean estadoVisitante=false ;
                            try {
                                estadoVisitante = objetoVisitante.getBoolean("winner");
                            }catch ( JSONException e){
                                estadoLocal =false;

                            }


                    int golesLocal = objetoGoals.getInt("home");
                            int golesoVisitante = objetoGoals.getInt("away");

                            Partido p = new Partido(idFixture, timeStamp, idVenue, nameVenue, longStatus, idLiga, nameLiga, idLocal, nameLocal, logoLocal, estadoLocal, idVisitante, nameVisitante, logoVisitante, estadoVisitante, golesLocal, golesoVisitante);

                            listaPartidos.add(p);
                            adaptadorUsuario.notifyItemRangeInserted(listaPartidos.size(), 1);
                            //}


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

    private void ObtenerUsuario(String correo){
        String  url = "https://7jnsf5isq8.execute-api.us-east-1.amazonaws.com/usuario/obtener/"+correo;
        StringRequest postResquest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    try {
                        JSONObject objeto =  jsonObject.getJSONObject("data");
                        txt_home_nombreUsuario.setText("Hola, "+objeto.getString("nombre_usuario"));
                        txt_home_creditos.setText(objeto.getString("creditos"));

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


    private class AdaptadorUsuario extends  RecyclerView.Adapter<AdaptadorUsuario.AdaptadorUsuarioHolder>{
        @NonNull
        @Override
        public AdaptadorUsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AdaptadorUsuarioHolder(getLayoutInflater().inflate(R.layout.layout_tarjeta_partidos,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull AdaptadorUsuarioHolder holder, int position) {
            holder.imprimir(position);
        }

        @Override
        public int getItemCount() {
            return listaPartidos.size();
        }

        class AdaptadorUsuarioHolder extends  RecyclerView.ViewHolder{
            TextView txt_partido_fecha,txt_partido_hora,txt_partido_local,txt_partido_visitante;
            Button bton_partido_detalles;
            ImageView img_partido_local,img_partido_visitante;

            public AdaptadorUsuarioHolder(@NonNull View itemView) {
                super(itemView);
                txt_partido_fecha=itemView.findViewById(R.id.txt_partido_fecha);
                txt_partido_hora=itemView.findViewById(R.id.txt_partido_hora);
                txt_partido_local=itemView.findViewById(R.id.txt_partido_local);
                txt_partido_visitante=itemView.findViewById(R.id.txt_partido_visitante);
                bton_partido_detalles=itemView.findViewById(R.id.bton_partido_detalles);
                img_partido_local=itemView.findViewById(R.id.img_partido_local);
                img_partido_visitante=itemView.findViewById(R.id.img_partido_visitante);



            }

            public void imprimir(int position) {
                    txt_partido_visitante.setText(listaPartidos.get(position).getAway_name());
                    txt_partido_local.setText(listaPartidos.get(position).getHome_name());
                    txt_partido_fecha.setText(listaPartidos.get(position).getTimestamp_fixture());
                    txt_partido_hora.setText(listaPartidos.get(position).getTimestamp_fixture());
                    recuperarImagen(listaPartidos.get(position).getHome_logo(),img_partido_local);
                    recuperarImagen(listaPartidos.get(position).getAway_logo(),img_partido_visitante);
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
    }
}