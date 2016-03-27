/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.personrest;

import com.rorrell.personrest.data.PersonDaoDynamo;
import com.rorrell.personrest.model.Person;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author rachel
 */
public class PersonResource {

    private String id;

    /**
     * Creates a new instance of PersonResource
     */
    private PersonResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the PersonResource
     */
    public static PersonResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of PersonResource class.
        return new PersonResource(id);
    }

    /**
     * Retrieves representation of an instance of com.rorrell.personrest.PersonResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Person getJson() {
        return PersonDaoDynamo.getInstance().findById(Long.parseLong(this.id));
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource PersonResource
     */
    @DELETE
    public void delete() {
    }
}
