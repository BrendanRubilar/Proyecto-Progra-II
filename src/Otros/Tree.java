package Otros;

import PanelCentral.Vehicle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import PanelCentral.InGame;
public class Tree {

    double X=40,Y=0;
    public static double Velocity=300;

    public Tree(int aux){

        Random r = new Random();

        if(aux==0){

            double aux2=r.nextDouble(200-90)+90;
            aux2=aux2 * -1;
            Y = aux2;

            X = r.nextDouble(InGame.x-80);

        }else if(aux==1){
            double aux2 = InGame.x + InGame.w;

            X = r.nextDouble(1020-aux2)+aux2;

        }


    }
   
    public void paint(Graphics g){
        ImageIcon tree = new ImageIcon("Multimedia//arbusto.png");
        
        //g.fillRect((int)X, (int)Y, 60, 60);  
        g.drawImage(tree.getImage(), (int)X, (int)Y,60,60,null); 
    }
    
    public void moveDown(){
        if(Y<=2000) Y= Y + Velocity * InGame.delta_time;

        if(((Vehicle.X >= X-60 && Vehicle.X < X + 60) && (Vehicle.Y >= Y-60 && Vehicle.Y < Y + 60)) || (Vehicle.X == X && Vehicle.Y == Y)){
            System.out.println("COLISIOOOOOON!!!!!");

            InGame.isPlaying = false;

        }
        
    }

    public void setYNearToLimit(){
        Y = 1998;
    }
    

    public Boolean deleteTime(){
        if(Y>=2000){
            return true;
        }else{
            return false;
        }
    }   
}
