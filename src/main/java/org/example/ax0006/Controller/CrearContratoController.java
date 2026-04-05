package org.example.ax0006.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import org.example.ax0006.Entity.Contrato;
import org.example.ax0006.Entity.Clausula;
import org.example.ax0006.Repository.ContratoRepository;
import org.example.ax0006.Service.ContratoService;
import org.example.ax0006.db.H2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CrearContratoController {

    @FXML
    private DatePicker dateFecha;

    @FXML
    private TextArea txtClausula;

    @FXML
    private ListView<String> listClausulas;

    private ObservableList<String> clausulasTexto = FXCollections.observableArrayList();

    private ContratoService contratoService;

    // CONSTRUCTOR LÓGICO
    public CrearContratoController() {
        this.contratoService = new ContratoService(new ContratoRepository(new H2()));
    }
    public CrearContratoController(ContratoService contratoService) {
    this.contratoService = contratoService;
    }

    @FXML
    public void initialize() {
        listClausulas.setItems(clausulasTexto);
    }

    // =========================
    // AGREGAR CLAUSULA
    // =========================
    @FXML
    public void agregarClausula() {

        String texto = txtClausula.getText();

        if (texto == null || texto.isEmpty()) {
            mostrarAlerta("Error", "La cláusula no puede estar vacía");
            return;
        }

        clausulasTexto.add(texto);
        txtClausula.clear();
    }

    // =========================
    // GUARDAR CONTRATO
    // =========================
    @FXML
    public void guardarContrato() {

        LocalDate fecha = dateFecha.getValue();

        if (fecha == null) {
            mostrarAlerta("Error", "Debe seleccionar una fecha");
            return;
        }

        if (clausulasTexto.isEmpty()) {
            mostrarAlerta("Error", "Debe agregar al menos una cláusula");
            return;
        }

        // Crear contrato
        Contrato contrato = new Contrato();
        contrato.setFecha(fecha);

        // Convertir cláusulas
        List<Clausula> lista = new ArrayList<>();

        for (String texto : clausulasTexto) {
            Clausula cl = new Clausula();
            cl.setClausula(texto);
            lista.add(cl);
        }

        contrato.setClausulas(lista);

        boolean guardado = contratoService.crearContrato(contrato);

        if (guardado) {
            mostrarAlerta("Éxito", "Contrato guardado correctamente");

            // limpiar
            dateFecha.setValue(null);
            clausulasTexto.clear();
        } else {
            mostrarAlerta("Error", "No se pudo guardar el contrato");
        }
    }

    // =========================
    // CANCELAR
    // =========================
    @FXML
    public void cancelar() {
        dateFecha.setValue(null);
        txtClausula.clear();
        clausulasTexto.clear();
    }

    // =========================
    // ALERTAS
    // =========================
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}