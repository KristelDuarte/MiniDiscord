package cr.ac.una.est.minidiscord.business.dto;

public class MessageDTO {
    private String autor;
    private String contenido;

    public MessageDTO() { }

    public MessageDTO(String autor, String contenido) {
        this.autor = autor;
        this.contenido = contenido;
    }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
}