package Modelo;

import java.io.*;
import java.util.HashMap;

public class GestorUsuarios {
    private final String ARCHIVO = "usuarios.txt";
    private HashMap<String, Usuario> usuarios;

    public GestorUsuarios() {
        usuarios = new HashMap<>();
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        File file = new File(ARCHIVO);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                // Compatibilidad: Si tiene 4 partes es antiguo (sin tiempo), si tiene 5 es nuevo
                if (partes.length == 4 || partes.length == 5) {
                    int aciertos = Integer.parseInt(partes[2]);
                    int fallos = Integer.parseInt(partes[3]);
                    int mejorTiempo = (partes.length == 5) ? Integer.parseInt(partes[4]) : 0;
                    
                    Usuario u = new Usuario(partes[0], partes[1], aciertos, fallos, mejorTiempo);
                    usuarios.put(u.getUsername(), u);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    public void guardarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Usuario u : usuarios.values()) {
                bw.write(u.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }

    public boolean registrarUsuario(String username, String password) {
        if (usuarios.containsKey(username)) {
            return false;
        }
        Usuario nuevo = new Usuario(username, password, 0, 0, 0); // 0 segundos al crear
        usuarios.put(username, nuevo);
        guardarUsuarios();
        return true;
    }

    public Usuario validarUsuario(String username, String password) {
        Usuario u = usuarios.get(username);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
}