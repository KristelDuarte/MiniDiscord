package cr.ac.una.est.minidiscord.data.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Lee los datos de conexión desde config.properties, un archivo que NO se sube a Git.
// Así la clave nunca queda escrita en el código fuente ni en el historial del repositorio.
public final class DatabaseConfig {

    private static final String ARCHIVO = "config.properties";

    private final String url;
    private final String usuario;
    private final String clave;

    private DatabaseConfig(String url, String usuario, String clave) {
        this.url = url;
        this.usuario = usuario;
        this.clave = clave;
    }

    public static DatabaseConfig cargar() {
        Properties props = new Properties();
        try (InputStream entrada = new FileInputStream(ARCHIVO)) {
            props.load(entrada);
        } catch (IOException e) {
            throw new DataAccessException("No se encontró " + ARCHIVO
                    + ". Copie config.example.properties como " + ARCHIVO + " y complete sus datos.", e);
        }
        return new DatabaseConfig(
                requerida(props, "db.url"),
                requerida(props, "db.user"),
                props.getProperty("db.password", ""));
    }

    private static String requerida(Properties props, String nombre) {
        String valor = props.getProperty(nombre);
        if (valor == null || valor.isBlank()) {
            throw new DataAccessException("Falta la propiedad " + nombre + " en " + ARCHIVO + ".", null);
        }
        return valor.trim();
    }

    // Quien necesita una conexión la pide aquí; nadie más ve el usuario ni la clave.
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, usuario, clave);
    }
}
