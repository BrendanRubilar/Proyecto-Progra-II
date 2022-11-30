package Otros;

import PanelCentral.InGame;
import PanelCentral.Status;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Random;

/*Esta clase es de suma importancia en el juego ya que controla el tiempo en el que aparecen los objetos y
aumenta la dificultad. Tener en cuenta que la generacion de objetos es capaz de detectar los limites de la 
pista y asi no generar autos fuera de ella. */
public class Generator extends Thread {

    Timer timer = new Timer();
    private ArrayList<Car> cars; 
    private ArrayList<Tree> trees;
    private ArrayList<Gas> gasList;
    private ArrayList<ShootPower> rocketBoxes;
    private ArrayList<JumpPower> jumpBoxes;

    private InGame InGamePointer;
    Random r = new Random();

    public Generator(InGame a) {
        InGamePointer = a;
        cars  = InGamePointer.getArrayCars();
        trees = InGamePointer.getArrayTrees();
        gasList = InGamePointer.getArrayGas();
        rocketBoxes = InGamePointer.getArrayBoxes();
        jumpBoxes = InGamePointer.getArrayJumpBoxes();

    }
    @Override
    public void run() {
        timer.schedule(carGenerator, 0, 2000); 
        timer.schedule(treeGenerator, 0, 1000); 
        timer.schedule(velocityController, 0, 3000); 
        timer.schedule(gasGenerator, 0, 4000); 
        timer.schedule(rocketBoxGenerator, 0, 5000); 
        timer.schedule(jumpBoxesGenerator, 0, 5000);         
    }
    TimerTask carGenerator = new TimerTask() {
        @Override
        public void run() {
            if(InGame.isPlaying && !InGame.isOnPause){

                if(Status.dificultad==0){
                   
                    cars.add(new Car(0));
                    cars.add(new Car(1));
                }
                if(Status.dificultad==1){
                    cars.add(new Car(0));
                    cars.add(new Car(1));
                    cars.add(new Car(2));

                }
                if(Status.dificultad==2){
                    cars.add(new Car(0));
                    cars.add(new Car(1));
                    cars.add(new Car(2));
                    cars.add(new Car(3));
                }

            }
        }
    };

    TimerTask treeGenerator = new TimerTask() {
        @Override
        public void run() {
            if(InGame.isPlaying && !InGame.isOnPause){
                
                trees.add(new Tree(0));
                trees.add(new Tree(1));
            }
        }
    };

    TimerTask velocityController = new TimerTask() { 
        @Override
        public void run(){
            //Aumenta de manera progresiva la velocidad de los vehiculos 
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
    TimerTask gasGenerator = new TimerTask() {
        @Override
        public void run() {

            if(InGame.isPlaying && !InGame.isOnPause){
                int aux = r.nextInt(2);

                if(aux==1){
                    System.out.println("Se crea gas");
                    gasList.add(new Gas());
                }
            }
        }
    };
    TimerTask rocketBoxGenerator = new TimerTask() {
        @Override
        public void run() {

            if(InGame.isPlaying && !InGame.isOnPause){
                int aux = r.nextInt(2);

                if(aux==1){
                    System.out.println("Se crea una caja de cohetes");
                    rocketBoxes.add(new ShootPower());
                }
            }
        }
    };
    TimerTask jumpBoxesGenerator = new TimerTask() {
        @Override
        public void run() {

            if(InGame.isPlaying && !InGame.isOnPause){
                int aux = r.nextInt(2);

                if(aux==1){
                    System.out.println("Se crea una caja de Saltos");
                    jumpBoxes.add(new JumpPower());
                }
            }
        }
    };

}
