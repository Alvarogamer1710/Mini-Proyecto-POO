package Vista;

import javax.swing.*;
import java.awt.*;

public class Mainframe extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Mainframe() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        this.setSize(600, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Plataforma de Juegos");
        this.setLocationRelativeTo(null); 

        mainPanel.add(new LoginView(this), "PantallaLogin");
        mainPanel.add(new GamesMenuView(this), "PantallaMenu");

        this.add(mainPanel);
        this.setVisible(true);
    }
    
    public void mostrarPantalla(String nombrePantalla) {
        cardLayout.show(mainPanel, nombrePantalla);
    }

    // NUEVO MÉTODO: Seguro para añadir vistas desde otros paneles
    public void agregarPantalla(JPanel panel, String nombrePantalla) {
        mainPanel.add(panel, nombrePantalla);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Mainframe();
    }
}