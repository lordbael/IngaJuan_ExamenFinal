package com.example.ingajuan_examenfinal.modelo;

import android.content.Context;

public class Empleados {

    private String cedula;
    protected String nombres;
    private String apellidos;
    private String fecha_contrato;
    private String salario;
    private String discapacidad;
    private String horario;

    public Empleados() {

    }
    public void guardar(Context context){
        Base base =new Base(context);
        String nosql="INSERT INTO empleados (cedula, nombres,apellidos,fecha_contrato,salario,discapacidad, horario)VALUES("+getCedula()+",'"+getNombres()+"','"+getApellidos()+"',"+getFecha_contrato()+","+getSalario()+"','"+getDiscapacidad()+"',"+getHorario()+")";

        base.noQuery(nosql);
        base.close();


    }

    public Empleados(String cedula, String nombres, String apellidos, String fecha_contrato, String salario, String discapacidad, String horario) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fecha_contrato = fecha_contrato;
        this.salario = salario;
        this.discapacidad = discapacidad;
        this.horario = horario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha_contrato() {
        return fecha_contrato;
    }

    public void setFecha_contrato(String fecha_contrato) {
        this.fecha_contrato = fecha_contrato;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
