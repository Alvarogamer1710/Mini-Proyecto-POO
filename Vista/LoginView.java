package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JPanel {

    JTextField usuario;
    JPasswordField password;

    public LoginView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Paleta de colores VIP Casino
        Color fondoNegro = new Color(15, 15, 15);      // Negro casi puro
        Color doradoFuerte = new Color(212, 175, 55);  // Dorado metálico
        Color rojoCasino = new Color(180, 20, 20);     // Rojo tapete de cartas/ruleta
        Color textoBlanco = Color.WHITE;

        // Fondo y márgenes
        this.setBackground(fondoNegro);
        this.setBorder(new EmptyBorder(50, 40, 50, 40));

        // Letrero principal del Casino
        JLabel titulo = new JLabel("🃏 SALÓN DE JUEGO 🃏");
        titulo.setFont(new Font("Georgia", Font.BOLD, 30)); // Fuente con serifas, más clásica
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
        estilizarCampo(usuario, doradoFuerte, fondoNegro, textoBlanco);

        password = new JPasswordField(15);
        estilizarCampo(password, doradoFuerte, fondoNegro, textoBlanco);

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
        
        this.add(Box.createVerticalStrut(50));
        this.add(botonJugar);
    }

    // Método para estandarizar los campos
    private void estilizarCampo(JTextField campo, Color colorBorde, Color colorFondo, Color colorTexto) {
        campo.setMaximumSize(new Dimension(280, 45));
        campo.setPreferredSize(new Dimension(280, 45));
        campo.setFont(new Font("Georgia", Font.PLAIN, 18));
        campo.setBackground(colorFondo);
        campo.setForeground(colorTexto);
        campo.setCaretColor(colorTexto); 
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(colorBorde, 2, false), // Borde dorado recto (más serio)
                BorderFactory.createEmptyBorder(5, 10, 5, 10) // Margen interior para que el texto no se pegue
        ));
        campo.setHorizontalAlignment(JTextField.CENTER);
    }
}