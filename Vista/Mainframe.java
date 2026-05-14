package Vista;

import javax.swing.*;
import java.awt.*;

public class Mainframe extends JFrame {

    // Declaramos el CardLayout y el panel principal a nivel de clase para poder usarlos
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Mainframe() {
        // Inicializamos el gestor de cartas y el panel que las contendrá
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        this.setSize(550, 650);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("🎰 Salón de Juego de Aranjuez 🎰");
        this.setLocationRelativeTo(null); 

        // Añadimos las dos "cartas" (vistas) a nuestra baraja
        // Le pasamos 'this' (el Mainframe) para que las vistas puedan decirle que cambie de pantalla
        mainPanel.add(new LoginView(this), "PantallaLogin");
        mainPanel.add(new GamesMenuView(this), "PantallaMenu");

        // Añadimos el contenedor principal a la ventana
        this.add(mainPanel);
        this.setVisible(true);
    }
    
    // Este es el método mágico que las vistas llamarán para cambiar de pantalla
    public void mostrarPantalla(String nombrePantalla) {
        cardLayout.show(mainPanel, nombrePantalla);
    }

    public static void main(String[] args) {
        new Mainframe();
    }
}