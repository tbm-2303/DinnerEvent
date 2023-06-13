package facades;

import dtos.EventDTO;
import entities.*;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class AssignmentFacadeTest {

    private static EntityManagerFactory emf;
    private static AssignmentFacade facade;

    Event Event1, Event2, Event3;
    Assignment assignment1, assignment2;
    Member member1, member2;


    public AssignmentFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = AssignmentFacade.getAssignmentFacade(emf);
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
        Event1 = new Event("10:00", "Helsingør", "Tikka masala", 100);
        Event2 = new Event("23:00", "Helsingør", "butter chicken", 100);
        Event3 = new Event("12:00", "Helsingør", "risengrød", 10);
        member1 = new Member("stomlugen 11", "222", "dd@dd.dk", 1999, 1000);
        member2 = new Member("nordhavnsvej 22", "3333", "ff@dd.dk", 1992, 1000);
        User user1 = new User("timmy", "timmy123");
        User user2 = new User("lars", "lars123");


        member1.addUser(user1);
        member2.addUser(user2);

        assignment1.addEvent(Event1);
        assignment2.addEvent(Event2);

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
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }




}
