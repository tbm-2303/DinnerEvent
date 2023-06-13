package facades;

import dtos.AssignmentDTO;
import dtos.MemberDTO;
import entities.Assignment;
import entities.Member;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MemberFacade {


    private static EntityManagerFactory emf;
    private static MemberFacade instance;

    //Private Constructor to ensure Singleton
    private MemberFacade() {
    }

    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



//get member by username
    public MemberDTO getMemberByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            User u = em.find(User.class, username);
            Member m = em.find(Member.class, u.getMember().getId());
            return new MemberDTO(m);
        } finally {
            em.close();
        }
    }
}
