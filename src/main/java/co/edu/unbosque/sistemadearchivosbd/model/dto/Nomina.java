package co.edu.unbosque.sistemadearchivosbd.model.dto;

public class Nomina {

    private int codigo;
    private String nombre;
    private String dependencia;
    private String cargo;
    private int cantidad;

    public Nomina(){

    }

    public Nomina(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Nomina(int codigo, String nombre, String dependencia) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dependencia = dependencia;
    }

    public Nomina(int codigo, String nombre, String dependencia, String cargo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dependencia = dependencia;
        this.cargo = cargo;
    }

    public Nomina(String dependencia, int cantidad) {
        this.dependencia = dependencia;
        this.cantidad = cantidad;
    }

    public Nomina(String cargo, int cantidad,String dependencia, int codigo) {
        this.codigo = codigo;
        this.dependencia = dependencia;
        this.cargo = cargo;
        this.cantidad = cantidad;
    }

    public Nomina(String dependencia, String cargo) {
        this.dependencia = dependencia;
        this.cargo = cargo;
    }

    public Nomina(String dependencia) {
        this.dependencia = dependencia;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
