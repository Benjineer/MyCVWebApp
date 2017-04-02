/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils.Auth;

import entities.Owner;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author NIYI
 */
@RequestScoped
public class AuthenticatedUserProducer {

    @Produces
    @RequestScoped
    @AuthenticatedUser
    private Owner authenticatedOwner;

    @PersistenceContext
    private EntityManager em;

    public void handleAuthenticationEvent(@Observes @AuthenticatedUser String email) {
        this.authenticatedOwner = findUser(email);
    }

    private Owner findUser(String email) {
        // Hit the the database or a service to find a user by its username and return it
        // Return the User instance
        Owner owner = em.createQuery("SELECT o FROM Owner o WHERE o.email=:email", Owner.class)
                .setParameter("email", email).getSingleResult();

        return owner;
    }

}
