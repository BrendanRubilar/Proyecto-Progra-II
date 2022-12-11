package Otros;

import java.awt.*;
import java.util.Random;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import PanelCentral.*;
import Objetos.*;
/**
 * 
 * Clase JumpPower. Es una habilidad que permite "saltar" por una cantidad de segundos, permitiendo
 * no colisionar con obstaculos.
 * 
 */

public class JumpPower {

    Random r = new Random();
    Point jumpBox = new Point(40,0);
    private Clip gasPlus;
    public static boolean ThePlayerHasJump = false;
    Vehicle instance = Vehicle.getInstance();

    /**
     * Constructor de JumpPower
     */

    public JumpPower(){
        int aux = InGame.w+InGame.x-70; //Punto maximo en donde puede aparecer
        int aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        jumpBox.x = r.nextInt(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto
    }

    /**
     * 
     * Método para pintar, en este caso dibuja la imagen donde aparece el poder de salto.
     * 
     * @param g Parámetro necesario para la ejecución de paint (java.awt.Graphics).
     */

    public void paint(Graphics g){
        ImageIcon jumpBoxIco = new ImageIcon("Multimedia//jumpBox.png");
        g.drawImage(jumpBoxIco.getImage(), (int)jumpBox.x, (int) jumpBox.y,60,60,null); 
    }

    /**
     * 
     * Método para que la imagen baje por la pantalla con respecto al tiempo.
     * También sirve para detectar la colisión entre el auto del jugador y la imagen
     * que representa el poder. También hace que se obtenga dicho poder y se guarde para usarlo
     * 
     */

    public void moveDown(){
        if(jumpBox.y<=2000){
            jumpBox.y = (int) (jumpBox.y + Car.Velocity * InGame.delta_time);
            
            if(((instance.vehiclePosition.x >= jumpBox.x-60 && instance.vehiclePosition.x < jumpBox.x + 60) && (instance.vehiclePosition.y >= jumpBox.y-60 && instance.vehiclePosition.y < jumpBox.y + 60)) || (instance.vehiclePosition.x == jumpBox.x && instance.vehiclePosition.x == jumpBox.y)){
                
                gasSound();
                ThePlayerHasJump=true;
                if(ShootPower.ThePlayerHasRocket) ShootPower.ThePlayerHasRocket=false;
                jumpBox.y = 1990;
            }
        }
    }

    /**
     * 
     * Método que devuelve un booleano dependiendo de si la imagen de salto salió del límite en y (2000).
     * @return Retorna true o false para saber si la imagen salió.
     * 
     */

    public Boolean deleteTime(){
        if(jumpBox.y>=2000){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 
     * Método para reproducir el archivo gota.wav de la carpeta Multimedia el cual simula
     * un sonido de goteo, representando así la obtención del poder de JumpPower.
     * 
     */

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