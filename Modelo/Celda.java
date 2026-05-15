package Modelo;

public class Celda {
    private boolean esMina;
    private boolean revelada;
    private boolean tieneBandera;
    private int minasAdyacentes;

    public Celda() {
        this.esMina = false;
        this.revelada = false;
        this.tieneBandera = false;
        this.minasAdyacentes = 0;
    }

    public boolean isEsMina() { return esMina; }
    public void setEsMina(boolean esMina) { this.esMina = esMina; }
    public boolean isRevelada() { return revelada; }
    public void setRevelada(boolean revelada) { this.revelada = revelada; }
    public boolean isTieneBandera() { return tieneBandera; }
    public void setTieneBandera(boolean tieneBandera) { this.tieneBandera = tieneBandera; }
    public int getMinasAdyacentes() { return minasAdyacentes; }
    public void setMinasAdyacentes(int minasAdyacentes) { this.minasAdyacentes = minasAdyacentes; }
}