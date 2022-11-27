package Otros;

import java.awt.*;
import java.util.Random;

import PanelCentral.InGame;
import PanelCentral.Vehicle;
public class Car{
    boolean c = false;
    Vehicle vehicle;
    double carX=300,carY=0;
    public static double Velocity=300;
    Random r = new Random();

    public Car(int a){
        
        //Estos condicionales indican a que altura se generaran los autos, asi no chocan entre ellos
        if(a==1){ 
            double aux=r.nextDouble(200-90)+90;
            aux=aux * -1;
            carY = aux;
        }
        if(a==2){ 
            double aux=r.nextDouble(300-285)+285;
            aux=aux * -1;
            carY = aux;
        }
        if(a==3){ 
            double aux=r.nextDouble(400-385)+385;
            aux=aux * -1;
            carY = aux;
        }

        double aux = InGame.w+InGame.x-70; //Punto maximo en donde puede aparecer
        double aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        carX = r.nextDouble(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto
    }

    public void paint(Graphics g){
        Graphics2D wheel1 = (Graphics2D) g;
        Graphics2D wheel2 = (Graphics2D) g;
        Graphics2D wheel3 = (Graphics2D) g;
        Graphics2D wheel4 = (Graphics2D) g;

        wheel1.setColor(Color.BLACK);
        wheel1.fillRoundRect((int)carX-5, (int)carY+5, 10, 20,5,10);

        wheel2.setColor(Color.BLACK);
        wheel2.fillRoundRect((int)carX+55, (int)carY+10, 10, 20,5,10);

        wheel3.setColor(Color.BLACK);
        wheel3.fillRoundRect((int)carX-5, (int)carY+50, 10, 20,5,10);

        wheel4.setColor(Color.BLACK);
        wheel4.fillRoundRect((int)carX+55, (int)carY+50, 10, 20,5,10);
        
        g.setColor(Color.BLUE);
        g.fillRoundRect((int)carX, (int)carY, (int)60, (int)85,(int)55,(int)15);

    }

    public void moveDown(){
        if(carY<=2000){
            carY= carY + Velocity * InGame.delta_time;

            if(((Vehicle.X >= carX && Vehicle.X < carX + 60) && (Vehicle.Y >= carY && Vehicle.Y < carY + 85)) || (Vehicle.X == carX && Vehicle.Y == carY)){
                //System.out.println("COLISIOOOOOON!!!!!");

                InGame.objetsOnMovement = false;

            }

        } 


    }

    public Boolean deleteTime(){
        if(carY>=2000){
            return true;
        }else{
            return false;
        }
    }

}

