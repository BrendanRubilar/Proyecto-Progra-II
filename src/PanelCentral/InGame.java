package PanelCentral;

import Otros.Car;
import Otros.Generator;
import Otros.JumpPower;
import Otros.Tree;
import Otros.Gas;
import Otros.Shoot;
import Otros.ShootPower;

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
import java.lang.reflect.Array;
import java.awt.event.*;

//ESTA CLASE ES UN PANEL QUE REPRESENTARÁ IN GAME, SE HACE COMO CLASE PARA PODER USAR EL METODO PAINT 
public class InGame extends JPanel implements KeyListener,ActionListener{

    Vehicle vehicle;
    boolean aux = false, MoveUp, MoveLeft, MoveRight, MoveDown;
    public static boolean Jump=false, shooting=false;
    public static boolean isPlaying = false;
    JLabel rayas = new JLabel();
    Generator generador;
    double timerAuxiliar=0;
    private Clip jump_clip;
    private ArrayList<Car> cars; 
    private ArrayList<Tree> trees;
    private ArrayList<Gas> gasList;
    private ArrayList<ShootPower> rocketBoxes;
    private ArrayList<Shoot> shootsList;
    private ArrayList<JumpPower> jumpBoxes;

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
        rocketBoxes = new ArrayList<ShootPower>();
        shootsList = new ArrayList<Shoot>();
        jumpBoxes = new ArrayList<JumpPower>();


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
    public ArrayList getArrayBoxes(){
        return rocketBoxes;
    }
    public ArrayList getArrayJumpBoxes(){
        return jumpBoxes;
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

        if(!rocketBoxes.isEmpty()){
            for (int i = 0; i < rocketBoxes.size(); i++){
                rocketBoxes.get(i).paint(g);

                if(isPlaying){
                    rocketBoxes.get(i).moveDown();
                }

                if(rocketBoxes.get(i).deleteTime()){ 
                    rocketBoxes.remove(i);
                 }
                //System.out.println("Arraylist: "+rocketBoxes.size());
            }
        }
        if(!jumpBoxes.isEmpty()){
            for (int i = 0; i < jumpBoxes.size(); i++){
                jumpBoxes.get(i).paint(g);

                if(isPlaying){
                    jumpBoxes.get(i).moveDown();
                }
                if(jumpBoxes.get(i).deleteTime()){ 
                    jumpBoxes.remove(i);
                 }
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
             

             if(shooting){

                if(!shootsList.isEmpty()){
                    for (int i = 0; i < shootsList.size(); i++){
                        shootsList.get(i).paint(g);
                       
                        shootsList.get(i).moveUp();          
                    }
                }
        
             }else{
                if(!shootsList.isEmpty()){
                    for (int i = 0; i < shootsList.size(); i++){
                        if(shootsList.get(i).deleteTime()){ 
                            shootsList.remove(i);
                         }
                    }
                }
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
            
            g.drawString("FIN DE LA PARTIDA", 480,380);
            isPlaying = false;
            
            boton.setVisible(true);
            g.setColor(Color.blue); 
            g.fillRect(440, 400, 200, 100);
            g.setColor(Color.black);
            g.drawString("VOLVER AL MENU", 480,460);

        }

        g.setColor(Color.gray); //Puntuacion
        g.fillRect(800, 0, 200, 30);
        
        g.setColor(Color.black);
        g.drawString("Puntuacion: "+points, 820, 20);

        g.setColor(Color.gray); //Inventario de poderes
        g.fillRect(910, 100, 90, 90);

        if(ShootPower.ThePlayerHasRocket){
            ImageIcon tree = new ImageIcon("Multimedia//coheteIcon.png");
            g.drawImage(tree.getImage(), 910, 100,90,90,null); 
        }else if(JumpPower.ThePlayerHasJump){
            ImageIcon tree = new ImageIcon("Multimedia//jump.png");
            g.drawImage(tree.getImage(), 910, 100,90,90,null);
        }

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

    //Control del teclado LISTA DE CODIGOS:     
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

                if(JumpPower.ThePlayerHasJump){
                    if(!Jump){ 
                        Jump=true;
                        JumpSound();
                    }
                }
                
                if(ShootPower.ThePlayerHasRocket){
                    shooting = true;
                    shootsList.add(new Shoot());
                    ShootPower.ThePlayerHasRocket = false;
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
            ShootPower.ThePlayerHasRocket = false;
            JumpPower.ThePlayerHasJump = false;
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
