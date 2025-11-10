package com.example.atlantafxtemplate.Modelo;

public class Carro {
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private String categoria;
    private String imagen;
    private String descripcion;

    public Carro(int id,String marca, String modelo , double precio, String categoria, String imagen, String descripcion) {
        setId(id);
        setMarca(marca);
        setModelo(modelo);
        setPrecio(precio);
        setCategoria(categoria);
        setImagen(imagen);
        setDescripcion(descripcion);

    }

    public void setId(int id){this.id = id;}
    public int getId(){return id;}

    public void setMarca(String marca){this.marca= marca;}
    public String getMarca(){return marca;}

    public void setModelo(String modelo){this.modelo= modelo;}
    public String getModelo(){return modelo;}

    public void setPrecio(double precio){this.precio= precio;}
    public double getPrecio(){return precio;}

    public void setCategoria(String categoria){this.categoria= categoria;}
    public String getCategoria(){return categoria;}

    public void setImagen(String imagen){this.imagen= imagen;}
    public String getImagen(){return imagen;}

    public void setDescripcion(String descripcion){this.descripcion= descripcion;}
    public String getDescripcion(){return descripcion;}
}
