package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import Modelo.Usuario;

public class LoginView extends JPanel {

    JTextField usuario;
    JPasswordField password;
    Mainframe mainframe;

    public LoginView(Mainframe mainframe) {
        this.mainframe = mainframe; 
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(245, 247, 250)); 
        this.setBorder(new EmptyBorder(80, 80, 80, 80));

        Color azulPrimario = new Color(33, 150, 243);
        Color textoOscuro = new Color(50, 50, 50);

        JLabel titulo = new JLabel("Bienvenido");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32)); 
        titulo.setForeground(textoOscuro);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usuariol = new JLabel("Usuario");
        usuariol.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        usuariol.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contrasena = new JLabel("Contraseña");
        contrasena.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        contrasena.setAlignmentX(Component.CENTER_ALIGNMENT);

        usuario = new JTextField(15);
        estilizarCampo(usuario);

        password = new JPasswordField(15);
        estilizarCampo(password);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setOpaque(false);

        JButton botonJugar = new JButton("Entrar");
        estilizarBoton(botonJugar, azulPrimario, Color.WHITE);

        JButton botonRegistrar = new JButton("Crear Cuenta");
        estilizarBoton(botonRegistrar, Color.WHITE, azulPrimario);

        botonJugar.addActionListener(e -> {
            String user = usuario.getText();
            String pass = new String(password.getPassword());
            
            Usuario u = mainframe.getGestorUsuarios().validarUsuario(user, pass);
            if (u != null) {
                mainframe.setUsuarioLogueado(u); 
                usuario.setText(""); 
                password.setText("");
                mainframe.mostrarPantalla("PantallaMenu");
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas o usuario no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        botonRegistrar.addActionListener(e -> {
            String user = usuario.getText();
            String pass = new String(password.getPassword());
            if(user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Rellena los campos primero."); 
                return;
            }
            if (mainframe.getGestorUsuarios().registrarUsuario(user, pass)) {
                JOptionPane.showMessageDialog(this, "Usuario creado con éxito. Ya puedes entrar.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Ese nombre de usuario ya está cogido.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        panelBotones.add(botonJugar);
        panelBotones.add(botonRegistrar);

        this.add(titulo); 
        this.add(Box.createVerticalStrut(40));
        this.add(usuariol); 
        this.add(usuario); 
        this.add(Box.createVerticalStrut(20));
        this.add(contrasena); 
        this.add(password); 
        this.add(Box.createVerticalStrut(40));
        this.add(panelBotones);
    }

    private void estilizarCampo(JTextField campo) {
        campo.setMaximumSize(new Dimension(300, 40));
        campo.setPreferredSize(new Dimension(300, 40));
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        campo.setHorizontalAlignment(JTextField.CENTER);
    }

    private void estilizarBoton(JButton btn, Color fondo, Color texto) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(fondo);
        btn.setForeground(texto);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(140, 40));
        if(fondo.equals(Color.WHITE)) {
            btn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        } else {
            btn.setBorder(BorderFactory.createEmptyBorder());
        }
    }
}