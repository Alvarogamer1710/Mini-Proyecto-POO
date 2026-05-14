package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class PasapalabraView extends JPanel {

    Mainframe mainframe;
    Color azulPrimario = new Color(33, 150, 243);
    
    private JLabel letraActual;
    private JTextArea definicion;
    private JTextField respuesta;
    private JButton btnResponder;
    private JButton btnPasar;
    private JButton btnSalir;
    private PanelRoscoCircular roscoCircular;
    private ArrayList<LetraRosco> letrasGraficas; 

    public PasapalabraView(Mainframe mainframe) {
        this.mainframe = mainframe;
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(245, 247, 250));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Pasapalabra");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(new Color(50, 50, 50));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo, BorderLayout.NORTH);

        JPanel gameArea = new JPanel(new BorderLayout());
        gameArea.setOpaque(false);

        roscoCircular = new PanelRoscoCircular();
        letrasGraficas = new ArrayList<>();
        
        String[] alfabeto = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U","V","X","Y","Z"};
        for (String letra : alfabeto) {
            LetraRosco lr = new LetraRosco(letra);
            letrasGraficas.add(lr);
            roscoCircular.add(lr);
        }
        
        JPanel centroPanel = new JPanel();
        centroPanel.setLayout(new BoxLayout(centroPanel, BoxLayout.Y_AXIS));
        centroPanel.setOpaque(false);
        
        letraActual = new JLabel("?");
        letraActual.setFont(new Font("Segoe UI", Font.BOLD, 80));
        letraActual.setForeground(azulPrimario);
        letraActual.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        definicion = new JTextArea("Cargando...");
        definicion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        definicion.setForeground(new Color(50, 50, 50));
        definicion.setBackground(Color.WHITE);
        definicion.setLineWrap(true);
        definicion.setWrapStyleWord(true);
        definicion.setEditable(false);
        definicion.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        definicion.setMaximumSize(new Dimension(300, 150));

        centroPanel.add(Box.createVerticalGlue()); 
        centroPanel.add(letraActual);
        centroPanel.add(Box.createVerticalStrut(10));
        centroPanel.add(definicion);
        centroPanel.add(Box.createVerticalGlue()); 

        gameArea.add(roscoCircular, BorderLayout.CENTER);
        roscoCircular.setLayout(new OverlayLayout(roscoCircular));
        roscoCircular.add(centroPanel);

        this.add(gameArea, BorderLayout.CENTER);

        JPanel bottomArea = new JPanel();
        bottomArea.setLayout(new BoxLayout(bottomArea, BoxLayout.Y_AXIS));
        bottomArea.setOpaque(false);

        respuesta = new JTextField(15);
        estilizarCampo(respuesta);
        respuesta.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false);

        btnResponder = crearBotonAccion("Responder", azulPrimario, Color.WHITE);
        btnPasar = crearBotonAccion("Pasapalabra", Color.WHITE, new Color(50, 50, 50));
        btnSalir = crearBotonAccion("Salir", new Color(244, 67, 54), Color.WHITE);
        
        btnSalir.addActionListener(e -> mainframe.mostrarPantalla("PantallaMenu"));

        buttonPanel.add(btnResponder);
        buttonPanel.add(btnPasar);
        buttonPanel.add(btnSalir);

        bottomArea.add(respuesta);
        bottomArea.add(buttonPanel);
        this.add(bottomArea, BorderLayout.SOUTH);
    }

    public JButton getBtnResponder() { return btnResponder; }
    public JButton getBtnPasar() { return btnPasar; }
    public String getRespuestaUsuario() { return respuesta.getText(); }
    public void limpiarRespuesta() { respuesta.setText(""); }
    
    public void mostrarPreguntaCentral(String letra, String def) {
        letraActual.setText(letra);
        definicion.setText(def);
    }

    public void actualizarColorLetra(int indice, int estado) {
        if(indice >= 0 && indice < letrasGraficas.size()){
            letrasGraficas.get(indice).setEstado(estado);
        }
    }

    public void pintarLetraActual(int indice) {
        actualizarColorLetra(indice, 3); 
    }

    private void estilizarCampo(JTextField campo) {
        campo.setMaximumSize(new Dimension(400, 45));
        campo.setPreferredSize(new Dimension(400, 45));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10) 
        ));
        campo.setHorizontalAlignment(JTextField.CENTER);
    }

    private JButton crearBotonAccion(String texto, Color fondo, Color colorTexto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(fondo);
        btn.setForeground(colorTexto);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 40));
        if(fondo.equals(Color.WHITE)) {
            btn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        } else {
            btn.setBorder(BorderFactory.createEmptyBorder());
        }
        return btn;
    }
}

class PanelRoscoCircular extends JPanel {
    public PanelRoscoCircular() { this.setOpaque(false); }
    @Override
    public void doLayout() {
        super.doLayout();
        int cx = getWidth() / 2, cy = getHeight() / 2;
        int radio = Math.min(cx, cy) - 30; 
        int n = getComponentCount(), letrasContadas = 0;
        int totalLetras = 0;
        for (int i = 0; i < n; i++) if (getComponent(i) instanceof LetraRosco) totalLetras++;

        for (int i = 0; i < n; i++) {
            Component c = getComponent(i);
            if (c instanceof LetraRosco) {
                double angle = -Math.PI / 2 + (2 * Math.PI * letrasContadas / totalLetras);
                Dimension size = c.getPreferredSize();
                int x = (int) (cx + radio * Math.cos(angle)) - size.width / 2;
                int y = (int) (cy + radio * Math.sin(angle)) - size.height / 2;
                c.setBounds(x, y, size.width, size.height);
                letrasContadas++;
            } else {
                c.setBounds(0, 0, getWidth(), getHeight());
            }
        }
    }
}

class LetraRosco extends JLabel {
    private Color colorFondo;
    public LetraRosco(String text) {
        super(text, SwingConstants.CENTER);
        setFont(new Font("Segoe UI", Font.BOLD, 16));
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(35, 35)); 
        colorFondo = new Color(200, 200, 200); 
    }
    public void setEstado(int estado) { 
        if (estado == 1) colorFondo = new Color(76, 175, 80); 
        else if (estado == 2) colorFondo = new Color(244, 67, 54); 
        else if (estado == 3) colorFondo = new Color(33, 150, 243); 
        else colorFondo = new Color(200, 200, 200); 
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorFondo);
        g2.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}