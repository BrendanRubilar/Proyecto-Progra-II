package PanelCentral;

import java.awt.*;
import java.awt.Rectangle;

public class Vehicle {

    private double X = 250, Y = 195, H = 80, W = 60, Velocity = 0.15, giro = 0;

    public Vehicle() {

    }

    public void paint(Graphics g) {
        Graphics2D car = (Graphics2D) g;
        Graphics2D wheel1 = (Graphics2D) g;
        Graphics2D wheel2 = (Graphics2D) g;
        Graphics2D wheel3 = (Graphics2D) g;
        Graphics2D wheel4 = (Graphics2D) g;

        wheel1.rotate(Math.toRadians(giro), X+30, Y+10);
        wheel1.setColor(Color.BLACK);
        wheel1.fillRect((int)X-5, (int)Y+5, (int)W-50, (int)H-60);

        wheel2.rotate(Math.toRadians(giro), X+30, Y+10);
        wheel2.setColor(Color.BLACK);
        wheel2.fillRect((int)X+55, (int)Y+5, (int)W-50, (int)H-60);

        wheel3.rotate(Math.toRadians(giro), X+30, Y+50);
        wheel3.setColor(Color.BLACK);
        wheel3.fillRect((int)X-5, (int)Y+50, (int)W-50, (int)H-60);

        wheel4.rotate(Math.toRadians(giro), X+30, Y+50);
        wheel4.setColor(Color.BLACK);
        wheel4.fillRect((int)X+55, (int)Y+50, (int)W-50, (int)H-60);

        car.rotate(Math.toRadians(giro), X + 30, Y+70);
        car.setColor(Color.yellow);
        car.fillRect((int)X, (int)Y, (int)W, (int)H);

    }

    public void SetPosition(int x, int y) {

    }

    public void MoveUp() {
        if (Y >= 20) {
            Y = Y - Velocity;
            if(giro>0.4){
                giro=giro-0.5;
            }else if(giro<-0.4){
                giro=giro+0.5;
            }else{
                giro=0;
            }
        }
    }

    public void MoveRight() {
        if (X <= 980) {
             X = X + Velocity;
             if(giro<=1){
                giro = giro + 0.01;
            }
        }
    }

    public void MoveLeft() {
        if (X >= 20) {
            X = X - Velocity;
            if(giro>=-1){
                giro = giro - 0.01;
            }
        }
    }

    public void MoveDown() {
        if (Y <= 580) {
            Y = Y + Velocity ;
            if(giro>0.4){
                giro=giro-0.5;
            }else if(giro<-0.4){
                giro=giro+0.5;
            }else{
                giro=0;
            }
        }

    }


    public void Jump(InGame auxInGame){
        
        if(W>=60) W= Math.sin(auxInGame.getTimerAuxiliar())*4*Math.PI+60;
        if(H>=80) H= Math.sin(auxInGame.getTimerAuxiliar())*4*Math.PI+80;
        
        if(H<=80 || W<=60){
            auxInGame.setJumpFalse();
        }

    }

    public void setSize(double H, double W){
        this.H=H;
        this.W=W; 
    }




}   
