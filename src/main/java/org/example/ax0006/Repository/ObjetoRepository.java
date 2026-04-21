package org.example.ax0006.Repository;

import org.example.ax0006.Entity.*;
import org.example.ax0006.db.H2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Clase que gestiona la obtencion de los objetos */

public class ObjetoRepository {

    private H2 h2;

    public ObjetoRepository(H2 h2) {
        this.h2 = h2;
    }

    /* Metodo para obtener los objetos, independientemente de su disponibilidad */
    public List<Objeto> obtenerObjetos() {
        List<Objeto> lista = new ArrayList<>();

        String sql = """
            SELECT o.idObjeto, o.estado, o.observaciones, o.disponible,
                   m.idModelo, m.nombre AS modeloNombre,
                   t.idTipoObjeto, t.nombre AS tipoNombre,
                   i.idInventario, i.nombre AS inventarioNombre
            FROM Objeto o
            JOIN ModeloObjeto m ON o.idModelo = m.idModelo
            JOIN TipoObjeto t ON m.idTipoObjeto = t.idTipoObjeto
            JOIN Inventario i ON o.idInventario = i.idInventario
            WHERE o.disponible = TRUE
        """;

        try (Connection conn = h2.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                TipoObjeto tipo = new TipoObjeto(
                        rs.getInt("idTipoObjeto"),
                        rs.getString("tipoNombre")
                );

                ModeloObjeto modelo = new ModeloObjeto(
                        rs.getInt("idModelo"),
                        rs.getString("modeloNombre"),
                        tipo
                );

                Inventario inv = new Inventario(
                        rs.getInt("idInventario"),
                        rs.getString("inventarioNombre"),
                        rs.getString("ubicacion")
                );

                Objeto o = new Objeto(
                        rs.getInt("idObjeto"),
                        modelo,
                        inv,
                        rs.getString("estado"),
                        rs.getString("observaciones"),
                        rs.getBoolean("disponible")
                );

                lista.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    /* Metodo para obtener los objetos disponibles */
    public List<Objeto> obtenerObjetosDisponibles() {
        List<Objeto> lista = new ArrayList<>();

        String sql = """
            SELECT o.idObjeto, o.estado, o.observaciones, o.disponible,
                   m.idModelo, m.nombre AS modeloNombre,
                   t.idTipoObjeto, t.nombre AS tipoNombre,
                   i.idInventario, i.nombre AS inventarioNombre
            FROM Objeto o
            JOIN ModeloObjeto m ON o.idModelo = m.idModelo
            JOIN TipoObjeto t ON m.idTipoObjeto = t.idTipoObjeto
            JOIN Inventario i ON o.idInventario = i.idInventario
            WHERE o.disponible = TRUE
        """;

        try (Connection conn = h2.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                TipoObjeto tipo = new TipoObjeto(
                        rs.getInt("idTipoObjeto"),
                        rs.getString("tipoNombre")
                );

                ModeloObjeto modelo = new ModeloObjeto(
                        rs.getInt("idModelo"),
                        rs.getString("modeloNombre"),
                        tipo
                );

                Inventario inv = new Inventario(
                        rs.getInt("idInventario"),
                        rs.getString("inventarioNombre"),
                        rs.getString("inventarioUbicacion")
                );

                Objeto o = new Objeto(
                        rs.getInt("idObjeto"),
                        modelo,
                        inv,
                        rs.getString("estado"),
                        rs.getString("observaciones"),
                        rs.getBoolean("disponible")
                );

                lista.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    /* Metodo para Actualizar la disponibilidad de los objetos, ya sea para decir que no estan disponibles o si estan disponibles */
    public void actualizarDisponibilidad(int idObjeto, boolean disponible) {
        String sql = "UPDATE Objeto SET disponible = ? WHERE idObjeto = ?";

        try (Connection conn = h2.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, disponible);
            stmt.setInt(2, idObjeto);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*Metodo para guarda un objeto*/
    public void guardar(int idModelo, int idInventario, String estado, String obs, boolean disponible) {

        String sql = """
        INSERT INTO Objeto (idModelo, idInventario, estado, observaciones, disponible)
        VALUES (?, ?, ?, ?, ?)
    """;

        try (Connection conn = h2.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idModelo);
            stmt.setInt(2, idInventario);
            stmt.setString(3, estado);
            stmt.setString(4, obs);
            stmt.setBoolean(5, disponible);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Objeto> obtenerObjetosInventario(int idInventario) {

        List<Objeto> objetos = new ArrayList<>();

        String sql = """
        SELECT o.*, 
               m.nombre AS modeloNombre, 
               t.nombre AS tipoNombre
        FROM Objeto o
        JOIN ModeloObjeto m ON o.idModelo = m.idModelo
        JOIN TipoObjeto t ON m.idTipoObjeto = t.idTipoObjeto
        WHERE o.idInventario = ?
    """;

        try (Connection conn = h2.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idInventario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Objeto obj = new Objeto();
                obj.setIdObjeto(rs.getInt("idObjeto"));
                obj.setEstado(rs.getString("estado"));
                obj.setObservaciones(rs.getString("observaciones"));
                obj.setDisponible(rs.getBoolean("disponible"));

                // Modelo
                ModeloObjeto modelo = new ModeloObjeto();
                modelo.setIdModelo(rs.getInt("idModelo"));
                modelo.setNombre(rs.getString("modeloNombre"));

                // Tipo
                TipoObjeto tipo = new TipoObjeto();
                tipo.setIdTipoObjeto(rs.getInt("idTipoObjeto"));
                tipo.setNombre(rs.getString("tipoNombre"));

                modelo.setTipoObjeto(tipo);
                obj.setModelo(modelo);

                objetos.add(obj);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objetos;
    }


    public void eliminar(int idObjeto) {

        String sql = "DELETE FROM Objeto WHERE idOjeto = ?";

        try (Connection conn = h2.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idObjeto);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}