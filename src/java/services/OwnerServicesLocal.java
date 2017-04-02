/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Owner;
import javax.ejb.Local;
import javax.json.JsonObject;

/**
 *
 * @author NIYI
 */
@Local
public interface OwnerServicesLocal {

    public Owner createOwner(JsonObject ownerJson);

    public boolean authenticateUser(String plainPassword, String hashedPassword);

}
