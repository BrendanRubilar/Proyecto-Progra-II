package PanelCentral;

import Otros.Car;
import Otros.Generator;
import Otros.Tree;
import Otros.Gas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.PublicKey;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.awt.event.*;



//ESTA CLASE ES UN PANEL QUE REPRESENTARÁ IN GAME, SE HACE COMO CLASE PARA PODER USAR EL METODO PAINT 
public class InGame extends JPanel implements KeyListener,ActionListener{

    Vehicle vehicle;
    boolean aux = false, MoveUp, MoveLeft, MoveRight, MoveDown;
    public static boolean Jump=false;
    public static boolean isPlaying = false;
    JLabel rayas = new JLabel();
    Generator generador;
    double timerAuxiliar=0;
    private Clip jump_clip;
    private ArrayList<Car> cars; 
    private ArrayList<Tree> trees;
    private ArrayList<Gas> gasList;
    
    long last_time = System.currentTimeMillis();
    //Esto se puede reordenar arriba mas tarde
    public static double delta_time = 0; 
    public static double gasAmount = 200;
    public static boolean gasOut = false;

    public static int x = 290, y = 0, w= 500, h=1000, points=0; //Ubicacion de la pista, (ancho minimo 200)
    JButton boton = new JButton();

    

    public InGame() {
        cars = new ArrayList<Car>();
        trees = new ArrayList<Tree>();
        gasList = new ArrayList<Gas>();

        generador = new Generator(this);
        this.setLayout(null);
        vehicle = new Vehicle();
        this.addKeyListener(this);
        this.setFocusable(true);
        generador.start();
        boton.setBounds(400,400,200,100);
        this.add(boton);
        boton.setVisible(false);
        boton.addActionListener(this);
        
    }

    public ArrayList getArrayCars(){
        return cars;
    }

    public ArrayList getArrayTrees(){
        return trees;
    }
    public ArrayList getArrayGas(){
        return gasList;
    }

    //DIBUJAR EN EL PANEL 
    public void paint(Graphics g) {
        super.paint(g);
        
  

        g.setColor(Color.GREEN); //Cesped
        g.fillRect(0, 0, 1080, 720);

        g.setColor(Color.GRAY); //Pista
        g.fillRect((int)x, (int)y, (int)w, (int)h);
       


        g.setColor(Color.white); //Decoracion, falta añadir efecto de movimiento
        g.fillRect((int)x+(int)w/2, 10, 15, 70);
        g.fillRect((int)x+(int)w/2, 90, 15, 70);
        g.fillRect((int)x+(int)w/2, 170, 15, 70);
        g.fillRect((int)x+(int)w/2, 250, 15, 70);
        g.fillRect((int)x+(int)w/2, 330, 15, 70);
        g.fillRect((int)x+(int)w/2, 410, 15, 70);
        g.fillRect((int)x+(int)w/2, 490, 15, 70);
        g.fillRect((int)x+(int)w/2, 570, 15, 70);

        if(!cars.isEmpty()){
            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).paint(g);

                if(isPlaying){
                     cars.get(i).moveDown();
                }

                if(cars.get(i).deleteTime()){
                    cars.remove(i);
                }
                //System.out.println("ARRAYLIST  "+cars.size()); //Contabiliza la cantidad de autos activos
            }
        }
        
        if(!trees.isEmpty()){
            for (int i = 0; i < trees.size(); i++) {
                trees.get(i).paint(g);

                if(isPlaying){
                     trees.get(i).moveDown();
                }
               
                if(trees.get(i).deleteTime()){ 
                    trees.remove(i);
                 }
                //System.out.println("Arraylist: "+trees.size());
            }
        }

        if(!gasList.isEmpty()){
            for (int i = 0; i < gasList.size(); i++){
                gasList.get(i).paint(g);

                if(isPlaying){
                    gasList.get(i).moveDown();
                }

                if(gasList.get(i).deleteTime()){ 
                    gasList.remove(i);
                 }
                 //System.out.println("Arraylist: "+gasList.size());
                
            }
        }

        

        if(isPlaying){
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
                 timerAuxiliar= timerAuxiliar+0.017;
                 vehicle.Jump(this);
           
             }else if(!Jump){
                 vehicle.setSize(80, 60);
                 timerAuxiliar=0;
             }
        }


        //Control de combustible, detener vehiculo de manera progresiva
        g.setColor(Color.gray); //Medidor de combustible
        g.fillRect(800, 40, 200, 30);

        g.setColor(Color.red); 
        g.fillRect(800, 40, (int)gasAmount, 30);

        g.setColor(Color.black); 
        g.drawString("Combustible: "+(int)gasAmount, 850,60);

        if(isPlaying){
            points++;

            if(gasAmount>0) gasAmount=gasAmount-0.05;
            if(gasAmount<=0) gasOut=true;

            if(gasOut){
                Vehicle.Velocity--;
                Tree.Velocity--;
            } 

            if(Vehicle.Velocity<=0) isPlaying = false;

        }


        if(!isPlaying){
            
            g.drawString("FIN DE LA PARTIDA", 400,300);
            isPlaying = false;
            
            boton.setVisible(true);
            g.setColor(Color.black); 
            g.fillRect(440, 400, 200, 100);

        }

        g.setColor(Color.gray); //Puntuacion
        g.fillRect(800, 0, 200, 30);
        
        g.setColor(Color.black);
        g.drawString("Puntuacion: "+points, 820, 20);

        g.setColor(Color.gray);
        g.fillRect(920, 100, 80, 80);

        vehicle.paint(g);   

        //Control de fps
        long time = System.currentTimeMillis();
        int milisegundos = (int) (time - last_time);
        int milisegundos_para_completar = 16 - milisegundos;
        delta_time = (time - last_time) / 1000.0;
        last_time = time;

        try {
            Thread.sleep(milisegundos_para_completar);
        } catch (Exception e) {

        }

        repaint();
    }
    
    public void Redraw(){
        repaint();
    }

    public double getTimerAuxiliar(){
        return timerAuxiliar;
    }

    public void setJumpFalse(){
        Jump = false;
    }

     //Efectos sonido------------------------------------------------------------
     private void JumpSound(){
        try{
        jump_clip = AudioSystem.getClip();
        jump_clip.open(AudioSystem.getAudioInputStream(new File("Multimedia//Salto.wav")));
        jump_clip.start();
        }catch(Exception e){
            System.out.println("No funcionó, verifique si tiene el archivo de audio en carpeta Multimedia " + e);
        }
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

                if(!Jump){ 
                    Jump=true;
                    JumpSound();
                }
               
                
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        //System.out.println("You released key char: " + e.getKeyChar());
        
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

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == boton) {

            //Restablecemos todo antes de volver al panel de menu
            //Se envian todos los objetos cerca a su limite (Donde no hacen nada)
            for (int i = 0; i < cars.size(); i++){
                cars.get(i).setYNearToLimit();
            }
            for (int i = 0; i < trees.size(); i++){
                trees.get(i).setYNearToLimit();
            }
            for (int i = 0; i < gasList.size(); i++){
                gasList.get(i).setYNearToLimit();
            }
            //Se eliminan todos los objetos de sus arraylist (Ahora java deberia borrarlos eventualmente)
            for (int i = 0; i < cars.size(); i++){
                cars.remove(i);
            }
            for (int i = 0; i < trees.size(); i++){
                trees.remove(i);
            }
            for (int i = 0; i < gasList.size(); i++){
                gasList.remove(i);
            }            
            gasAmount = 200;
            Car.Velocity = 300;
            Tree.Velocity = 300;
            points = 0;
            Vehicle.vehiclePosition.x=510;
            Vehicle.vehiclePosition.y=540;
            Vehicle.giro=0;
            Status.c1.show(Status.Panels, "1");
            Status.inGame_theme.stop();
            Status.menu_theme.setMicrosecondPosition(0);
            Status.menu_theme.start();
            //System.out.println("SE PRESIONA BOTON");
          
        }
    }



}
