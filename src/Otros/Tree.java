package Otros;

import PanelCentral.Vehicle;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Point;
import java.awt.Rectangle;
 
import javax.swing.ImageIcon;
import PanelCentral.InGame;

/**
 * Clase Tree. Su función es establecer los parámetros para que se generen árboles
 * que son obstaculos para el usuario.
 */
public class Tree {

    Point trees = new Point(40,0);
    public static double Velocity=300;

    /**
     * 
     * Constructor de Tree.
     * @param aux El parámetro utilizado sirve para generar árboles a la izquierda o a la derecha 
     * (0 y 1 respectivamente).
     */

    public Tree(int aux){

        Random r = new Random();

        if(aux==0){
            int aux2=r.nextInt(200-90)+90;
            aux2=aux2 * -1;
            trees.y = aux2;
            trees.x = (int) r.nextDouble(InGame.x-80);
        }else if(aux==1){
            double aux2 = InGame.x + InGame.w;
            trees.x = (int) (r.nextDouble(1020-aux2)+aux2);
        }

    }

    /**
     * 
     * Método para pintar, en este caso dibuja la imagen de los árboles.
     * 
     * @param g Parámetro necesario para la ejecución de paint (java.awt.Graphics).
     */
   
    public void paint(Graphics g){
        ImageIcon tree = new ImageIcon("Multimedia//arbusto.png");
        g.drawImage(tree.getImage(), trees.x, trees.y,60,60,null); 
    }
    
    /**
     * 
     * Método para que la imagen baje por la pantalla con respecto al tiempo.
     * También sirve para detectar la colisión entre el auto del jugador y la imagen
     * que representa el arbol. También hace que al colisionar se termine la partida.
     * 
     */

    public void moveDown(){
        if(trees.y<=2000) trees.y = (int) (trees.y + Velocity*(0.5) * InGame.delta_time);

        if(new Rectangle(trees.x,trees.y,60,60).intersects(new Rectangle(Vehicle.vehiclePosition.x, Vehicle.vehiclePosition.y, (int)Vehicle.W, (int)Vehicle.H))){
            InGame.isPlaying = false;
            Vehicle.destroy = true;
        }
    }

    /**
     * 
     * Envía el árbol al limite del eje Y.
     * 
     */

    public void setYNearToLimit(){
        trees.y = 1998;
    }

    /**
     * 
     * Método que devuelve un booleano dependiendo de si el árbol salió del límite en y (2000).
     * @return Retorna true o false para saber si el árbol salió.
     * 
     */
    
    public Boolean deleteTime(){
        if(trees.y>=2000){
            return true;
        }else{
            return false;
        }
    }   
}
