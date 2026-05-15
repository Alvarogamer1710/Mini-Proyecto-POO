package Controlador;

import Modelo.SnakeModel;
import Modelo.Usuario;
import Vista.SnakeView;
import Vista.Mainframe;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeController {
    private Timer timer;
    private boolean juegoIniciado = false;

    public SnakeController(SnakeView vista, SnakeModel modelo, Mainframe mainframe) {
        
        SwingUtilities.invokeLater(() -> {
            vista.setFocusable(true);
            vista.requestFocusInWindow();
        });
        
        vista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!juegoIniciado) juegoIniciado = true;
                int t = e.getKeyCode();
                if (t == KeyEvent.VK_UP) modelo.setDireccion(0);
                else if (t == KeyEvent.VK_RIGHT) modelo.setDireccion(1);
                else if (t == KeyEvent.VK_DOWN) modelo.setDireccion(2);
                else if (t == KeyEvent.VK_LEFT) modelo.setDireccion(3);
                if (t == KeyEvent.VK_ESCAPE) { timer.stop(); mainframe.mostrarPantalla("PantallaMenu"); }
            }
        });

        timer = new Timer(150, e -> {
            if (juegoIniciado) {
                modelo.mover();
                vista.repaint();
                if (modelo.isJuegoTerminado()) {
                    timer.stop();
                    gestionarFinJuego(modelo, mainframe, vista);
                }
            }
        });
        timer.start();
    }

    private void gestionarFinJuego(SnakeModel modelo, Mainframe mainframe, SnakeView vista) {
        int puntosObtenidos = modelo.getPuntuacion();
        Usuario u = mainframe.getUsuarioLogueado();
        String msg = "GAME OVER. Puntos: " + puntosObtenidos;

        if (u != null) {
            if (puntosObtenidos > u.getMejorPuntuacionSnake()) {
                u.setMejorPuntuacionSnake(puntosObtenidos);
                mainframe.getGestorUsuarios().guardarUsuarios();
                msg += "\n\n🔥 ¡NUEVO RÉCORD PERSONAL! 🔥";
            }
        }

        JOptionPane.showMessageDialog(vista, msg);
        mainframe.mostrarPantalla("PantallaMenu");
    }
}