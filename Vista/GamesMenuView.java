package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Modelo.PasapalabraModel;
import Controlador.PasapalabraController;
import Modelo.BuscaminasModel;
import Controlador.BuscaminasController;
import Modelo.SnakeModel;
import Controlador.SnakeController;

public class GamesMenuView extends JPanel {

    // Esta línea elimina la alerta amarilla del IDE
    private static final long serialVersionUID = 1L;

    private Mainframe mainframe;
    private Color azulPrimario = new Color(33, 150, 243);
    private Color fondoGris = new Color(245, 247, 250);
    private Color textoOscuro = new Color(50, 50, 50);

    public GamesMenuView(Mainframe mainframe) {
        this.mainframe = mainframe;
        this.setLayout(new BorderLayout());
        this.setBackground(fondoGris);
        this.setBorder(new EmptyBorder(40, 50, 40, 50));

        // TÍTULO DEL MENÚ
        JLabel titulo = new JLabel("Menú de Juegos");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(textoOscuro);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo, BorderLayout.NORTH);

        // GRID DE JUEGOS (2 filas x 2 columnas)
        JPanel gridJuegos = new JPanel(new GridLayout(2, 2, 25, 25));
        gridJuegos.setOpaque(false); 
        gridJuegos.setBorder(new EmptyBorder(40, 20, 40, 20));

        // --- BOTÓN BUSCAMINAS ---
        JButton btnBuscaminas = crearBotonJuego("Buscaminas", "💣");
        btnBuscaminas.addActionListener(e -> {
            try {
                BuscaminasModel modeloBusca = new BuscaminasModel();
                BuscaminasView vistaBusca = new BuscaminasView(mainframe, modeloBusca.getFilas(), modeloBusca.getColumnas());
                new BuscaminasController(vistaBusca, modeloBusca, mainframe);
                
                mainframe.agregarPantalla(vistaBusca, "PantallaBuscaminas");
                mainframe.mostrarPantalla("PantallaBuscaminas");
            } catch (Exception ex) {
                mostrarError("Buscaminas", ex);
            }
        });
        gridJuegos.add(btnBuscaminas);

        // --- BOTÓN PASAPALABRA ---
        JButton btnPasapalabra = crearBotonJuego("Pasapalabra", "🔠");
        btnPasapalabra.addActionListener(e -> {
            try {
                PasapalabraView vistaRosco = new PasapalabraView(mainframe);
                PasapalabraModel modeloRosco = new PasapalabraModel();
                new PasapalabraController(vistaRosco, modeloRosco, mainframe);
                
                mainframe.agregarPantalla(vistaRosco, "PantallaPasapalabra");
                mainframe.mostrarPantalla("PantallaPasapalabra");
            } catch (Exception ex) {
                mostrarError("Pasapalabra", ex);
            }
        });
        gridJuegos.add(btnPasapalabra);

        // --- BOTÓN SNAKE (Con centrado corregido) ---
        JButton btnSnake = crearBotonJuego("Snake", "🐍");
        btnSnake.addActionListener(e -> {
            try {
                SnakeModel modeloSnake = new SnakeModel();
                SnakeView vistaSnake = new SnakeView(mainframe, modeloSnake);
                new SnakeController(vistaSnake, modeloSnake, mainframe);
                
                // Panel contenedor para que el Snake no se pegue a la esquina
                JPanel contenedorCentrado = new JPanel(new GridBagLayout());
                contenedorCentrado.setBackground(new Color(30, 30, 30));
                contenedorCentrado.add(vistaSnake);
                
                mainframe.agregarPantalla(contenedorCentrado, "PantallaSnake");
                mainframe.mostrarPantalla("PantallaSnake");
            } catch (Exception ex) {
                mostrarError("Snake", ex);
            }
        });
        gridJuegos.add(btnSnake);

        // --- BOTÓN PRÓXIMAMENTE ---
        JButton btnProximamente = crearBotonJuego("Próximamente", "✨");
        btnProximamente.setEnabled(false);
        gridJuegos.add(btnProximamente);

        this.add(gridJuegos, BorderLayout.CENTER);

        // BOTÓN CERRAR SESIÓN
        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSalir.setBackground(Color.WHITE); 
        btnSalir.setForeground(azulPrimario);
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.setPreferredSize(new Dimension(0, 50));
        
        btnSalir.addActionListener(e -> {
            mainframe.setUsuarioLogueado(null);
            mainframe.mostrarPantalla("PantallaLogin");
        });
        this.add(btnSalir, BorderLayout.SOUTH);
    }

    private JButton crearBotonJuego(String nombre, String icono) {
        JButton btn = new JButton("<html><center>" + icono + "<br>" + nombre + "</center></html>");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btn.setBackground(Color.WHITE); 
        btn.setForeground(textoOscuro);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if(btn.isEnabled()) btn.setBackground(new Color(240, 240, 240));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if(btn.isEnabled()) btn.setBackground(Color.WHITE);
            }
        });
        
        return btn;
    }

    private void mostrarError(String juego, Exception e) {
        JOptionPane.showMessageDialog(this, "No se pudo iniciar " + juego + ": " + e.getMessage(), 
                                      "Error de Inicio", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}