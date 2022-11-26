package Otros;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import PanelCentral.InGame;

public class Tree {

    double X=40,Y=0;
    static double Velocity=300;

    public Tree(){

        Random r = new Random();
        int aux = r.nextInt(2);
       
        if(aux==0){

            X = r.nextDouble();

        }else if(aux==1){

        }
  

    }
   
    public void paint(Graphics g){
        g.setColor(Color.PINK);
        g.fillRect((int)X, (int)Y, 60, 60);   
    }
    
    public void moveDown(){
        if(Y<=1205) Y= Y + Velocity * InGame.delta_time;
    }

    public Boolean deleteTime(){
        if(Y>=1200){
            return true;
        }else{
            return false;
        }
    }
    
}
