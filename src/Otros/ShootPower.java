package Otros;

import java.awt.*;
import java.util.Random;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import PanelCentral.InGame;
import PanelCentral.Vehicle;

public class ShootPower {

    Random r = new Random();
    Point rocketBox = new Point(40,0);
    private Clip gasPlus;
    public static boolean ThePlayerHasRocket = false;

    public ShootPower(){

        int aux = InGame.w+InGame.x-70; //Punto maximo en donde puede aparecer
        int aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        rocketBox.x = r.nextInt(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto


    }

    public void paint(Graphics g){

        ImageIcon rocketBoxIco = new ImageIcon("Multimedia//rocketBox.png");
        g.drawImage(rocketBoxIco.getImage(), (int)rocketBox.x, (int) rocketBox.y,60,60,null); 
        

    }

    public void moveDown(){
        if(rocketBox.y<=2000){
            rocketBox.y = (int) (rocketBox.y + Car.Velocity * InGame.delta_time);
            
            if(((Vehicle.vehiclePosition.x >= rocketBox.x-60 && Vehicle.vehiclePosition.x < rocketBox.x + 60) && (Vehicle.vehiclePosition.y >= rocketBox.y-60 && Vehicle.vehiclePosition.y < rocketBox.y + 60)) || (Vehicle.vehiclePosition.x == rocketBox.x && Vehicle.vehiclePosition.x == rocketBox.y)){
                
                gasSound();
                ThePlayerHasRocket=true;
                rocketBox.y = 1990;
                
            }

        }

    }

    public Boolean deleteTime(){
        if(rocketBox.y>=2000){
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
