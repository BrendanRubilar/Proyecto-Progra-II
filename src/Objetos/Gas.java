package Objetos;

import java.awt.*;
import java.util.Random;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

import PanelCentral.InGame;

/**
 * 
 * Clase gas. Esta clase establece los parámetros para generar bidones de gasolina
 * para que el jugador las recoja en el juego y recupere gasolina de su medidor.
 * 
 */


public class Gas {
    
    Random r = new Random();
    Point gas = new Point(40,0);
    private Clip gasPlus;

    /**
    * 
    * Constructor de la clase. Establece las posiciones donde se generan los bidones.
    * 
    */
    
    public Gas(){
        int aux = InGame.w+InGame.x-70; //Punto maximo en donde puede aparecer
        int aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        gas.x = r.nextInt(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto
    }

    /**
    * 
    * Método para pintar. En este caso toma la imagen Gas.png de la carpeta multimedia y la dibuja
    * en pantalla.
    * @param g Parámetro necesario para la ejecución de paint (java.awt.Graphics).
    */

    public void paint(Graphics g){
        ImageIcon tree = new ImageIcon("Multimedia//Gas.png");
        g.drawImage(tree.getImage(), (int)gas.x, (int) gas.y,60,60,null); 
    }

    /**
    * 
    * Método para que los bidones bajen por la pantalla. También se detecta la colisión con el auto del usuario.
    * 
    */

    public void moveDown(){
        if(gas.y<=2000){
            gas.y = (int) (gas.y + Car.Velocity * InGame.delta_time);
            
            if(((Vehicle.vehiclePosition.x >= gas.x-60 && Vehicle.vehiclePosition.x < gas.x + 60) && (Vehicle.vehiclePosition.y >= gas.y-60 && Vehicle.vehiclePosition.y < gas.y + 60)) || (Vehicle.vehiclePosition.x == gas.x && Vehicle.vehiclePosition.x == gas.y)){
                gasSound();
                gas.y = 1990;
                InGame.gasAmount = InGame.gasAmount + 50;
                if(InGame.gasAmount>200) InGame.gasAmount=200;
            }
        }
    }

    /**
     * 
     * Envía el bidón al límite del eje y.
     * 
     */

    public void setYNearToLimit(){
        gas.y = 1998;
    }

    /**
     * 
     * Método que devuelve un booleano dependiendo de si la imagen salió del límite en y (2000).
     * @return Retorna true o false para saber si el auto salió.
     * 
     */
    
    public Boolean deleteTime(){
        if(gas.y>=2000){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * 
     * Método para reproducir el archivo gota.wav de la carpeta Multimedia el cual simula
     * un sonido de goteo, representando así la obtención del bidón.
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
