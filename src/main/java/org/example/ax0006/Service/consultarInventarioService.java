package org.example.ax0006.Service;

import org.example.ax0006.Repository.InventarioRepository;

import java.util.List;

public class consultarInventarioService {

    private InventarioRepository inventarioRepository;

    public consultarInventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    public String obtenerDetalle(int id) {
        return inventarioRepository.obtenerDetalleInventario(id);
    }

    public List<Integer> listarIdsInventarios() {
        return inventarioRepository.obtenerTodosLosIds();
    }
}