package com.manishk;

import org.glassfish.jersey.server.ManagedAsync;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

/**
 * Created by manish on 15/04/15.
 */
@Path("/async")
public class AsyncResource {

    @GET
    @ManagedAsync
    @Produces(MediaType.APPLICATION_JSON)
    public void asyncMethod(@Suspended AsyncResponse response){

    }
}
