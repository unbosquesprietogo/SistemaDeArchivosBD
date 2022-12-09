package co.edu.unbosque.sistemadearchivosbd.controller;

import co.edu.unbosque.sistemadearchivosbd.model.dto.Empleado;
import co.edu.unbosque.sistemadearchivosbd.model.services.EmpleadoService;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Path("/empleados/{empleado}")
public class EmpleadoResource {

        @Context
        ServletContext context;

        static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://192.168.0.16/nomina";
        static final String USER = "sprietogo";
        static final String PASS = "sprietogo";

        @GET

        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)


        public Response employeeData(@PathParam("empleado") int codigo) {

            // Objects for handling connection
            Connection conn = null;
            Empleado empleado = null;

            try {

                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

                EmpleadoService empleadoService = new EmpleadoService(conn);
                empleado = empleadoService.infoPersonal(codigo);

                conn.close();
            } catch (SQLException se) {
                se.printStackTrace(); //
            } catch (ClassNotFoundException e) {
                e.printStackTrace(); //
            } finally {
                try {
                    if (conn != null) conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            return Response.ok().entity(empleado).build();

        }

}
