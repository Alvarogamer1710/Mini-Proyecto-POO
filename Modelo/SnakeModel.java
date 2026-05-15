package Modelo;

import java.util.LinkedList;
import java.util.Random;

public class SnakeModel {
    private LinkedList<Punto> cuerpo;
    private Punto comida;
    private int direccion; // 0: Arriba, 1: Derecha, 2: Abajo, 3: Izquierda
    private int filas = 20, columnas = 20;
    private boolean juegoTerminado = false;
    private int puntuacion = 0;

    public SnakeModel() {
        cuerpo = new LinkedList<>();
        cuerpo.add(new Punto(10, 10)); // Cabeza inicial
        direccion = 1; 
        generarComida();
    }

    public void generarComida() {
        Random r = new Random();
        do {
            comida = new Punto(r.nextInt(columnas), r.nextInt(filas));
        } while (cuerpo.contains(comida));
    }

    public void mover() {
        if (juegoTerminado) return;
        Punto cabeza = cuerpo.getFirst();
        Punto nuevaCabeza = new Punto(cabeza.x, cabeza.y);

        if (direccion == 0) nuevaCabeza.y--;
        else if (direccion == 1) nuevaCabeza.x++;
        else if (direccion == 2) nuevaCabeza.y++;
        else if (direccion == 3) nuevaCabeza.x--;

        // Colisión con bordes o cuerpo
        if (nuevaCabeza.x < 0 || nuevaCabeza.x >= columnas || nuevaCabeza.y < 0 || nuevaCabeza.y >= filas || cuerpo.contains(nuevaCabeza)) {
            juegoTerminado = true;
            return;
        }

        cuerpo.addFirst(nuevaCabeza);
        if (nuevaCabeza.equals(comida)) {
            puntuacion += 10;
            generarComida();
        } else {
            cuerpo.removeLast();
        }
    }

    public void setDireccion(int d) {
        // Evitar que la serpiente se gire 180 grados sobre sí misma
        if (Math.abs(this.direccion - d) != 2) this.direccion = d;
    }

    public LinkedList<Punto> getCuerpo() { return cuerpo; }
    public Punto getComida() { return comida; }
    public int getPuntuacion() { return puntuacion; }
    public boolean isJuegoTerminado() { return juegoTerminado; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
}