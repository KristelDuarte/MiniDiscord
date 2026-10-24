package cr.ac.una.est.minidiscord.business.model;

import java.io.Serializable;

// Serializable permite guardar el objeto en disco como bytes.
public class User implements Serializable {

    // final: el username no cambia una vez creado el usuario.
    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() { return username; }
}
