package co.edu.unbosque.sistemadearchivosbd.controller;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import co.edu.unbosque.sistemadearchivosbd.model.services.DataFile;

@Path("/data/uploadData")
public class UploadFile {


    @Context
    ServletContext context;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response uploadData(MultipartFormDataInput inputData) {

        try {

            Map<String, List<InputPart>> formParts = inputData.getFormDataMap();
            List<InputPart> inputParts = formParts.get("formFile");

            for (InputPart inputPart : inputParts) {
                try {
                    DataFile datafile = new DataFile();
                    // Retrieving headers and reading the Content-Disposition header to file name
                    MultivaluedMap<String, String> headers = inputPart.getHeaders();
                    String fileName = "prueba" + "." + datafile.parseFileName(headers).split("\\.")[1];
                    // Handling the body of the part with an InputStream
                    InputStream istream = inputPart.getBody(InputStream.class, null);


                    datafile.saveFile(istream,fileName, context);


                } catch (IOException e) {
                    return Response.serverError().build();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Response.status(201)
                .entity("Data successfully uploaded")
                .build();
    }
}
