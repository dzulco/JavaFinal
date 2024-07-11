package edu.ar.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ar.model.Director;

public class DirectorDAO {
    public List<Director> getDirectors() {
        List<Director> directors = new ArrayList<>();
        try (Connection connection = Conexion.getConexion();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT id, nombre, apellido FROM directores")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                directors.add(new Director(id, nombre, apellido));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return directors;
    }
}
