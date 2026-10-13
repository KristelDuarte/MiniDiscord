package cr.ac.una.est.minidiscord;

import cr.ac.una.est.minidiscord.business.model.User;
import cr.ac.una.est.minidiscord.business.service.ChatService;
import cr.ac.una.est.minidiscord.presentation.controller.ChatViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

// Extender de Application indica a la JVM que esta clase arranca el entorno gráfico nativo
public class App extends Application {

    private static final String HOJA_ESTILOS = "/cr/ac/una/est/minidiscord/presentation/view/discord.css";

    @Override
    public void start(Stage stage) throws IOException {
        // Antes de abrir la ventana principal se pregunta quién es el usuario.
        User usuario = pedirUsuario();
        if (usuario == null) {
            return; // canceló: no hay ventanas abiertas, así que la aplicación termina sola
        }

        // FXMLLoader es el encargado de leer el XML e inyectarlo en memoria
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/cr/ac/una/est/minidiscord/presentation/view/chat-view.fxml"));

        // Se coloca la estructura compilada dentro de un lienzo o "Escena" general
        Scene scene = new Scene(fxmlLoader.load(), 960, 600);

        // La hoja de estilos (CSS) se aplica a toda la escena
        scene.getStylesheets().add(App.class.getResource(HOJA_ESTILOS).toExternalForm());

        // El controller ya existe porque load() lo creó: se le entrega el usuario elegido
        ChatViewController controller = fxmlLoader.getController();
        controller.setCurrentUser(usuario);

        // Stage representa el marco físico de la ventana brindado por el Sistema Operativo
        stage.setTitle("Mini-Discord UNA");
        stage.setMinWidth(640);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.show();
    }

    // Repite la pregunta hasta que el nombre sea válido; devuelve null si la persona cancela.
    private User pedirUsuario() {
        ChatService servicio = new ChatService();

        TextInputDialog dialogo = new TextInputDialog();
        dialogo.setTitle("Mini-Discord UNA");
        dialogo.setHeaderText("Elija su nombre de usuario");
        dialogo.setContentText("Usuario:");
        dialogo.getDialogPane().getStylesheets().add(App.class.getResource(HOJA_ESTILOS).toExternalForm());

        while (true) {
            Optional<String> respuesta = dialogo.showAndWait();
            if (respuesta.isEmpty()) {
                return null;
            }
            try {
                return servicio.createUser(respuesta.get());
            } catch (IllegalArgumentException e) {
                dialogo.setHeaderText(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
