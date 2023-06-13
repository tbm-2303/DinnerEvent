package facades;

import dtos.AssignmentDTO;
import dtos.EventDTO;
import entities.Member;
import entities.User;
import errorhandling.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class AssignmentFacade {


    private static EntityManagerFactory emf;
    private static AssignmentFacade instance;

    //Private Constructor to ensure Singleton
    private AssignmentFacade() {
    }

    public static AssignmentFacade getAssignmentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AssignmentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<AssignmentDTO> getAssignmentsByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            User u = em.find(User.class, username);
            Member m = em.find(Member.class, u.getMember().getId());
            List<AssignmentDTO> assignments = em.createQuery("SELECT new dtos.AssignmentDTO(a) FROM Assignment a WHERE a.members = :member", AssignmentDTO.class).setParameter("member", m).getResultList();
            return assignments;
        } finally {
            em.close();
        }
    }


    public List<EventDTO> getAllAssignments() {
        EntityManager em = emf.createEntityManager();
        try {
            List<EventDTO> assignments = em.createQuery("SELECT new dtos.EventDTO(e) FROM Event e", EventDTO.class).getResultList();
            return assignments;
        } finally {
            em.close();
        }
    }
}
