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

// App hereda de Application: así JavaFX sabe cómo arrancarla
public class App extends Application {

    private static final String HOJA_ESTILOS = "/cr/ac/una/est/minidiscord/presentation/view/discord.css";

    @Override
    public void start(Stage stage) throws IOException {
        // Primero se pregunta quién es el usuario.
        User usuario = pedirUsuario();
        if (usuario == null) {
            return; // la persona canceló el cuadro: no se abre la ventana
        }

        // El FXMLLoader lee el archivo FXML y arma la ventana
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/cr/ac/una/est/minidiscord/presentation/view/chat-view.fxml"));

        // La escena contiene todo lo que se ve dentro de la ventana
        Scene scene = new Scene(fxmlLoader.load(), 960, 600);

        // El CSS se aplica a toda la escena
        scene.getStylesheets().add(App.class.getResource(HOJA_ESTILOS).toExternalForm());

        // load() ya creó el controller; aquí se le pasa el usuario
        ChatViewController controller = fxmlLoader.getController();
        controller.setCurrentUser(usuario);

        // El Stage es la ventana del sistema operativo
        stage.setTitle("Mini-Discord UNA");
        stage.setMinWidth(640);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.show();
    }

    // Repite la pregunta hasta que el nombre sirva; devuelve null si cancelan.
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
