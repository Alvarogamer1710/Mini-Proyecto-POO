package Modelo;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class PasapalabraModel {
    private List<Pregunta> rosco;
    private int indiceActual;

    public PasapalabraModel() {
        rosco = new ArrayList<>();
        indiceActual = 0;
        try {
            cargarPreguntasReales();
        } catch (Exception e) {
            System.err.println("Error crítico al cargar las preguntas: " + e.getMessage());
        }
    }

    private void cargarPreguntasReales() throws Exception {
        rosco.add(new Pregunta("A", "Empieza por A: Conjunto de instrucciones sistemáticas y finitas para resolver un problema, muy usado en programación.", "Algoritmo"));
        rosco.add(new Pregunta("B", "Empieza por B: Sistema de numeración utilizado por los procesadores que solo emplea los dígitos 0 y 1.", "Binario"));
        rosco.add(new Pregunta("C", "Empieza por C: Órgano vital de los vertebrados que bombea la sangre a todo el cuerpo.", "Corazón"));
        rosco.add(new Pregunta("D", "Empieza por D: Reptil prehistórico, generalmente de enorme tamaño, que habitó la Tierra hace millones de años.", "Dinosaurio"));
        rosco.add(new Pregunta("E", "Empieza por E: Astro que brilla con luz propia en el firmamento.", "Estrella"));
        rosco.add(new Pregunta("F", "Empieza por F: Fenómeno caracterizado por la emisión de calor y de luz, generalmente con llama.", "Fuego"));
        rosco.add(new Pregunta("G", "Empieza por G: Instrumento musical de cuerda provisto de un mástil y una caja de resonancia.", "Guitarra"));
        rosco.add(new Pregunta("H", "Empieza por H: Conjunto de los componentes físicos o materiales de un ordenador.", "Hardware"));
        rosco.add(new Pregunta("I", "Empieza por I: Red informática mundial, descentralizada y formada por la conexión de computadoras.", "Internet"));
        rosco.add(new Pregunta("J", "Empieza por J: Popular lenguaje de programación orientado a objetos, cuyo logo es una taza de café humeante.", "Java"));
        rosco.add(new Pregunta("K", "Empieza por K: Arte marcial de origen japonés que se basa en golpes secos con el borde de la mano, los codos o los pies.", "Karate"));
        rosco.add(new Pregunta("L", "Empieza por L: Único satélite natural de la Tierra.", "Luna"));
        rosco.add(new Pregunta("M", "Empieza por M: Periférico de salida que muestra la información gráfica de un ordenador.", "Monitor"));
        rosco.add(new Pregunta("N", "Empieza por N: Masa visible formada por cristales de nieve o gotas de agua suspendidas en la atmósfera.", "Nube"));
        rosco.add(new Pregunta("Ñ", "Empieza por Ñ: Mamífero rumiante africano que tiene un aspecto a medio camino entre el antílope y el toro.", "Ñu"));
        rosco.add(new Pregunta("O", "Empieza por O: Órgano principal del sistema visual de los seres vivos.", "Ojo"));
        rosco.add(new Pregunta("P", "Empieza por P: Cuerpo celeste sólido que gira alrededor de una estrella y que no emite luz propia.", "Planeta"));
        rosco.add(new Pregunta("Q", "Empieza por Q: Alimento sólido que se obtiene por maduración de la cuajada de la leche.", "Queso"));
        rosco.add(new Pregunta("R", "Empieza por R: Dispositivo de red que permite conectar tu casa a internet y repartir la señal Wi-Fi.", "Router"));
        rosco.add(new Pregunta("S", "Empieza por S: Estrella luminosa, centro de nuestro sistema planetario.", "Sol"));
        rosco.add(new Pregunta("T", "Empieza por T: Periférico de entrada provisto de teclas, fundamental para escribir en el ordenador.", "Teclado"));
        rosco.add(new Pregunta("U", "Empieza por U: Conjunto de todo lo que tiene existencia física, en la Tierra y fuera de ella.", "Universo"));
        rosco.add(new Pregunta("V", "Empieza por V: Nombre de la profesora icónica de la asignatura de Programación Orientada a Objetos.", "Vanessa"));
        rosco.add(new Pregunta("X", "Contiene la X: Coche de alquiler con conductor, provisto de taxímetro, que presta un servicio público.", "Taxi"));
        rosco.add(new Pregunta("Y", "Empieza por Y: Embarcación de recreo a vela o a motor, normalmente de lujo.", "Yate"));
        rosco.add(new Pregunta("Z", "Empieza por Z: Calzado que no pasa del tobillo, con la parte inferior de suela y el resto de cuero, tela u otro material.", "Zapato"));
        
        if (rosco.size() != 26) {
            throw new Exception("El rosco no contiene exactamente 26 preguntas.");
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
        String respuestaLimpia = normalizarTexto(respuestaUsuario);
        String correctaLimpia = normalizarTexto(actual.getRespuestaCorrecta());

        if (correctaLimpia.equalsIgnoreCase(respuestaLimpia)) {
            actual.setEstado(1); 
            return true;
        } else {
            actual.setEstado(2); 
            return false;
        }
    }

    public void pasarPalabra() {
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

    private String normalizarTexto(String texto) {
        if (texto == null) return "";
        String limpio = texto.trim(); 
        limpio = Normalizer.normalize(limpio, Normalizer.Form.NFD);
        limpio = limpio.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return limpio;
    }

    public List<Pregunta> getRosco() {
        return rosco;
    }
}