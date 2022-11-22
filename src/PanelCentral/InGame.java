package PanelCentral;

import Otros.Generator;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//ESTA CLASE ES UN PANEL QUE REPRESENTARÁ IN GAME, SE HACE COMO CLASE PARA PODER USAR EL METODO PAINT 
public class InGame extends JPanel implements KeyListener {

    Vehicle vehicle;
    boolean aux = true;
    JLabel rayas = new JLabel();
    Generator generador = new Generator();

    public InGame() {
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

        vehicle.paint(g);

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    //Control del teclado LISTA DE CODIGOS: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case 37:
                vehicle.MoveLeft();
                repaint();
                break;

            case 38:
                vehicle.MoveUp();
                repaint();

                break;
            case 39:

                vehicle.MoveRight();
                repaint();
                System.out.println("Coordenadas:"+vehicle.x_locate()+" , " + vehicle.y_locate());
                break;
            case 40:
                vehicle.MoveDown();
                repaint();
                break;

            case 27:

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //BANDERA
        System.out.println("You released key char: " + e.getKeyChar());

    }

}
