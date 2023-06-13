package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.EventDTO;
import entities.User;
import facades.EventFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
@Path("event")
public class EventResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final EventFacade FACADE = EventFacade.getEventFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response getAllEvents() {
        List<EventDTO> events = FACADE.getAllEvents();
        return Response.ok().entity(GSON.toJson(events)).build();

    }
}