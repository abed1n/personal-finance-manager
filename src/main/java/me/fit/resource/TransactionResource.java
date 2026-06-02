package me.fit.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.model.MultipartBody;
import me.fit.model.Transaction;
import me.fit.model.UploadedFile;
import me.fit.service.TransactionService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @GET
    @Path("/getTransactionWithFiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionWithFiles(@QueryParam("id") Long id) {
        Transaction transaction = transactionService.getTransactionWithFiles(id);
        if (transaction == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Transaction with id " + id + " not found")
                    .build();
        }
        return Response.ok().entity(transaction).build();
    }

    @POST
    @Path("/uploadFile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadFile(@QueryParam("id") Long id, MultipartBody multipartBody) {
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Transaction with id " + id + " not found")
                    .build();
        }

        java.nio.file.Path uploadDir = Paths.get("uploads");
        java.nio.file.Path destination = uploadDir.resolve(multipartBody.filename);

        if (Files.exists(destination)) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("Fajl vec postoji na putanji: " + destination.toAbsolutePath())
                    .build();
        }

        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            Files.copy(multipartBody.file.uploadedFile(), destination);
        } catch (IOException e) {
            return Response.serverError().build();
        }

        UploadedFile uploadedFile = new UploadedFile();
        uploadedFile.setFilename(destination.toAbsolutePath().toString());
        transactionService.addUploadedFileToTransaction(id, uploadedFile);

        return Response.ok().build();
    }
}
