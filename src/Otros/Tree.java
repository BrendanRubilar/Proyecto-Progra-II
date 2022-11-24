package Otros;

import java.awt.Color;
import java.awt.Graphics;

public class Tree {

    double X=40,Y=0, Velocity=0.1;
   
    public void paint(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect((int)X, (int)Y, 60, 60);   
    }
    
    public void moveDown(){
        Y= Y + Velocity;
    }

    
}
