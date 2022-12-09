package co.edu.unbosque.sistemadearchivosbd.controller;

import co.edu.unbosque.sistemadearchivosbd.model.dto.AporteSocial;
import co.edu.unbosque.sistemadearchivosbd.model.services.AporteSocialService;
import jakarta.servlet.ServletContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Path("/AporteSocial")
public class AporteSocialResourse {

    @Context
    ServletContext context;

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.0.16/nomina";
    static final String USER = "sprietogo";
    static final String PASS = "sprietogo";

    @GET
    @Path("/eps")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listEPS() {

        // Objects for handling connection
        Connection conn = null;
        List<AporteSocial> epsLista = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            AporteSocialService aporteSocialService = new AporteSocialService(conn);
            epsLista = aporteSocialService.getCantEps();

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
        return Response.ok().entity(epsLista).build();

    }

    @GET
    @Path("/pensiones")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listPension() {

        // Objects for handling connection
        Connection conn = null;
        List<AporteSocial> pensionLista = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            AporteSocialService aporteSocialService = new AporteSocialService(conn);
            pensionLista = aporteSocialService.getCantPension();

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
        return Response.ok().entity(pensionLista).build();

    }

    @GET
    @Path("/eps/dependencias/{dependencia}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listEPSDepe(@PathParam("dependencia") String dependencia) {

        // Objects for handling connection
        Connection conn = null;
        List<AporteSocial> epsLista = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            AporteSocialService aporteSocialService = new AporteSocialService(conn);
            epsLista = aporteSocialService.getCantEPSDepe(dependencia);

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
        return Response.ok().entity(epsLista).build();

    }

    @GET
    @Path("/pensiones/dependencias/{dependencia}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listPensionDepe(@PathParam("dependencia") String dependencia) {

        // Objects for handling connection
        Connection conn = null;
        List<AporteSocial> pensionLista = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            AporteSocialService aporteSocialService = new AporteSocialService(conn);
            pensionLista = aporteSocialService.getCantPensionDepe(dependencia);

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
        return Response.ok().entity(pensionLista).build();

    }
    @GET
    @Path("/eps/cargos")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listaEPSCargo(@QueryParam("orden") String orden) {

        // Objects for handling connection
        Connection conn = null;
        List<AporteSocial> epsLista = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            AporteSocialService aporteSocialService = new AporteSocialService(conn);
            epsLista = aporteSocialService.getListaEPSCargo(orden);

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
        return Response.ok().entity(epsLista).build();

    }

    @GET
    @Path("/pensiones/cargos")
    @Produces(MediaType.APPLICATION_JSON)

    public Response listaPensionCargo(@QueryParam("orden") String orden) {

        // Objects for handling connection
        Connection conn = null;
        List<AporteSocial> pensionLista = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            AporteSocialService aporteSocialService = new AporteSocialService(conn);
            pensionLista = aporteSocialService.getListaPensionCargo(orden);

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
        return Response.ok().entity(pensionLista).build();

    }

    @GET
    @Path("/eps/cargos/cantidad")
    @Produces(MediaType.APPLICATION_JSON)

    public Response cantEpsCargo() {

        // Objects for handling connection
        Connection conn = null;
        List<AporteSocial> epsLista = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            AporteSocialService aporteSocialService = new AporteSocialService(conn);
            epsLista = aporteSocialService.getCantEPSCargo();

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
        return Response.ok().entity(epsLista).build();

    }

    @GET
    @Path("/pensiones/cargos/cantidad")
    @Produces(MediaType.APPLICATION_JSON)

    public Response cantPensionCargo() {

        // Objects for handling connection
        Connection conn = null;
        List<AporteSocial> pensionLista = null;

        try {

            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            AporteSocialService aporteSocialService = new AporteSocialService(conn);
            pensionLista = aporteSocialService.getCantPensionCargo();

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
        return Response.ok().entity(pensionLista).build();

    }





}
