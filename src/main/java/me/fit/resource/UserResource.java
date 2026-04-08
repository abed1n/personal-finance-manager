package me.fit.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.model.Account;
import me.fit.model.User;
import me.fit.service.UserService;

import java.util.List;

@Path("/user")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public Response addUser(User user) {
        userService.createUser(user);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllUsers")
    @RolesAllowed("admin")
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok().entity(users).build();
    }

    @GET
    @Path("/getAccountsByUserId")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    public Response getAccountsByUserId(@QueryParam("id") Long id) {
        List<Account> accounts = userService.getAccountsByUserId(id);
        return Response.ok().entity(accounts).build();
    }

}
