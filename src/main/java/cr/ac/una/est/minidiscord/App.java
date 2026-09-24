package cr.ac.una.est.minidiscord;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// Extender de Application indica a la JVM que esta clase arranca el entorno gráfico nativo
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader es el encargado de leer el XML e inyectarlo en memoria
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/cr/ac/una/est/minidiscord/presentation/view/chat-view.fxml"));

        // Se coloca la estructura compilada dentro de un lienzo o "Escena" general
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Stage representa el marco físico de la ventana brindado por el Sistema Operativo
        stage.setTitle("Mini-Discord UNA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}