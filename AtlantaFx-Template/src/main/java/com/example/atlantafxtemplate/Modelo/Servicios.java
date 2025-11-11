package com.example.atlantafxtemplate.Modelo;

public class Servicios {

    private String nombre;
    private String tipoServicio;
    private String tipoVehiculo;
    private double precio;
    private String descripcion;

    public Servicios(String nombre, String tipoServicio, String tipoVehiculo, double precio, String descripcion ) {
        setNombre(nombre);
        setTipoServicio(tipoServicio);
        setTipoVehiculo(tipoVehiculo);
        setPrecio(precio);
        setDescripcion(descripcion);
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getTipoServicio() {return tipoServicio;}
    public void setTipoServicio(String tipoServicio) {this.tipoServicio = tipoServicio;}

    public String getTipoVehiculo() {return tipoVehiculo;}
    public void setTipoVehiculo(String tipoVehiculo) {this.tipoVehiculo = tipoVehiculo;}

    public double getPrecio() {return precio;}
    public void setPrecio(double precio) {this.precio = precio;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
}
