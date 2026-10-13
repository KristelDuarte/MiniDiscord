package cr.ac.una.est.minidiscord.business.model;

import java.io.Serializable;
import java.time.LocalDateTime;

// Como Message contiene un atributo de tipo 'User', la clase 'User' también debió
// implementar Serializable de manera obligatoria.
public class Message implements Serializable {
    private final User author;
    private final String content;
    private final LocalDateTime sentAt;

    // Mensaje nuevo: la fecha es el momento en que se crea.
    public Message(User author, String content) {
        this(author, content, LocalDateTime.now());
    }

    // Mensaje que ya existía (por ejemplo, leído de la base de datos): conserva su fecha original.
    public Message(User author, String content, LocalDateTime sentAt) {
        this.author = author;
        this.content = content;
        this.sentAt = sentAt;
    }

    public User getAuthor() { return author; }
    public String getContent() { return content; }
    public LocalDateTime getSentAt() { return sentAt; }

    // Sobrescribimos el comportamiento natural para que la vista gráfica
    // sepa exactamente qué texto mostrar al imprimir este objeto en la lista.
    @Override
    public String toString() {
        return author.getUsername() + ": " + content;
    }
}
