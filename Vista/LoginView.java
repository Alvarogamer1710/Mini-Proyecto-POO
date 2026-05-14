package Vista;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel{

    JTextField usuario;
    JPasswordField password;

    public LoginView(){

        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel usuariol = new JLabel("Usuario:");
        JLabel contrasena = new JLabel("Contrasena:");

        usuario = new JTextField(15);

        password = new JPasswordField(15);

        usuariol.setAlignmentX(Component.CENTER_ALIGNMENT);
        contrasena.setAlignmentX(Component.CENTER_ALIGNMENT);

        usuario.setMaximumSize(usuario.getPreferredSize());
        password.setMaximumSize(password.getPreferredSize());

        this.add(Box.createVerticalStrut(20));

        this.add(usuariol);
        this.add(usuario);

        this.add(Box.createVerticalStrut(10));

        this.add(contrasena);
        this.add(password);

    }

}