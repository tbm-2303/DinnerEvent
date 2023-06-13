package facades;

import dtos.PersonDTO;
import dtos.HobbyDTO;
import entities.Hobby;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade {

    private static PersonFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private PersonFacade() {
    }


    /**
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public boolean removeHobbyFromPerson(Long personId, Long hobbyId) {
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, personId);
        Hobby hobby = em.find(Hobby.class, hobbyId);
        if (person == null || hobby == null)
            throw new IllegalArgumentException("Person or Hobby not found");
        person.removeHobby(hobby);
        try {
            em.getTransaction().begin();
            em.merge(person);
            em.getTransaction().commit();
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    public PersonDTO addHobby(Long personId, Long hobbyId) {
        EntityManager em = getEntityManager();
        Person person = em.find(Person.class, personId);
        Hobby hobby = em.find(Hobby.class, hobbyId);
        person.addHobby(hobby);
        try {
            em.getTransaction().begin();
            em.merge(person);
            // em.merge(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);

    }

    public HobbyDTO createHobby(HobbyDTO hdto) {
        Hobby hobby = new Hobby(hdto.getName(), hdto.getDescription());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new HobbyDTO(hobby);
    }


    public PersonDTO create(PersonDTO pdto) {
        Person person = new Person(pdto.getName(), pdto.getAge());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(person);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(person);
    }

    public PersonDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new PersonDTO(person);
    }

    public List<PersonDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> rms = query.getResultList();
        return PersonDTO.getDtos(rms);
    }


}
