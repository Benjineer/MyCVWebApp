/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apis;

import Utils.Auth.JwtAuth;
import Utils.Auth.AuthenticatedUser;
import entities.Award;
import entities.Owner;
import entities.Skill;
import enums.Role;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;
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
public class OwnerAPI {

    @PersistenceContext
    private EntityManager em;

    @Inject
    @AuthenticatedUser
    Owner authenticatedOwner;

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
            JsonArray ja = ownerJson.getJsonArray("skills");
            List<Skill> skills = new LinkedList<>();
            for (JsonValue jv : ja) {
                Skill skill = new Skill(jv.toString());
                skills.add(skill);
            }
            owner.setSkills(skills);
        }

        if (ownerJson.containsKey("awards")) {
            JsonArray ja = ownerJson.getJsonArray("awards");
            List<Award> awards = new LinkedList<>();
            for (JsonValue jv : ja) {
                Award award = new Award(jv.toString());
                awards.add(award);
            }
            owner.setAwards(awards);
        }

        if (ownerJson.containsKey("linkedInLink")) {
            String linkedInLink = ownerJson.getString("linkedInLink");
            owner.setLinkedInLink(linkedInLink);
        }

        em.merge(owner);

        JsonObject jo = Json.createObjectBuilder()
                .add("surname", owner.getSurname())
                .add("firstname", owner.getFirstname())
                .add("othername", owner.getOthername())
                .add("phoneNo", owner.getPhoneNo())
                .add("homeAddress", owner.getHomeAddress())
                .add("profilePix", owner.getProfilePixPath())
                .add("objectives", owner.getMissionStatement())
                .add("linkedInLink", owner.getLinkedInLink())
                .build();

        return Response.ok(jo).build();

    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response fetchBasicProfile() {

        Owner owner = em.createQuery("SELECT o FROM Owner o WHERE o.email=:email", Owner.class)
                .setParameter("email", "oke3ben@gmail.com").getSingleResult();

        JsonArrayBuilder jab = Json.createArrayBuilder();

        List<Skill> skills = owner.getSkills();
        skills.forEach((skill) -> {
            jab.add(skill.getSkill());
        });

        JsonObject jo = Json.createObjectBuilder()
                .add("surname", owner.getSurname())
                .add("firstname", owner.getFirstname())
                .add("othername", owner.getOthername())
                .add("email", owner.getEmail())
                .add("phoneNo", owner.getPhoneNo())
                .add("homeAddress", owner.getHomeAddress())
                .add("profilePix", owner.getProfilePixPath())
                .add("objectives", owner.getMissionStatement())
                .add("linkedInLink", owner.getLinkedInLink())
                .add("skills", jab)
                .build();

        return Response.ok(jo).build();

    }

}
