package cr.ac.una.est.minidiscord.data.dao;

import cr.ac.una.est.minidiscord.business.model.Message;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    private static final String ARCHIVO = "mensajes.dat";

    public void guardarTodos(List<Message> mensajes) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            salida.writeObject(new ArrayList<>(mensajes));
        } catch (IOException e) {
            System.out.println("No se pudo guardar el historial: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Message> cargarTodos() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Message>) entrada.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se pudo leer el historial: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}