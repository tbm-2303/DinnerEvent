package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.FlagDTO;
import dtos.JokeDTO;
import dtos.RenameMeDTO;
import entities.Flag;
import entities.RenameMe;
import errorhandling.NotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.Fetch;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FlagFacade {
    private static FlagFacade instance;
    private static EntityManagerFactory emf;
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //Private Constructor to ensure Singleton
    private FlagFacade() {}
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FlagFacade getFlagFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FlagFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static String fetchData(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json"); // Add this line
        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        } catch (Exception e) {
            System.out.println("Error in fetchData");
            e.printStackTrace();
            System.out.println(e.getStackTrace());
        }
        return null;
    }


    public Flag create(Flag flag) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(flag);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return flag;
    }

    public Flag update(Flag flag) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Flag found = em.find(Flag.class, flag.getId());
        if (found == null) {
            throw new NotFoundException("Entity with ID: " + flag.getId() + " not found");
        }
        // update values here
        try {
            em.getTransaction().begin();
            Flag updated = em.merge(flag);
            em.getTransaction().commit();
            return updated;
        } finally {
            em.close();
        }
    }

    public Flag delete(Long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Flag found = em.find(Flag.class, id);
        if (found == null) {
            throw new NotFoundException("Could not remove Entity with id: " + id);
        }
        try {
            em.getTransaction().begin();
            em.remove(found);
            em.getTransaction().commit();
            return found;
        } finally {
            em.close();
        }
    }
    public Flag getById(Long id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        Flag flag;
        try {
            flag = em.find(Flag.class, id);
            if (flag == null) {
                throw new NotFoundException();
            }
        } finally {
            em.close();
        }
        return flag;
    }
    public List<Flag> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Flag> query = em.createQuery("SELECT z FROM Flag z", Flag.class);
        return query.getResultList();
    }


    public Flag getByName(String name) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Flag> query = em.createQuery("SELECT f FROM Flag f WHERE f.countryName = '"+name+"'", Flag.class);
        List<Flag> flags = query.getResultList();
        if (flags.size() == 0) {
            throw new NotFoundException("No flag found named '"+name+"'");
        }
        return flags.get(0);
    }



}
