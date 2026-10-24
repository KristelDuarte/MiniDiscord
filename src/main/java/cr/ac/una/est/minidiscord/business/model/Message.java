package cr.ac.una.est.minidiscord.business.model;

import java.io.Serializable;
import java.time.LocalDateTime;

// Message guarda un User adentro, por eso User también
// tiene que ser Serializable.
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

    // toString devuelve el texto que se muestra del mensaje:
    // el autor y su contenido.
    @Override
    public String toString() {
        return author.getUsername() + ": " + content;
    }
}
