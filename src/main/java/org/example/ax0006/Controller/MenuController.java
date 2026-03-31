package org.example.ax0006.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.example.ax0006.Manager.SceneManager;
import org.example.ax0006.Manager.SesionManager;

import java.io.IOException;

public class MenuController {

    /*ATRIBUTOS*/
    private SceneManager sceneManager;
    private SesionManager sesion;

    /*CONSTRUCTOR*/
    public MenuController(SceneManager sceneManager, SesionManager sesion){
        this.sceneManager = sceneManager;
        this.sesion = sesion;
    }

    @FXML
    private Label fid_Bienvenido;

    /*METODO CAMBIA EL BIENVENIDO POR EL BIENVENIDO CON EL NOMBRE DEL USUARIO*/
    public void setNombreBienvenido(){
        fid_Bienvenido.setText("Bienvenido " + sesion.getUsuarioActual().getNombre());
    }

    @FXML
    private Button fid_bt_volver;

    @FXML
    /*METODO QUE CAMBIA A LA PANTALLA DE LOGIN*/
    void On_btvolver(ActionEvent event) throws IOException {
        sesion.cerrarSesion();
        sceneManager.showLogin();
    }

    @FXML
    /*APENAS SE ABRE LA VENTANA, SE REALIZA EL MENSAJE DE BIENVENIDO NOMBRE DE USUARIO*/
    public void initialize() {
        if (sesion.getUsuarioActual() != null) {
            fid_Bienvenido.setText("Bienvenido " + sesion.getUsuarioActual().getNombre());
        }
    }

    //boton de administracion de usuarios para asignacion de roles.
    @FXML
    void On_admin(ActionEvent event) throws IOException {
        sceneManager.showAdminUsuarios();
    }

    //Boton para entrar al perfil del usuario.
    @FXML
    void On_perfil(ActionEvent event) {
        try {
            sceneManager.showProfile();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir la ventana de perfil");
            alert.setContentText("Ocurrió un problema al cargar la vista.");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
