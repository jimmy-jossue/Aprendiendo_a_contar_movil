package com.janus.aprendiendoacontar.Models;

public class Usuario {

    private int id;
    private int imagen;
    private String nombre;

    public Usuario() {
    }

    public Usuario(int imagen, String nombre) {
        this.imagen = imagen;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
