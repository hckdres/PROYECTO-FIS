package org.example.ax0006.Manager;

import org.example.ax0006.Entity.Usuario;
import org.example.ax0006.Entity.Inventario;

/*ESTA CLASE CONTIENE LA INFORMACION DE LA SESION
* POR AHORA ESTA CONTIENE EL USUARIO QUE ACTUALMENTE ESTA LOGEADO A LA APLICACION */
public class SesionManager {

    /*ATRIBUTO*/
    private Usuario usuarioActual;

    private Inventario inventarioSeleccionado;

    /*METODO PARA OBTENER EL USUARIO ACTUALMENTE LOGEADO EN EL PROGRAMA, SI NADIE SE HA LOGEADO PUES ESTE ESTA EN NULL*/
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /*METODO PARA SETTEAR EL USUARIO ACTUAL, ESTE SE USA EN EL LOGIN*/
    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    /*METODO PARA PONER EL USUARIO ACTUAL EN NULL PARA SIMBOLIZAR QUE SE CERRO LA SESION*/
    public void cerrarSesion() {
        this.usuarioActual = null;
    }

    public Inventario getInventarioSeleccionado() {
        return inventarioSeleccionado;
    }

    public void setInventarioSeleccionado(Inventario inventarioSeleccionado) {
        this.inventarioSeleccionado = inventarioSeleccionado;
    }

    public void deseleccionarInventario(){this.inventarioSeleccionado = null;}
}
