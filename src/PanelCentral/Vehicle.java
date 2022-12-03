package PanelCentral;

import java.awt.*;
import java.awt.Point;
import javax.swing.ImageIcon;

import Otros.JumpPower;
public class Vehicle {

    public static Point vehiclePosition = new Point(510,540);
    public static double Velocity = 300, giro = 0;
    public static double H = 80, W = 60;
    public static boolean destroy=false;

    private static Vehicle instance;

    public Vehicle() {}
    public static Vehicle getInstance(){
        if(instance == null){
            instance = new Vehicle();
        }
        return instance;
    }
    public void paint(Graphics g) {
        Graphics2D car = (Graphics2D) g;
        Graphics2D wheel1 = (Graphics2D) g;
        Graphics2D wheel2 = (Graphics2D) g;
        Graphics2D wheel3 = (Graphics2D) g;
        Graphics2D wheel4 = (Graphics2D) g;

        if(!destroy){
            wheel1.rotate(Math.toRadians(giro)*10, vehiclePosition.x+30, vehiclePosition.y+10);
            wheel1.setColor(Color.BLACK);
            wheel1.fillRoundRect((int)vehiclePosition.x-5, (int)vehiclePosition.y+5, (int)W-50, (int)H-60,(int)W-55,(int)H-70);
    
            wheel2.rotate(Math.toRadians(giro)*10, vehiclePosition.x+30, vehiclePosition.y+10);
            wheel2.setColor(Color.BLACK);
            wheel2.fillRoundRect((int)vehiclePosition.x+55, (int)vehiclePosition.y+5, (int)W-50, (int)H-60,(int)W-55,(int)H-70);
    
            wheel3.rotate(Math.toRadians(giro), vehiclePosition.x+30, vehiclePosition.y+50);
            wheel3.setColor(Color.BLACK);
            wheel3.fillRoundRect((int)vehiclePosition.x-5, (int)vehiclePosition.y+50, (int)W-50, (int)H-60,(int)W-55,(int)H-70);
    
            wheel4.rotate(Math.toRadians(giro), vehiclePosition.x+30, vehiclePosition.y+50);
            wheel4.setColor(Color.BLACK);
            wheel4.fillRoundRect((int)vehiclePosition.x+55, (int)vehiclePosition.y+50, (int)W-50, (int)H-60,(int)W-55,(int)H-70);
    
            car.rotate(Math.toRadians(giro)*(0.1), vehiclePosition.x + 30, vehiclePosition.y+80);
            car.setColor(Color.yellow);
            car.fillRoundRect((int)vehiclePosition.x, (int)vehiclePosition.y, (int)W, (int)H, (int)W-5, (int)H-60);
        }else{
            ImageIcon VehicleIco = new ImageIcon("Multimedia//Explosion.png");
            g.drawImage(VehicleIco.getImage(), (int)vehiclePosition.x, (int) vehiclePosition.y,(int)W,(int)W,null);  
        }
    }


    public void MoveUp() {
        
        if (vehiclePosition.y >= 20 && vehiclePosition.x>=290 && vehiclePosition.x<=790) {
            vehiclePosition.y = (int) (vehiclePosition.y - Velocity * InGame.delta_time);
        }
        if (vehiclePosition.y>=20 && vehiclePosition.x >= 0 && vehiclePosition.x<=290 || vehiclePosition.y>=20 && vehiclePosition.x >= 790 && vehiclePosition.x<=1080) {
            vehiclePosition.y = (int) (vehiclePosition.y - (Velocity*0.66)* InGame.delta_time); 
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
        if (vehiclePosition.x>=290 && vehiclePosition.x<=790) {
             vehiclePosition.x = (int) (vehiclePosition.x + (Velocity) * InGame.delta_time);
        }
        if (vehiclePosition.x<=290 || (vehiclePosition.x >= 790 && vehiclePosition.x <= 980)) {
            vehiclePosition.x = (int) (vehiclePosition.x + (Velocity*0.66) * InGame.delta_time);
        }   
        if(giro<=1){
            giro = giro + 0.05;
        }  
    }

    public void MoveLeft() {
        if ((vehiclePosition.x>=20 && vehiclePosition.x<=290) || vehiclePosition.x >= 790) {
            vehiclePosition.x = (int) (vehiclePosition.x - (Velocity*0.66) * InGame.delta_time);
        }
        if(vehiclePosition.x>=290 && vehiclePosition.x<=790){ 
            vehiclePosition.x = (int) (vehiclePosition.x - (Velocity) * InGame.delta_time); 
        }   
        if(giro>=-1){
            giro = giro - 0.05;
        }
    }

    public void MoveDown() {
        if (vehiclePosition.y <= 580 && vehiclePosition.x>=290 && vehiclePosition.x<=790) {
            vehiclePosition.y = (int) (vehiclePosition.y + (Velocity) * InGame.delta_time);
        }
        if((vehiclePosition.y <= 580 && vehiclePosition.x >= 0 && vehiclePosition.x<=290) || (vehiclePosition.y<=600 && vehiclePosition.x >= 790 && vehiclePosition.x<=1080)){
            vehiclePosition.y = (int) (vehiclePosition.y + (Velocity*0.66) * InGame.delta_time);
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
        
        if(W>=60) W=  (Math.sin(auxInGame.getTimerAuxiliar())*4*Math.PI+60);
        if(H>=80) H=  (Math.sin(auxInGame.getTimerAuxiliar())*4*Math.PI+80);
        
        if(H<=80 || W<=60){
            auxInGame.setJumpFalse();
            JumpPower.ThePlayerHasJump = false;
        }
    }

    public void setSize(int H, int W){
        this.H=H;
        this.W=W; 
    }

    public void setDestroyTrue(){
        destroy = true;
    }
    
    public static void CorrectVehiclePositionX() throws InvalidPositionException{
        if(vehiclePosition.x<20 ||vehiclePosition.x>980)  throw new InvalidPositionException("Error en los limites de juego");  
    }
    public static void CorrectVehiclePositionY() throws InvalidPositionException{
        if(vehiclePosition.y<20 ||vehiclePosition.x>580)  throw new InvalidPositionException("Error en los limites de juego");  
    }

}