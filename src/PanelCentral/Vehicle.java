package PanelCentral;

import java.awt.*;

public class Vehicle {

    private static double X = 250, Y = 195, H = 80, W = 60, Velocity = 300, giro = 0;

    public Vehicle() {


    }

    public void paint(Graphics g) {
        Graphics2D car = (Graphics2D) g;
        Graphics2D wheel1 = (Graphics2D) g;
        Graphics2D wheel2 = (Graphics2D) g;
        Graphics2D wheel3 = (Graphics2D) g;
        Graphics2D wheel4 = (Graphics2D) g;

        wheel1.rotate(Math.toRadians(giro)*10, X+30, Y+10);
        wheel1.setColor(Color.BLACK);
        wheel1.fillRoundRect((int)X-5, (int)Y+5, (int)W-50, (int)H-60,(int)W-55,(int)H-70);

        wheel2.rotate(Math.toRadians(giro)*10, X+30, Y+10);
        wheel2.setColor(Color.BLACK);
        wheel2.fillRoundRect((int)X+55, (int)Y+5, (int)W-50, (int)H-60,(int)W-55,(int)H-70);

        wheel3.rotate(Math.toRadians(giro), X+30, Y+50);
        wheel3.setColor(Color.BLACK);
        wheel3.fillRoundRect((int)X-5, (int)Y+50, (int)W-50, (int)H-60,(int)W-55,(int)H-70);

        wheel4.rotate(Math.toRadians(giro), X+30, Y+50);
        wheel4.setColor(Color.BLACK);
        wheel4.fillRoundRect((int)X+55, (int)Y+50, (int)W-50, (int)H-60,(int)W-55,(int)H-70);

        car.rotate(Math.toRadians(giro)*(0.1), X + 30, Y+80);
        car.setColor(Color.yellow);
        car.fillRoundRect((int)X, (int)Y, (int)W, (int)H,(int)W-5,(int)H-60);

    }

    public void SetPosition(int x, int y) {

    }

    public void MoveUp() {
        if (Y >= 20 && X>=290 && X<=790) {
            Y = Y - Velocity * InGame.delta_time;
        }
        if (Y>=20 && X >= 0 && X<=290 || Y>=20 && X >= 790 && X<=1080) {
            Y = Y - (Velocity*0.66)* InGame.delta_time; 
        }
        if (giro>0.2){
            giro=giro-0.02;
        }else if(giro<-0.2){
            giro=giro+0.02;
        }else{
            giro=0;
        }
    }

    public void MoveRight() {
        if (X>=290 && X<=790) {
             X = X + (Velocity) * InGame.delta_time;
        }
        if (X<=290 || (X >= 790 && X <= 980)) {
            X = X + (Velocity*0.66) * InGame.delta_time;
        }   
        if(giro<=1){
            giro = giro + 0.05;
        }  
    }

    public void MoveLeft() {
        if ((X>=20 && X<=290) || X >= 790) {
            X = X - (Velocity*0.66) * InGame.delta_time;
        }
        if(X>=290 && X<=790){ 
            X = X - (Velocity) * InGame.delta_time; 
        }   
        if(giro>=-1){
            giro = giro - 0.05;
        }
    }

    public void MoveDown() {
        if (Y <= 600 && X>=290 && X<=790) {
            Y = Y + (Velocity) * InGame.delta_time;
        }
        if((Y <= 600 && X >= 0 && X<=290) || (Y<=600 && X >= 790 && X<=1080)){
            Y = Y + (Velocity*0.66) * InGame.delta_time;
        }
        if(giro>0.2){
            giro=giro-0.02;
        }else if(giro<-0.2){
            giro=giro+0.02;
        }else{
            giro=0;
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
