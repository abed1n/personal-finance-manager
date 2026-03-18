package me.fit.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    public String addUser(User user) {
        userService.createUser(user);
        return "User created successfully";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllUsers")
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok().entity(users).build();
    }
}
