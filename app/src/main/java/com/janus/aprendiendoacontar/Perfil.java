package com.janus.aprendiendoacontar;

public class Perfil {

    private int id;
    private int imagen;
    private String nombre;

    public Perfil() {
    }

    public Perfil(int imagen, String nombre) {
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
