package org.example.ax0006.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.ax0006.Service.InventarioHorarioService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class asignarHorarioInventarioController {

    public Button bt_asignar;
    private InventarioHorarioService service;
    @FXML
    private Button bt_volver;


    public asignarHorarioInventarioController(InventarioHorarioService service) {
        this.service = service;
    }

    @FXML private TextField ii_inventario;
    @FXML private TextField ii_horario;
    @FXML private Label lbl_msg;

    @FXML
    void on_bt_asignar(ActionEvent event) {
        try {
            int inv = Integer.parseInt(ii_inventario.getText());
            int hor = Integer.parseInt(ii_horario.getText());

            service.asignarInventarioAHorario(inv, hor);
            lbl_msg.setText("Asignado");

        } catch (Exception e) {
            lbl_msg.setText("Error en datos");
        }
    }

    @FXML
    void on_bt_volver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/ax0006/menu.fxml")
            );

            menuController controller = new menuController(null, null);

            loader.setController(controller);

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) bt_volver.getScene().getWindow();
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}