package cr.ac.una.est.minidiscord.presentation.component;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

// Crea el avatar: un círculo de color con la inicial del usuario.
// Solo tiene métodos estáticos, por eso el constructor es privado.
public final class Avatars {

    private static final Color[] PALETA = {
            Color.web("#5865f2"), Color.web("#3ba55d"), Color.web("#faa61a"),
            Color.web("#ed4245"), Color.web("#eb459e"), Color.web("#9b59b6")
    };

    private Avatars() { }

    public static StackPane create(String username, double radius) {
        // El color sale del nombre: el mismo usuario siempre tiene el mismo color.
        // Se usa floorMod porque hashCode() puede dar un número negativo.
        int indice = Math.floorMod(username.toLowerCase().hashCode(), PALETA.length);
        Circle circulo = new Circle(radius, PALETA[indice]);

        Label inicial = new Label(username.substring(0, 1).toUpperCase());
        inicial.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: " + (radius * 0.95) + "px;");

        // StackPane pone la inicial encima del círculo.
        return new StackPane(circulo, inicial);
    }
}
