
package cr.ac.una.est.minidiscord.business.service;

import cr.ac.una.est.minidiscord.business.model.Message;
import cr.ac.una.est.minidiscord.business.model.User;

public class ChatService {

    public Message processMessage(User sender, String text) {

        // La validación de negocio rechaza textos vacíos lanzando una excepción matemática.
        // La capa de negocio NUNCA debe modificar ventanas de alerta gráficas directamente.
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío.");
        }

        // La regla de formato indica que los espacios laterales se eliminan al construir.
        return new Message(sender, text.trim());
    }
}
 