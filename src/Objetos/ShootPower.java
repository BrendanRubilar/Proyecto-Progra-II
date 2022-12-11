package Objetos;

import java.awt.*;
import java.util.Random;
import java.awt.Point;
import javax.swing.ImageIcon;

import Otros.JumpPower;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import PanelCentral.InGame;

/**
 * 
 * Clase ShootPower. Es una habilidad que permite lanzar un misil para destruir un auto NPC
 * 
 */
public class ShootPower {
    Random r = new Random();
    Point rocketBox = new Point(40,0);
    private Clip gasPlus;
    public static boolean ThePlayerHasRocket = false;
    Vehicle instance=Vehicle.getInstance();

    /**
     * 
     * Constructor de ShootPower. Asigna el área donde puede aparecer el poder.
     * 
     */

    public ShootPower(){

        int aux = InGame.w+InGame.x-70; //Punto maximo en donde puede aparecer
        int aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        rocketBox.x = r.nextInt(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto

    /**
     * 
     * Método para pintar, en este caso dibuja la imagen que el jugador recoge para obtener el poder.
     * 
     * @param g Parámetro necesario para la ejecución de paint (java.awt.Graphics).
     */

    }
    public void paint(Graphics g){
        ImageIcon rocketBoxIco = new ImageIcon("Multimedia//rocketBox.png");
        g.drawImage(rocketBoxIco.getImage(), (int)rocketBox.x, (int) rocketBox.y,60,60,null); 
    }

    /**
     * 
     * Método para que la imagen baje por la pantalla con respecto al tiempo.
     * También sirve para detectar la colisión entre el auto del jugador y la imagen
     * que representa el poder. También hace que se obtenga dicho poder y se guarde para usarlo
     * 
     */

    public void moveDown(){
        if(rocketBox.y<=2000){
            rocketBox.y = (int) (rocketBox.y + Car.Velocity * InGame.delta_time);
            
            if(((instance.vehiclePosition.x >= rocketBox.x-60 && instance.vehiclePosition.x < rocketBox.x + 60) && (instance.vehiclePosition.y >= rocketBox.y-60 && instance.vehiclePosition.y < rocketBox.y + 60)) || (instance.vehiclePosition.x == rocketBox.x && instance.vehiclePosition.x == rocketBox.y)){
                gasSound();
                ThePlayerHasRocket=true;
                if(JumpPower.ThePlayerHasJump) JumpPower.ThePlayerHasJump=false;
                rocketBox.y = 1990;   
            }
        }
    }


  /**
     * 
     * Envía a las cajas de poder al límite del eje y.
     * 
     */
    public void setYNearToLimit(){
        rocketBox.y = 1998;
    }

    /**
     * 
     * Método que devuelve un booleano dependiendo de si la imagen salió del límite en y (2000).
     * @return Retorna true o false para saber si la imagen salió.
     * 
     */

    public Boolean deleteTime(){
        if(rocketBox.y>=2000){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 
     * Método para reproducir el archivo gota.wav de la carpeta Multimedia el cual simula
     * un sonido de goteo, representando así la obtención del poder.
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
