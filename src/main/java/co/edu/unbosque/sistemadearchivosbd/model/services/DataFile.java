package co.edu.unbosque.sistemadearchivosbd.model.services;

import jakarta.servlet.ServletContext;
import jakarta.ws.rs.core.MultivaluedMap;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.time.LocalDateTime;

public class DataFile {

    private Connection conn;
    private String UPLOAD_DIRECTORY = "DATA";

    public DataFile(){

    }
    public DataFile(Connection conn) {
        this.conn = conn;
    }

    public void saveFile(InputStream uploadedInputStream, String fileName, ServletContext context) {
        int read = 0;
        byte[] bytes = new byte[1024];

        try {
            // Complementing servlet path with the relative path on the server
            String uploadPath = context.getRealPath("") + java.io.File.separator + UPLOAD_DIRECTORY + java.io.File.separator;

            System.out.println(uploadPath);
            // Creating the upload folder, if not exist
            java.io.File uploadDir = new java.io.File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();

            // Persisting the file by output stream
            OutputStream outpuStream = new FileOutputStream(uploadPath+fileName);
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }

            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String parseFileName(MultivaluedMap<String, String> headers) {
        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");

        for (String name : contentDispositionHeader) {
            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                String fileName = tmp[1].trim().replaceAll("\"", "");
                return fileName;
            }
        }
        return "unknown";
    }




}
