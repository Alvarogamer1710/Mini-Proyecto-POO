package Modelo;

public class Usuario {
    private String username;
    private String password;
    private int ultimosAciertos;
    private int ultimosFallos;

    public Usuario(String username, String password, int ultimosAciertos, int ultimosFallos) {
        this.username = username;
        this.password = password;
        this.ultimosAciertos = ultimosAciertos;
        this.ultimosFallos = ultimosFallos;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getUltimosAciertos() { return ultimosAciertos; }
    public void setUltimosAciertos(int ultimosAciertos) { this.ultimosAciertos = ultimosAciertos; }
    public int getUltimosFallos() { return ultimosFallos; }
    public void setUltimosFallos(int ultimosFallos) { this.ultimosFallos = ultimosFallos; }

    @Override
    public String toString() {
        return username + ";" + password + ";" + ultimosAciertos + ";" + ultimosFallos;
    }
}