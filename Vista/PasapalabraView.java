package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PasapalabraView extends JPanel {

    Mainframe mainframe;
    Color doradoFuerte = new Color(212, 175, 55);  
    Color rojoCasino = new Color(180, 20, 20);     

    public PasapalabraView(Mainframe mainframe) {
        this.mainframe = mainframe;
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(30, 30, 30, 30));

        // Título VIP
        JLabel titulo = new JLabel("🔠 EL ROSCO DE ARANJUEZ 🔠");
        titulo.setFont(new Font("Georgia", Font.BOLD, 32));
        titulo.setForeground(doradoFuerte);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo, BorderLayout.NORTH);

        // ZONA CENTRAL DEL JUEGO
        JPanel gameArea = new JPanel();
        gameArea.setLayout(new BoxLayout(gameArea, BoxLayout.Y_AXIS));
        gameArea.setOpaque(false);
        gameArea.add(Box.createVerticalStrut(20));

        // 1. Rejilla de letras simulando el Rosco
        JPanel roscoGrid = new JPanel(new GridLayout(2, 13, 8, 8));
        roscoGrid.setOpaque(false);
        String[] alfabeto = {"A","B","C","D","E","F","G","H","I","J","L","M","N","Ñ","O","P","Q","R","S","T","U","V","X","Y","Z","★"};
        
        for (int i = 0; i < alfabeto.length; i++) {
            LetraRosco lr = new LetraRosco(alfabeto[i]);
            // Ponemos algunos colores de prueba para que veas cómo luce
            if (i == 0) lr.setEstado(1); // Verde (Acierto)
            if (i == 1) lr.setEstado(2); // Rojo (Fallo)
            if (i == 25) lr.setEstado(3); // Estrella dorada
            roscoGrid.add(lr);
        }
        
        // Centramos la rejilla
        JPanel roscoWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        roscoWrapper.setOpaque(false);
        roscoWrapper.add(roscoGrid);
        gameArea.add(roscoWrapper);
        gameArea.add(Box.createVerticalStrut(20));

        // 2. Letra Actual gigante
        JLabel letraActual = new JLabel("C");
        letraActual.setFont(new Font("Georgia", Font.BOLD, 90));
        letraActual.setForeground(doradoFuerte);
        letraActual.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameArea.add(letraActual);
        gameArea.add(Box.createVerticalStrut(10));

        // 3. Definición de la palabra
        JTextArea definicion = new JTextArea("Empieza por C:\nLugar maravilloso donde vas a dejarte el sueldo apostando al rojo porque 'esta vez sí que sale, te lo juro'.");
        definicion.setFont(new Font("Georgia", Font.ITALIC, 20));
        definicion.setForeground(Color.WHITE);
        definicion.setOpaque(false);
        definicion.setLineWrap(true);
        definicion.setWrapStyleWord(true);
        definicion.setEditable(false);
        definicion.setFocusable(false);
        
        JPanel defPanel = new JPanel(new BorderLayout());
        defPanel.setOpaque(false);
        defPanel.add(definicion, BorderLayout.CENTER);
        defPanel.setMaximumSize(new Dimension(500, 100));
        gameArea.add(defPanel);
        gameArea.add(Box.createVerticalStrut(20));

        // 4. Caja para escribir la respuesta
        JTextField respuesta = new JTextField(15);
        estilizarCampo(respuesta, doradoFuerte, new Color(15, 15, 15, 220), Color.WHITE);
        respuesta.setAlignmentX(Component.CENTER_ALIGNMENT);
        gameArea.add(respuesta);

        this.add(gameArea, BorderLayout.CENTER);

        // ZONA INFERIOR: Botones de Acción
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        buttonPanel.setOpaque(false);

        JButton btnResponder = new JButton("✅ RESPONDER");
        estilizarBoton(btnResponder, new Color(40, 167, 69), doradoFuerte);
        
        JButton btnPasar = new JButton("⏭️ PASAPALABRA");
        estilizarBoton(btnPasar, new Color(0, 102, 204), doradoFuerte);

        JButton btnSalir = new JButton("🚪 HUIR AL MENÚ");
        estilizarBoton(btnSalir, rojoCasino, doradoFuerte);
        
        // Volver al menú de juegos
        btnSalir.addActionListener(e -> mainframe.mostrarPantalla("PantallaMenu"));

        buttonPanel.add(btnResponder);
        buttonPanel.add(btnPasar);
        buttonPanel.add(btnSalir);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    // Estilos para el campo de texto
    private void estilizarCampo(JTextField campo, Color colorBorde, Color colorFondo, Color colorTexto) {
        campo.setMaximumSize(new Dimension(400, 50));
        campo.setPreferredSize(new Dimension(400, 50));
        campo.setFont(new Font("Georgia", Font.BOLD, 22));
        campo.setBackground(colorFondo);
        campo.setForeground(colorTexto);
        campo.setCaretColor(colorTexto); 
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde, 2, false), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10) 
        ));
        campo.setHorizontalAlignment(JTextField.CENTER);
    }

    // Estilos para los botones del rosco
    private void estilizarBoton(JButton btn, Color bgColor, Color borderColor) {
        btn.setFont(new Font("Georgia", Font.BOLD, 15));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 2),
            BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
    }

    // Fondo de la bandera de España con el marco de casino
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

        g2d.setColor(new Color(15, 15, 15, 215)); // Un pelín más oscuro para resaltar bien las letras
        g2d.fillRoundRect(20, 20, ancho - 40, alto - 40, 30, 30); 
        g2d.setColor(doradoFuerte);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(20, 20, ancho - 40, alto - 40, 30, 30);
    }
}

// --- CLASE AUXILIAR PARA PINTAR LAS LETRAS REDONDAS ---
class LetraRosco extends JLabel {
    private Color colorFondo;

    public LetraRosco(String text) {
        super(text, SwingConstants.CENTER);
        setFont(new Font("Arial", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(36, 36));
        colorFondo = new Color(0, 102, 204); // Azul base
    }

    // Cambiar color según acierto o fallo
    public void setEstado(int estado) { 
        if (estado == 1) colorFondo = new Color(40, 167, 69);       // Verde acierto
        else if (estado == 2) colorFondo = new Color(220, 53, 69);  // Rojo fallo
        else if (estado == 3) colorFondo = new Color(212, 175, 55); // Dorado estrella
        else colorFondo = new Color(0, 102, 204);                   // Azul pendiente
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorFondo);
        g2.fillOval(2, 2, getWidth()-4, getHeight()-4);
        g2.setColor(new Color(212, 175, 55)); // Borde dorado
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(2, 2, getWidth()-4, getHeight()-4);
        super.paintComponent(g);
    }
}