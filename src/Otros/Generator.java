package Otros;

import PanelCentral.InGame;
import PanelCentral.Status;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

/**
 * Esta clase es de suma importancia en el juego ya que controla el tiempo en el que aparecen los objetos y
 *aumenta la dificultad. Tener en cuenta que la generacion de objetos es capaz de detectar los limites de la 
 *pista y asi no generar autos fuera de ella. 
 */
public class Generator extends Thread {

    private static Generator instance;

    Timer timer = new Timer();
    Random r = new Random();
    /**
     * Constructor de Generator, se inicia el hilo y asi comienzan a funcionar los temporizadores.
     * 
     */
    public Generator() {
        this.start();
    }
    /**
     * Patron Singleton para generar solo una instancia de esta clase
     * 
     */
    public static Generator getInstance(){
        if(instance == null){
            instance = new Generator();
        }
        return instance;
    }
    /**
     * Metodo run de la clase, el temporizador inicia diferentes tareas cada cierto tiempo
     * 
     */
    @Override
    public void run() {
        timer.schedule(carGenerator, 0, 2000); 
        timer.schedule(treeGenerator, 0, 1000); 
        timer.schedule(velocityController, 0, 3000); 
        timer.schedule(gasGenerator, 0, 4000); 
        timer.schedule(rocketBoxGenerator, 0, 5000); 
        timer.schedule(jumpBoxesGenerator, 0, 5000);         
    }
    /**
     * Tarea generar vehiculos, solo si se está en el panel InGame y no se encuentra en pausa se va a generar una
     * cantidad de vehiculos que depende de la dificultad seleccionada en el menu.
     */
    TimerTask carGenerator = new TimerTask() {
        @Override
        public void run() {
            if(InGame.isPlaying && !InGame.isOnPause){

                if(Status.dificultad==0){
                   
                    InGame.cars.add(new Car(0));
                    InGame.cars.add(new Car(1));
                }
                if(Status.dificultad==1){
                    InGame.cars.add(new Car(0));
                    InGame.cars.add(new Car(1));
                    InGame.cars.add(new Car(2));

                }
                if(Status.dificultad==2){
                    InGame.cars.add(new Car(0));
                    InGame.cars.add(new Car(1));
                    InGame.cars.add(new Car(2));
                    InGame.cars.add(new Car(3));
                }

            }
        }
    };
    /**
     * Tarea generar arboles, solo si se está en el panel InGame y no se encuentra en pausa se van a generar arboles
     * en el pasto.
     */
    TimerTask treeGenerator = new TimerTask() {
        @Override
        public void run() {
            if(InGame.isPlaying && !InGame.isOnPause){
                
                InGame.trees.add(new Tree(0));
                InGame.trees.add(new Tree(1));
            }
        }
    };
    /**
     * Tarea que aumenta la velocidad de los vehiculos y arboles de manera progresiva hasta llegar a cierto limite
     */
    TimerTask velocityController = new TimerTask() { 
        @Override
        public void run(){
            if(InGame.isPlaying && !InGame.isOnPause){
                if(Car.Velocity<=800){ 
                    Car.Velocity = Car.Velocity+8;
                }
                if(Tree.Velocity<=700){
                    Tree.Velocity = Tree.Velocity+8;
                }
            }
        }   
    };
    /**
     * Tarea que genera gas, solo si se está en el panel InGame y no se encuentra en pausa, el gas aparece en el area de la pista
     *
     */
    TimerTask gasGenerator = new TimerTask() {
        @Override
        public void run() {

            if(InGame.isPlaying && !InGame.isOnPause){
                int aux = r.nextInt(2);

                if(aux==1){
                    System.out.println("Se crea gas");
                    InGame.gasList.add(new Gas());
                }
            }
        }
    };
        /**
     * Tarea que genera cajas de cohetes, solo si se está en el panel InGame y no se encuentra en pausa, aparecen en el area de la pista
     *
     */
    TimerTask rocketBoxGenerator = new TimerTask() {
        @Override
        public void run() {

            if(InGame.isPlaying && !InGame.isOnPause){
                int aux = r.nextInt(2);

                if(aux==1){
                    System.out.println("Se crea una caja de cohetes");
                    InGame.rocketBoxes.add(new ShootPower());
                }
            }
        }
    };
     /**
     * Tarea que genera cajas de Salto, solo si se está en el panel InGame y no se encuentra en pausa, aparecen en el area de la pista
     *
     */
    TimerTask jumpBoxesGenerator = new TimerTask() {
        @Override
        public void run() {

            if(InGame.isPlaying && !InGame.isOnPause){
                int aux = r.nextInt(2);

                if(aux==1){
                    System.out.println("Se crea una caja de Saltos");
                    InGame.jumpBoxes.add(new JumpPower());
                }
            }
        }
    };

}
