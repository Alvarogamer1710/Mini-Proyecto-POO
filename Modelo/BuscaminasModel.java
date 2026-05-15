package Modelo;

import java.util.Random;

public class BuscaminasModel {
    private Celda[][] tablero;
    private int filas = 10;
    private int columnas = 10;
    private int totalMinas = 10;
    private boolean juegoTerminado;
    private boolean victoria;
    private int casillasReveladas;

    public BuscaminasModel() {
        iniciarJuego();
    }

    public void iniciarJuego() {
        tablero = new Celda[filas][columnas];
        juegoTerminado = false;
        victoria = false;
        casillasReveladas = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = new Celda();
            }
        }
        colocarMinas();
        calcularAdyacentes();
    }

    private void colocarMinas() {
        Random rand = new Random();
        int minasColocadas = 0;
        while (minasColocadas < totalMinas) {
            int f = rand.nextInt(filas);
            int c = rand.nextInt(columnas);
            if (!tablero[f][c].isEsMina()) {
                tablero[f][c].setEsMina(true);
                minasColocadas++;
            }
        }
    }

    private void calcularAdyacentes() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!tablero[i][j].isEsMina()) {
                    int count = 0;
                    for (int df = -1; df <= 1; df++) {
                        for (int dc = -1; dc <= 1; dc++) {
                            int nf = i + df;
                            int nc = j + dc;
                            if (nf >= 0 && nf < filas && nc >= 0 && nc < columnas && tablero[nf][nc].isEsMina()) {
                                count++;
                            }
                        }
                    }
                    tablero[i][j].setMinasAdyacentes(count);
                }
            }
        }
    }

    public void revelarCelda(int f, int c) {
        if (f < 0 || f >= filas || c < 0 || c >= columnas || juegoTerminado) return;
        Celda celda = tablero[f][c];
        
        if (celda.isRevelada() || celda.isTieneBandera()) return;

        celda.setRevelada(true);
        casillasReveladas++;

        if (celda.isEsMina()) {
            juegoTerminado = true;
            victoria = false;
        } else if (celda.getMinasAdyacentes() == 0) {
            // Revelar en cadena si está vacía
            for (int df = -1; df <= 1; df++) {
                for (int dc = -1; dc <= 1; dc++) {
                    revelarCelda(f + df, c + dc);
                }
            }
        }
        comprobarVictoria();
    }

    public void alternarBandera(int f, int c) {
        if (juegoTerminado) return;
        Celda celda = tablero[f][c];
        if (!celda.isRevelada()) {
            celda.setTieneBandera(!celda.isTieneBandera());
        }
    }

    private void comprobarVictoria() {
        if (!juegoTerminado && casillasReveladas == (filas * columnas - totalMinas)) {
            juegoTerminado = true;
            victoria = true;
        }
    }

    public Celda getCelda(int f, int c) { return tablero[f][c]; }
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
    public boolean isJuegoTerminado() { return juegoTerminado; }
    public boolean isVictoria() { return victoria; }
}