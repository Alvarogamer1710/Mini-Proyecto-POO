package Modelo;

import java.util.ArrayList;
import java.util.List;

public class PasapalabraModel {
    private List<Pregunta> rosco;
    private int indiceActual;

    public PasapalabraModel() {
        rosco = new ArrayList<>();
        indiceActual = 0;
        cargarPreguntas();
    }

    private void cargarPreguntas() {
        // Cargamos 26 preguntas para que el círculo se dibuje completo
        String[] alfabeto = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","X","Y","Z"};
        for (String letra : alfabeto) {
            // Generamos preguntas de relleno para que lo pruebes. Luego pones las tuyas.
            rosco.add(new Pregunta(letra, "Empieza por " + letra + ": Palabra de prueba para la letra " + letra + ".", letra));
        }
    }

    public Pregunta getPreguntaActual() {
        if (rosco.isEmpty()) return null;
        return rosco.get(indiceActual);
    }

    public int getIndiceActual() {
        return indiceActual;
    }

    public boolean comprobarRespuesta(String respuestaUsuario) {
        Pregunta actual = getPreguntaActual();
        if (actual.getRespuestaCorrecta().equalsIgnoreCase(respuestaUsuario.trim())) {
            actual.setEstado(1); 
            return true;
        } else {
            actual.setEstado(2); 
            return false;
        }
    }

    public void pasarPalabra() {
        // Se queda en estado 0 (pendiente)
    }

    public boolean avanzarSiguientePendiente() {
        int inicio = indiceActual;
        do {
            indiceActual++;
            if (indiceActual >= rosco.size()) {
                indiceActual = 0; 
            }
            if (rosco.get(indiceActual).getEstado() == 0) {
                return true; 
            }
        } while (indiceActual != inicio);
        
        return false; 
    }
}