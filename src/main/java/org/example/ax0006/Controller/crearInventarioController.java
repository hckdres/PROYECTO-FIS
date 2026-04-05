package org.example.ax0006.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.ax0006.Service.InventarioService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class crearInventarioController {

    public Button bt_crear;
    private InventarioService service;
    @FXML
    private Button bt_volver;

    public crearInventarioController(InventarioService service) {
        this.service = service;
    }

    @FXML private Label lbl_msg;

    @FXML
    void on_bt_crear(ActionEvent event) {
        int id = service.crearInventario();

        if (id != -1) {
            lbl_msg.setText("Inventario creado con ID: " + id);
        } else {
            lbl_msg.setText("Error al crear inventario");
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