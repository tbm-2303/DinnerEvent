package facades;

import dtos.EventDTO;
import entities.Assignment;
import entities.Event;
import entities.Member;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class EventFacadeTest {

    private static EntityManagerFactory emf;
    private static EventFacade facade;

    Event event1, event2, event3;
    Assignment assignment1, assignment2;
    Member member1, member2;
    User u1;
    User u2;

    public EventFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = EventFacade.getEventFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        assignment1 = new Assignment("Larsen", "11-11-23", "121212");
        assignment2 = new Assignment("Rasmussen", "12-12-23", "2323232");
        event1 = new Event("10:00", "Helsingør", "risengrød", 100);
        event2 = new Event("23:00", "Helsingør", "butter chicken", 100);
        event3 = new Event("12:00", "Helsingør", "risengrød", 10);
        member1 = new Member("stomlugen 11", "222", "dd@dd.dk", 1999, 1000);
        member2 = new Member("nordhavnsvej 22", "3333", "ff@dd.dk", 1992, 1000);
        u1 = new User("timmy", "timmy123");
        u2 = new User("lars", "lars123");


        member1.addUser(u1);
        member2.addUser(u2);

        assignment1.addEvent(event1);
        assignment2.addEvent(event2);

        assignment1.addMember(member1);//assingment 1 has 1 member
        assignment2.addMember(member2);//assignment 2 has 2 members
        assignment2.addMember(member1);


        try {
            em.getTransaction().begin();
            em.createNamedQuery("Assignment.deleteAllRows").executeUpdate();
            em.createNamedQuery("Event.deleteAllRows").executeUpdate();
            em.createNamedQuery("Member.deleteAllRows").executeUpdate();
            em.createNamedQuery("User.deleteAllRows").executeUpdate();
            em.getTransaction().commit();


            em.getTransaction().begin();
            em.persist(event1);
            em.persist(event2);
            em.persist(event3);
            em.persist(member1);
            em.persist(member2);
            em.persist(u1);
            em.persist(u1);
            em.persist(assignment1);
            em.persist(assignment2);


        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }


    @Test
    void createEvent() {
        Event event = new Event("11-11-23", "Helsingør", "dahl", 25);
        EventDTO eventDTO = new EventDTO(event);
        EventDTO created = facade.createNewEvent(eventDTO);
        assertNotNull(created.getId());
        assertEquals(1, facade.getAllEvents().size());

    }
@Disabled
    @Test
    void deleteEvent() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            event1 = new Event("11-11-23", "Helsingør", "risengrød", 20);
            assignment1 = new Assignment("Larsen", "11-11-23", "121212");
            assignment1.addEvent(event1);
            em.persist(event1);
            em.persist(assignment1);
            em.getTransaction().commit();


        } finally {
            em.close();
        }
        facade.deleteEvent(1L);

        assertEquals(facade.getAllEvents().size(), 1);

    }


}
