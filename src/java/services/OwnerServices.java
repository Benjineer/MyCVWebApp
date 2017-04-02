/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Owner;
import javax.ejb.Stateless;
import javax.json.JsonObject;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author NIYI
 */
@Stateless
public class OwnerServices implements OwnerServicesLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public Owner createOwner(JsonObject ownerJson) {

        Owner owner = new Owner();
        String surname = ownerJson.getString("surname");
        String email = ownerJson.getString("email");
        String phoneNo = ownerJson.getString("phoneNo");
        owner.setSurname(surname);

        String firstname = ownerJson.getString("firstname");
        owner.setFirstname(firstname);

        String othername = ownerJson.getString("othername");
        owner.setOthername(othername);

        owner.setEmail(email);

        String password = ownerJson.getString("password");
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        owner.setPassword(hashedPassword);

        owner.setPhoneNo(phoneNo);

        return owner;

    }

    @Override
    public boolean authenticateUser(String plainPassword, String hashedPassword) {
        boolean match = BCrypt.checkpw(plainPassword, hashedPassword);
        return match;

    }

}
