package cr.ac.una.est.minidiscord.data.dao;

import cr.ac.una.est.minidiscord.business.dto.MessageDTO;
import cr.ac.una.est.minidiscord.business.model.Message;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageXmlExporter {

    private static final String ARCHIVO_XML = "mensajes.xml";

    public void exportar(List<Message> mensajes) {
        List<MessageDTO> dtos = new ArrayList<>();
        for (Message m : mensajes) {
            dtos.add(new MessageDTO(m.getAuthor().getUsername(), m.getContent()));
        }
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(ARCHIVO_XML)))) {
            encoder.writeObject(dtos);
        } catch (IOException e) {
            System.out.println("No se pudo exportar a XML: " + e.getMessage());
        }
    }
}