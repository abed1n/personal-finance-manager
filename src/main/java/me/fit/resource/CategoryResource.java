package me.fit.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import me.fit.model.Category;
import me.fit.service.CategoryService;

@Path("/category")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addCategory")
    public Response addCategory(Category category) {
        categoryService.addCategory(category);
        return Response.ok().build();
    }

}
