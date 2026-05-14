package Vista;

import javax.swing.*;
import java.awt.*;

public class Mainframe extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Mainframe() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // ¡OJO AQUÍ! He ampliado el tamaño a 650x700 para que quepa bien el rosco entero
        this.setSize(650, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("🎰 Salón de Juego de Aranjuez 🎰");
        this.setLocationRelativeTo(null); 

        // Añadimos las tres "cartas" (vistas) a nuestra baraja
        mainPanel.add(new LoginView(this), "PantallaLogin");
        mainPanel.add(new GamesMenuView(this), "PantallaMenu");
        mainPanel.add(new PasapalabraView(this), "PantallaPasapalabra");

        this.add(mainPanel);
        this.setVisible(true);
    }
    
    public void mostrarPantalla(String nombrePantalla) {
        cardLayout.show(mainPanel, nombrePantalla);
    }

    public static void main(String[] args) {
        new Mainframe();
    }
}