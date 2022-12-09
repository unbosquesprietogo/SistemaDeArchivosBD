package co.edu.unbosque.sistemadearchivosbd.model.dto;

import java.sql.Date;

public class Empleado {

    private int codigo;
    private String nombre;
    private String dependencia;
    private String cargo;
    private Date fecha_ingreso;
    private String eps;
    private String pension;
    private int sueldo;
    private int bonificacion;
    private int transporte;
    private int dias_trabajados;
    private int dias_incapacidad;
    private int dias_vacaciones;

    public Empleado(int codigo, String nombre, String dependencia, String cargo,
                    Date fecha_ingreso, String eps, String pension, int sueldo,
                    int bonificacion, int transporte, int dias_trabajados,
                    int dias_incapacidad, int dias_vacaciones) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.dependencia = dependencia;
        this.cargo = cargo;
        this.fecha_ingreso = fecha_ingreso;
        this.eps = eps;
        this.pension = pension;
        this.sueldo = sueldo;
        this.bonificacion = bonificacion;
        this.transporte = transporte;
        this.dias_trabajados = dias_trabajados;
        this.dias_incapacidad = dias_incapacidad;
        this.dias_vacaciones = dias_vacaciones;
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

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
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

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public int getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(int bonificacion) {
        this.bonificacion = bonificacion;
    }

    public int getTransporte() {
        return transporte;
    }

    public void setTransporte(int transporte) {
        this.transporte = transporte;
    }

    public int getDias_trabajados() {
        return dias_trabajados;
    }

    public void setDias_trabajados(int dias_trabajados) {
        this.dias_trabajados = dias_trabajados;
    }

    public int getDias_incapacidad() {
        return dias_incapacidad;
    }

    public void setDias_incapacidad(int dias_incapacidad) {
        this.dias_incapacidad = dias_incapacidad;
    }

    public int getDias_vacaciones() {
        return dias_vacaciones;
    }

    public void setDias_vacaciones(int dias_vacaciones) {
        this.dias_vacaciones = dias_vacaciones;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", dependencia='" + dependencia + '\'' +
                ", cargo='" + cargo + '\'' +
                ", fecha_ingreso=" + fecha_ingreso +
                ", eps='" + eps + '\'' +
                ", pension='" + pension + '\'' +
                ", sueldo=" + sueldo +
                ", bonificacion=" + bonificacion +
                ", transporte=" + transporte +
                ", dias_trabajados=" + dias_trabajados +
                ", dias_incapacidad=" + dias_incapacidad +
                ", dias_vacaciones=" + dias_vacaciones +
                '}';
    }
}
