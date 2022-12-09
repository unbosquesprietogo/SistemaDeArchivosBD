package co.edu.unbosque.sistemadearchivosbd.model.services;

import co.edu.unbosque.sistemadearchivosbd.model.dto.Nomina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NominaService {

    private Connection conn;

    public NominaService(Connection conn) {
        this.conn = conn;
    }


    public List<Nomina> getListEmployees(String dependencia, String cargo, String orden) {
        PreparedStatement stmt = null;

        List<Nomina> employeeList = new ArrayList<Nomina>();

        try {

            String sql="";

            if("Mostrar todos".equals(dependencia) && "Mostrar todos".equals(cargo)){

                sql = "SELECT A.CODEMPLEADO, A.NOMBRE, B.TIPO, C.TIPO \n" +
                        "FROM EMPLEADO A \n" +
                        "JOIN CARGO B ON A.CODCARGO = B.CODCARGO\n" +
                        "JOIN DEPENDENCIA C ON B.CODDEP = C.CODDEP\n" +
                        "ORDER BY A.NOMBRE "+orden+";";

                stmt = this.conn.prepareStatement(sql);


            }else if("Mostrar todos".equals(cargo) && !"Mostrar todos".equals(dependencia)){


                sql="SELECT A.CODEMPLEADO, A.NOMBRE, C.TIPO, B.TIPO\n" +
                        "FROM EMPLEADO A \n" +
                        "JOIN CARGO B ON A.CODCARGO = B.CODCARGO\n" +
                        "JOIN DEPENDENCIA C ON B.CODDEP = C.CODDEP\n" +
                        "WHERE C.TIPO = ? \n" +
                        "ORDER BY C.TIPO ASC ,A.NOMBRE  "+orden+";";


                stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, (dependencia));



            }else {

                sql="SELECT A.CODEMPLEADO, A.NOMBRE, C.TIPO, B.TIPO\n" +
                        "FROM EMPLEADO A \n" +
                        "JOIN CARGO B ON A.CODCARGO = B.CODCARGO\n" +
                        "JOIN DEPENDENCIA C ON B.CODDEP = C.CODDEP\n" +
                        "WHERE C.TIPO = ? AND B.TIPO = ?\n" +
                        "ORDER BY C.TIPO ASC, B.TIPO ASC, A.NOMBRE  "+orden+";";

                stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, (dependencia));
                stmt.setString(2, (cargo));
            }


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                employeeList.add(new Nomina(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                ));
            }

            rs.close();
            stmt.close();
        } catch (SQLException se) {

        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return employeeList;
    }

    public int getCantEmployees(String dependencia, String cargo, String orden) {
        PreparedStatement stmt = null;

        int cantidad = 0;

        try {

            String sql="";

            if("Mostrar todos".equals(dependencia) && "Mostrar todos".equals(cargo)){

                sql = "SELECT COUNT(*) \n" +
                        "FROM EMPLEADO A \n" +
                        "JOIN CARGO B ON A.CODCARGO = B.CODCARGO\n" +
                        "JOIN DEPENDENCIA C ON B.CODDEP = C.CODDEP\n" +
                        "ORDER BY A.NOMBRE "+orden+";";

                stmt = this.conn.prepareStatement(sql);


            }else if("Mostrar todos".equals(cargo) && !"Mostrar todos".equals(dependencia)){


                sql="SELECT COUNT(*)\n" +
                        "FROM EMPLEADO A \n" +
                        "JOIN CARGO B ON A.CODCARGO = B.CODCARGO\n" +
                        "JOIN DEPENDENCIA C ON B.CODDEP = C.CODDEP\n" +
                        "WHERE C.TIPO = ? \n" +
                        "ORDER BY C.TIPO ASC ,A.NOMBRE  "+orden+";";


                stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, (dependencia));



            }else {

                sql="SELECT COUNT(*)\n" +
                        "FROM EMPLEADO A \n" +
                        "JOIN CARGO B ON A.CODCARGO = B.CODCARGO\n" +
                        "JOIN DEPENDENCIA C ON B.CODDEP = C.CODDEP\n" +
                        "WHERE C.TIPO = ? AND B.TIPO = ?\n" +
                        "ORDER BY C.TIPO ASC, B.TIPO ASC, A.NOMBRE  "+orden+";";

                stmt = this.conn.prepareStatement(sql);
                stmt.setString(1, (dependencia));
                stmt.setString(2, (cargo));
            }


            ResultSet rs = stmt.executeQuery();

            rs.next();
            cantidad = rs.getInt(1);

            rs.close();
            stmt.close();
        } catch (SQLException se) {

        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return cantidad;
    }
    public List<Nomina> getCantidadDependencias() {
        PreparedStatement stmt = null;

        List<Nomina> dependenciasLista = new ArrayList<Nomina>();

        try {


            String sql="";


                sql="SELECT C.TIPO, COUNT(A.CODEMPLEADO)\n" +
                        "FROM EMPLEADO A\n" +
                        "JOIN CARGO B ON A.CODCARGO = B.CODCARGO\n" +
                        "JOIN DEPENDENCIA C ON B.CODDEP = C.CODDEP\n" +
                        "GROUP BY C.TIPO\n" +
                        "ORDER BY C.TIPO ASC;";

                stmt = this.conn.prepareStatement(sql);


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dependenciasLista.add(new Nomina(
                        rs.getString(1),
                        rs.getInt(2)
                ));
            }

            rs.close();
            stmt.close();
        } catch (SQLException se) {

        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return dependenciasLista;
    }

    public List<Nomina> getCantidadCargos() {
        PreparedStatement stmt = null;

        List<Nomina> cargosLista = new ArrayList<Nomina>();

        try {


            String sql="";


            sql="SELECT B.TIPO, COUNT(A.CODCARGO), C.TIPO, C.CODDEP\n" +
                    "FROM EMPLEADO A\n" +
                    "JOIN CARGO B ON A.CODCARGO = B.CODCARGO\n" +
                    "JOIN DEPENDENCIA C ON B.CODDEP = C.CODDEP\n" +
                    "GROUP BY B.TIPO, C.TIPO\n" +
                    "ORDER BY B.TIPO ASC, C.TIPO ASC;";

            stmt = this.conn.prepareStatement(sql);


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cargosLista.add(new Nomina(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }

            rs.close();
            stmt.close();
        } catch (SQLException se) {

        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return cargosLista;
    }

    public List<Nomina> listaDependencias() {
        PreparedStatement stmt = null;

        List<Nomina> dependencias = new ArrayList<Nomina>();

        try {


            String sql="";


            sql="SELECT TIPO FROM DEPENDENCIA;";

            stmt = this.conn.prepareStatement(sql);


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dependencias.add(new Nomina(
                        rs.getString(1)
                ));
            }

            rs.close();
            stmt.close();
        } catch (SQLException se) {

        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return dependencias;
    }

    public List<Nomina> listaCargos(String dependencia) {
        PreparedStatement stmt = null;

        List<Nomina> cargos = new ArrayList<Nomina>();

        try {


            String sql="";


            sql="SELECT A.TIPO FROM CARGO A \n" +
                    "JOIN DEPENDENCIA B \n" +
                    "ON A.CODDEP = B.CODDEP\n" +
                    "WHERE B.TIPO = ?;";

            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,dependencia);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cargos.add(new Nomina(
                        rs.getString(1)
                ));
            }

            rs.close();
            stmt.close();
        } catch (SQLException se) {

        } finally {
            // Cleaning-up environment
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return cargos;
    }
}
