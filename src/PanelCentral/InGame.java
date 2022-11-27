package PanelCentral;

import Otros.Car;
import Otros.Generator;
import Otros.Tree;
import Otros.Colisions;
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


//ESTA CLASE ES UN PANEL QUE REPRESENTARÁ IN GAME, SE HACE COMO CLASE PARA PODER USAR EL METODO PAINT 
public class InGame extends JPanel implements KeyListener {

    Vehicle vehicle;
    boolean aux = false, MoveUp, MoveLeft, MoveRight, MoveDown, Jump=false;
    public static boolean objetsOnMovement=true, isPlaying = false;
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

    public static double x = 290, y = 0, w= 500, h=1000; //Ubicacion de la pista, (ancho minimo 200)

    Button boton = new Button("Volver al menu");

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
        isPlaying = true;
  

        g.setColor(Color.GREEN); //Cesped
        g.fillRect(0, 0, 1080, 720);

        g.setColor(Color.GRAY); //Pista
        g.fillRect((int)x, (int)y, (int)w, (int)h);
       
        g.setColor(Color.gray); //Medidor de combustible
        g.fillRect(800, 10, 200, 30);

        
        g.setColor(Color.red); 
        g.fillRect(800, 10, (int)gasAmount, 30);

        g.setColor(Color.black); 
        g.drawString("Combustible: "+(int)gasAmount, 850,30);
        
        //Control de combustible, detener vehiculo de manera progresiva
        if(objetsOnMovement){
            if(gasAmount>0) gasAmount=gasAmount-0.05;
            if(gasAmount<=0) gasOut=true;

            if(gasOut){
                Vehicle.Velocity--;
                Car.Velocity--;
                Tree.Velocity--;
            } 

            if(Vehicle.Velocity<=0) objetsOnMovement = false;

        }


        if(!objetsOnMovement){
            
            g.drawString("FIN DE LA PARTIDA", 400,300);
            isPlaying = false;
            //boton.setBounds(400,400,200,100); ESTOY TESTEANDO CREAR UN BOTON PARA VOLVER AL MENU (No olviden Reiniciar booleanos si quieren intentarlo)
            //this.add(boton);

        }

        if(!cars.isEmpty()){
            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).paint(g);

                if(objetsOnMovement){
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

                if(objetsOnMovement){
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

                if(objetsOnMovement){
                    gasList.get(i).moveDown();
                }

                if(gasList.get(i).deleteTime()){ 
                    gasList.remove(i);
                 }
                 //System.out.println("Arraylist: "+gasList.size());
                
            }
        }

        
        
        vehicle.paint(g);

        if(objetsOnMovement==true){
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

}
