package facades;

import dtos.EventDTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class EventFacade {

    private static EntityManagerFactory emf;
    private static EventFacade instance;

    //Private Constructor to ensure Singleton
    private EventFacade() {}

    public static EventFacade getEventFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EventFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<EventDTO> getAllEvents() {
        EntityManager em = emf.createEntityManager();
        try {
            List<EventDTO> events = em.createQuery("SELECT new dtos.EventDTO(e) FROM Event e", EventDTO.class).getResultList();
            return events;
        } finally {
            em.close();
        }
    }
}
