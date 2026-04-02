package org.example.ax0006.Service;

import org.example.ax0006.Entity.Usuario;
import org.example.ax0006.Repository.UsuarioRepository;

public class ProfileService {

    private UsuarioRepository usuarioRepository;

    public ProfileService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario obtenerPerfilCompleto(int idUsuario) {
        return usuarioRepository.buscarCompletoPorId(idUsuario);
    }

    public void actualizarPerfil(Usuario usuario) {
        usuarioRepository.actualizarPerfil(usuario);
    }
}