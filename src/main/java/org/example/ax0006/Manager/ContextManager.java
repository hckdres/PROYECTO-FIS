package org.example.ax0006.Manager;

import org.example.ax0006.Repository.RolRepository;
import org.example.ax0006.Repository.UsuarioRepository;
import org.example.ax0006.Service.AutenticacionService;
import org.example.ax0006.Service.ProfileService;
import org.example.ax0006.Service.RolService;
import org.example.ax0006.db.H2;

public class ContextManager {

    private H2 h2;
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private AutenticacionService autenService;
    private ProfileService profileService;
    private RolService rolService;
    private SesionManager sesion;

    public ContextManager() {
        this.h2 = new H2();
        this.usuarioRepository = new UsuarioRepository(h2);
        this.rolRepository = new RolRepository(h2);
        this.autenService = new AutenticacionService(usuarioRepository);
        this.profileService = new ProfileService(usuarioRepository);
        this.rolService = new RolService(rolRepository, usuarioRepository);
        this.sesion = new SesionManager();
    }

    public H2 getH2() {
        return h2;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public AutenticacionService getAutenService() {
        return autenService;
    }

    public ProfileService getProfileService() {
        return profileService;
    }

    public RolService getRolService() {
        return rolService;
    }

    public SesionManager getSesion() {
        return sesion;
    }
}