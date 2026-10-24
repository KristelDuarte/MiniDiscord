
package cr.ac.una.est.minidiscord.business.service;

import cr.ac.una.est.minidiscord.business.model.Message;
import cr.ac.una.est.minidiscord.business.model.User;

public class ChatService {

    // Mismos límites que las columnas de la base: VARCHAR(50) y VARCHAR(500).
    // Así el error sale claro y no el de MySQL.
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

        // El texto vacío no se acepta: se lanza una excepción.
        // Esta capa solo valida; no toca la ventana.
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("El mensaje no puede estar vacío.");
        }

        // Se quitan los espacios del inicio y del final.
        String limpio = text.trim();
        if (limpio.length() > MAX_MESSAGE) {
            throw new IllegalArgumentException("El mensaje no puede superar los " + MAX_MESSAGE + " caracteres.");
        }
        return new Message(sender, limpio);
    }
}
