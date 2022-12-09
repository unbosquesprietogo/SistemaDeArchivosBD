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

    public List<Nomina> getCantidadDependencias() {
        PreparedStatement stmt = null;

        List<Nomina> dependenciasLista = new ArrayList<Nomina>();

        try {


            String sql="";


                sql="SELECT C.TIPO, COUNT(A.CODEMPLEADO)\n" +
                        "FROM EMPLEADO A \n" +
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

}
