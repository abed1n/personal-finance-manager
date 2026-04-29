package me.fit.rest.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import me.fit.model.CurrencyResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api")
@RegisterRestClient(configKey = "currency-api")
public interface CurrencyApi {

    @GET
    @Path("/rates")
    public CurrencyResponse currencyConversion(@QueryParam("from") String from, @QueryParam("to") String to);


}
