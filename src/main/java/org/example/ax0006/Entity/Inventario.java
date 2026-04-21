package org.example.ax0006.Entity;

public class Inventario {

    private int idInventario;
    private String nombre;
    private String ubicacion;

    public Inventario() {}

    public Inventario(int idInventario, String nombre, String ubicacion) {
        this.idInventario = idInventario;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}