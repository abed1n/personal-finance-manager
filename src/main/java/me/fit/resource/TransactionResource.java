package me.fit.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.model.Transaction;
import me.fit.service.TransactionService;

import java.util.List;

@Path("/transaction")
public class TransactionResource {

    @Inject
    private TransactionService transactionService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addTransaction")
    public Response addTransaction(Transaction transaction) {
        transactionService.addTransaction(transaction);
        return Response.ok().build();
    }

    @GET
    @Path("/getTransactionsByCategory")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionsByCategory(@QueryParam("categoryName") String categoryName) {
        List<Transaction> transactions = transactionService.getTransactionsByCategory(categoryName);
        return Response.ok().entity(transactions).build();
    }

    @GET
    @Path("/getTransactionsByType")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionsByType(@QueryParam("type") String type) {
        List<Transaction> transactions = transactionService.getTransactionsByType(type);
        return Response.ok().entity(transactions).build();
    }

    @GET
    @Path("/getTransactionsByAccountId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionsByAccountId(@QueryParam("id") Long id) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountId(id);
        return Response.ok().entity(transactions).build();
    }

    @GET
    @Path("/getTransactionsByCategoryId")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionsByCategoryId(@QueryParam("id") Long id) {
        List<Transaction> transactions = transactionService.getTransactionsByCategoryId(id);
        return Response.ok().entity(transactions).build();
    }
}
