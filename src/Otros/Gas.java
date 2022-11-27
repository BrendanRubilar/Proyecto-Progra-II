package Otros;

import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

import PanelCentral.InGame;
import PanelCentral.Vehicle;



public class Gas {
    
    double GasX=40, GasY=0;
    static double Velocity=300;
    Random r = new Random();

    public Gas(){

        
        double aux = InGame.w+InGame.x-70; //Punto maximo en donde puede aparecer
        double aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        GasX = r.nextDouble(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto


    }

    public void paint(Graphics g){

        ImageIcon tree = new ImageIcon("Multimedia//Gas.png");
        g.drawImage(tree.getImage(), (int)GasX, (int)GasY,60,60,null); 

    }

    public void moveDown(){
        if(GasY<=1205){
            GasY= GasY + Velocity * InGame.delta_time;
            
        }

        

    }

}
