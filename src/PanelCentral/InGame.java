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
import java.util.ArrayList;

//ESTA CLASE ES UN PANEL QUE REPRESENTARÁ IN GAME, SE HACE COMO CLASE PARA PODER USAR EL METODO PAINT 
public class InGame extends JPanel implements KeyListener {

    Vehicle vehicle;
    boolean aux = false, MoveUp, MoveLeft, MoveRight, MoveDown,objetsOnMovement=true, Jump;
    JLabel rayas = new JLabel();
    Generator generador;
    private ArrayList<Car> cars; 
    private ArrayList<Tree> trees;

    public InGame() {
        cars = new ArrayList<Car>();
        trees = new ArrayList<Tree>();

        generador = new Generator(cars, trees);

        
        this.setLayout(null);
        vehicle = new Vehicle();
        this.addKeyListener(this);
        this.setFocusable(true);
        generador.start();
        
        
        
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
            vehicle.Jump();
        }
        
                        

        repaint();
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

            case 27:

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
