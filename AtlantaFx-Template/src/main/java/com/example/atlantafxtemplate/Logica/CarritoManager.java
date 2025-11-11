package com.example.atlantafxtemplate.Logica;

import com.example.atlantafxtemplate.Modelo.CarritoItem;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.nio.file.Path;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.lang.reflect.Type;

// En CarritoManager.java
public class CarritoManager {
    private static CarritoManager instance;
    private static List<CarritoItem> items = new ArrayList<>();
    
    private CarritoManager() {} // Constructor privado
    
    public static CarritoManager getInstance() {
        if (instance == null) {
            instance = new CarritoManager();
        }
        return instance;
    }
    
    // Agregar item al carrito
    public void agregarItem(CarritoItem item) {
        // Verifica si el item ya existe
        for (CarritoItem existingItem : items) {
            if (existingItem.getNombre().equals(item.getNombre())) {
                existingItem.setCantidad(existingItem.getCantidad() + 1);
                return;
            }
        }
        items.add(item);
    }

    // Obtener todos los items
    public List<CarritoItem> getItems() {
        return items;
    }

    // Calcular total
    public double calcularTotal() {
        return items.stream()
                   .mapToDouble(item -> item.getPrecio() * item.getCantidad())
                   .sum();
    }

    // Eliminar item
    public void eliminarItem(CarritoItem item) {
        items.remove(item);
    }

    // Limpiar carrito
    public void limpiarCarrito() {
        items.clear();
    }
}