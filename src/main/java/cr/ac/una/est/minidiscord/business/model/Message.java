package cr.ac.una.est.minidiscord.business.model;

import java.io.Serializable;

// Como Message contiene un atributo de tipo 'User', la clase 'User' también debió
// implementar Serializable de manera obligatoria.
public class Message implements Serializable {
    private final User author;
    private final String content;

    public Message(User author, String content) {
        this.author = author;
        this.content = content;
    }

    public User getAuthor() { return author; }
    public String getContent() { return content; }

    // Sobrescribimos el comportamiento natural para que la vista gráfica
    // sepa exactamente qué texto mostrar al imprimir este objeto en la lista.
    @Override
    public String toString() {
        return author.getUsername() + ": " + content;
    }
}