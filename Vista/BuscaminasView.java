package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Modelo.Usuario;

public class BuscaminasView extends JPanel {

    Mainframe mainframe;
    Color azulPrimario = new Color(33, 150, 243);
    
    private JButton[][] botones;
    private JLabel lblEstado;
    private JLabel lblTiempo;
    private JButton btnSalir;
    private JPanel panelTablero;

    public BuscaminasView(Mainframe mainframe, int filas, int columnas) {
        this.mainframe = mainframe;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245, 247, 250));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel titulo = new JLabel("Buscaminas");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titulo, BorderLayout.CENTER);

        // Subpanel para poner el temporizador y el récord
        JPanel panelInfo = new JPanel(new GridLayout(2, 1));
        panelInfo.setOpaque(false);
        
        lblTiempo = new JLabel("⏱️ 0s");
        lblTiempo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTiempo.setForeground(azulPrimario);
        lblTiempo.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInfo.add(lblTiempo);

        Usuario u = mainframe.getUsuarioLogueado();
        String recordTxt = (u != null && u.getMejorTiempoBuscaminas() > 0) ? "Récord: " + u.getMejorTiempoBuscaminas() + "s" : "Sin récord";
        JLabel lblRecord = new JLabel(recordTxt);
        lblRecord.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblRecord.setForeground(new Color(100, 100, 100));
        lblRecord.setHorizontalAlignment(SwingConstants.RIGHT);
        panelInfo.add(lblRecord);

        topPanel.add(panelInfo, BorderLayout.EAST);

        lblEstado = new JLabel("Botón Izquierdo: Descubrir | Botón Derecho: Bandera 🚩");
        lblEstado.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblEstado.setForeground(new Color(100, 100, 100));
        lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstado.setBorder(new EmptyBorder(10, 0, 0, 0));
        topPanel.add(lblEstado, BorderLayout.SOUTH);
        
        this.add(topPanel, BorderLayout.NORTH);

        panelTablero = new JPanel(new GridLayout(filas, columnas, 2, 2));
        panelTablero.setOpaque(false);
        panelTablero.setBorder(new EmptyBorder(20, 50, 20, 50));
        
        botones = new JButton[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setFont(new Font("Segoe UI", Font.BOLD, 14));
                botones[i][j].setFocusPainted(false);
                botones[i][j].setBackground(new Color(220, 220, 225));
                botones[i][j].setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180), 1));
                panelTablero.add(botones[i][j]);
            }
        }
        this.add(panelTablero, BorderLayout.CENTER);

        JPanel bottomArea = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomArea.setOpaque(false);

        btnSalir = new JButton("Abandonar Tablero");
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSalir.setBackground(new Color(244, 67, 54));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.setPreferredSize(new Dimension(200, 40));
        btnSalir.setBorder(BorderFactory.createEmptyBorder());
        
        btnSalir.addActionListener(e -> mainframe.mostrarPantalla("PantallaMenu"));
        bottomArea.add(btnSalir);

        this.add(bottomArea, BorderLayout.SOUTH);
    }

    public JButton getBoton(int f, int c) { return botones[f][c]; }
    public void setEstadoTxt(String txt, Color color) { 
        lblEstado.setText(txt); 
        lblEstado.setForeground(color);
    }
    public void setTiempoTxt(String txt) {
        lblTiempo.setText(txt);
    }
}