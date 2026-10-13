package cr.ac.una.est.minidiscord.presentation.controller;

import cr.ac.una.est.minidiscord.business.model.Message;
import cr.ac.una.est.minidiscord.business.model.User;
import cr.ac.una.est.minidiscord.business.service.ChatService;
import cr.ac.una.est.minidiscord.data.dao.DataAccessException;
import cr.ac.una.est.minidiscord.data.dao.JdbcMessageRepository;
import cr.ac.una.est.minidiscord.data.dao.MessageRepository;
import cr.ac.una.est.minidiscord.presentation.component.Avatars;
import cr.ac.una.est.minidiscord.presentation.component.MessageCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

public class ChatViewController {

    // La anotación @FXML inyecta los elementos visuales creados en el archivo XML
    @FXML private ListView<Message> messageListView;
    @FXML private TextField messageInputField;
    @FXML private Label statusLabel;
    @FXML private Label userNameLabel;
    @FXML private StackPane userAvatarHolder;

    private final ChatService chatService = new ChatService();

    // Colección observable (Implementación nativa del Patrón Observer en JavaFX):
    // cada vez que se agrega un Message, el ListView se actualiza solo.
    private final ObservableList<Message> messages = FXCollections.observableArrayList();

    // El campo es del TIPO de la interfaz, la instancia es la implementación JDBC
    private final MessageRepository messageRepository = new JdbcMessageRepository();

    // Lo asigna App después de pedir el nombre en el diálogo inicial.
    private User currentUser;

    @FXML
    public void initialize() {
        messageListView.setItems(messages);

        // El cell factory le dice al ListView cómo dibujar cada fila: con una MessageCell nueva.
        messageListView.setCellFactory(lista -> new MessageCell());

        // La franja de error solo ocupa espacio mientras tenga texto.
        statusLabel.visibleProperty().bind(statusLabel.textProperty().isNotEmpty());
        statusLabel.managedProperty().bind(statusLabel.visibleProperty());

        try {
            messages.addAll(messageRepository.obtenerTodos());
            scrollToEnd();
        } catch (DataAccessException e) {
            statusLabel.setText(e.getMessage());
        }
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        userNameLabel.setText(user.getUsername());
        userAvatarHolder.getChildren().setAll(Avatars.create(user.getUsername(), 16));
    }

    @FXML
    private void handleSendMessage() {
        try {
            Message nuevoMsg = chatService.processMessage(currentUser, messageInputField.getText());

            // Primero se guarda: si falla, el mensaje no aparece en pantalla como si se hubiera enviado.
            messageRepository.guardar(nuevoMsg);

            messages.add(nuevoMsg);
            messageInputField.clear();
            statusLabel.setText("");
            scrollToEnd();

        } catch (IllegalArgumentException | DataAccessException e) {
            statusLabel.setText(e.getMessage());
        }
    }

    private void scrollToEnd() {
        if (!messages.isEmpty()) {
            messageListView.scrollTo(messages.size() - 1);
        }
    }
}
