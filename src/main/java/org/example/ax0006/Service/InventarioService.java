package org.example.ax0006.Service;

import org.example.ax0006.Entity.Inventario;
import org.example.ax0006.Repository.ConciertoObjetoRepository;
import org.example.ax0006.Repository.InventarioRepository;
import org.example.ax0006.Repository.ObjetoRepository;

import java.util.List;

public class InventarioService {

    private ObjetoRepository objetoRepo;
    private ConciertoObjetoRepository conciertoObjetoRepo;
    private InventarioRepository inventarioRepo;

    public InventarioService(ObjetoRepository objetoRepo, ConciertoObjetoRepository conciertoObjetoRepo, InventarioRepository inventarioRepo) {
        this.objetoRepo = objetoRepo;
        this.conciertoObjetoRepo = conciertoObjetoRepo;
        this.inventarioRepo = inventarioRepo;
    }

    public void asignarObjetoAConcierto(int idConcierto, int idObjeto) {

        /*
        if (!objetoRepo.estaDisponible(idObjeto)) {
            throw new IllegalStateException("El objeto no está disponible");
        }

        Confirmacion pendiente para que lo haga un validator
        */

        // 1. asignar relación
        conciertoObjetoRepo.asignarObjeto(idConcierto, idObjeto);

        // 2. marcar como no disponible
        objetoRepo.actualizarDisponibilidad(idObjeto, false);
    }

    public void liberarObjetoDeConcierto(int idConcierto, int idObjeto) {

        // 1. eliminar relación
        conciertoObjetoRepo.eliminarObjeto(idConcierto, idObjeto);

        // 2. volver disponible
        objetoRepo.actualizarDisponibilidad(idObjeto, true);
    }

    public List<Inventario> obtenerInventarios(){
        return inventarioRepo.obtenerInventarios();
    }
}