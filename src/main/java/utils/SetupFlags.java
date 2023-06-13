package utils;


import entities.Flag;
import entities.Role;
import entities.User;
import errorhandling.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;

public class SetupFlags {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    Flag germany = new Flag("Germany", "https://www.countryflagicons.com/FLAT/64/DE.png");
    Flag england = new Flag("England", "https://www.countryflagicons.com/FLAT/64/GB.png");
    Flag france = new Flag("France", "https://www.countryflagicons.com/FLAT/64/FR.png");
    Flag italy = new Flag("Italy", "https://www.countryflagicons.com/FLAT/64/IT.png");
    Flag spain = new Flag("Spain", "https://www.countryflagicons.com/FLAT/64/ES.png");
    Flag ukraine = new Flag("Ukraine", "https://www.countryflagicons.com/FLAT/64/UA.png");
    Flag russia = new Flag("Russia", "https://www.countryflagicons.com/FLAT/64/RU.png");
    Flag poland = new Flag("Poland", "https://www.countryflagicons.com/FLAT/64/PL.png");
    Flag romania = new Flag("Romania", "https://www.countryflagicons.com/FLAT/64/RO.png");
    Flag netherlands = new Flag("Netherlands", "https://www.countryflagicons.com/FLAT/64/NL.png");
    Flag belgium = new Flag("Belgium", "https://www.countryflagicons.com/FLAT/64/BE.png");
    Flag greece = new Flag("Greece", "https://www.countryflagicons.com/FLAT/64/GR.png");
    Flag portugal = new Flag("Portugal", "https://www.countryflagicons.com/FLAT/64/PT.png");
    Flag sweden = new Flag("Sweden", "https://www.countryflagicons.com/FLAT/64/SE.png");
    Flag hungary = new Flag("Hungary", "https://www.countryflagicons.com/FLAT/64/HU.png");
    Flag belarus = new Flag("Belarus", "https://www.countryflagicons.com/FLAT/64/BY.png");
    Flag austria = new Flag("Austria", "https://www.countryflagicons.com/FLAT/64/AT.png");
    Flag serbia = new Flag("Serbia", "https://www.countryflagicons.com/FLAT/64/RS.png");
    Flag switzerland = new Flag("Switzerland", "https://www.countryflagicons.com/FLAT/64/CH.png");
    Flag denmark = new Flag("Denmark", "https://www.countryflagicons.com/FLAT/64/DK.png");
    Flag norway = new Flag("Norway", "https://www.countryflagicons.com/FLAT/64/NO.png");
    Flag bulgaria = new Flag("Bulgaria", "https://www.countryflagicons.com/FLAT/64/BG.png");
    Flag finland = new Flag("Finland", "https://www.countryflagicons.com/FLAT/64/FI.png");
    Flag slovakia = new Flag("Slovakia", "https://www.countryflagicons.com/FLAT/64/SK.png");
    Flag ireland = new Flag("Ireland", "https://www.countryflagicons.com/FLAT/64/IE.png");
    Flag croatia = new Flag("Croatia", "https://www.countryflagicons.com/FLAT/64/HR.png");
    Flag moldova = new Flag("Moldova", "https://www.countryflagicons.com/FLAT/64/MD.png");
    Flag bosnia = new Flag("Bosnia & Herz.", "https://www.countryflagicons.com/FLAT/64/BA.png");
    Flag albania = new Flag("Albania", "https://www.countryflagicons.com/FLAT/64/AL.png");
    Flag lithuania = new Flag("Lithuania", "https://www.countryflagicons.com/FLAT/64/LT.png");
    Flag northMacedonia = new Flag("North Macedonia", "https://www.countryflagicons.com/FLAT/64/MK.png");
    Flag slovenia = new Flag("Slovenia", "https://www.countryflagicons.com/FLAT/64/SI.png");
    Flag latvia = new Flag("Latvia", "https://www.countryflagicons.com/FLAT/64/LV.png");
    Flag estonia = new Flag("Estonia", "https://www.countryflagicons.com/FLAT/64/EE.png");
    Flag montenegro = new Flag("Montenegro", "https://www.countryflagicons.com/FLAT/64/ME.png");
    Flag luxembourg = new Flag("Luxembourg", "https://www.countryflagicons.com/FLAT/64/LU.png");
    Flag malta = new Flag("Malta", "https://www.countryflagicons.com/FLAT/64/MT.png");
    Flag iceland = new Flag("Iceland", "https://www.countryflagicons.com/FLAT/64/IS.png");
    Flag andorra = new Flag("Andorra", "https://www.countryflagicons.com/FLAT/64/AD.png");
    Flag monaco = new Flag("Monaco", "https://www.countryflagicons.com/FLAT/64/MC.png");
    Flag liechtenstein = new Flag("Liechtenstein", "https://www.countryflagicons.com/FLAT/64/LI.png");
    Flag sanMarino = new Flag("San Marino", "https://www.countryflagicons.com/FLAT/64/SM.png");


    try {
      em.getTransaction().begin();
      em.persist(germany);
      em.persist(england);
      em.persist(france);
      em.persist(italy);
      em.persist(spain);
      em.persist(ukraine);
      em.persist(russia);
      em.persist(poland);
      em.persist(romania);
      em.persist(netherlands);
      em.persist(belgium);
      em.persist(greece);
      em.persist(portugal);
      em.persist(sweden);
      em.persist(hungary);
      em.persist(belarus);
      em.persist(austria);
      em.persist(serbia);
      em.persist(switzerland);
      em.persist(denmark);
      em.persist(norway);
      em.persist(bulgaria);
      em.persist(finland);
      em.persist(slovakia);
      em.persist(finland);
      em.persist(ireland);
      em.persist(croatia);
      em.persist(moldova);
      em.persist(bosnia);
      em.persist(albania);
      em.persist(lithuania);
      em.persist(northMacedonia);
      em.persist(slovenia);
      em.persist(latvia);
      em.persist(estonia);
      em.persist(montenegro);
      em.persist(luxembourg);
      em.persist(malta);
      em.persist(iceland);
      em.persist(andorra);
      em.persist(monaco);
      em.persist(liechtenstein);
      em.persist(sanMarino);

      em.getTransaction().commit();
    } finally {
      em.close();
    }
  }







}
