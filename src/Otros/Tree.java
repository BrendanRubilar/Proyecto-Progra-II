package Otros;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import PanelCentral.InGame;

public class Tree {

    double X=40,Y=0;
    static double Velocity=300;

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
        g.setColor(Color.PINK);
        g.fillRect((int)X, (int)Y, 60, 60);   
    }
    
    public void moveDown(){
        if(Y<=1205) Y= Y + Velocity * InGame.delta_time;
    }

    public Boolean deleteTime(){
        if(Y>=2000){
            return true;
        }else{
            return false;
        }
    }   
}
