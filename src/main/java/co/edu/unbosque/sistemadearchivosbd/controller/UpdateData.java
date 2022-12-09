package co.edu.unbosque.sistemadearchivosbd.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/data/uploadData")
public class UpdateData {

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Response updateData() {


        return Response.ok()
                .entity("")
                .build();
    }


}
