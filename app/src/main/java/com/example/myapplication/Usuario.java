package com.example.myapplication;

public class Usuario {

    public String nombre;
    public  String fechaNac;
    public  String correo;
    public  String creditos;
    public  String nombre_usuario;
    public  String celular;
    public  String dni;
    public  String genero;


    public Usuario(String nombre, String fechaNac, String correo, String creditos, String nombre_usuario, String celular, String dni, String genero) {
        this.nombre = nombre;
        this.fechaNac = fechaNac;
        this.correo = correo;
        this.creditos = creditos;
        this.nombre_usuario = nombre_usuario;
        this.celular = celular;
        this.dni = dni;
        this.genero = genero;
    }
}
