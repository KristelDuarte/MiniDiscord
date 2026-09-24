package cr.ac.una.est.minidiscord.data.dao;

public class PersistenceManager {
    private static PersistenceManager instancia;
    private final MessageDAO dao;

    private PersistenceManager() {
        this.dao = new MessageDAO();
    }

    public static synchronized PersistenceManager getInstance() {
        if (instancia == null) {
            instancia = new PersistenceManager();
        }
        return instancia;
    }

    public MessageDAO getMessageDAO() { return dao; }
}