package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AssignmentDTO;
import dtos.MemberDTO;
import facades.AssignmentFacade;
import facades.MemberFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author lam@cphbusiness.dk
 */
@Path("member")
public class MemberResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final MemberFacade FACADE = MemberFacade.getMemberFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello member\"}";
    }

  //member by user name
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{username}")
    public Response getBalance(@PathParam("username") String username) {
        MemberDTO member = FACADE.getMemberByUsername(username);
        return Response.ok().entity(GSON.toJson(member)).build();
    }

}