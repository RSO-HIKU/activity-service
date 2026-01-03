package com.hiku.activityService.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.PermitAll;

@Path("/hello")
public class Hello {

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello, Activity Service!";
    }
}
