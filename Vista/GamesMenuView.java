package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Modelo.PasapalabraModel;
import Controlador.PasapalabraController;
import Modelo.BuscaminasModel;
import Controlador.BuscaminasController;

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
        
        JButton btnBuscaminas = crearBotonJuego("Buscaminas", "💣");
        btnBuscaminas.addActionListener(e -> {
            try {
                BuscaminasModel modeloBusca = new BuscaminasModel();
                BuscaminasView vistaBusca = new BuscaminasView(mainframe, modeloBusca.getFilas(), modeloBusca.getColumnas());
                // Pasamos el mainframe al controlador para guardar el tiempo
                new BuscaminasController(vistaBusca, modeloBusca, mainframe);
                
                mainframe.agregarPantalla(vistaBusca, "PantallaBuscaminasMVC");
                mainframe.mostrarPantalla("PantallaBuscaminasMVC");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
        gridJuegos.add(btnBuscaminas);
        
        JButton btnPasapalabra = crearBotonJuego("Pasapalabra", "🔠");
        btnPasapalabra.addActionListener(e -> {
            try {
                PasapalabraView vistaRosco = new PasapalabraView(mainframe);
                PasapalabraModel modeloRosco = new PasapalabraModel();
                new PasapalabraController(vistaRosco, modeloRosco, mainframe);
                
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
        
        btnSalir.addActionListener(e -> {
            mainframe.setUsuarioLogueado(null);
            mainframe.mostrarPantalla("PantallaLogin");
        });
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