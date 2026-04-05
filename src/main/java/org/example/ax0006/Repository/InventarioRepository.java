package org.example.ax0006.Repository;

import org.example.ax0006.Entity.Inventario;
import org.example.ax0006.Entity.Rol;
import org.example.ax0006.Entity.TipoObjeto;
import org.example.ax0006.db.H2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioRepository {
    private H2 h2;
    private List<Inventario> Inventarios = new ArrayList<>();

    //CONSTRUCTOR
    public InventarioRepository(H2 h2) {
        this.h2 = h2;
    }

    //SE CREA UN NUEVO INVENTARIO:
    public int guardarInventario(Inventario I) {
        String sql = "INSERT INTO Inventario DEFAULT VALUES";
        try (Connection conn = h2.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int idGenerado = rs.getInt(1);
                System.out.println("Inventario creado correctamente con ID: " + idGenerado);
                return idGenerado;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // error
    }
    public Inventario buscarInventarioPorId(int idBuscar) {
        String sql = "SELECT * FROM Inventario WHERE idInventario = ?";
        try (Connection conn = h2.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idBuscar);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Inventario();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String obtenerDetalleInventario(int idInventario) {
        StringBuilder resultado = new StringBuilder();

        try (Connection conn = h2.getConnection()) {

            String sqlObjetos = """
                  SELECT t.nombre
                  FROM ObjetoInventario oi
                  JOIN TipoObjeto t ON oi.idTipoObjeto = t.idTipoObjeto
                   WHERE oi.idInventario = ?
             """;

            PreparedStatement stmtObj = conn.prepareStatement(sqlObjetos);
            stmtObj.setInt(1, idInventario);
            ResultSet rsObj = stmtObj.executeQuery();

            resultado.append("Objetos:\n");
            boolean hayObjetos = false;

            while (rsObj.next()) {
                resultado.append("- ").append(rsObj.getString("nombre")).append("\n");
                hayObjetos = true;
            }

            if (!hayObjetos) resultado.append("Sin objetos\n");

            String sqlHorarios = """
            SELECT h.fecha, h.horaInc, h.horaFin
            FROM InventarioHorario ih
            JOIN Horario h ON ih.idHorario = h.idHorario
            WHERE ih.idInventario = ?
        """;

            PreparedStatement stmtHor = conn.prepareStatement(sqlHorarios);
            stmtHor.setInt(1, idInventario);
            ResultSet rsHor = stmtHor.executeQuery();

            resultado.append("\nHorarios:\n");
            boolean hayHorarios = false;

            while (rsHor.next()) {
                resultado.append("- ")
                        .append(rsHor.getDate("fecha")).append(" ")
                        .append(rsHor.getTime("horaInc")).append(" - ")
                        .append(rsHor.getTime("horaFin")).append("\n");
                hayHorarios = true;
            }

            if (!hayHorarios) resultado.append("Sin horarios\n");

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error al consultar inventario";
        }

        return resultado.toString();
    }
}
