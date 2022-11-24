
package Otros;

import java.awt.Color;
import java.awt.Graphics;

public class Car{
    double X=300,Y=0, Velocity=0.1;
   
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect((int)X, (int)Y, 60, 80);   
    }
    
    public void moveDown(){
        Y= Y + Velocity;
    }
    
}

