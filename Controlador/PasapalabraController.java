package Controlador;

import Modelo.PasapalabraModel;
import Modelo.Pregunta;
import Modelo.Usuario;
import Vista.PasapalabraView;
import Vista.Mainframe;
import javax.swing.JOptionPane;

public class PasapalabraController {
    private PasapalabraView vista;
    private PasapalabraModel modelo;
    private Mainframe mainframe;

    public PasapalabraController(PasapalabraView vista, PasapalabraModel modelo, Mainframe mainframe) {
        this.vista = vista;
        this.modelo = modelo;
        this.mainframe = mainframe; 

        actualizarVistaPregunta();

        this.vista.getBtnResponder().addActionListener(e -> procesarRespuesta());
        this.vista.getBtnPasar().addActionListener(e -> procesarPasapalabra());
    }

    private void procesarRespuesta() {
        String respuesta = vista.getRespuestaUsuario();
        if (respuesta.isEmpty()) return; 

        int indiceAnterior = modelo.getIndiceActual();
        boolean acertado = modelo.comprobarRespuesta(respuesta);

        vista.actualizarColorLetra(indiceAnterior, acertado ? 1 : 2);
        
        vista.limpiarRespuesta();
        avanzarTurno();
    }

    private void procesarPasapalabra() {
        int indiceAnterior = modelo.getIndiceActual();
        modelo.pasarPalabra();
        
        vista.actualizarColorLetra(indiceAnterior, 0);
        
        vista.limpiarRespuesta();
        avanzarTurno();
    }

    private void avanzarTurno() {
        boolean quedanPreguntas = modelo.avanzarSiguientePendiente();
        
        if (quedanPreguntas) {
            actualizarVistaPregunta();
        } else {
            finalizarJuego(); 
        }
    }

    private void finalizarJuego() {
        vista.mostrarPreguntaCentral("FIN", "¡El rosco ha terminado!");
        vista.getBtnResponder().setEnabled(false);
        vista.getBtnPasar().setEnabled(false);

        int aciertos = 0;
        int fallos = 0;
        for (Pregunta p : modelo.getRosco()) {
            if (p.getEstado() == 1) aciertos++;
            if (p.getEstado() == 2) fallos++;
        }

        Usuario u = mainframe.getUsuarioLogueado();
        if (u != null) {
            u.setUltimosAciertos(aciertos);
            u.setUltimosFallos(fallos);
            mainframe.getGestorUsuarios().guardarUsuarios();
        }

        JOptionPane.showMessageDialog(vista, "Juego Terminado.\nAciertos: " + aciertos + "\nFallos: " + fallos + "\n\nSe ha guardado tu puntuación.");
    }

    private void actualizarVistaPregunta() {
        Pregunta p = modelo.getPreguntaActual();
        vista.mostrarPreguntaCentral(p.getLetra(), p.getDefinicion());
        vista.pintarLetraActual(modelo.getIndiceActual()); 
    }
}