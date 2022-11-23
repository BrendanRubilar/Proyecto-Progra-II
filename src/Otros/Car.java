
package Otros;

import java.awt.Color;
import java.awt.Graphics;

public class Car {
    
    int X=300,Y=0;

    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(X, Y, 60, 80);
        
        //while(Y!=600){
           // Y= Y + 1; 
        //}
        
    }
    
}

