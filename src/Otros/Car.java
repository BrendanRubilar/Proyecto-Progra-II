package Otros;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import PanelCentral.Vehicle;

public class Car{
    boolean c = false;
    Vehicle vehicle;
    double X=300,Y=0, Velocity=0.1;

    public Car(){
       
    }

    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect((int)X, (int)Y, 60, 80);
    }

    public void moveDown(){
        Y= Y + Velocity;
    }

    /*
    public void colision(){
	if(r1.intersects(r2)==true){
        c = true;
        }
    }
    */

}

