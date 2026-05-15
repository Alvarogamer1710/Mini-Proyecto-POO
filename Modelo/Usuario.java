package Modelo;

public class Usuario {
    private String username;
    private String password;
    private int ultimosAciertos;
    private int ultimosFallos;
    private int mejorTiempoBuscaminas;
    private int mejorPuntuacionSnake; // NUEVO DATO

    public Usuario(String username, String password, int ultimosAciertos, int ultimosFallos, int mejorTiempoBuscaminas, int mejorPuntuacionSnake) {
        this.username = username;
        this.password = password;
        this.ultimosAciertos = ultimosAciertos;
        this.ultimosFallos = ultimosFallos;
        this.mejorTiempoBuscaminas = mejorTiempoBuscaminas;
        this.mejorPuntuacionSnake = mejorPuntuacionSnake;
    }

    // Getters y Setters necesarios
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getUltimosAciertos() { return ultimosAciertos; }
    public void setUltimosAciertos(int u) { this.ultimosAciertos = u; }
    public int getUltimosFallos() { return ultimosFallos; }
    public void setUltimosFallos(int f) { this.ultimosFallos = f; }
    public int getMejorTiempoBuscaminas() { return mejorTiempoBuscaminas; }
    public void setMejorTiempoBuscaminas(int t) { this.mejorTiempoBuscaminas = t; }
    public int getMejorPuntuacionSnake() { return mejorPuntuacionSnake; }
    public void setMejorPuntuacionSnake(int s) { this.mejorPuntuacionSnake = s; }

    @Override
    public String toString() {
        return username + ";" + password + ";" + ultimosAciertos + ";" + ultimosFallos + ";" + mejorTiempoBuscaminas + ";" + mejorPuntuacionSnake;
    }
}