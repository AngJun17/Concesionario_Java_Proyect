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

    private void cargarUsuarios(){

        try(Reader reader = new InputStreamReader(getClass().getResourceAsStream("/data/usuarios.json"))) {;
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Usuario>>(){}.getType();
            usuarios = gson.fromJson(reader, listType);

        } catch(Exception e){
            e.printStackTrace();
        }

    }
    public boolean validar(String user, String pass){
        if (usuarios == null) return false;
        return usuarios.stream()
                .anyMatch(u -> u.getUsuario().equals(user) && u.getPassword().equals(pass));

    }

}
