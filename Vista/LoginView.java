package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JPanel {

    JTextField usuario;
    JPasswordField password;
    Mainframe mainframe;

    public LoginView(Mainframe mainframe) {
        this.mainframe = mainframe; 
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(245, 247, 250)); 
        this.setBorder(new EmptyBorder(100, 80, 80, 80));

        Color azulPrimario = new Color(33, 150, 243);
        Color textoOscuro = new Color(50, 50, 50);

        JLabel titulo = new JLabel("Bienvenido");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32)); 
        titulo.setForeground(textoOscuro);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usuariol = new JLabel("Usuario");
        usuariol.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usuariol.setForeground(textoOscuro);
        usuariol.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contrasena = new JLabel("Contraseña");
        contrasena.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        contrasena.setForeground(textoOscuro);
        contrasena.setAlignmentX(Component.CENTER_ALIGNMENT);

        usuario = new JTextField(15);
        estilizarCampo(usuario);

        password = new JPasswordField(15);
        estilizarCampo(password);

        JButton botonJugar = new JButton("Iniciar Sesión");
        botonJugar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        botonJugar.setBackground(azulPrimario);
        botonJugar.setForeground(Color.WHITE);
        botonJugar.setFocusPainted(false);
        botonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonJugar.setMaximumSize(new Dimension(300, 45));
        botonJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        botonJugar.addActionListener(e -> {
            String user = usuario.getText();
            String pass = new String(password.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                usuario.setText("");
                password.setText("");
                mainframe.mostrarPantalla("PantallaMenu");
            } else {
                JOptionPane.showMessageDialog(LoginView.this, 
                    "Credenciales incorrectas.", 
                    "Error de autenticación", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        this.add(titulo);
        this.add(Box.createVerticalStrut(60));
        this.add(usuariol);
        this.add(Box.createVerticalStrut(5));
        this.add(usuario);
        this.add(Box.createVerticalStrut(20));
        this.add(contrasena);
        this.add(Box.createVerticalStrut(5));
        this.add(password);
        this.add(Box.createVerticalStrut(50));
        this.add(botonJugar);
    }

    private void estilizarCampo(JTextField campo) {
        campo.setMaximumSize(new Dimension(300, 40));
        campo.setPreferredSize(new Dimension(300, 40));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10) 
        ));
        campo.setHorizontalAlignment(JTextField.CENTER);
    }
}