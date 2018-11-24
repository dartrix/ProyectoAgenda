package com.dartrix.proyectoagenda;

public class Asignacion {

    //Atributos de la Asignacion
    private String id;
    private int materiaFK;
    private String nombre;
    private String descripcion;
    private String calificacion;
    private String fechalimite;
    private String horalimite;
    private String tipo;
    private String  estados;

    //Metodo vacio para su proximo
    public Asignacion (){

    }

    public Asignacion(String id, int materiaFK, String nombre, String descripcion, String calificacion, String fechalimite, String horalimite, String tipo, String estados) {
        this.id = id;
        this.materiaFK = materiaFK;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.calificacion = calificacion;
        this.fechalimite = fechalimite;
        this.horalimite = horalimite;
        this.tipo = tipo;
        this.estados = estados;
    }

    //Getters & Setters de la asignacion
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMateriaFK() {
        return materiaFK;
    }

    public void setMateriaFK(int materiaFK) {
        this.materiaFK = materiaFK;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(String fechalimite) {
        this.fechalimite = fechalimite;
    }

    public String getHoralimite() {
        return horalimite;
    }

    public void setHoralimite(String horalimite) {
        this.horalimite = horalimite;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstados() {
        return estados;
    }

    public void setEstados(String estados) {
        this.estados = estados;
    }
}
