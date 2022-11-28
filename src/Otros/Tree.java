package Otros;

import PanelCentral.Vehicle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import PanelCentral.InGame;
public class Tree {

    Point trees = new Point(40,0);
    public static double Velocity=300;

    public Tree(int aux){

        Random r = new Random();

        if(aux==0){

            int aux2=r.nextInt(200-90)+90;
            aux2=aux2 * -1;
            trees.y = aux2;

            trees.x = (int) r.nextDouble(InGame.x-80);

        }else if(aux==1){
            double aux2 = InGame.x + InGame.w;

            trees.x = (int) (r.nextDouble(1020-aux2)+aux2);

        }


    }
   
    public void paint(Graphics g){
        ImageIcon tree = new ImageIcon("Multimedia//arbusto.png");
        
        //g.fillRect((int)X, (int)Y, 60, 60);  
        g.drawImage(tree.getImage(), trees.x, trees.y,60,60,null); 
    }
    
    public void moveDown(){
        if(trees.y<=2000) trees.y = (int) (trees.y + Velocity * InGame.delta_time);

        if(new Rectangle(trees.x,trees.y,60,60).intersects(new Rectangle(Vehicle.vehiclePosition.x, Vehicle.vehiclePosition.y, (int)Vehicle.W, (int)Vehicle.H))){

            InGame.isPlaying = false;

        }
        
    }

    public void setYNearToLimit(){
        trees.y = 1998;
    }
    

    public Boolean deleteTime(){
        if(trees.y>=2000){
            return true;
        }else{
            return false;
        }
    }   
}
