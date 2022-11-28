package Otros;

import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

import java.awt.Point;

import PanelCentral.InGame;
import PanelCentral.Vehicle;
public class Car{
    boolean c = false;
    Vehicle vehicle;
    Point carPosition = new Point(300,0);
    public static double Velocity=300;
    Random r = new Random();
    boolean destroy=false;

    public Car(int a){
        
        //Estos condicionales indican a que altura se generaran los autos, asi no chocan entre ellos
        if(a==1){ 
            int aux=r.nextInt(200-90)+90;
            aux=aux * -1;
            carPosition.y = aux;
        }
        if(a==2){ 
            int aux=r.nextInt(300-285)+285;
            aux=aux * -1;
            carPosition.y = aux;
        }
        if(a==3){ 
            int aux=r.nextInt(400-385)+385;
            aux=aux * -1;
            carPosition.y = aux;
        }

        int aux = (int) (InGame.w+InGame.x-70); //Punto maximo en donde puede aparecer
        int aux2 = (int) (InGame.x+30); //Punto minimo en donde puede aparecer
        carPosition.x = r.nextInt(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto
    }

    public void paint(Graphics g){
        Graphics2D wheel1 = (Graphics2D) g;
        Graphics2D wheel2 = (Graphics2D) g;
        Graphics2D wheel3 = (Graphics2D) g;
        Graphics2D wheel4 = (Graphics2D) g;

        if(!destroy){
            wheel1.setColor(Color.BLACK);
            wheel1.fillRoundRect((int)carPosition.x-5, (int)carPosition.y+5, 10, 20,5,10);
    
            wheel2.setColor(Color.BLACK);
            wheel2.fillRoundRect((int)carPosition.x+55, (int)carPosition.y+10, 10, 20,5,10);
    
            wheel3.setColor(Color.BLACK);
            wheel3.fillRoundRect((int)carPosition.x-5, (int)carPosition.y+50, 10, 20,5,10);
    
            wheel4.setColor(Color.BLACK);
            wheel4.fillRoundRect((int)carPosition.x+55, (int)carPosition.y+50, 10, 20,5,10);

            g.setColor(Color.BLUE);
            g.fillRoundRect((int)carPosition.x, (int)carPosition.y, (int)60, (int)85,(int)55,(int)15);
        }else{
            ImageIcon tree = new ImageIcon("Multimedia//Explosion.png");
            g.drawImage(tree.getImage(), (int)carPosition.x, (int) carPosition.y,100,100,null); 
        }

      

        

    }

    public void moveDown(){
        if(carPosition.y<=2000){
            carPosition.y= (int) (carPosition.y + Velocity * InGame.delta_time);

            if(!InGame.Jump){

                if(new Rectangle(carPosition.x+4 , carPosition.y+3, 57, 75).intersects(new Rectangle(Vehicle.vehiclePosition.x, Vehicle.vehiclePosition.y, (int)Vehicle.W, (int)Vehicle.H))){
                    InGame.isPlaying = false;  
                    destroy= true; 
                }
                if(new Rectangle(carPosition.x , carPosition.y, 60, 80).intersects(new Rectangle(ShootPower.shoot.x, ShootPower.shoot.y, 35, 50))){
                    destroy=true;
                }

            }
        } 


    }

    public void setYNearToLimit(){
        carPosition.y = 1998;
    }
    

    public Boolean deleteTime(){
        if(carPosition.y>=2000){
            return true;
        }else{
            return false;
        }
    }

}

