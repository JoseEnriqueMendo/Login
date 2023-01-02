package com.example.myapplication;


import java.util.ArrayList;
import java.util.List;

public class Estadisticas {

    public int      id;
    public String   name;
    public String   logo;

    public ArrayList<Estadisticas_Detalles> list;


    public Estadisticas(int id, String name, String logo, List<Estadisticas_Detalles> lista) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.list= (ArrayList<Estadisticas_Detalles>) lista;
    }
}
