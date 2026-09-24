package cr.ac.una.est.minidiscord.business.model;

import java.io.Serializable;

// Se implementa Serializable para poder convertir la instancia en bytes y almacenarla en disco.
public class User implements Serializable {

    // La propiedad 'final' garantiza inmutabilidad: el username no cambiará tras ser instanciado.
    private final String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() { return username; }
}
