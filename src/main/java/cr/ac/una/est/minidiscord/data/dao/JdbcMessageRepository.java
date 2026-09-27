package cr.ac.una.est.minidiscord.data.dao;

import cr.ac.una.est.minidiscord.business.model.Message;
import cr.ac.una.est.minidiscord.business.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMessageRepository implements MessageRepository {

    private static final String URL = "jdbc:mysql://localhost:3306/minidiscord";
    private static final String USUARIO = "root";
    private static final String CLAVE = "";

    private int obtenerOcrearUsuario(Connection con, String username) throws SQLException {
        try (PreparedStatement buscar = con.prepareStatement("SELECT id FROM usuarios WHERE username = ?")) {
            buscar.setString(1, username);
            try (ResultSet rs = buscar.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        try (PreparedStatement crear = con.prepareStatement(
                "INSERT INTO usuarios (username) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            crear.setString(1, username);
            crear.executeUpdate();
            try (ResultSet claves = crear.getGeneratedKeys()) {
                claves.next();
                return claves.getInt(1);
            }
        }
    }

    @Override
    public void guardar(Message mensaje) {
        try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE)) {
            int usuarioId = obtenerOcrearUsuario(con, mensaje.getAuthor().getUsername());
            try (PreparedStatement stmt = con.prepareStatement(
                    "INSERT INTO mensajes (usuario_id, contenido) VALUES (?, ?)")) {
                stmt.setInt(1, usuarioId);
                stmt.setString(2, mensaje.getContent());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("No se pudo guardar en la base de datos: " + e.getMessage());
        }
    }

    @Override
    public List<Message> obtenerTodos() {
        List<Message> resultado = new ArrayList<>();
        String sql = "SELECT u.username, m.contenido " +
                     "FROM mensajes m JOIN usuarios u ON m.usuario_id = u.id " +
                     "ORDER BY m.fecha_envio";
        try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE);
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User autor = new User(rs.getString("username"));
                resultado.add(new Message(autor, rs.getString("contenido")));
            }
        } catch (SQLException e) {
            System.out.println("No se pudo leer el historial: " + e.getMessage());
        }
        return resultado;
    }
}
