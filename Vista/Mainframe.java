package Vista;

import javax.swing.*;
import java.awt.*;
import Modelo.GestorUsuarios;
import Modelo.Usuario;

public class Mainframe extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private GestorUsuarios gestorUsuarios;
    private Usuario usuarioLogueado;

    public Mainframe() {
        gestorUsuarios = new GestorUsuarios();
        
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
    
    public void agregarPantalla(JPanel panel, String nombrePantalla) { 
        mainPanel.add(panel, nombrePantalla); 
    }
    
    public GestorUsuarios getGestorUsuarios() { return gestorUsuarios; }
    public Usuario getUsuarioLogueado() { return usuarioLogueado; }
    public void setUsuarioLogueado(Usuario usuario) { this.usuarioLogueado = usuario; }

    public static void main(String[] args) {
        try { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); 
        } catch (Exception e) {}
        new Mainframe();
    }
}