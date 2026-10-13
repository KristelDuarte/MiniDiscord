
package cr.ac.una.est.minidiscord.business.service;

import cr.ac.una.est.minidiscord.business.model.Message;
import cr.ac.una.est.minidiscord.business.model.User;

public class ChatService {

    // Estos límites replican las columnas de la base de datos: VARCHAR(50) y VARCHAR(500).
    // Validarlos aquí evita que MySQL rechace el INSERT con un error menos claro.
    public static final int MAX_USERNAME = 50;
    public static final int MAX_MESSAGE = 500;

    public User createUser(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no puede estar vacío.");
        }
        String limpio = name.trim();
        if (limpio.length() > MAX_USERNAME) {
            throw new IllegalArgumentException("El nombre no puede superar los " + MAX_USERNAME + " caracteres.");
        }
        return new User(limpio);
    }

    public Message processMessage(User sender, String text) {

        // La validación de negocio rechaza textos vacíos lanzando una excepción matemática.
        // La capa de negocio NUNCA debe modificar ventanas de alerta gráficas directamente.
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío.");
        }

        // La regla de formato indica que los espacios laterales se eliminan al construir.
        String limpio = text.trim();
        if (limpio.length() > MAX_MESSAGE) {
            throw new IllegalArgumentException("El mensaje no puede superar los " + MAX_MESSAGE + " caracteres.");
        }
        return new Message(sender, limpio);
    }
}
