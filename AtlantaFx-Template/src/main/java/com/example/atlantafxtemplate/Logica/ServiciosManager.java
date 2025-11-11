package com.example.atlantafxtemplate.Logica;

import com.example.atlantafxtemplate.Modelo.Servicios;
import com.example.atlantafxtemplate.Modelo.Repuestos;

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

public class ServiciosManager {
    private List<Servicios> servicios;
    private List<Repuestos> repuestos;

    private final Path rutaServicios = Paths.get(System.getProperty("user.dir"), "data", "servicios.json");
    private final Path rutaRepuestos = Paths.get(System.getProperty("user.dir"), "data", "repuestos.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ServiciosManager() {
        servicios = new ArrayList<>();
        repuestos = new ArrayList<>();
        
        System.out.println("Directorio actual: " + System.getProperty("user.dir"));
        System.out.println("Buscando servicios en: " + rutaServicios.toString());
        System.out.println("Buscando repuestos en: " + rutaRepuestos.toString());
        
        cargarServicios();
        cargarRepuestos();
    }

    private void cargarServicios() {
        try {
            if (Files.exists(rutaServicios)) {
                System.out.println("Archivo servicios.json encontrado");
                String contenido = Files.readString(rutaServicios);
                Type listType = new TypeToken<List<Servicios>>(){}.getType();
                List<Servicios> loaded = gson.fromJson(contenido, listType);
                if (loaded != null) {
                    servicios = loaded;
                    System.out.println("Servicios cargados: " + servicios.size());
                } else {
                    System.out.println("No se pudieron cargar los servicios del JSON");
                }
            } else {
                System.out.println("No se encontró el archivo servicios.json");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar servicios: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cargarRepuestos() {
        try {
            if (Files.exists(rutaRepuestos)) {
                System.out.println("Archivo repuestos.json encontrado");
                String contenido = Files.readString(rutaRepuestos);
                Type listType = new TypeToken<List<Repuestos>>(){}.getType();
                List<Repuestos> loaded = gson.fromJson(contenido, listType);
                if (loaded != null) {
                    repuestos = loaded;
                    System.out.println("Repuestos cargados: " + repuestos.size());
                } else {
                    System.out.println("No se pudieron cargar los repuestos del JSON");
                }
            } else {
                System.out.println("No se encontró el archivo repuestos.json");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar repuestos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Servicios> getServiciosPorTipo(String tipoVehiculo, String tipoServicio) {
        List<Servicios> filtrados = new ArrayList<>();

        for (Servicios servicio : servicios){
            if ((tipoVehiculo == null || servicio.getTipoVehiculo().equalsIgnoreCase(tipoVehiculo)) &&
             (tipoServicio == null || servicio.getTipoServicio().equalsIgnoreCase(tipoServicio))) {

                filtrados.add(servicio);
            }
        }
        return filtrados;
    }

    public List<Repuestos> getRepuestosPorTipo(String tipoVehiculo, String tipoRepuesto) {
        List<Repuestos> filtrados = new ArrayList<>();

        // Cambiamos el nombre de la variable en el for para evitar confusión
        for (Repuestos repuesto : repuestos) {  // Cambio aquí
            if ((tipoVehiculo == null || repuesto.getTipoVehiculo().equalsIgnoreCase(tipoVehiculo)) &&
                    (tipoRepuesto == null || repuesto.getTipoDeRepuesto().equalsIgnoreCase(tipoRepuesto))) {
            filtrados.add(repuesto);
        }
    }
    return filtrados;
}
}