package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JPanel {

    JTextField usuario;
    JPasswordField password;

    public LoginView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Colores estilo TikTok
        Color fondoOscuro = new Color(20, 20, 20);
        Color rosaNeon = new Color(254, 44, 85);
        Color cianNeon = new Color(37, 244, 238);
        Color textoBlanco = Color.WHITE;

        // Aplicamos el fondo y unos márgenes
        this.setBackground(fondoOscuro);
        this.setBorder(new EmptyBorder(40, 40, 40, 40));

        // Título llamativo
        JLabel titulo = new JLabel("🔥 ¡VAMOS A JUGAR! 🔥");
        titulo.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
        titulo.setForeground(textoBlanco);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Etiquetas
        JLabel usuariol = new JLabel("👾 USUARIO:");
        usuariol.setFont(new Font("Arial", Font.BOLD, 18));
        usuariol.setForeground(cianNeon);
        usuariol.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contrasena = new JLabel("🔒 CONTRASEÑA:");
        contrasena.setFont(new Font("Arial", Font.BOLD, 18));
        contrasena.setForeground(rosaNeon);
        contrasena.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campos de texto
        usuario = new JTextField(15);
        estilizarCampo(usuario, cianNeon, fondoOscuro, textoBlanco);

        password = new JPasswordField(15);
        estilizarCampo(password, rosaNeon, fondoOscuro, textoBlanco);

        // Botón gigante para jugar
        JButton botonJugar = new JButton("▶ JUGAR ▶");
        botonJugar.setFont(new Font("Arial", Font.BOLD, 24));
        botonJugar.setBackground(rosaNeon);
        botonJugar.setForeground(textoBlanco);
        botonJugar.setFocusPainted(false);
        botonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonJugar.setMaximumSize(new Dimension(250, 50));
        botonJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Añadimos los componentes con espaciado (struts) para que quede limpio
        this.add(titulo);
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

    // Método auxiliar para no repetir código al poner bonitos los campos de texto
    private void estilizarCampo(JTextField campo, Color colorBorde, Color colorFondo, Color colorTexto) {
        campo.setMaximumSize(new Dimension(250, 40));
        campo.setPreferredSize(new Dimension(250, 40));
        campo.setFont(new Font("Arial", Font.BOLD, 16));
        campo.setBackground(colorFondo);
        campo.setForeground(colorTexto);
        campo.setCaretColor(Color.WHITE); // El palito que parpadea al escribir
        campo.setBorder(BorderFactory.createLineBorder(colorBorde, 3, true)); // Borde grueso
        campo.setHorizontalAlignment(JTextField.CENTER); // Texto centrado
    }
}