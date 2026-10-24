package cr.ac.una.est.minidiscord.data.dao;

// Excepción propia de la capa de datos. Hereda de RuntimeException,
// así los métodos de MessageRepository no necesitan declarar throws.
// Convierte la SQLException de JDBC en un error que el resto del programa
// puede mostrar sin saber que por debajo hay una base de datos.
public class DataAccessException extends RuntimeException {

    // cause guarda el error original (SQLException, IOException...).
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
