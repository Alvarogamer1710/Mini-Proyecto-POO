package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JPanel {

    JTextField usuario;
    JPasswordField password;

    public LoginView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Hacemos el panel transparente para que se vea el fondo que vamos a dibujar
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(60, 40, 50, 40));

        // Paleta de colores VIP Casino
        Color doradoFuerte = new Color(212, 175, 55);  
        Color rojoCasino = new Color(180, 20, 20);     
        Color textoBlanco = Color.WHITE;
        Color fondoComponentesOscuro = new Color(20, 20, 20, 220); // Muy oscuro para resaltar campos

        // Letrero principal del Casino
        JLabel titulo = new JLabel("🃏 SALÓN DE JUEGO 🃏");
        titulo.setFont(new Font("Georgia", Font.BOLD, 30)); 
        titulo.setForeground(doradoFuerte);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitulo = new JLabel("DE ARANJUEZ");
        subtitulo.setFont(new Font("Georgia", Font.BOLD, 24));
        subtitulo.setForeground(rojoCasino);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Etiquetas
        JLabel usuariol = new JLabel("Nombre del Jugador:");
        usuariol.setFont(new Font("Georgia", Font.ITALIC, 18));
        usuariol.setForeground(doradoFuerte);
        usuariol.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contrasena = new JLabel("Código de Acceso:");
        contrasena.setFont(new Font("Georgia", Font.ITALIC, 18));
        contrasena.setForeground(doradoFuerte);
        contrasena.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campos de texto estilo VIP 
        usuario = new JTextField(15);
        estilizarCampo(usuario, doradoFuerte, fondoComponentesOscuro, textoBlanco);

        password = new JPasswordField(15);
        estilizarCampo(password, doradoFuerte, fondoComponentesOscuro, textoBlanco);

        // Botón de "Apostar"
        JButton botonJugar = new JButton("🎲 ENTRAR AL CASINO 🎲");
        botonJugar.setFont(new Font("Georgia", Font.BOLD, 20));
        botonJugar.setBackground(rojoCasino);
        botonJugar.setForeground(textoBlanco);
        botonJugar.setFocusPainted(false);
        botonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonJugar.setMaximumSize(new Dimension(300, 55));
        botonJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonJugar.setBorder(BorderFactory.createLineBorder(doradoFuerte, 2));

        // Ensamblamos la vista
        this.add(titulo);
        this.add(subtitulo);
        this.add(Box.createVerticalStrut(40));
        
        this.add(usuariol);
        this.add(Box.createVerticalStrut(10));
        this.add(usuario);
        
        this.add(Box.createVerticalStrut(30));
        
        this.add(contrasena);
        this.add(Box.createVerticalStrut(10));
        this.add(password);
        
        this.add(Box.createVerticalStrut(60));
        this.add(botonJugar);
    }

    // Método para estandarizar los campos
    private void estilizarCampo(JTextField campo, Color colorBorde, Color colorFondo, Color colorTexto) {
        campo.setMaximumSize(new Dimension(300, 45));
        campo.setPreferredSize(new Dimension(300, 45));
        campo.setFont(new Font("Georgia", Font.PLAIN, 18));
        campo.setBackground(colorFondo);
        campo.setForeground(colorTexto);
        campo.setCaretColor(colorTexto); 
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde, 2, false), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10) 
        ));
        campo.setHorizontalAlignment(JTextField.CENTER);
    }

    // AQUI ESTÁ LA MAGIA: Dibujamos la bandera y el fondo oscuro con código
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Suavizado de bordes
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int ancho = getWidth();
        int alto = getHeight();

        // Colores de la bandera
        Color rojoEspana = new Color(173, 21, 25);
        Color amarilloEspana = new Color(250, 201, 34);

        // Dibujar franja roja superior (25% del alto)
        g2d.setColor(rojoEspana);
        g2d.fillRect(0, 0, ancho, alto / 4);

        // Dibujar franja amarilla central (50% del alto)
        g2d.setColor(amarilloEspana);
        g2d.fillRect(0, alto / 4, ancho, alto / 2);

        // Dibujar franja roja inferior (25% del alto)
        g2d.setColor(rojoEspana);
        g2d.fillRect(0, (alto / 4) * 3, ancho, alto / 4);

        // Dibujar un "cristal" oscuro semitransparente en el medio para que se lean las letras
        g2d.setColor(new Color(15, 15, 15, 210)); // Negro al 82% de opacidad
        // Dejamos un margen de 20px por los lados para que se vea la bandera como un marco
        g2d.fillRoundRect(20, 20, ancho - 40, alto - 40, 30, 30); 
        
        // Borde dorado para el panel oscuro interior
        g2d.setColor(new Color(212, 175, 55));
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRoundRect(20, 20, ancho - 40, alto - 40, 30, 30);
    }
}