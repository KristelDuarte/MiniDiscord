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

// Cada fila de la lista es una celda; esta dibuja un Message.
// El ListView reutiliza unas pocas celdas mientras se hace scroll,
// por eso el dibujo va en updateItem y no en el constructor.
public class MessageCell extends ListCell<Message> {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // Espacio que no es del texto: avatar (40) + separación (16) + relleno (32) + barra de scroll.
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

        // wrapText: el texto largo baja de línea en vez de salirse de la ventana.
        // USE_PREF_SIZE: evita que JavaFX corte el texto con "...".
        contenido.setWrapText(true);
        contenido.setMinHeight(Region.USE_PREF_SIZE);

        HBox encabezado = new HBox(8, autor, fecha);
        encabezado.setAlignment(Pos.BASELINE_LEFT);

        VBox texto = new VBox(2, encabezado, contenido);
        HBox.setHgrow(texto, Priority.ALWAYS);

        raiz = new HBox(16, contenedorAvatar, texto);
        raiz.setAlignment(Pos.TOP_LEFT);

        // Ancho preferido 0: la celda toma el ancho de la lista y no el del texto.
        setPrefWidth(0);
    }

    @Override
    protected void updateItem(Message mensaje, boolean vacia) {
        // Obligatorio: la clase padre actualiza su propio estado.
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

        // El texto se ajusta al ancho de la lista cuando cambia el tamaño de la ventana.
        contenido.maxWidthProperty().bind(getListView().widthProperty().subtract(ANCHO_RESERVADO));

        setGraphic(raiz);
    }
}
