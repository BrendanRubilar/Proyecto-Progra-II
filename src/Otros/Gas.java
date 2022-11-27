package Otros;

import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import PanelCentral.InGame;
import PanelCentral.Vehicle;

public class Gas {
    
    double GasX=40, GasY=0;
    static double Velocity=300;
    Random r = new Random();
    private Clip gasPlus;

    public Gas(){

        
        double aux = InGame.w+InGame.x-70; //Punto maximo en donde puede aparecer
        double aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        GasX = r.nextDouble(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto


    }

    public void paint(Graphics g){

        ImageIcon tree = new ImageIcon("Multimedia//Gas.png");
        g.drawImage(tree.getImage(), (int)GasX, (int)GasY,60,60,null); 

    }

    public void moveDown(){
        if(GasY<=2000){
            GasY= GasY + Velocity * InGame.delta_time;
            
            if(((Vehicle.X >= GasX && Vehicle.X < GasX + 60) && (Vehicle.Y >= GasY && Vehicle.Y < GasY + 60)) || (Vehicle.X == GasX && Vehicle.Y == GasY)){
                //System.out.println("COLISIOOOOOON!!!!!");
                gasSound();
                GasY = 1990;
                InGame.gasAmount = InGame.gasAmount + 50;
                if(InGame.gasAmount>200) InGame.gasAmount=200;

            }

        }

    }

    public Boolean deleteTime(){
        if(GasY>=2000){
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
