package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Modelo.PasapalabraModel;
import Controlador.PasapalabraController;

public class GamesMenuView extends JPanel {

    Mainframe mainframe;
    Color azulPrimario = new Color(33, 150, 243);

    public GamesMenuView(Mainframe mainframe) {
        this.mainframe = mainframe;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245, 247, 250));
        this.setBorder(new EmptyBorder(40, 50, 40, 50));

        JLabel titulo = new JLabel("Menú de Juegos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo, BorderLayout.NORTH);

        JPanel gridJuegos = new JPanel(new GridLayout(2, 2, 20, 20));
        gridJuegos.setOpaque(false); 
        gridJuegos.setBorder(new EmptyBorder(40, 20, 40, 20));

        gridJuegos.add(crearBotonJuego("Ruleta", "🎡"));
        gridJuegos.add(crearBotonJuego("Cartas", "🃏"));
        gridJuegos.add(crearBotonJuego("Buscaminas", "💣"));
        
        JButton btnPasapalabra = crearBotonJuego("Pasapalabra", "🔠");
        btnPasapalabra.addActionListener(e -> {
            try {
                PasapalabraView vistaRosco = new PasapalabraView(mainframe);
                PasapalabraModel modeloRosco = new PasapalabraModel();
                new PasapalabraController(vistaRosco, modeloRosco);
                
                mainframe.agregarPantalla(vistaRosco, "PantallaPasapalabraMVC");
                mainframe.mostrarPantalla("PantallaPasapalabraMVC");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        gridJuegos.add(btnPasapalabra);

        this.add(gridJuegos, BorderLayout.CENTER);

        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSalir.setBackground(Color.WHITE); 
        btnSalir.setForeground(azulPrimario);
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnSalir.addActionListener(e -> mainframe.mostrarPantalla("PantallaLogin"));
        this.add(btnSalir, BorderLayout.SOUTH);
    }

    private JButton crearBotonJuego(String nombre, String icono) {
        JButton btn = new JButton(icono + " " + nombre);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(Color.WHITE); 
        btn.setForeground(new Color(50, 50, 50));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        return btn;
    }
}