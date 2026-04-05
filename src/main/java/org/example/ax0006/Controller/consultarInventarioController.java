package org.example.ax0006.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.ax0006.Entity.Inventario;
import org.example.ax0006.Service.consultarInventarioService;

public class consultarInventarioController {

    private consultarInventarioService service;

    public consultarInventarioController(consultarInventarioService service) {
        this.service = service;
    }

    @FXML private TextField ii_idInventario;
    @FXML private Label lbl_resultado;
    @FXML private Button bt_buscar;
    @FXML private Button bt_volver;

    @FXML
    void on_bt_buscar(ActionEvent event) {
        try {
            int id = Integer.parseInt(ii_idInventario.getText());

            String detalle = service.obtenerDetalle(id);

            lbl_resultado.setText(detalle);

        } catch (Exception e) {
            lbl_resultado.setText("Error en datos");
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