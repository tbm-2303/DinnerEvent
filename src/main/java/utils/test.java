package utils;


import entities.Assignment;
import entities.Member;
import entities.Role;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class test {

  public static void main(String[] args) {
    removeMemberFromAssignment();


  }

  public static void a() {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    User rene = new User("rene", "rene123");
    User timmy = new User("timmy", "timmy123");
    User nico = new User("nico", "nico123");

    Role userRole = em.find(Role.class, "user");

    rene.addRole(userRole);
    timmy.addRole(userRole);
    nico.addRole(userRole);

    Member m1 = new Member("Rene", "23", "@dfwd", 1991, 1000);
    Member m2 = new Member("Timmy", "23", "@dfwd", 1996, 1000);
    Member m3 = new Member("Nico", "23", "@dfwd", 1996, 1000);

    m1.addUser(rene);
    m2.addUser(timmy);
    m3.addUser(nico);


    try {
      em.getTransaction().begin();
      em.persist(rene);
      em.persist(timmy);
      em.persist(nico);
      em.persist(m1);
      em.persist(m2);
      em.persist(m3);
      em.getTransaction().commit();
    } finally {
      em.close();
    }


  }

  public static void removeMemberFromAssignment() {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    try {
      Member m = em.find(Member.class, 2L);//timmy
      Assignment a = em.find(Assignment.class, 1L);//assignment 1
      a.removeMember(m);


      em.getTransaction().begin();
      em.merge(a);
      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }

  public static void addMemberToAssignment() {
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    try {
      Member m = em.find(Member.class, 2L);//timmy
      Assignment a = em.find(Assignment.class, 2L);//assignment 1
      a.addMember(m);

      em.getTransaction().begin();
      em.merge(a);
      em.getTransaction().commit();
    } finally {
      em.close();
    }

  }

}
