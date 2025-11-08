package com.example.atlantafxtemplate.Logica;

import com.example.atlantafxtemplate.Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.lang.reflect.Type;


public class LoginManager {

    private List<Usuario> usuarios;

    public LoginManager() {
        cargarUsuarios();
    }

    public void cargarUsuarios() {
        try {
            var input = getClass().getResourceAsStream("/data/usuarios.json");
            if (input == null) {
                System.err.println("⚠️ No se encontró el archivo usuarios.json");
                return;
            }

            try (Reader reader = new InputStreamReader(input)) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<Usuario>>(){}.getType();
                usuarios = gson.fromJson(reader, listType);

                if (usuarios == null) {
                    System.err.println("⚠️ Error: Gson no pudo parsear el JSON.");
                } else {
                    System.out.println("✅ Usuarios cargados: " + usuarios.size());
                    usuarios.forEach(u -> System.out.println(u.getUsuario() + " / " + u.getPassword()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validar(String user, String pass){

        if (usuarios == null) return false;
        return usuarios.stream()
                .anyMatch(u -> u.getUsuario().equals(user) && u.getPassword().equals(pass));

    }

}
