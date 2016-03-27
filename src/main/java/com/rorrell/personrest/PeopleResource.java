/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rorrell.personrest;

import com.rorrell.personrest.data.PersonDaoDynamo;
import com.rorrell.personrest.model.Person;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author rachel
 */
@Path("/ps")
public class PeopleResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PeopleResource
     */
    public PeopleResource() {
    }

    /**
     * Retrieves representation of an instance of com.rorrell.personrest.PeopleResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Person> getJson() {
        return PersonDaoDynamo.getInstance().getAll();
    }

    /**
     * POST method for creating an instance of PersonResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public PersonResource getPersonResource(@PathParam("id") String id) {
        return PersonResource.getInstance(id);
    }
}
