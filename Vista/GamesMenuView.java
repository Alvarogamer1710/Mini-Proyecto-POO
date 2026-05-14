package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamesMenuView extends JPanel {

    Mainframe mainframe;

    public GamesMenuView(Mainframe mainframe) {
        this.mainframe = mainframe;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(40, 40, 40, 40));

        Color doradoFuerte = new Color(212, 175, 55);  
        Color rojoCasino = new Color(180, 20, 20);     
        Color textoBlanco = Color.WHITE;

        JLabel titulo = new JLabel("✨ SALA VIP ✨");
        titulo.setFont(new Font("Georgia", Font.BOLD, 36));
        titulo.setForeground(doradoFuerte);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo, BorderLayout.NORTH);

        JPanel gridJuegos = new JPanel(new GridLayout(2, 2, 20, 20));
        gridJuegos.setOpaque(false); 
        gridJuegos.setBorder(new EmptyBorder(40, 20, 40, 20));

        gridJuegos.add(crearBotonJuego("RULETA", "🔴", doradoFuerte, textoBlanco));
        gridJuegos.add(crearBotonJuego("BLACKJACK", "♠️", doradoFuerte, textoBlanco));
        gridJuegos.add(crearBotonJuego("TRAGAPERRAS", "🍒", doradoFuerte, textoBlanco));
        
        // BOTÓN ACTUALIZADO: Pasapalabra con lógica de navegación
        JButton btnPasapalabra = crearBotonJuego("PASAPALABRA", "🔠", doradoFuerte, textoBlanco);
        btnPasapalabra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframe.mostrarPantalla("PantallaPasapalabra");
            }
        });
        gridJuegos.add(btnPasapalabra);

        this.add(gridJuegos, BorderLayout.CENTER);

        JButton btnSalir = new JButton("🚪 COBRAR FICHAS Y SALIR");
        btnSalir.setFont(new Font("Georgia", Font.BOLD, 16));
        btnSalir.setBackground(rojoCasino);
        btnSalir.setForeground(textoBlanco);
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.setBorder(BorderFactory.createLineBorder(doradoFuerte, 2));
        
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainframe.mostrarPantalla("PantallaLogin");
            }
        });

        this.add(btnSalir, BorderLayout.SOUTH);
    }

    private JButton crearBotonJuego(String nombre, String icono, Color borde, Color texto) {
        JButton btn = new JButton(icono + " " + nombre);
        btn.setFont(new Font("Georgia", Font.BOLD, 18));
        btn.setBackground(new Color(20, 20, 20, 220)); 
        btn.setForeground(texto);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(borde, 2));
        return btn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int ancho = getWidth();
        int alto = getHeight();
        Color rojoEspana = new Color(173, 21, 25);
        Color amarilloEspana = new Color(250, 201, 34);

        g2d.setColor(rojoEspana);
        g2d.fillRect(0, 0, ancho, alto / 4);
        g2d.setColor(amarilloEspana);
        g2d.fillRect(0, alto / 4, ancho, alto / 2);
        g2d.setColor(rojoEspana);
        g2d.fillRect(0, (alto / 4) * 3, ancho, alto / 4);

        g2d.setColor(new Color(15, 15, 15, 210)); 
        g2d.fillRoundRect(20, 20, ancho - 40, alto - 40, 30, 30); 
        g2d.setColor(new Color(212, 175, 55));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(20, 20, ancho - 40, alto - 40, 30, 30);
    }
}