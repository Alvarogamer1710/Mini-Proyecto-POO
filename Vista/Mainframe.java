package Vista;

import javax.swing.*;
import java.awt.*;

public class Mainframe extends JFrame {

    public Mainframe() {
        this.setLayout(new CardLayout());
        
        // Un poco más grande para que quepa todo el glamour
        this.setSize(500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("🎰 Salón de Juego de Aranjuez 🎰");
        
        this.setLocationRelativeTo(null); 

        this.add(new LoginView());
        this.setVisible(true);
    }
}