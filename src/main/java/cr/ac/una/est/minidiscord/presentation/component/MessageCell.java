package cr.ac.una.est.minidiscord.presentation.component;

import cr.ac.una.est.minidiscord.business.model.Message;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.time.format.DateTimeFormatter;

// Cada fila del ListView es una celda. ListCell<Message> indica que cada celda dibuja un Message.
// ListView reutiliza un puñado de celdas mientras uno hace scroll: por eso el dibujo se hace
// en updateItem y no en el constructor.
public class MessageCell extends ListCell<Message> {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Espacio horizontal que NO pertenece al texto: avatar (40) + separación (16) + relleno de la celda (32) + barra de scroll.
    private static final double ANCHO_RESERVADO = 110;

    private final StackPane contenedorAvatar = new StackPane();
    private final Label autor = new Label();
    private final Label fecha = new Label();
    private final Label contenido = new Label();
    private final HBox raiz;

    public MessageCell() {
        autor.getStyleClass().add("message-author");
        fecha.getStyleClass().add("message-time");
        contenido.getStyleClass().add("message-content");

        // wrapText hace que el texto largo baje de línea en vez de salirse de la ventana.
        // USE_PREF_SIZE en la altura mínima evita que JavaFX recorte el texto con "...".
        contenido.setWrapText(true);
        contenido.setMinHeight(Region.USE_PREF_SIZE);

        HBox encabezado = new HBox(8, autor, fecha);
        encabezado.setAlignment(Pos.BASELINE_LEFT);

        VBox texto = new VBox(2, encabezado, contenido);
        HBox.setHgrow(texto, Priority.ALWAYS);

        raiz = new HBox(16, contenedorAvatar, texto);
        raiz.setAlignment(Pos.TOP_LEFT);

        // Prefiere ancho 0 para que la celda tome el ancho del ListView y no el del texto sin cortar.
        setPrefWidth(0);
    }

    @Override
    protected void updateItem(Message mensaje, boolean vacia) {
        // Obligatorio: la clase padre actualiza su propio estado interno.
        super.updateItem(mensaje, vacia);

        if (vacia || mensaje == null) {
            setGraphic(null);
            return;
        }

        String username = mensaje.getAuthor().getUsername();
        autor.setText(username);
        fecha.setText(mensaje.getSentAt().format(FORMATO_FECHA));
        contenido.setText(mensaje.getContent());
        contenedorAvatar.getChildren().setAll(Avatars.create(username, 20));

        // El ancho máximo del texto sigue al ancho de la lista: al cambiar el tamaño de la ventana, se reacomoda.
        contenido.maxWidthProperty().bind(getListView().widthProperty().subtract(ANCHO_RESERVADO));

        setGraphic(raiz);
    }
}
