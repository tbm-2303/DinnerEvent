package facades;

import dtos.EventDTO;
import entities.Assignment;
import entities.Event;
import utils.EMF_Creator;

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

    public EventDTO createNewEvent(EventDTO eventDTO) {
        EntityManager em = emf.createEntityManager();
        Event event = new Event(eventDTO.getTime(),eventDTO.getLocation(),eventDTO.getDish(),eventDTO.getPrice());

        try {
            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();
            return new EventDTO(event);
        } finally {
            em.close();
        }
    }



    //delete event entity that has relationship with assignment entity.

    public EventDTO deleteEvent(Long eventID) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Event e1 = em.find(Event.class, eventID);
        helper(e1.getAssignments(), e1.getId());
        em.getTransaction().begin();
        em.remove(e1);
        em.getTransaction().commit();
        return new EventDTO(e1);
    }

    public static void helper(List<Assignment> assignments, Long id) {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        Event e1 = em.find(Event.class, id);
        for (Assignment a : assignments) {
            a.removeEvent(e1);
            em.getTransaction().begin();
            em.merge(a);
            em.getTransaction().commit();
        }
    }
// US-6
    public EventDTO updateEvent(EventDTO eventDTO) {
        EntityManager em = emf.createEntityManager();
        Event event = em.find(Event.class, eventDTO.getId());
        event.setTime(eventDTO.getTime());
        event.setLocation(eventDTO.getLocation());
        event.setDish(eventDTO.getDish());
        event.setPrice(eventDTO.getPrice());
        try {
            em.getTransaction().begin();
            em.merge(event);
            em.getTransaction().commit();
            return new EventDTO(event);
        } finally {
            em.close();
        }
    }

    public EventDTO getEventById(Long eventID) {
        EntityManager em = emf.createEntityManager();
        try {
            Event event = em.find(Event.class, eventID);
            return new EventDTO(event);
        } finally {
            em.close();
        }

    }
}
