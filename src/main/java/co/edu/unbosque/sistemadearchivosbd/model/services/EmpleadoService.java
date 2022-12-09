package co.edu.unbosque.sistemadearchivosbd.model.services;

import co.edu.unbosque.sistemadearchivosbd.model.dto.Empleado;

import java.sql.*;

public class EmpleadoService {

    private Connection conn;

    public EmpleadoService(Connection conn) {
        this.conn = conn;
    }

    public Empleado infoPersonal (int codigo){

        System.out.println("entré");

        PreparedStatement stmt = null;

        Empleado empleado = null;

        try {
            String sql = "SELECT A.CODEMPLEADO, A.NOMBRE, C.TIPO, B.TIPO, A.FECHA_INGRESO,\n" +
                    "D.ENTIDAD, E.ENTIDAD, A.SUELDO, F.BONIFICACION, F.TRANSPORTE, F.DIAS\n" +
                    "FROM EMPLEADO A\n" +
                    "JOIN CARGO B ON A.CODCARGO = B.CODCARGO\n" +
                    "JOIN DEPENDENCIA C ON B.CODDEP = C.CODDEP\n" +
                    "JOIN EPS D ON A.CODEPS = D.CODEPS\n" +
                    "JOIN PENSION E ON A.CODPENSION = E.CODPENSION\n" +
                    "JOIN NOVEDAD_MES F ON A.CODEMPLEADO = F.CODEMPLEADO\n" +
                    "WHERE A.CODEMPLEADO = ?;";

            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            int codigoEmp = rs.getInt(1);
            String nombre = rs.getString(2);
            String dependencia =rs.getString(3);
            String cargo =rs.getString(4);
            Date fecha_ingreso = rs.getDate(5);
            String eps =rs.getString(6);
            String pension =rs.getString(7);
            int sueldo = rs.getInt(8);
            int bonificacion = rs.getInt(9);
            int transporte = rs.getInt(10);
            int dias_trabajados = rs.getInt(11);



            sql = "SELECT DIAS FROM INCAPACIDAD WHERE CODEMPLEADO =?;";

            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            rs.next();

            int dias_incapacidad =0;

            try {
                dias_incapacidad = rs.getInt(1);
            }catch (Exception ex){
                dias_incapacidad = 0;
            }



            sql = "SELECT DIAS FROM VACACIONES WHERE CODEMPLEADO =?;";

            stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, codigo);
            rs = stmt.executeQuery();
            rs.next();

            int dias_vacaciones = 0;

            try {
                dias_vacaciones = rs.getInt(1);
            }catch (Exception ex){
                dias_vacaciones = 0;
            }
            empleado = new Empleado(codigoEmp,nombre,dependencia,cargo,
                    fecha_ingreso,eps,pension,sueldo,bonificacion,transporte,
                    dias_trabajados,dias_incapacidad,dias_vacaciones);
            

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
        return empleado;


    }
}
