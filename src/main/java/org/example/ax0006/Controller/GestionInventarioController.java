package org.example.ax0006.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.example.ax0006.Entity.Inventario;
import org.example.ax0006.Manager.SceneManager;
import org.example.ax0006.Manager.SesionManager;
import org.example.ax0006.Service.InventarioService;

import java.io.IOException;

public class GestionInventarioController {

    @FXML private TableView<Inventario> tablaInventarios;
    @FXML private TableColumn<Inventario, String> colNombre;
    @FXML private TableColumn<Inventario, String> colUbicacion;
    @FXML private TextField txtNombreInventario;
    @FXML private TextField txtUbicacion;

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

        colUbicacion.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getUbicacion()));

        cargarInventarios();
    }

    private void cargarInventarios() {
        tablaInventarios.getItems().setAll(inventarioService.obtenerInventarios());
    }

    @FXML
    void On_crearInventario(ActionEvent event) {
        inventarioService.crearInventario(txtNombreInventario.getText(), txtUbicacion.getText());
        cargarInventarios();
    }

    @FXML
    void On_eliminarInventario(ActionEvent event) {
        inventarioService.eliminarInvertario(txtNombreInventario.getText());
        cargarInventarios();
    }

    @FXML
    void On_gestionarInventario(ActionEvent event) throws IOException {
        Inventario inv = tablaInventarios.getSelectionModel().getSelectedItem();
        if (inv != null) {
            session.setInventarioSeleccionado(inv);
            sceneManager.showInventarioDetalle();
        }
    }

    @FXML
    void On_volver(ActionEvent event) throws IOException {
        sceneManager.showMenu();
    }
}