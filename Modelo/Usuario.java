package Modelo;

public class Usuario {
    private String username;
    private String password;
    private int ultimosAciertos;
    private int ultimosFallos;
    private int mejorTiempoBuscaminas; // NUEVO: Guardamos el récord en segundos

    public Usuario(String username, String password, int ultimosAciertos, int ultimosFallos, int mejorTiempoBuscaminas) {
        this.username = username;
        this.password = password;
        this.ultimosAciertos = ultimosAciertos;
        this.ultimosFallos = ultimosFallos;
        this.mejorTiempoBuscaminas = mejorTiempoBuscaminas;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    
    public int getUltimosAciertos() { return ultimosAciertos; }
    public void setUltimosAciertos(int ultimosAciertos) { this.ultimosAciertos = ultimosAciertos; }
    
    public int getUltimosFallos() { return ultimosFallos; }
    public void setUltimosFallos(int ultimosFallos) { this.ultimosFallos = ultimosFallos; }
    
    public int getMejorTiempoBuscaminas() { return mejorTiempoBuscaminas; }
    public void setMejorTiempoBuscaminas(int mejorTiempoBuscaminas) { this.mejorTiempoBuscaminas = mejorTiempoBuscaminas; }

    @Override
    public String toString() {
        // Añadimos el nuevo dato al final separado por ;
        return username + ";" + password + ";" + ultimosAciertos + ";" + ultimosFallos + ";" + mejorTiempoBuscaminas;
    }
}