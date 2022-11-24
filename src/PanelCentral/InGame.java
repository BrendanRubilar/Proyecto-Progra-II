package PanelCentral;

import Otros.Car;
import Otros.Generator;
import Otros.Tree;
import Otros.Colisions;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.PublicKey;
import java.util.ArrayList;

//ESTA CLASE ES UN PANEL QUE REPRESENTARÁ IN GAME, SE HACE COMO CLASE PARA PODER USAR EL METODO PAINT 
public class InGame extends JPanel implements KeyListener {

    Vehicle vehicle;
    boolean aux = false, MoveUp, MoveLeft, MoveRight, MoveDown,objetsOnMovement=true, Jump=false;
    JLabel rayas = new JLabel();
    Generator generador;
    double timerAuxiliar=0;
    private ArrayList<Car> cars; 
    private ArrayList<Tree> trees;

    public InGame() {
        cars = new ArrayList<Car>();
        trees = new ArrayList<Tree>();

        generador = new Generator(this);
        
        this.setLayout(null);
        vehicle = new Vehicle();
        this.addKeyListener(this);
        this.setFocusable(true);
        generador.start();
        
    }

    public ArrayList getArrayCars(){
        return cars;
    }

    public ArrayList getArrayTrees(){
        return trees;
    }


    //DIBUJAR EN EL PANEL 
    public void paint(Graphics g) {
        super.paint(g);

        //TEST DE UNA PISTA
        int x = 290, y = 0;

        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 1080, 720);

        g.setColor(Color.GRAY);
        g.fillRect(x, y, 500, 1000);
        

        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).paint(g);
            if(objetsOnMovement){
                 cars.get(i).moveDown();
            }
        }
        for (int i = 0; i < trees.size(); i++) {
            trees.get(i).paint(g);
            if(objetsOnMovement){
                 trees.get(i).moveDown();
            }
        }
        
        vehicle.paint(g);
        
        if(MoveLeft){
           vehicle.MoveLeft();
        }
        if(MoveUp){
           vehicle.MoveUp();
        }
        
        if(MoveRight){
           vehicle.MoveRight();
        }
        
        if(MoveDown){
            vehicle.MoveDown();
        }
        if(Jump){
            timerAuxiliar= timerAuxiliar+0.002;
            vehicle.Jump(this);
      
        }else if(!Jump){
            vehicle.setSize(80, 60);
            timerAuxiliar=0;

        }
        
        repaint();
    }

    public double getTimerAuxiliar(){
        return timerAuxiliar;
    }

    public void setJumpFalse(){
        Jump = false;
    }
            
    @Override
    public void keyTyped(KeyEvent e) {
      
    }

    //Control del teclado LISTA DE CODIGOS: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case 37:
                MoveLeft = true;
                break;

            case 38:
                
                MoveUp = true;
                
                break;
            case 39:

                MoveRight = true;
                
                break;
            case 40:
                
                MoveDown = true;
                
                break;

            case 32:

                if(!Jump) Jump=true;
                //jump_clip.start();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //BANDERA
        System.out.println("You released key char: " + e.getKeyChar());
        
        switch (e.getKeyCode()) {

            case 37:
                MoveLeft = false;
                break;

            case 38:
                
                MoveUp = false;
                
                break;
            case 39:

                MoveRight = false;
                
                break;
            case 40:
                
                MoveDown = false;
                
                break;

            case 27:

                break;
        }

    }

}
