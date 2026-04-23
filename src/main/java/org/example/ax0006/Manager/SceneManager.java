package org.example.ax0006.Manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.ax0006.Controller.*;

import java.io.IOException;

public class SceneManager {

    private Stage stage;
    private ContextManager context;

    public SceneManager(Stage stage, ContextManager context) {
        this.stage = stage;
        this.context = context;
    }

    public void showLogin() throws IOException {
        LoginController loginController = new LoginController(this, context.getAutenService(), context.getSesion());
        loadScene("/org/example/ax0006/login.fxml", loginController);
    }

    public void showSignUp() throws IOException {
        SignUpController signUpControl = new SignUpController(this, context.getAutenService(), context.getSesion());
        loadScene("/org/example/ax0006/signup.fxml", signUpControl);
    }

    public void showMenu() throws IOException {
        MenuController menuControl = new MenuController(this, context.getSesion(), context.getConciertoService());
        loadScene("/org/example/ax0006/menu.fxml", menuControl);
    }

    public void showAdminUsuarios() throws IOException {
        AdminUsuariosController controller = new AdminUsuariosController(
                context.getSesion(),
                context.getRolService(),
                this,
                context.getConciertoService(),
                context.getStaffService()
        );
        loadScene("/org/example/ax0006/adminUsuarios.fxml", controller);
    }

    public void showProfile() throws IOException {
        ProfileController profileController = new ProfileController(
                this,
                context.getSesion(),
                context.getProfileService()
        );
        loadScene("/org/example/ax0006/profile.fxml", profileController);
    }

    public void showEditProfile() throws IOException {
        EditProfileController editProfileController = new EditProfileController(
                this,
                context.getSesion(),
                context.getProfileService()
        );
        loadScene("/org/example/ax0006/editprofile.fxml", editProfileController);
    }

    public void showChangePassword() throws IOException {
        ChangePasswordController changePasswordController = new ChangePasswordController(
                this,
                context.getSesion(),
                context.getProfileService()
        );
        loadScene("/org/example/ax0006/changepassword.fxml", changePasswordController);
    }

    public void showConsultarSolicitudes() throws IOException {
        ConsultarSolicitudesController c = new ConsultarSolicitudesController(context.getSesion(), context.getConciertoService(), this);
        loadScene("/org/example/ax0006/consultarsolicitudes.fxml", c);
    }

    public void showCrearConcierto() throws IOException {
        CrearConciertoController c = new CrearConciertoController(context.getSesion(), context.getConciertoService(), this);
        loadScene("/org/example/ax0006/crearconcierto.fxml", c);
    }

    public void showConciertosProgramados() throws IOException {
        ConciertosProgramadosController c = new ConciertosProgramadosController(context.getSesion(), context.getConciertoService(), this);
        loadScene("/org/example/ax0006/verconciertosprogramados.fxml", c);
    }

    public void showMenuConcierto() throws IOException {
        MenuConciertoController c = new MenuConciertoController(this, context.getSesion());
        loadScene("/org/example/ax0006/menuconcierto.fxml", c);
    }

    // ← setControllerFactory porque el FXML declara fx:controller
    public void showNomina() throws IOException {
        NominaController nominaController = new NominaController(
                this,
                context.getSesion(),
                context.getConciertoService(),
                context.getNominaService(),
                context.getStaffService()
        );
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/ax0006/liquidacionhoras.fxml"));
        loader.setControllerFactory(c -> nominaController);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    private void loadScene(String fxml, Object controller) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }
}