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

   public void agregarCarro(Carro carro) {
       carros.add(carro);
       guardarCarros();
   }
}
