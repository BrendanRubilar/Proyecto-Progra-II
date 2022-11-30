package Otros;

import java.awt.*;
import java.util.Random;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import PanelCentral.InGame;
import PanelCentral.Vehicle;

public class Gas {
    
    Random r = new Random();
    Point gas = new Point(40,0);
    private Clip gasPlus;
    
    public Gas(){
        int aux = InGame.w+InGame.x-70; //Punto maximo en donde puede aparecer
        int aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        gas.x = r.nextInt(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto
    }

    public void paint(Graphics g){
        ImageIcon tree = new ImageIcon("Multimedia//Gas.png");
        g.drawImage(tree.getImage(), (int)gas.x, (int) gas.y,60,60,null); 
    }

    public void moveDown(){
        if(gas.y<=2000){
            gas.y = (int) (gas.y + Car.Velocity * InGame.delta_time);
            
            if(((Vehicle.vehiclePosition.x >= gas.x-60 && Vehicle.vehiclePosition.x < gas.x + 60) && (Vehicle.vehiclePosition.y >= gas.y-60 && Vehicle.vehiclePosition.y < gas.y + 60)) || (Vehicle.vehiclePosition.x == gas.x && Vehicle.vehiclePosition.x == gas.y)){
                //System.out.println("COLISIOOOOOON!!!!!");
                gasSound();
                gas.y = 1990;
                InGame.gasAmount = InGame.gasAmount + 50;
                if(InGame.gasAmount>200) InGame.gasAmount=200;
            }
        }
    }

    public void setYNearToLimit(){
        gas.y = 1998;
    }
    
    public Boolean deleteTime(){
        if(gas.y>=2000){
            return true;
        }else{
            return false;
        }
    }

    private void gasSound(){
        try{
            gasPlus = AudioSystem.getClip();
            gasPlus.open(AudioSystem.getAudioInputStream(new File("Multimedia//gota.wav")));
            gasPlus.loop(0);
        }catch (Exception e){
            System.out.println("No funcionó, verifique si tiene el archivo de audio en carpeta Multimedia " + e);
        }
    }
}
