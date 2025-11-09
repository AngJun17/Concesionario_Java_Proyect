package com.example.atlantafxtemplate.Logica;

import com.example.atlantafxtemplate.Modelo.Usuario;
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


public class LoginManager {

    private List<Usuario> usuarios;
    private final Path rutaArchivo = Paths.get(System.getProperty("user.dir"), "data", "usuarios.json");

    public LoginManager() {
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        try {
            // Si no existe la carpeta, la crea
            if (!Files.exists(rutaArchivo.getParent())) {
                Files.createDirectories(rutaArchivo.getParent());
            }

            if (!Files.exists(rutaArchivo)) {
                // Si el archivo no existe, crea uno vacío
                usuarios = new ArrayList<>();
                guardarUsuarios();
                return;
            }

            try (Reader reader = Files.newBufferedReader(rutaArchivo)) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Usuario>>(){}.getType();
                usuarios = gson.fromJson(reader, listType);

                if (usuarios == null) usuarios = new ArrayList<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
            usuarios = new ArrayList<>();
        }
    }

    public boolean validar(String user, String pass) {
        if (usuarios == null) return false;
        return usuarios.stream()
                .anyMatch(u -> u.getUsuario().equals(user) && u.getPassword().equals(pass));
    }

    public boolean registrarUsuario(String user, String pass) {
        if (usuarios.stream().anyMatch(u -> u.getUsuario().equals(user))) {
            return false;
        }

        usuarios.add(new Usuario(user, pass));
        guardarUsuarios();
        return true;
    }

    private void guardarUsuarios() {
        try (Writer writer = Files.newBufferedWriter(rutaArchivo)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
