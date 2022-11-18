package PanelCentral;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

public class Window extends JFrame {
    
    
    public Window(){
        
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("FORZA HORIZON 5");
        setResizable(false);
        
        Status StatusController = new Status(this);
        

        
        
                
        setVisible(true);
        
    }
    
    
    
    
}
