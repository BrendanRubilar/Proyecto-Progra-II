package PanelCentral;

import javax.swing.*;

import Objetos.Car;
import Objetos.Gas;
import Objetos.Shoot;
import Objetos.ShootPower;
import Objetos.Tree;
import Objetos.Vehicle;
import Otros.Generator;
import Otros.JumpPower;
import Otros.Score;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.awt.event.*;

public class InGame extends JPanel implements KeyListener,ActionListener{

    private static InGame instance;

    Vehicle vehicle;
    JLabel rayas = new JLabel();
    long last_time = System.currentTimeMillis();   
    double timerAuxiliar=0, rectY=10, rectY2=90,rectY3=170,rectY4=250,rectY5=330,rectY6=410,rectY7=490,rectY8=570,rectY9=650, rectY10 = 730, rectY11=810;
    boolean aux = false, MoveUp, MoveLeft, MoveRight, MoveDown;
    Color VerdeHoja, AzulAgua, GrisGrava; 

    public static boolean Jump=false, shooting=false,gasOut = false,isOnPause=false,isPlaying = false;
    public static double delta_time = 0.016,gasAmount = 200; 
    public static int x = 290, y = 0, w= 500, h=1000, points=0; 

    private int highScore;
    private Clip jump_clip;
    public static ArrayList<Car> cars; 
    public static ArrayList<Tree> trees;
    public static ArrayList<Gas> gasList;
    public static ArrayList<ShootPower> rocketBoxes;
    public static ArrayList<Shoot> shootsList;
    public static ArrayList<JumpPower> jumpBoxes;

    JButton boton = new JButton();
    JButton boton2 = new JButton();
    JButton boton3 = new JButton();
    /**
    * Constructor de la clase, se inicializan las arraylist de objetos,
    * el generador de objetos, el vehiculo y botones.
    */
    private InGame() {
        cars = new ArrayList<Car>();
        trees = new ArrayList<Tree>();
        gasList = new ArrayList<Gas>();
        rocketBoxes = new ArrayList<ShootPower>();
        shootsList = new ArrayList<Shoot>();
        jumpBoxes = new ArrayList<JumpPower>();

        VerdeHoja = new Color(15, 201, 30);
        AzulAgua = new Color(17, 155, 240);
        GrisGrava = new Color ( 85, 85, 85);

        Generator timeController = Generator.getInstance();

        this.setLayout(null);
        vehicle = Vehicle.getInstance();
        this.setFocusable(true);
        highScore=Score.readHighscore();

        JLabel Puntaje = new JLabel("Mejor puntaje: " + highScore);
        Puntaje.setBounds(470,350,300,100);
        Puntaje.setBackground(Color.green);
        this.add(Puntaje);

        boton.setBounds(400,400,280,70);
        boton2.setBounds(800,400,80,60);
        boton3.setBounds(900,400,80,60);
        this.add(boton);
        this.add(boton2);
        this.add(boton3);
        boton.setVisible(false);
        boton2.setVisible(false);
        boton2.setFocusable(false);
        boton3.setVisible(false);
        boton3.setFocusable(false);
        boton.addActionListener(this);
        boton2.addActionListener(this);
        boton3.addActionListener(this);
        this.addKeyListener(this);
    }

    /**
     * Metodo de patron Singleton para devolver la misma clase
     */
    public static InGame getInstance(){
        if(instance == null){
            instance = new InGame();
        }
        return instance;
    }

    /**
     * Metodo de dibujo de la clase, aqui se desarrolla el loop del juego
     * se dibujan todos los objetos, la pista, los botones, se controla
     * el uso de combustible, el estado del juego, el control de objetos.
     */
    public void paint(Graphics g) {
        super.paint(g);
        
        g.setColor(VerdeHoja); //Cesped
        g.fillRect(0, 0, 1080, 720);

        g.setColor(Color.GRAY); //Pista
        g.fillRect((int)x, (int)y, (int)w, (int)h);
       
        g.setColor(Color.white); //Decoracion pista
        g.fillRect((int)x+(int)w/2, 0, 15, 720);

        g.setColor(Color.white); //Decoracion pista
        g.fillRect((int)x, 0, 15, 720);

        g.setColor(Color.white); //Decoracion pista
        g.fillRect((int)x+(int)w, 0, 15, 720);
        
        if(!cars.isEmpty()){
            for (int i = 0; i < cars.size(); i++) {
                cars.get(i).paint(g);

                if(isPlaying && !isOnPause){
                     cars.get(i).moveDown();
                }

                if(cars.get(i).deleteTime()){
                    cars.remove(i);
                }
            }
        }
        
        if(!trees.isEmpty()){
            for (int i = 0; i < trees.size(); i++){
                trees.get(i).paint(g);

                if(isPlaying && !isOnPause){
                     trees.get(i).moveDown();
                }
                if(trees.get(i).deleteTime()){ 
                    trees.remove(i);
                }
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

        //Control de combustible
        g.setColor(Color.gray); 
        g.fillRect(800, 40, 200, 30);

        g.setColor(Color.red); 
        g.fillRect(800, 40, (int)gasAmount, 30);

        g.setColor(Color.black);
        g.setFont(new Font("Cascadia Mono SemiBold", Font.BOLD, 12));
        g.drawString("Combustible: "+(int)gasAmount, 850,60);

        if(isPlaying && !isOnPause){
            boton.setVisible(false);
            boton2.setVisible(false);
            boton3.setVisible(false);

            boton.setEnabled(false);
            boton2.setEnabled(false);
            boton3.setEnabled(false);

         
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
        g.setFont(new Font("Cascadia Mono SemiBold", Font.BOLD, 12));
        g.drawString("Puntuacion: "+points, 820, 20);
        

        ImageIcon inventoryIcon = new ImageIcon("Multimedia//poder.png");
        g.drawImage(inventoryIcon.getImage(), 910, 100,90,90,null); 

        if(ShootPower.ThePlayerHasRocket){
            ImageIcon tree = new ImageIcon("Multimedia//coheteIcon.png");
            g.drawImage(tree.getImage(), 910, 100,90,90,null); 
        }else if(JumpPower.ThePlayerHasJump){
            ImageIcon tree = new ImageIcon("Multimedia//jump.png");
            g.drawImage(tree.getImage(), 910, 100,90,90,null);
        }

        vehicle.paint(g);  
        
        if(isPlaying && isOnPause){
            boton.setVisible(true);
            boton.setEnabled(true);
            boton2.setVisible(true);
            boton2.setEnabled(true);
            boton3.setVisible(true);
            boton3.setEnabled(true);

            g.setColor(Color.black); 
            g.fillRect(800, 400, 80, 60);
            g.setColor(Color.white);
            g.setFont(new Font("Cascadia Mono SemiBold", Font.BOLD, 12));
            g.drawString("Pista: -20", 815,435);

            g.setColor(Color.black); 
            g.fillRect(900, 400, 80, 60);
            g.setColor(Color.white);
            g.setFont(new Font("Cascadia Mono SemiBold", Font.BOLD, 12));
            g.drawString("Pista: +20", 915,435);

            ImageIcon buttonIco = new ImageIcon("Multimedia//botones.png");
            g.drawImage(buttonIco.getImage(),400, 400, 280, 70,null);

            g.setColor(Color.black);
            g.setFont(new Font("Cascadia Mono SemiBold", Font.BOLD, 18));
            g.drawString("Volver al MENU", 465,445);
        }

        if(!isPlaying && !isOnPause){
            isPlaying = false;
            boton.setVisible(true);
            boton.setEnabled(true);

            Vehicle.giro=0;
            Status.inGame_theme.stop();
            Status.GameOver_theme.loop(-1);
            Score.writeHighscore(points,highScore);

            g.setColor(Color.BLACK);
            g.setFont(new Font("Cascadia Mono SemiBold", Font.BOLD, 40));
            g.drawString("FIN DE LA PARTIDA", 360,300);
            
            ImageIcon buttonIco = new ImageIcon("Multimedia//botones.png");
            g.drawImage(buttonIco.getImage(),400, 400, 280, 70,null);

            g.setColor(Color.black);
            g.setFont(new Font("Cascadia Mono SemiBold", Font.BOLD, 18));
            g.drawString("Volver al MENU", 465,445);

        }

        //Control de fps
        long time = System.currentTimeMillis();
        int milisegundos = (int) (time - last_time);
        int milisegundos_para_completar = 16 - milisegundos;
        delta_time = (time - last_time) / 1000.0;
        last_time = time;
        try {
            Thread.sleep(milisegundos_para_completar);
        } catch (Exception e) {}

        repaint();
    }
    /**
     * Metodo que retorna un valor double
     * @return esta variable se utiliza en el metodo de salto del vehiculo
     */
    public double getTimerAuxiliar(){
        return timerAuxiliar;
    }
    /**
     * Metodo que modifica el booleano que controla el cuando se puede usar salto
     */
    public void setJumpFalse(){
        Jump = false;
    }
    /**
     * Metodo de control de sonido al realizar acciones
     */
    private void inGameSounds(){
        try{
        jump_clip = AudioSystem.getClip();
        jump_clip.open(AudioSystem.getAudioInputStream(new File("Multimedia//SaltoV2.wav")));
        jump_clip.start();
        }catch(Exception e){
            System.out.println("No funcionó, verifique si tiene el archivo de audio en carpeta Multimedia " + e);
        }
    }
            
    @Override
    public void keyTyped(KeyEvent e) {}
    
    /**
     * Metodo que restablece todos los parametros a sus valores
     * originales, asi se puede volver al jugar sin problemas.
     */
    public void resetGame(){
        Vehicle instance= Vehicle.getInstance();
        gasAmount = 200;
        points = 0;
        highScore = Score.readHighscore();
        isOnPause = false;
        isPlaying = false;
        Car.Velocity = 350;
        Tree.Velocity = 300;
        ShootPower.ThePlayerHasRocket = false;
        JumpPower.ThePlayerHasJump = false;
        instance.vehiclePosition.x=510;
        instance.vehiclePosition.y=540;
        Vehicle.giro=0;
        Vehicle.destroy = false;
        Status.UpdateHighScoreOnMenu(highScore);
        Status.c1.show(Status.Panels, "1");
        Status.GameOver_theme.stop();
        Status.GameOver_theme.setMicrosecondPosition(0);
        Status.inGame_theme.setMicrosecondPosition(0);
        Status.inGame_theme.stop();
        Status.menu_theme.setMicrosecondPosition(0);
        Status.menu_theme.start(); 
    }

    /**
     * Metodo que controla el movimiento del vehiculo y el uso de poderes.
     */
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
                        inGameSounds();
                    }
                }
                if(ShootPower.ThePlayerHasRocket){
                    shooting = true;
                    shootsList.add(new Shoot());
                    ShootPower.ThePlayerHasRocket = false;
                }
                break;
            case 27:
                Vehicle.giro = 0; 
                if(isOnPause){
                    isOnPause = false;
                }else if(!isOnPause){
                    isOnPause = true;
                } 
                break;
        }
    }
    /**
     * Metodo de control de vehiculo (Soltar teclas)
     */
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

    /**
     * Metodo para dar funcionalidades a los botones de pausa o perder.
     */
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
            for (int i = 0; i < rocketBoxes.size(); i++){
                rocketBoxes.get(i).setYNearToLimit();
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
            for (int i = 0; i < rocketBoxes.size(); i++){
                rocketBoxes.remove(i);
            }                        
            resetGame();
        }

        if (e.getSource() == boton2){
            if(w>=200){
                w = w - 20; 
                x = x + 10;
            }
        }
        if (e.getSource() == boton3){
            if(w<=700){
                w = w + 20; 
                x = x - 10;
            }  
        }
    }

}
