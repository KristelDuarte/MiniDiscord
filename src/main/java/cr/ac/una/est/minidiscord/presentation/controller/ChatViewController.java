package cr.ac.una.est.minidiscord.presentation.controller;

import cr.ac.una.est.minidiscord.business.model.Message;
import cr.ac.una.est.minidiscord.business.model.User;
import cr.ac.una.est.minidiscord.business.service.ChatService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import cr.ac.una.est.minidiscord.data.dao.MessageDAO;
import cr.ac.una.est.minidiscord.data.dao.MessageXmlExporter;
import cr.ac.una.est.minidiscord.data.dao.PersistenceManager;
import java.util.ArrayList;
import java.util.List;

public class ChatViewController {

    // La anotación @FXML inyecta los elementos visuales creados en el archivo XML
    @FXML private ListView<String> messageListView;
    @FXML private TextField messageInputField;

    // Instancia de los servicios y creación de un usuario local en memoria
    private final ChatService chatService = new ChatService();
    private final User currentUser = new User("EstudianteUNA");

    // Colección observable (Implementación nativa del Patrón Observer en JavaFX)
    private final ObservableList<String> messages = FXCollections.observableArrayList();

    private final MessageDAO messageDAO = PersistenceManager.getInstance().getMessageDAO();
    private final MessageXmlExporter xmlExporter = new MessageXmlExporter();
    private final List<Message> historial = new ArrayList<>();

    @FXML
    public void initialize() {
        messageListView.setItems(messages);

        for (Message m : messageDAO.cargarTodos()) {
            historial.add(m);
            messages.add(m.toString());
        }
    }

    @FXML
    private void handleSendMessage() {
        try {
            Message nuevoMsg = chatService.processMessage(currentUser, messageInputField.getText());

            historial.add(nuevoMsg);
            messages.add(nuevoMsg.toString());
            messageInputField.clear();

            messageDAO.guardarTodos(historial);
            xmlExporter.exportar(historial);

        } catch (IllegalArgumentException e) {
            System.out.println("Aviso de Validación: " + e.getMessage());
        }
    }
}