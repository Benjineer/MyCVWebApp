/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apis;

import Utils.Auth.JwtAuth;
import Utils.Auth.AuthenticatedUser;
import entities.Owner;
import enums.Role;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author NIYI
 */
@Path("owner")
@Stateless
@JwtAuth
public class OwnerAPI {

    @PersistenceContext
    private EntityManager em;

    @Inject
    @AuthenticatedUser
    Owner authenticatedOwner;

    @Path("update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JwtAuth(Role.USER)
    public Response updateProfile(JsonObject ownerJson) {
        if (Objects.isNull(ownerJson) || ownerJson.isEmpty()) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Request Body is empty").build();
        }

        Owner owner = em.find(Owner.class, authenticatedOwner.getId());
        if (ownerJson.containsKey("phoneNo")) {
            String phoneNo = ownerJson.getString("phoneNo");
            owner.setPhoneNo(phoneNo);
        }

        if (ownerJson.containsKey("surname")) {
            String surname = ownerJson.getString("surname");
            owner.setSurname(surname);
        }
        if (ownerJson.containsKey("firstname")) {
            String firtname = ownerJson.getString("firstname");
            owner.setFirstname(firtname);
        }
        if (ownerJson.containsKey("othername")) {
            String othername = ownerJson.getString("othername");
            owner.setOthername(othername);
        }
        if (ownerJson.containsKey("homeAddress")) {
            String homeAddress = ownerJson.getString("homeAddress");
            owner.setHomeAddress(homeAddress);
        }
        if (ownerJson.containsKey("objectives")) {
            String objectives = ownerJson.getString("objectives");
            owner.setMissionStatement(objectives);
        }
        if (ownerJson.containsKey("skills")) {
            JsonArray skills = ownerJson.getJsonArray("skills");
            owner.setSkills(skills);
        }

        em.merge(owner);

        return Response.ok(owner.toString()).build();

    }

    @Path("profile")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JwtAuth(Role.USER)
    public Response fetchBasicProfile() {
        JsonObject jo = Json.createObjectBuilder()
                .add("surname", authenticatedOwner.getSurname())
                .add("firstname", authenticatedOwner.getFirstname())
                .add("othername", authenticatedOwner.getOthername())
                .add("email", authenticatedOwner.getEmail())
                .add("phoneNo", authenticatedOwner.getPhoneNo())
                .add("homeAddress", authenticatedOwner.getHomeAddress())
                .add("profilePix", authenticatedOwner.getProfilePixPath())
                .add("objectives", authenticatedOwner.getMissionStatement())
                .build();

        return Response.ok(jo).build();

    }

}
