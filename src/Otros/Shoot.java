package Otros;

import java.awt.*;
import java.util.Random;
import java.awt.Point;
import javax.swing.ImageIcon;
import PanelCentral.InGame;
import PanelCentral.Vehicle;
public class Shoot {

    Random r = new Random();
    public static Point shoot = new Point(0,0);
    int Velocity= 300;
    
    public Shoot(){
       shoot.x = Vehicle.vehiclePosition.x;
       shoot.y = Vehicle.vehiclePosition.y;
    }

    public void paint(Graphics g){
        ImageIcon tree = new ImageIcon("Multimedia//rocket.png");
        g.drawImage(tree.getImage(), (int)shoot.x, (int) shoot.y,35,50,null); 
    }

    public void moveUp(){

        if(shoot.y>=-50){
            shoot.y = (int) (shoot.y - Velocity * InGame.delta_time);
        }else{
            shoot.y = -2000;
            InGame.shooting = false;
        }
    }

    public Boolean deleteTime(){
        if(shoot.y<= -50 || shoot.y>= 2000){
            return true;
        }else{
            return false;
        }
    }
}
