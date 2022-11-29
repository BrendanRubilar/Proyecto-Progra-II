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

public class JumpPower {

    Random r = new Random();
    Point jumpBox = new Point(40,0);
    private Clip gasPlus;
    public static boolean ThePlayerHasJump = false;

    public JumpPower(){
        int aux = InGame.w+InGame.x-70; //Punto maximo en donde puede aparecer
        int aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        jumpBox.x = r.nextInt(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto
    }

    public void paint(Graphics g){

        ImageIcon jumpBoxIco = new ImageIcon("Multimedia//jumpBox.png");
        g.drawImage(jumpBoxIco.getImage(), (int)jumpBox.x, (int) jumpBox.y,60,60,null); 
        

    }

    public void moveDown(){
        if(jumpBox.y<=2000){
            jumpBox.y = (int) (jumpBox.y + Car.Velocity * InGame.delta_time);
            
            if(((Vehicle.vehiclePosition.x >= jumpBox.x-60 && Vehicle.vehiclePosition.x < jumpBox.x + 60) && (Vehicle.vehiclePosition.y >= jumpBox.y-60 && Vehicle.vehiclePosition.y < jumpBox.y + 60)) || (Vehicle.vehiclePosition.x == jumpBox.x && Vehicle.vehiclePosition.x == jumpBox.y)){
                
                gasSound();
                ThePlayerHasJump=true;
                if(ShootPower.ThePlayerHasRocket) ShootPower.ThePlayerHasRocket=false;
                jumpBox.y = 1990;
                
            }

        }

    }

    public Boolean deleteTime(){
        if(jumpBox.y>=2000){
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