package Vista;

import javax.swing.*;
import java.awt.*;
import Modelo.Punto;
import Modelo.SnakeModel;
import Modelo.Usuario;

public class SnakeView extends JPanel {
    private Mainframe mainframe;
    private SnakeModel modelo;
    private int escala = 25;

    public SnakeView(Mainframe mainframe, SnakeModel modelo) {
        this.mainframe = mainframe;
        this.modelo = modelo;
        int ancho = modelo.getColumnas() * escala;
        int alto = modelo.getFilas() * escala;
        this.setPreferredSize(new Dimension(ancho, alto));
        this.setBackground(new Color(15, 15, 15));
        this.setBorder(BorderFactory.createLineBorder(new Color(33, 150, 243), 2));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int anchoT = modelo.getColumnas() * escala;
        int altoT = modelo.getFilas() * escala;

        // Rejilla
        g2.setColor(new Color(40, 40, 40));
        for (int i = 0; i <= modelo.getColumnas(); i++) g2.drawLine(i * escala, 0, i * escala, altoT);
        for (int j = 0; j <= modelo.getFilas(); j++) g2.drawLine(0, j * escala, anchoT, j * escala);

        // Comida
        g2.setColor(new Color(255, 65, 54));
        g2.fillOval(modelo.getComida().x * escala + 2, modelo.getComida().y * escala + 2, escala - 4, escala - 4);

        // Serpiente
        g2.setColor(new Color(46, 204, 113));
        for (Punto p : modelo.getCuerpo()) g2.fillRoundRect(p.x * escala + 1, p.y * escala + 1, escala - 2, escala - 2, 10, 10);

        // Puntuación y Récord
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        g2.drawString("SCORE: " + modelo.getPuntuacion(), 15, 30);
        
        Usuario u = mainframe.getUsuarioLogueado();
        if (u != null) {
            g2.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            g2.drawString("BEST: " + u.getMejorPuntuacionSnake(), 15, 55);
        }
    }
}