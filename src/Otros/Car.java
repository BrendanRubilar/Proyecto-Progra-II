package Otros;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Point;

import PanelCentral.InGame;
import PanelCentral.Vehicle;

/**
 * Esta clase establece los parámetros para generar los autos que 
 * son obstaculos en el juego.
 */

public class Car{
    boolean c = false;
    Vehicle vehicle;
    Point carPosition = new Point(300,0);
    double carW=100, carH = 100;
    public static double Velocity=350;
    Random r = new Random();
    boolean destroy=false;

    /**
     * Constuctor para los autos NPC (Car)
     * @param a El parámetro a sirve para establecer distintas posiciones para generar
     * autos NPC en el eje y. 1 para generar autos cerca del jugador, 2 para generarlos un poco
     * más lejos y 3 para generarlos aún más lejos.
     */



    public Car(int a){
        if(a==1){ 
            int aux=r.nextInt(200-100)+100;
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

        int aux = (int) (InGame.w+InGame.x-70); 
        int aux2 = (int) (InGame.x+30); 
        carPosition.x = r.nextInt(aux-aux2)+aux2;
    }

    /**
     * Método para pintar, en este caso sirve para pintar las ruedas y el auto.
     * También sirve para cuando colisiona con el auto del jugador se cree una imagen con una explosión.
     * 
     * @param g Parámetro necesario para la ejecución de paint (java.awt.Graphics).
     */

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
            g.drawImage(tree.getImage(), (int)carPosition.x, (int) carPosition.y,(int)carW,(int)carH,null); 
        }
    }

    /**
     * 
     * Método para que los autos NPC bajen por la pantalla con respecto al tiempo
     * También sirve para detectar las colisiones con el poder del misil y la colisión
     * y terminar el juego.
     * 
     */

    public void moveDown(){
        if(carPosition.y<=2000){
            carPosition.y= (int) (carPosition.y + Velocity * InGame.delta_time);

            if(!InGame.Jump){

                if(!destroy){
                    if(new Rectangle(carPosition.x+4 , carPosition.y+3, 57, 75).intersects(new Rectangle(Vehicle.vehiclePosition.x, Vehicle.vehiclePosition.y, (int)Vehicle.W, (int)Vehicle.H))){
                        InGame.isPlaying = false;  
                        destroy = true; 
                        
                    }
                    if(new Rectangle(carPosition.x+4 , carPosition.y+3, 57, 75).intersects(new Rectangle(Shoot.shoot.x, Shoot.shoot.y, 35, 50))){
                        destroy=true;
                        InGame.shooting = false;
                        Shoot.shoot.y = -2000;
                    }
                }else{
                    if(carW>0){
                        carW = carW - 0.6;
                    }
                    if(carH>0){
                        carH = carH - 0.6;
                    }
                }
            }
        } 
    }

    /**
     * 
     * Envía el auto al limite del eje Y.
     * 
     */

    public void setYNearToLimit(){
        carPosition.y = 1998;
    }

    /**
     * 
     * Método que devuelve un booleano dependiendo de si el auto NPC salió del límite en y (2000)
     * @return Retorna true o false para saber si el auto salió
     * 
     */
    
    public Boolean deleteTime(){
        if(carPosition.y>=2000){
            return true;
        }else{
            return false;
        }
    }

}

