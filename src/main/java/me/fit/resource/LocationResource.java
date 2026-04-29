package me.fit.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.exception.UserNotFoundException;
import me.fit.model.LocationResponse;
import me.fit.model.User;
import me.fit.rest.client.IpApi;
import me.fit.rest.client.LocationApi;
import me.fit.service.UserService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/location")
public class LocationResource {

    @Inject
    @RestClient
    private IpApi ipApi;

    @Inject
    @RestClient
    private LocationApi locationApi;

    @Inject
    private UserService userService;

    @GET
    @Path("/getLocationByUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocationByUser(@QueryParam("userId") Long userId){
        try {
            String ipAddress = ipApi.getPublicIp().trim();
            LocationResponse location = locationApi.getLocationByIp(ipAddress);
            userService.addLocationToUser(userId, location);
            return Response.ok().entity(location).build();
        } catch (UserNotFoundException e) {
            return Response.ok().status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }

}
