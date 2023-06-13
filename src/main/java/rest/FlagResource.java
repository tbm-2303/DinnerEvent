package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.FlagDTO;
import entities.Flag;
import entities.User;
import errorhandling.NotFoundException;
import facades.FlagFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
@Path("flag")
public class FlagResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final FlagFacade FACADE =  FlagFacade.getFlagFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"flag endpoint\"}";
    }

    @Path("{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Long id) throws NotFoundException {
        FlagDTO found = new FlagDTO(FACADE.getById(id));
        return Response.ok().entity(GSON.toJson(found)).build();
    }

    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        List<FlagDTO> dtoList = new ArrayList<>();
        for (Flag flag : FACADE.getAll()) {
            dtoList.add(new FlagDTO(flag));
        }
        return Response.ok().entity(GSON.toJson(dtoList)).build();
    }

}