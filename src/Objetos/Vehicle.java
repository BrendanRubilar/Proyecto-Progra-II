package Objetos;

import java.awt.*;
import java.awt.Point;
import javax.swing.ImageIcon;

import Otros.InvalidPositionException;
import Otros.JumpPower;
import PanelCentral.InGame;
public class Vehicle {

    public  Point vehiclePosition = new Point(510,540);
    public static double Velocity = 300, giro = 0;
    public  double H = 80, W = 60;
    public static boolean destroy=false;

    private static Vehicle instance;

    /**
    *Constructor y metodo get Instance de vehiculo para aplicar Singleton y llamar al mismo objeto
    *en diferentes partes del programa.
    */
    private Vehicle() {}
    public static Vehicle getInstance(){
        if(instance == null){
            instance = new Vehicle();
        }
        return instance;
    }
    /**
     * Metodo grafico del vehiculo, se dibuja su forma y ruedas o se dibuja la imagen
     * de explosión dependiendo de un booleano.
     */
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

    /**
     * Metodo de mover vehiculo arriba siempre y cuando el vehiculo no se salga de los limites,
     * y controla el giro.
     */
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

    /**
     * Movimiento de vehiculo a la derecha siempre y cuando el vehiculo no se salga de los limites
     *  y controla el giro.
     */
    public void MoveRight() {
        if (instance.vehiclePosition.x>=290 && instance.vehiclePosition.x<=790) {
             instance.vehiclePosition.x = (int) (instance.vehiclePosition.x + (Velocity) * InGame.delta_time);
        }
        if (instance.vehiclePosition.x<=290 || (instance.vehiclePosition.x >= 790 && instance.vehiclePosition.x <= 980)) {
            instance.vehiclePosition.x = (int) (instance.vehiclePosition.x + (Velocity*0.66) * InGame.delta_time);
        }   
        if(giro<=1){
            giro = giro + 0.05;
        }  
    }

    /**
     * Movimiento de vehiculo a la Izquierda siempre y cuando el vehiculo no se salga de los limites
     *  y controla el giro.
     */
    public void MoveLeft() {
        if ((instance.vehiclePosition.x>=20 && instance.vehiclePosition.x<=290) || instance.vehiclePosition.x >= 790) {
            instance.vehiclePosition.x = (int) (instance.vehiclePosition.x - (Velocity*0.66) * InGame.delta_time);
        }
        if(instance.vehiclePosition.x>=290 && instance.vehiclePosition.x<=790){ 
            instance.vehiclePosition.x = (int) (instance.vehiclePosition.x - (Velocity) * InGame.delta_time); 
        }   
        if(giro>=-1){
            giro = giro - 0.05;
        }
    }

    /**
     * Movimiento de vehiculo hacia abajo siempre y cuando el vehiculo no se salga de los limites
     *  y controla el giro.
     */
    public void MoveDown() {
        if (instance.vehiclePosition.y <= 580 && instance.vehiclePosition.x>=290 && instance.vehiclePosition.x<=790) {
            instance.vehiclePosition.y = (int) (instance.vehiclePosition.y + (Velocity) * InGame.delta_time);
        }
        if((instance.vehiclePosition.y <= 580 && instance.vehiclePosition.x >= 0 && instance.vehiclePosition.x<=290) || (instance.vehiclePosition.y<=600 && instance.vehiclePosition.x >= 790 && instance.vehiclePosition.x<=1080)){
            instance.vehiclePosition.y = (int) (instance.vehiclePosition.y + (Velocity*0.66) * InGame.delta_time);
        }
        if(giro>0.2){
            giro=giro-0.02;
        }else if(giro<-0.2){
            giro=giro+0.02;
        }else{
            giro=0;
        }
        
    }

    /**
    *Metodo saltar del vehiculo, en base a una función el auto crecera hasta cierto punto, 
    *luego comenzará a decrecer hasta cierto valor y modificará un booleano para dar a 
    *entender al programa que ya no se encuentra en el aire.
    */
    public void Jump(InGame auxInGame){
        
        if(W>=60) W=  (Math.sin(auxInGame.getTimerAuxiliar())*4*Math.PI+60);
        if(H>=80) H=  (Math.sin(auxInGame.getTimerAuxiliar())*4*Math.PI+80);
        
        if(H<=80 || W<=60){
            auxInGame.setJumpFalse();
            JumpPower.ThePlayerHasJump = false;
        }
    }

    /**
     * Metodo para controlar el tamaño del vehiculo, se utiliza
     * para restablecer el tamaño al inicar el juego y evitar problemas 
     * con el metodo salto.
     */
    public void setSize(int H, int W){
        this.H=H;
        this.W=W; 
    }

    /**
     * Metodo que controla un booleano del vehiculo, este
     * controla que es lo que se dibujará, vehiculo normal
     * o destruido.
     */
    public void setDestroyTrue(){
        destroy = true;
    }
    /**
     * Metodos para testear JUnit.
     */
    public void CorrectVehiclePositionX() throws InvalidPositionException{
        if(instance.vehiclePosition.x<20 ||instance.vehiclePosition.x>980)  throw new InvalidPositionException("Error en los limites de juego");  
    }
    public void CorrectVehiclePositionY() throws InvalidPositionException{
        if(instance.vehiclePosition.y<20 ||instance.vehiclePosition.x>580)  throw new InvalidPositionException("Error en los limites de juego");  
    }

    /**
     * Métodos para obtener el ancho y largo del vehiculo.
     */
    public int getSizeH(){
        return (int)H;
    }
    public int getSizeW(){
        return (int)W;
    }

}