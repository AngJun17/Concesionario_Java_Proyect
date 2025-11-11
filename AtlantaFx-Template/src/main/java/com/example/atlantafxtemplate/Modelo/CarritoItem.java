package com.example.atlantafxtemplate.Modelo;

public class CarritoItem {

    private String nombre;
    private double precio;
    private String descripcion;
    private String imagen;
    private String tipo;
    private int cantidad;

    public CarritoItem(String nombre, double precio, String descripcion, String imagen, String tipo, int cantidad) {
        setNombre(nombre);
        setPrecio(precio);
        setDescripcion(descripcion);
        setImagen(imagen);
        setTipo(tipo);
        setCantidad(cantidad);
    }

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public double getPrecio() {return precio;}
    public void setPrecio(double precio) {this.precio = precio;}

    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public String getImagen() {return imagen;}
    public void setImagen(String imagen) {this.imagen = imagen;}

    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    public int getCantidad() {return cantidad;}
    public void setCantidad(int cantidad) {this.cantidad = cantidad;}


}
