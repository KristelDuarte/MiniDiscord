package cr.ac.una.est.minidiscord.data.dao;

// Excepción propia de la capa de datos. Es "unchecked" (hereda de RuntimeException) para que
// MessageRepository no obligue a declarar throws en cada método.
// Traduce SQLException, que es un detalle de JDBC, a un error que las capas de arriba pueden entender
// sin saber que por debajo hay una base de datos.
public class DataAccessException extends RuntimeException {

    // 'cause' conserva el error original (SQLException, IOException...) para poder revisarlo en la consola.
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
