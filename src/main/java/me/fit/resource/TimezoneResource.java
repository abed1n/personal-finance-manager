package me.fit.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.exception.UserNotFoundException;
import me.fit.model.TimezoneResponse;
import me.fit.rest.client.IpApi;
import me.fit.rest.client.TimezoneApi;
import me.fit.service.UserService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/timezone")
public class TimezoneResource {

    @Inject
    @RestClient
    private IpApi ipApi;

    @Inject
    @RestClient
    private TimezoneApi timezoneApi;

    @Inject
    private UserService userService;

    @GET
    @Path("/getTimezone")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimezone() {
        String ipAddress = ipApi.getPublicIp().trim();
        TimezoneResponse timezoneResponse = timezoneApi.getTimeByIp(ipAddress);
        return Response.ok().entity(timezoneResponse).build();
    }

    @GET
    @Path("/getTimezoneByIP")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTimezoneByIP(@QueryParam("userId") Long userId) {
        try {
            String ipAddress = ipApi.getPublicIp().trim();
            TimezoneResponse timezoneResponse = timezoneApi.getTimeByIp(ipAddress);
            userService.addTimezoneToUser(userId, timezoneResponse);
            return Response.ok().entity(timezoneResponse).build();
        } catch (UserNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
