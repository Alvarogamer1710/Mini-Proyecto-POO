package Vista;

import Vista.LoginView;

import javax.swing.*;
import java.awt.*;

public class Mainframe extends JFrame{


    public Mainframe(){
        
        this.setLayout(new CardLayout());

        
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tus Juegos:");

        this.add(new LoginView());
        this.setVisible(true);

    }






}






