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
                String[] p = linea.split(";");
                // Soporta formatos antiguos (4 o 5 partes) y el nuevo de 6
                int aciertos = (p.length >= 3) ? Integer.parseInt(p[2]) : 0;
                int fallos = (p.length >= 4) ? Integer.parseInt(p[3]) : 0;
                int tiempoBusca = (p.length >= 5) ? Integer.parseInt(p[4]) : 0;
                int puntosSnake = (p.length >= 6) ? Integer.parseInt(p[5]) : 0;
                
                Usuario u = new Usuario(p[0], p[1], aciertos, fallos, tiempoBusca, puntosSnake);
                usuarios.put(u.getUsername(), u);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void guardarUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Usuario u : usuarios.values()) {
                bw.write(u.toString());
                bw.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public boolean registrarUsuario(String username, String password) {
        if (usuarios.containsKey(username)) return false;
        Usuario nuevo = new Usuario(username, password, 0, 0, 0, 0);
        usuarios.put(username, nuevo);
        guardarUsuarios();
        return true;
    }

    public Usuario validarUsuario(String username, String password) {
        Usuario u = usuarios.get(username);
        return (u != null && u.getPassword().equals(password)) ? u : null;
    }
}