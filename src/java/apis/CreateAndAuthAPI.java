/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apis;

import Utils.Auth.TokenIssuer;
import entities.Owner;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import services.OwnerServicesLocal;

/**
 *
 * @author NIYI
 */
@Stateless
@Path("user")
public class CreateAndAuthAPI {

    @Inject
    private OwnerServicesLocal ownerServicesLocal;

    @PersistenceContext
    private EntityManager em;

    @Context
    private UriInfo uriInfo;

    @Path("create")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOwner(JsonObject ownerJson) {

        if (Objects.isNull(ownerJson) || ownerJson.isEmpty() || !ownerJson.containsKey("email") || !ownerJson.containsKey("phoneNo")) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Request Body is empty").build();
        }
        String email = ownerJson.getString("email");
        String phoneNo = ownerJson.getString("phoneNo");
        List<Owner> result = em.createQuery("SELECT o FROM Owner o WHERE o.email =:email OR o.phoneNo =:phoneNo",
                Owner.class).setParameter("email", email).setParameter("phoneNo", phoneNo).getResultList();

        if (!result.isEmpty()) {
            return Response.status(Response.Status.CONFLICT).entity("Already Exists").build();
        } else {

            Owner owner = ownerServicesLocal.createOwner(ownerJson);
            em.persist(owner);

            return Response.ok(owner.toString()).status(Response.Status.CREATED).build();
        }

    }

    @Path("auth")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticate(JsonObject loginJson) {

        if (Objects.isNull(loginJson) || loginJson.isEmpty()) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Invalid Param values").build();

        }

        if (!loginJson.containsKey("email") || !loginJson.containsKey("password")) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Invalid Param values").build();

        }
        String email = loginJson.getString("email");
        String password = loginJson.getString("password");

        List<Owner> resultList = em.createQuery("SELECT o FROM Owner o WHERE o.email=:email", Owner.class
        )
                .setParameter("email", email).getResultList();

        if (resultList.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("Your email was not found to be a registered member").build();
        }

        Owner owner = resultList.get(0);
        boolean authenticated = ownerServicesLocal.authenticateUser(password, owner.getPassword());

        if (authenticated) {
            String token = TokenIssuer.issueToken(email, uriInfo);
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid email or password").build();
        }

    }

}
