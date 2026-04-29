package me.fit.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.exception.UserNotFoundException;
import me.fit.model.CurrencyResponse;
import me.fit.rest.client.CurrencyApi;
import me.fit.service.UserService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/currency")
public class CurrencyResource {

    @Inject
    @RestClient
    private CurrencyApi currencyApi;

    @Inject
    private UserService userService;

    @GET
    @Path("/currencyConversion")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"admin"})
    public Response currencyConversion(@QueryParam("from") String from, @QueryParam("to") String to, @QueryParam("value") double value, @QueryParam("userId") Long userId){
        try {
            CurrencyResponse currencyResponse = currencyApi.currencyConversion(from, to);
            currencyResponse.setConvertedValue(currencyResponse.getRate() * value);
            currencyResponse.setValue(value);
            userService.addCurrencyConversionToUser(userId, currencyResponse);
            return Response.ok().entity(currencyResponse).build();
        } catch (UserNotFoundException e) {
            return Response.ok().status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }


    }

}
