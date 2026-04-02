package org.example.ax0006.Entity;

public class TipoObjeto {
    int idTipoObjeto;
    String nombre;

    public TipoObjeto(String nombre, int idTipoObjeto) {
        this.nombre = nombre;
        this.idTipoObjeto = idTipoObjeto;
    }

    public int getIdTipoObjeto() {
        return idTipoObjeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
