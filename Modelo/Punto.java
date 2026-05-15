package Modelo;

public class Punto {
    public int x, y;
    public Punto(int x, int y) { this.x = x; this.y = y; }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Punto) {
            Punto p = (Punto) obj;
            return p.x == this.x && p.y == this.y;
        }
        return false;
    }
}