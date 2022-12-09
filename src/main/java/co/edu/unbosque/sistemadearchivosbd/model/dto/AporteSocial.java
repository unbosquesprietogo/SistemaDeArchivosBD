package co.edu.unbosque.sistemadearchivosbd.model.dto;

public class AporteSocial {

    private int cantidad;
    private String eps;
    private String pension;
    private String depedencia;
    private String cargo;

    private String nombre;

    private int codigo;

    public AporteSocial(int cantidad, String eps) {
        this.cantidad = cantidad;
        this.eps = eps;
    }

    public AporteSocial( String pension, int cantidad) {
        this.cantidad = cantidad;
        this.pension = pension;
    }

    public AporteSocial(int cantidad, String eps, String depedencia) {
        this.cantidad = cantidad;
        this.eps = eps;
        this.depedencia = depedencia;
    }


    public AporteSocial(int codigo, String nombre, String pension, String depedencia,int cantidad) {
        this.cantidad = cantidad;
        this.pension = pension;
        this.depedencia = depedencia;
        this.codigo = codigo;
        this.nombre =nombre;
    }

    public AporteSocial(int codigo, String nombre,  String pension, int cantidad, String cargo) {
        this.cantidad = cantidad;
        this.pension = pension;
        this.cargo = cargo;
        this.codigo = codigo;
        this.nombre =nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getPension() {
        return pension;
    }

    public void setPension(String pension) {
        this.pension = pension;
    }

    public String getDepedencia() {
        return depedencia;
    }

    public void setDepedencia(String depedencia) {
        this.depedencia = depedencia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
