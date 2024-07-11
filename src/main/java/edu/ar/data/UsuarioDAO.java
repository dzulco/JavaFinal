package edu.ar.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ar.model.Usuario;

public class UsuarioDAO {
    // Método para obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection connection = Conexion.getConexion();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id_usuario");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String email = resultSet.getString("email");
                String contrasenia = resultSet.getString("contrasenia");
                String fechaNacimiento = resultSet.getString("fecha_nac");
                String pais = resultSet.getString("pais");

                Usuario usuario = new Usuario(id, nombre, apellido, email, contrasenia, fechaNacimiento, pais);
                usuarios.add(usuario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    // Método para insertar un nuevo usuario
    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, apellido, email, contrasenia, fecha_nac, pais) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = Conexion.getConexion();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getContrasenia());
            statement.setString(5, usuario.getFechaNacimiento());
            statement.setString(6, usuario.getPais());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
