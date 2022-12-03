package Otros;

import java.awt.*;
import java.util.Random;
import java.awt.Point;
import javax.swing.ImageIcon;
import PanelCentral.InGame;
import PanelCentral.Vehicle;

/**
 * Clase Shoot. Esta clase es el misil que se dispara para destruir un auto NPC.
 */

public class Shoot {

    Random r = new Random();
    public static Point shoot = new Point(0,0);
    int Velocity= 300;

    /**
     * Constructor de Shoot. Asigna al misil la misma posición que el auto del jugador.
     */
    
    public Shoot(){
       shoot.x = Vehicle.vehiclePosition.x;
       shoot.y = Vehicle.vehiclePosition.y;
    }

    /**
     * 
     * Método para pintar, en este caso dibuja la imagen donde aparece el misil.
     * 
     * @param g Parámetro necesario para la ejecución de paint (java.awt.Graphics).
     */


    public void paint(Graphics g){
        ImageIcon tree = new ImageIcon("Multimedia//rocket.png");
        g.drawImage(tree.getImage(), (int)shoot.x, (int) shoot.y,35,50,null); 
    }

    /**
     * 
     * Método para que la imagen baje por la pantalla con respecto al tiempo.
     * También sirve para detectar la colisión entre el auto del jugador y la imagen
     * que representa el poder. También hace que se obtenga dicho poder y se guarde para usarlo
     * 
     */

    public void moveUp(){

        if(shoot.y>=-50){
            shoot.y = (int) (shoot.y - Velocity * InGame.delta_time);
        }else{
            shoot.y = -2000;
            InGame.shooting = false;
        }
    }

    /**
     * 
     * Método que devuelve un booleano dependiendo de si la imagen salió del límite en y (2000).
     * @return Retorna true o false para saber si la imagen salió.
     * 
     */

    public Boolean deleteTime(){
        if(shoot.y<= -50 || shoot.y>= 2000){
            return true;
        }else{
            return false;
        }
    }
}
