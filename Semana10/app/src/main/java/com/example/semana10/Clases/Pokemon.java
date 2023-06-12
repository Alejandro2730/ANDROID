package com.example.semana10.Clases;

public class Pokemon {

    String id;
    String pokedex;
    String nombre;
    String tipo;
    String imagen;

    public Pokemon() {
    }

    public Pokemon( String id, String pokedex ,String nombre, String tipo, String imagen) {
        this.id = id;
        this.pokedex = pokedex;
        this.nombre = nombre;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPokedex() {
        return pokedex;
    }

    public void setPokedex(String pokedex) {
        this.pokedex = pokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
