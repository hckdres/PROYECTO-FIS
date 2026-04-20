package org.example.ax0006.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.ax0006.Entity.Inventario;
import org.example.ax0006.Manager.SceneManager;
import org.example.ax0006.Manager.SesionManager;
import org.example.ax0006.Service.InventarioService;

import java.io.IOException;

public class GestionInventarioController {

    @FXML private TableView<Inventario> tablaInventarios;
    @FXML private TableColumn<Inventario, String> colNombre;
    @FXML private TextField txtNombreInventario;

    private SceneManager sceneManager;
    private SesionManager session;
    private InventarioService inventarioService;

    public GestionInventarioController(SceneManager sceneManager, SesionManager session, InventarioService inventarioService){
        this.sceneManager = sceneManager;
        this.session = session;
        this.inventarioService = inventarioService;
    }
    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getNombre()));

        cargarInventarios();
    }

    private void cargarInventarios() {
        tablaInventarios.getItems().setAll(inventarioService.obtenerInventarios());
    }

    @FXML
    void On_crearInventario(ActionEvent event) {
        inventarioService.crearInventario(txtNombreInventario.getText());
        cargarInventarios();
    }

    @FXML
    void On_eliminarInventario(ActionEvent event) {
        Inventario inv = tablaInventarios.getSelectionModel().getSelectedItem();
        if (inv != null) {
            //inventarioService.eliminarInventario(inv.getIdInventario());
            cargarInventarios();
        }
    }

    @FXML
    void On_gestionarInventario(ActionEvent event) {
        Inventario inv = tablaInventarios.getSelectionModel().getSelectedItem();
        if (inv != null) {
            //sceneManager.showInventarioDetalle(inv);
        }
    }

    @FXML
    void On_volver(ActionEvent event) throws IOException {
        sceneManager.showMenu();
    }
}