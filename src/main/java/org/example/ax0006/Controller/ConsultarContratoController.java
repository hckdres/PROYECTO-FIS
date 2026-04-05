package org.example.ax0006.Controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.example.ax0006.Entity.Contrato;
import org.example.ax0006.Entity.Clausula;
import org.example.ax0006.Repository.ContratoRepository;
import org.example.ax0006.Service.ContratoService;
import org.example.ax0006.db.H2;

import java.util.List;

public class ConsultarContratoController {

    @FXML
    private TextField txtIdContrato;

    @FXML
    private Label lblFecha;

    @FXML
    private ListView<String> listClausulas;

    private ContratoService contratoService;

    public ConsultarContratoController() {
        this.contratoService = new ContratoService(new ContratoRepository(new H2()));
    }
    public ConsultarContratoController(ContratoService contratoService) {
    this.contratoService = contratoService;
    }

    // =========================
    // BUSCAR CONTRATO
    // =========================
    @FXML
    public void buscarContrato() {

        String textoId = txtIdContrato.getText();

        if (textoId == null || textoId.isEmpty()) {
            mostrarAlerta("Error", "Ingrese un ID");
            return;
        }

        int id;

        try {
            id = Integer.parseInt(textoId);
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El ID debe ser un número");
            return;
        }

        Contrato contrato = contratoService.obtenerContratoCompleto(id);

        if (contrato == null) {
            mostrarAlerta("Error", "Contrato no encontrado");
            return;
        }

        // Mostrar fecha
        lblFecha.setText(contrato.getFecha().toString());

        // Mostrar cláusulas
        List<String> lista = contrato.getClausulas()
                .stream()
                .map(Clausula::getClausula)
                .toList();

        listClausulas.setItems(FXCollections.observableArrayList(lista));
    }

    // =========================
    // ALERTA
    // =========================
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}