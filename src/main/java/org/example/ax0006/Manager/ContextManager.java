package org.example.ax0006.Manager;

import org.example.ax0006.Repository.*;
import org.example.ax0006.Service.*;
import org.example.ax0006.db.H2;

public class ContextManager {

    private H2 h2;
    private SesionManager sesion;

    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private HorarioRepository horarioRepo;
    private ConciertoRepository conciertoRepo;

    private AutenticacionService autenService;
    private ProfileService profileService;
    private RolService rolService;
    private ConciertoService conciertoService;
    private StaffService staffService;
    private InventarioService inventarioService;
    private ObjetoService objetoService;

    public ContextManager(
            H2 h2,
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository,
            HorarioRepository horarioRepo,
            ConciertoRepository conciertoRepo,
            AutenticacionService autenService,
            ProfileService profileService,
            RolService rolService,
            ConciertoService conciertoService,
            SesionManager sesion,
            StaffService staffService,
            InventarioService inventarioService,
            ObjetoService objetoService
    ) {
        this.h2 = h2;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.horarioRepo = horarioRepo;
        this.conciertoRepo = conciertoRepo;
        this.autenService = autenService;
        this.profileService = profileService;
        this.conciertoService = conciertoService;
        this.rolService = rolService;
        this.sesion = sesion;
        this.staffService = staffService;

        this.inventarioService = inventarioService;
        this.objetoService = objetoService;
    }

    public H2 getH2() {
        return h2;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public RolRepository getRolRepository() {
        return rolRepository;
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

    public ConciertoService getConciertoService() {
        return conciertoService;
    }

    public StaffService getStaffService() {
        return staffService;
    }

    public InventarioService getInventarioService() {
        return inventarioService;
    }

    public ObjetoService getObjetoService() {return objetoService;}
}