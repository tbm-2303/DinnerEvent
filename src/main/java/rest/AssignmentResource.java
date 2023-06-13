package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AssignmentDTO;
import dtos.EventDTO;
import facades.AssignmentFacade;
import facades.EventFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
@Path("assignment")
public class AssignmentResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final AssignmentFacade FACADE = AssignmentFacade.getAssignmentFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello assignment\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/all")
    public Response getAllEvents() {
        List<EventDTO> events = FACADE.getAllAssignments();
        return Response.ok().entity(GSON.toJson(events)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllByUsername/{username}")
    public Response getAllByUsername(@PathParam("username") String username) {
        List<AssignmentDTO> assignments = FACADE.getAssignmentsByUsername(username);
        return Response.ok().entity(GSON.toJson(assignments)).build();
    }
}