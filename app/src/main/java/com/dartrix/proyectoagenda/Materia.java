package com.dartrix.proyectoagenda;

public class Materia {

    //Atributos de la materia
    private String id;
    private String nombreMateria;
    private String descripcionMateria;
    private String creditoMateria;
    private String profMateria;
    private String acumulado;

    private int color;

    public Materia (){

    }
    //Constructor de la clase materia

    public Materia(String id, String nombreMateria, String descripcionMateria, String creditoMateria, String profMateria,int color, String acumulado) {

        this.id = id;
        this.nombreMateria = nombreMateria;
        this.descripcionMateria = descripcionMateria;
        this.creditoMateria = creditoMateria;
        this.profMateria = profMateria;
        this.color=color;
        this.acumulado = acumulado;
    }



    public String getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(String acumulado) {
        this.acumulado = acumulado;
    }

    //Getters & Setters de la materia
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getDescripcionMateria() {
        return descripcionMateria;
    }

    public void setDescripcionMateria(String descripcionMateria) {
        this.descripcionMateria = descripcionMateria;
    }

    public String getCreditoMateria() {
        return creditoMateria;
    }

    public void setCreditoMateria(String creditoMateria) {
        this.creditoMateria = creditoMateria;
    }

    public String getProfMateria() {
        return profMateria;
    }

    public void setProfMateria(String profMateria) {
        this.profMateria = profMateria;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
