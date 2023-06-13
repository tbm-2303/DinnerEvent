package facades;

import dtos.AssignmentDTO;
import dtos.EventDTO;
import dtos.MemberDTO;
import entities.Assignment;
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


    public List<AssignmentDTO> getAllAssignments() {
        EntityManager em = emf.createEntityManager();
        try {
            List<AssignmentDTO> allAssignments = em.createQuery("SELECT new dtos.AssignmentDTO(a) FROM Assignment a", AssignmentDTO.class).getResultList();
            return allAssignments;
        } finally {
            em.close();
        }
    }

    public List<AssignmentDTO> getAssignmentsByEventId(Long eventId) {
        EntityManager em = emf.createEntityManager();
        try {
            List<AssignmentDTO> assignments = em.createQuery("SELECT new dtos.AssignmentDTO(a) FROM Assignment a WHERE a.event.id = :id", AssignmentDTO.class).setParameter("id", eventId).getResultList();
            return assignments;
        } finally {
            em.close();
        }
    }

    public AssignmentDTO getAssignmentById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            AssignmentDTO assignment = em.createQuery("SELECT new dtos.AssignmentDTO(a) FROM Assignment a WHERE a.id = :id", AssignmentDTO.class).setParameter("id", id).getSingleResult();
            return assignment;
        } finally {
            em.close();
        }
    }


    public List<MemberDTO> getMembersFromAssignment(Long assignmentId) {
        EntityManager em = emf.createEntityManager();
        try {
            List<MemberDTO> members = em.createQuery("SELECT new dtos.MemberDTO(m) FROM Member m JOIN m.assignments a WHERE a.id = :id", MemberDTO.class).setParameter("id", assignmentId).getResultList();
            return members;
        } finally {
            em.close();
        }
    }

    public AssignmentDTO removeMemberFromAssignment(Long assignmentId, Long memberId) {
        EntityManager em = emf.createEntityManager();
        Assignment assignment = em.find(Assignment.class, assignmentId);
        Member member = em.find(Member.class, memberId);
        assignment.removeMember(member);
        try {
            em.getTransaction().begin();
            em.merge(assignment);
            em.getTransaction().commit();
            return new AssignmentDTO(assignment);
        } finally {
            em.close();
        }
    }


}
