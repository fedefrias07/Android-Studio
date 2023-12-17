package com.example.zafiro.model;

public class Producto {

    private String id;
    private String nombre;
    private Double precio;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre; //
    }

    public Double getPrecio() {
        return precio;
    }
    public void setPrecio(Double precio) {
        this.precio = precio; //
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\nPrecio: $" + precio;
    }

}

