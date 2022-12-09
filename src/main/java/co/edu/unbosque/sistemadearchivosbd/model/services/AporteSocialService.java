package co.edu.unbosque.sistemadearchivosbd.model.services;

import co.edu.unbosque.sistemadearchivosbd.model.dto.AporteSocial;
import co.edu.unbosque.sistemadearchivosbd.model.dto.Empleado;
import co.edu.unbosque.sistemadearchivosbd.model.dto.Nomina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AporteSocialService {

    private Connection conn;

    public AporteSocialService(Connection conn) {
        this.conn = conn;
    }

    public List<AporteSocial> getCantEps() {
        PreparedStatement stmt = null;

        List<AporteSocial> cantidadEPS = new ArrayList<AporteSocial>();

        try {



            String sql="SELECT B.ENTIDAD, COUNT(A.CODEMPLEADO)\n" +
                    "FROM EMPLEADO A \n" +
                    "JOIN EPS B ON A.CODEPS = B.CODEPS\n" +
                    "GROUP BY B.ENTIDAD\n" +
                    "ORDER BY B.ENTIDAD ASC;";

            stmt = this.conn.prepareStatement(sql);


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadEPS.add(new AporteSocial(
                        rs.getInt(2),rs.getString(1)
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
        return cantidadEPS;
    }

    public List<AporteSocial> getCantPension() {
        PreparedStatement stmt = null;

        List<AporteSocial> cantidadEPS = new ArrayList<AporteSocial>();

        try {

            String sql="SELECT B.ENTIDAD, COUNT(A.CODEMPLEADO)\n" +
                    "FROM EMPLEADO A \n" +
                    "JOIN PENSION B ON A.CODPENSION = B.CODPENSION\n" +
                    "GROUP BY B.ENTIDAD\n" +
                    "ORDER BY B.ENTIDAD ASC;";

            stmt = this.conn.prepareStatement(sql);


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadEPS.add(new AporteSocial(
                        rs.getString(1),rs.getInt(2)
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
        return cantidadEPS;
    }

    public List<AporteSocial> getCantPensionDepe(String dependencia) {
        PreparedStatement stmt = null;

        List<AporteSocial> cantidadEPS = new ArrayList<AporteSocial>();

        try {

            String sql="SELECT B.ENTIDAD, COUNT(A.CODEMPLEADO)\n" +
                    "FROM EMPLEADO A \n" +
                    "JOIN PENSION B ON A.CODPENSION = B.CODPENSION\n" +
                    "JOIN CARGO C ON A.CODCARGO = C.CODCARGO\n" +
                    "JOIN DEPENDENCIA D ON C.CODDEP = D.CODDEP \n" +
                    "WHERE D.TIPO = ?\n" +
                    "GROUP BY B.ENTIDAD\n" +
                    "ORDER BY B.ENTIDAD ASC;";

            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,dependencia);


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadEPS.add(new AporteSocial(
                       rs.getString(1),rs.getInt(2)
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
        return cantidadEPS;
    }

    public List<AporteSocial> getCantEPSDepe(String dependencia) {
        PreparedStatement stmt = null;

        List<AporteSocial> cantidadEPS = new ArrayList<AporteSocial>();

        try {

            String sql="SELECT B.ENTIDAD, COUNT(A.CODEMPLEADO)\n" +
                    "FROM EMPLEADO A \n" +
                    "JOIN EPS B ON A.CODEPS = B.CODEPS\n" +
                    "JOIN CARGO C ON A.CODCARGO = C.CODCARGO\n" +
                    "JOIN DEPENDENCIA D ON C.CODDEP = D.CODDEP \n" +
                    "WHERE D.TIPO = ?\n" +
                    "GROUP BY B.ENTIDAD\n" +
                    "ORDER BY B.ENTIDAD ASC;";

            stmt = this.conn.prepareStatement(sql);
            stmt.setString(1,dependencia);


            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadEPS.add(new AporteSocial(
                        rs.getInt(2), rs.getString(1)
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
        return cantidadEPS;
    }

    public List<AporteSocial> getCantEPSCargo() {
        PreparedStatement stmt = null;

        List<AporteSocial> cantidadEPS = new ArrayList<AporteSocial>();

        try {

            String sql="SELECT C.TIPO, B.ENTIDAD, COUNT(A.CODEMPLEADO)\n" +
                    "FROM EMPLEADO A \n" +
                    "JOIN EPS B ON A.CODEPS = B.CODEPS\n" +
                    "JOIN CARGO C ON A.CODCARGO = C.CODCARGO\n" +
                    "GROUP BY C.TIPO, B.ENTIDAD\n" +
                    "ORDER BY C.TIPO ASC, B.ENTIDAD ASC;";

            stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadEPS.add(new AporteSocial(
                        rs.getInt(3), rs.getString(2), rs.getString(1)
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
        return cantidadEPS;
    }

    public List<AporteSocial> getCantPensionCargo() {
        PreparedStatement stmt = null;

        List<AporteSocial> cantidadEPS = new ArrayList<AporteSocial>();

        try {

            String sql="SELECT C.TIPO, B.ENTIDAD, COUNT(A.CODEMPLEADO)\n" +
                    "FROM EMPLEADO A \n" +
                    "JOIN PENSION B ON A.CODPENSION = B.CODPENSION\n" +
                    "JOIN CARGO C ON A.CODCARGO = C.CODCARGO\n" +
                    "GROUP BY C.TIPO, B.ENTIDAD\n" +
                    "ORDER BY C.TIPO ASC, B.ENTIDAD ASC;";

            stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadEPS.add(new AporteSocial(
                        rs.getString(2), rs.getString(1),rs.getInt(3)
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
        return cantidadEPS;
    }

    public List<AporteSocial> getListaEPSCargo(String orden) {
        PreparedStatement stmt = null;

        List<AporteSocial> cantidadEPS = new ArrayList<AporteSocial>();

        try {

            String sql="SELECT A.CODEMPLEADO, A.NOMBRE, C.TIPO, B.ENTIDAD\n" +
                    "FROM EMPLEADO A \n" +
                    "JOIN EPS B ON A.CODEPS = B.CODEPS\n" +
                    "JOIN CARGO C ON A.CODCARGO = C.CODCARGO\n" +
                    "ORDER BY C.TIPO ASC, B.ENTIDAD ASC, A.NOMBRE "+orden+";";

            stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadEPS.add(new AporteSocial(rs.getInt(1),
                        rs.getString(2), rs.getString(3),rs.getString(4)
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
        return cantidadEPS;
    }

    public List<AporteSocial> getListaPensionCargo(String orden) {
        PreparedStatement stmt = null;

        List<AporteSocial> cantidadEPS = new ArrayList<AporteSocial>();

        try {

            String sql="SELECT A.CODEMPLEADO, A.NOMBRE, C.TIPO, B.ENTIDAD\n" +
                    "FROM EMPLEADO A \n" +
                    "JOIN PENSION B ON A.CODPENSION = B.CODPENSION\n" +
                    "JOIN CARGO C ON A.CODCARGO = C.CODCARGO\n" +
                    "ORDER BY C.TIPO ASC, B.ENTIDAD ASC, A.NOMBRE "+orden+";";

            stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cantidadEPS.add(new AporteSocial(rs.getString(2),
                        rs.getString(3),rs.getString(4), rs.getInt(1)
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
        return cantidadEPS;
    }


}
