package co.edu.unbosque.sistemadearchivosbd.controller;

import co.edu.unbosque.sistemadearchivosbd.model.dto.Nomina;
import co.edu.unbosque.sistemadearchivosbd.model.services.NominaService;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/nomina")
public class NominaResource {


    @Context
    ServletContext context;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.0.16/nomina";
    static final String USER = "sprietogo";
    static final String PASS = "sprietogo";

    @GET
    @Path("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response listEmployees(Nomina nomina, @QueryParam("orden") String orden) {

        // Objects for handling connection
        Connection conn = null;
        List<Nomina> employees = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            NominaService nominaService = new NominaService(conn);
            employees = nominaService.getListEmployees(nomina.getDependencia(), nomina.getCargo(), orden);

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
        return Response.ok().entity(employees).build();

    }

    @GET
    @Path("/dependencias")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response cantDependencias (){

        // Objects for handling connection
        Connection conn = null;
        List<Nomina> cantidadDependencias = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            NominaService nominaService = new NominaService(conn);
            cantidadDependencias = nominaService.getCantidadDependencias();

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
        return Response.ok().entity(cantidadDependencias).build();


    }

    @GET
    @Path("/dependencias/cargos")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Response cantCargos (){

        // Objects for handling connection
        Connection conn = null;
        List<Nomina> cantidadCargos = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            NominaService nominaService = new NominaService(conn);
            cantidadCargos = nominaService.getCantidadCargos();

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
        return Response.ok().entity(cantidadCargos).build();


    }
}
