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
    Point shoot = new Point(40,0);
    private Clip gasPlus;
    

    public ShootPower(){

        int aux = InGame.w+InGame.x-70; //Punto maximo en donde puede aparecer
        int aux2 = InGame.x+30; //Punto minimo en donde puede aparecer
        shoot.x = r.nextInt(aux-aux2)+aux2; //Crear una posicion aleatoria para el auto

    }

    public void paint(Graphics g){

        ImageIcon tree = new ImageIcon("Multimedia//Gas.png");
       // g.drawImage(tree.getImage(), (int)gas.x, (int) gas.y,60,60,null); 

    }



    
}
