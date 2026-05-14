package Vista;

import javax.swing.*;
import java.awt.*;

public class Mainframe extends JFrame {

    public Mainframe() {
        this.setLayout(new CardLayout());
        
        // Lo hacemos un poco más grande para que respire el diseño
        this.setSize(450, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("✨ TUS JUEGOS ✨");
        
        // Que se centre en la pantalla al abrir
        this.setLocationRelativeTo(null); 

        this.add(new LoginView());
        this.setVisible(true);
    }
}