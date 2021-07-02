package com.flipkart.resources;

import com.flipkart.bean.Auth;
import com.flipkart.services.PersonService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private final PersonService personService;

    public PersonResource(PersonService personService){
        this.personService = personService;
    }

    @GET
    @Path("/name/{id}")
    public Response getNameById(@PathParam("id") Integer id){
        String name = personService.getNameById(String.valueOf(id));

        if(name != null){
            return Response.ok(name).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/")
    public Response createPerson(@FormParam("id") String id, @FormParam("name") String name, @FormParam("age") String age){
        String resp = personService.insertPerson(id, name, age);
        return Response.status(Response.Status.CREATED).entity(resp).build();
    }

    @GET
    @Path("/{id}")
    public Response getPersonById(@PathParam("id") String id){
        Auth auth = personService.getPersonById(id);

        if(auth != null){
            return Response.ok(auth).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
