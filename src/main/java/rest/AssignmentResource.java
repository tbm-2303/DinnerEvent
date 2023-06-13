package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AssignmentDTO;
import dtos.EventDTO;
import dtos.MemberDTO;
import facades.AssignmentFacade;
import facades.EventFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
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
    public Response getAllAssignments() {
        List<AssignmentDTO> assignments = FACADE.getAllAssignments();
        return Response.ok().entity(GSON.toJson(assignments)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllByUsername/{username}")
    public Response getAllByUsername(@PathParam("username") String username) {
        List<AssignmentDTO> assignments = FACADE.getAssignmentsByUsername(username);
        return Response.ok().entity(GSON.toJson(assignments)).build();
    }

    //get all assignments by event id
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllByEventId/{id}")
    public Response getAllByEventId(@PathParam("id") Long eventId) {
        List<AssignmentDTO> assignments = FACADE.getAssignmentsByEventId(eventId);
        return Response.ok().entity(GSON.toJson(assignments)).build();
    }

    //get asssignment by id
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getById/{id}")
    public Response getById(@PathParam("id") Long id) {
        AssignmentDTO assignment = FACADE.getAssignmentById(id);
        return Response.ok().entity(GSON.toJson(assignment)).build();
    }

    //get members from assignment
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getMembers/{id}")
    public Response getMembers(@PathParam("id") Long assignmentId) {
        List<MemberDTO> members = FACADE.getMembersFromAssignment(assignmentId);
        return Response.ok().entity(GSON.toJson(members)).build();
    }


    //remove member from assignment
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/removeMember/{aId}/{mId}")
    public Response removeMember(@PathParam("aId") Long assignmentId, @PathParam("mId") Long memberId) {
        AssignmentDTO assignment = FACADE.removeMemberFromAssignment(assignmentId, memberId);
        return Response.ok().entity(GSON.toJson(assignment)).build();
    }
}