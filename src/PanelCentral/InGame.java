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
    public static boolean gasOut = false,isOnPause=false;

    public static int x = 290, y = 0, w= 500, h=1000, points=0; //Ubicacion de la pista, (ancho minimo 200)
    JButton boton = new JButton();
    JButton boton2 = new JButton();
    JButton boton3 = new JButton();



    double rectY=10, rectY2=90,rectY3=170,rectY4=250,rectY5=330,rectY6=410,rectY7=490,rectY8=570,rectY9=650, rectY10 = 730, rectY11=810;



    

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
        boton2.setBounds(800,400,60,60);
        boton3.setBounds(860,400,60,60);
        this.add(boton);
        this.add(boton2);
        this.add(boton3);
        boton.setVisible(false);
        boton2.setVisible(false);
        boton3.setVisible(false);
        boton.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);


        
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

        g.setColor(Color.blue);
        g.fillRect(0, 0, 20, 1080);
        g.fillRect(1050, 0, 20, 1080);
       


        g.setColor(Color.white); //Decoracion, falta añadir efecto de movimiento
        g.fillRect((int)x+(int)w/2, (int)rectY, 15, 70);
        g.fillRect((int)x+(int)w/2, (int)rectY2, 15, 70);
        g.fillRect((int)x+(int)w/2, (int)rectY3, 15, 70);
        g.fillRect((int)x+(int)w/2, (int)rectY4, 15, 70);
        g.fillRect((int)x+(int)w/2, (int)rectY5, 15, 70);
        g.fillRect((int)x+(int)w/2, (int)rectY6, 15, 70);
        g.fillRect((int)x+(int)w/2, (int)rectY7, 15, 70);
        g.fillRect((int)x+(int)w/2, (int)rectY8, 15, 70);
        g.fillRect((int)x+(int)w/2, (int)rectY9, 15, 70);
        g.fillRect((int)x+(int)w/2, (int)rectY10, 15, 70);
        g.fillRect((int)x+(int)w/2, (int)rectY11, 15, 70);


        if(!cars.isEmpty()){
            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).paint(g);

                if(isPlaying && !isOnPause){
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

                if(isPlaying && !isOnPause){
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

                if(isPlaying && !isOnPause){
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

                if(isPlaying && !isOnPause){
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

                if(isPlaying && !isOnPause){
                    jumpBoxes.get(i).moveDown();
                }
                if(jumpBoxes.get(i).deleteTime()){ 
                    jumpBoxes.remove(i);
                 }
            }
        }

        if(isPlaying && !isOnPause){
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
             

             if(shooting && !isOnPause){

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

        if(isPlaying && !isOnPause){
            rectY=rectY + 150 *delta_time;
            rectY2=rectY2 + 150 *delta_time;
            rectY3=rectY3 + 150 *delta_time;
            rectY4=rectY4 + 150 *delta_time;
            rectY5=rectY5 + 150 *delta_time;
            rectY6=rectY6 + 150 *delta_time;
            rectY7=rectY7 + 150*delta_time;
            rectY8=rectY8 + 150*delta_time;
            rectY9=rectY9 + 150*delta_time;
            rectY10=rectY10 + 150*delta_time;
            rectY11=rectY11 + 150*delta_time;




            if(rectY>=720) rectY = -80;
            if(rectY2>=720) rectY2 = -80;
            if(rectY3>=720) rectY3 = -80;
            if(rectY4>=720) rectY4 = -80;
            if(rectY5>=720) rectY5 = -80;
            if(rectY6>=720) rectY6 = -80;
            if(rectY7>=720) rectY7 = -80;
            if(rectY8>=720) rectY8 = -80;
            if(rectY9>=720) rectY9 = -80;
            if(rectY10>=720) rectY10 = -80;
            if(rectY11>=720) rectY11 = -80;
            


            

            points++;

            if(gasAmount>0) gasAmount=gasAmount-0.05;
            if(gasAmount<=0) gasOut=true;

            if(gasOut){
                Vehicle.Velocity--;
                Tree.Velocity--;
            } 

            if(Vehicle.Velocity<=0) isPlaying = false;

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
        
        if(isPlaying && isOnPause){

            boton2.setVisible(true);
            boton3.setVisible(true);

            g.setColor(Color.gray); //Puntuacion
            g.fillRect(800, 400, 60, 60);

            g.setColor(Color.gray); //Puntuacion
            g.fillRect(870, 400, 60, 60);



        }

        if(!isPlaying){
            Vehicle.giro=0;
            Status.inGame_theme.stop();
            Status.GameOver_theme.start();
            g.drawString("FIN DE LA PARTIDA", 480,380);
            isPlaying = false;
            
            boton.setVisible(true);
            g.setColor(Color.blue); 
            g.fillRect(440, 400, 200, 100);
            g.setColor(Color.black);
            g.drawString("VOLVER AL MENU", 480,460);

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
            case 27:
                Vehicle.giro = 0; //Se debe buscar una forma de no hacer esto ya que resetea la rotacion del auto
                //La idea es que la interfaz aparezca sobre el resto de cosas
                if(isOnPause){
                    isOnPause = false;
                }else if(!isOnPause){
                    isOnPause = true;
                } 
                break;


        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
            
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
            Car.Velocity = 350;
            Tree.Velocity = 300;
            ShootPower.ThePlayerHasRocket = false;
            JumpPower.ThePlayerHasJump = false;
            points = 0;
            
            Vehicle.vehiclePosition.x=510;
            Vehicle.vehiclePosition.y=540;
            Vehicle.giro=0;
            Vehicle.destroy = false;

            Status.c1.show(Status.Panels, "1");
            Status.GameOver_theme.stop();
            Status.GameOver_theme.setMicrosecondPosition(0);
            Status.menu_theme.setMicrosecondPosition(0);
            Status.menu_theme.start();
            //System.out.println("SE PRESIONA BOTON");
        }

        if (e.getSource() == boton2){
            w = w - 20; 
            //System.out.println("SE PRESIONA BOTON 2");
        }
        if (e.getSource() == boton3){
            w = w + 20; 
            //System.out.println("SE PRESIONA BOTON 3");
        }

    }

}
