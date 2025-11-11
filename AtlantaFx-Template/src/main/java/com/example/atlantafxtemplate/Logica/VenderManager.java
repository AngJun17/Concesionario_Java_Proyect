package com.example.atlantafxtemplate.Logica;

import com.example.atlantafxtemplate.Modelo.Carro;
import com.example.atlantafxtemplate.Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.lang.reflect.Type;
import java.util.ResourceBundle;


public class VenderManager {

    private List<Carro> carros;
    private final Path rutaArchivo = Paths.get(System.getProperty("user.dir"), "data", "carros.json");
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public VenderManager() {
        cargarCarros();
    }

    private void cargarCarros() {
        try {
            if (!Files.exists(rutaArchivo.getParent())) {
                Files.createDirectories(rutaArchivo.getParent());
            }
            if (!Files.exists(rutaArchivo)) {
                guardarCarros();
                return;
            }
            try (Reader reader = Files.newBufferedReader(rutaArchivo)) {
                Type listType = new TypeToken<List<Carro>>(){}.getType();
                carros = gson.fromJson(reader, listType);

                if (carros == null) carros = new ArrayList<>();
            }
        }catch (IOException e) {
            e.printStackTrace();
            carros = new ArrayList<>();
        }
    }

   private void guardarCarros() {
       try (Writer writer = Files.newBufferedWriter(rutaArchivo)) {
           gson.toJson(carros, writer);
       }catch(IOException e) {
           e.printStackTrace();
       }
   }

   public List<Carro> getCarros() {
        return carros;
   }

    public boolean agregarCarro(String marca, String modelo, double precioOriginal, int anio, String categoria, String descripcion, String imagen, String estado) {
        double precioFinal = CalculadoraValorVehiculo.calcularPrecioFinal(anio, estado, precioOriginal);

        if (precioFinal == -1) {
            System.out.println("Vehículo demasiado antiguo, no se puede cotizar.");
            return false;
        }

        boolean existe = carros.stream()
                .anyMatch(c -> c.getMarca().equalsIgnoreCase(marca)
                        && c.getModelo().equalsIgnoreCase(modelo)
                        && c.getAnio() == anio);

        if (existe) {
            System.out.println("Ya existe otro vehículo registrado.");
            return false;
        }

        Carro nuevo = new Carro(
                carros.size() + 1, // id
                marca,
                modelo,
                precioFinal,
                categoria,
                imagen,
                descripcion,
                anio,
                estado
        );

        carros.add(nuevo);
        guardarCarros();
        return true;
    }

    public int obtenerSiguienteId() {
       if (carros == null || carros.isEmpty()) return 1;
       return carros.get(carros.size() - 1).getId() + 1;
   }
}
