package org.example.ax0006.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.ax0006.Entity.usuario;
import org.example.ax0006.Repository.usuarioRepository;

import java.io.IOException;

public class menuController {

    private usuarioRepository usuarioRepo;
    private usuario usuarioLogin; //el usuario que esta logeado

    /*CONSTRUCTOR DE LA CLASE*/
    public menuController(usuarioRepository usuarioRepo, usuario usuarioLogin) {
        this.usuarioRepo = usuarioRepo;
        this.usuarioLogin = usuarioLogin;
    }

    @FXML
    private Text fid_Bienvenido;

    /*METODO CAMBIA EL BIENVENIDO POR EL BIENVENIDO CON EL NOMBRE DEL USUARIO*/
    public void setNombreBienvenido(){
        fid_Bienvenido.setText("Bienvenido " + usuarioLogin.getNombre());
    }

    @FXML
    private Button fid_bt_volver;

    @FXML
    /*METODO QUE CAMBIA A LA PANTALLA DE LOGIN*/
    void On_btvolver(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/org/example/ax0006/login.fxml")
        );

        loginController loginControl = new loginController(usuarioRepo);

        loader.setController(loginControl);

        Scene scene = new Scene(loader.load());

        Stage stage = (Stage) fid_bt_volver.getScene().getWindow();
        stage.setScene(scene);
    }

}
