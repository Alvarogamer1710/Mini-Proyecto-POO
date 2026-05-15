package Controlador;

import Modelo.BuscaminasModel;
import Modelo.Celda;
import Modelo.Usuario;
import Vista.BuscaminasView;
import Vista.Mainframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BuscaminasController {
    private BuscaminasView vista;
    private BuscaminasModel modelo;
    private Mainframe mainframe;
    
    private Timer temporizador;
    private int segundosJugados;
    private boolean primerClic;

    public BuscaminasController(BuscaminasView vista, BuscaminasModel modelo, Mainframe mainframe) {
        this.vista = vista;
        this.modelo = modelo;
        this.mainframe = mainframe;
        
        this.segundosJugados = 0;
        this.primerClic = true;

        // Configuramos el reloj para que sume 1 segundo cada 1000 milisegundos
        temporizador = new Timer(1000, e -> {
            segundosJugados++;
            vista.setTiempoTxt("⏱️ " + segundosJugados + "s");
        });

        asignarEventos();
        actualizarVista();
    }

    private void asignarEventos() {
        for (int i = 0; i < modelo.getFilas(); i++) {
            for (int j = 0; j < modelo.getColumnas(); j++) {
                final int f = i;
                final int c = j;
                
                vista.getBoton(i, j).addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if (modelo.isJuegoTerminado()) return;

                        // Al hacer el primer clic, sea el que sea, empieza a correr el tiempo
                        if (primerClic) {
                            temporizador.start();
                            primerClic = false;
                        }

                        // Clic Derecho = Poner/Quitar Bandera | Clic Izquierdo = Descubrir
                        if (SwingUtilities.isRightMouseButton(e)) {
                            modelo.alternarBandera(f, c);
                        } else if (SwingUtilities.isLeftMouseButton(e)) {
                            modelo.revelarCelda(f, c);
                        }
                        actualizarVista();
                    }
                });
            }
        }
    }

    private void actualizarVista() {
        for (int i = 0; i < modelo.getFilas(); i++) {
            for (int j = 0; j < modelo.getColumnas(); j++) {
                Celda celda = modelo.getCelda(i, j);
                JButton btn = vista.getBoton(i, j);

                if (celda.isRevelada()) {
                    btn.setBackground(Color.WHITE);
                    if (celda.isEsMina()) {
                        btn.setText("💣");
                        btn.setBackground(new Color(244, 67, 54)); 
                    } else if (celda.getMinasAdyacentes() > 0) {
                        btn.setText(String.valueOf(celda.getMinasAdyacentes()));
                        asignarColorNumero(btn, celda.getMinasAdyacentes());
                    } else {
                        btn.setText("");
                    }
                } else {
                    btn.setBackground(new Color(220, 220, 225));
                    if (celda.isTieneBandera()) {
                        btn.setText("🚩");
                    } else {
                        btn.setText("");
                    }
                }
            }
        }

        if (modelo.isJuegoTerminado()) {
            temporizador.stop(); // Paramos el reloj
            
            if (modelo.isVictoria()) {
                vista.setEstadoTxt("¡HAS GANADO! Despejaste el campo.", new Color(76, 175, 80));
                gestionarVictoria();
            } else {
                vista.setEstadoTxt("¡BOOM! Pisaste una mina.", new Color(244, 67, 54));
                revelarTodasLasMinas();
                JOptionPane.showMessageDialog(vista, "Has explotado una mina. Fin del juego.", "Derrota", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void gestionarVictoria() {
        Usuario u = mainframe.getUsuarioLogueado();
        String mensaje = "¡Felicidades, ganaste la partida en " + segundosJugados + " segundos!";
        
        if (u != null) {
            int mejor = u.getMejorTiempoBuscaminas();
            // Si nunca había ganado (0) o si ha superado su récord
            if (mejor == 0 || segundosJugados < mejor) {
                u.setMejorTiempoBuscaminas(segundosJugados);
                mainframe.getGestorUsuarios().guardarUsuarios();
                mensaje += "\n\n🌟 ¡NUEVO RÉCORD PERSONAL! 🌟";
            }
        }
        
        JOptionPane.showMessageDialog(vista, mensaje, "Victoria", JOptionPane.INFORMATION_MESSAGE);
    }

    private void revelarTodasLasMinas() {
        for (int i = 0; i < modelo.getFilas(); i++) {
            for (int j = 0; j < modelo.getColumnas(); j++) {
                if (modelo.getCelda(i, j).isEsMina()) {
                    JButton btn = vista.getBoton(i, j);
                    btn.setText("💣");
                    btn.setBackground(Color.WHITE);
                }
            }
        }
    }

    private void asignarColorNumero(JButton btn, int numero) {
        switch (numero) {
            case 1: btn.setForeground(new Color(33, 150, 243)); break; 
            case 2: btn.setForeground(new Color(76, 175, 80)); break;  
            case 3: btn.setForeground(new Color(244, 67, 54)); break;  
            case 4: btn.setForeground(new Color(156, 39, 176)); break; 
            case 5: btn.setForeground(new Color(121, 85, 72)); break;  
            default: btn.setForeground(Color.BLACK); break;
        }
    }
}