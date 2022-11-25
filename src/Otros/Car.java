package Otros;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import PanelCentral.InGame;
import PanelCentral.Vehicle;
public class Car{
    boolean c = false;
    Vehicle vehicle;
    double carX=300,carY=0;
    static double Velocity=300;
    Random r = new Random();

    public Car(int a){
        
        if(a==1){ //Crea autos mas arriba, asi se pueden crear dos autos con un solo timer y no chocan entre ellos
            double aux=r.nextDouble(200-90)+90;
            aux=aux * -1;
            carY = aux;
        }

        double aux = InGame.w+InGame.x-90; //Punto maximo en donde puede aparecer
        double aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        carX = r.nextDouble(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto
    }

    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect((int)carX, (int)carY, 60, 80);
    }

    public void moveDown(){
        if(carY<=1205) carY= carY + Velocity * InGame.delta_time;
    }

    public Boolean deleteTime(){
        if(carY>=1200){
            return true;
        }else{
            return false;
        }
    }

}

