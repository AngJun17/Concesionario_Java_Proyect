package com.example.atlantafxtemplate.Modelo;

public class Repuestos {
    private String nombre;
    private String tipoVehiculo;
    private double  precio;
    private String imagen;
    private String tipoDeRepuesto;
    private String descripcion;
    private int existencia;

    public Repuestos(String nombre, String tipoVehiculo, double precio, String imagen, String tipoDeRepuesto, String descripcion, int existencia) {
        setNombre(nombre);
        setTipoVehiculo(tipoVehiculo);
        setPrecio(precio);
        setImagen(imagen);
        setTipoDeRepuesto(tipoDeRepuesto);
        setDescripcion(descripcion);
        setExistencia(existencia);
    }

    public void setNombre(String nombre){this.nombre = nombre;}
    public String getNombre(){return nombre;}

    public void setTipoVehiculo(String tipoVehiculo){this.tipoVehiculo = tipoVehiculo;}
    public String getTipoVehiculo(){return tipoVehiculo;}

    public void setImagen(String imagen){this.imagen = imagen;}
    public String getImagen(){return imagen;}

    public void setPrecio(double precio){this.precio = precio;}
    public double getPrecio(){return precio;}

    public void setTipoDeRepuesto(String tipoDeRepuesto){this.tipoDeRepuesto = tipoDeRepuesto;}
    public String getTipoDeRepuesto(){return tipoDeRepuesto;}

    public void setDescripcion(String descripcion){this.descripcion = descripcion;}
    public String getDescripcion(){return descripcion;}

    public void setExistencia(int existencia){this.existencia = existencia;}
    public int getExistencia(){return existencia;}

}
