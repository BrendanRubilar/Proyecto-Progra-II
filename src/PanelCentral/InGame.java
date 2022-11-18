
package PanelCentral;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//ESTA CLASE ES UN PANEL QUE REPRESENTARÁ IN GAME, SE HACE COMO CLASE PARA PODER USAR EL METODO PAINT 
public class InGame extends JPanel implements KeyListener{
    
    Vehicle vehicle;
    
    public InGame(){
        this.setLayout(null);
        vehicle = new Vehicle();
        this.addKeyListener(this);
        this.setFocusable(true);
    
    }

    //DIBUJAR EN EL PANEL 
    public void paint (Graphics g) {
        super.paint(g);
        
        //TEST DE UNA PISTA
        int x=200,y=5;

        g.setColor(new Color(159, 159, 159));
        g.fillOval(x, y, 500, 500);
        g.setColor(new Color(24, 129, 55));
        g.fillOval(x + 150, y + 150, 200, 200);
        g.setColor(Color.WHITE); 
        vehicle.paint(g);
    }
    
    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    //Control del teclado LISTA DE CODIGOS: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
    @Override
    public void keyPressed(KeyEvent e) {
        
        switch(e.getKeyCode()) {
            
            case 37:     
                vehicle.MoveLeft();
                repaint();
                break;
                
            case 38: 
                vehicle.MoveUp();
                repaint();

                break;
            case 39:
                
                vehicle.MoveRight();
                repaint();

                break;
            case 40: 
                vehicle.MoveDown();
                repaint();
                break;
    
            case 27:
               
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //BANDERA
        System.out.println("You released key char: " + e.getKeyChar());
    
    }  
    
}


