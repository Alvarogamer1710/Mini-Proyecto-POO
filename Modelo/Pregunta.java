package Modelo;

public class Pregunta {
    private String letra;
    private String definicion;
    private String respuestaCorrecta;
    private int estado; // 0 = Pendiente, 1 = Acierto (Verde), 2 = Fallo (Rojo)

    public Pregunta(String letra, String definicion, String respuestaCorrecta) {
        this.letra = letra;
        this.definicion = definicion;
        this.respuestaCorrecta = respuestaCorrecta;
        this.estado = 0; 
    }

    public String getLetra() { return letra; }
    public String getDefinicion() { return definicion; }
    public String getRespuestaCorrecta() { return respuestaCorrecta; }
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}