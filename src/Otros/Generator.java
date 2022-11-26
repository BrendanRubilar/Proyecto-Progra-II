package Otros;

import PanelCentral.InGame;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.ArrayList;


public class Generator extends Thread {

    Timer timer = new Timer();
    private ArrayList<Car> cars; 
    private ArrayList<Tree> trees;
    private InGame InGamePointer;

    public Generator(InGame a) {
        InGamePointer = a;
        cars  = InGamePointer.getArrayCars();
        trees = InGamePointer.getArrayTrees();

    }

    @Override
    public void run() {
        timer.schedule(carGenerator, 0, 1000); //Genera autos 
        timer.schedule(treeGenerator, 0, 8000); //Genera arboles
        timer.schedule(velocityController, 0, 3000); //Aumenta velocidad de autos
       
    }

    TimerTask carGenerator = new TimerTask() {
        @Override
        public void run() {
            if(InGame.objetsOnMovement){
                //System.out.println("Auto generado " + new Date()); 
                cars.add(new Car(0));
                cars.add(new Car(1));
            }
        }
    };


    TimerTask treeGenerator = new TimerTask() {
        @Override
        public void run() {
            if(InGame.objetsOnMovement){
                System.out.println("Arbol generado " + new Date());
                trees.add(new Tree());
            }
        }
    };

    TimerTask velocityController = new TimerTask() { //Aumenta la velocidad de los autos y arboles cada x tiempo
        @Override
        public void run(){

            if(Car.Velocity<=640){
                Car.Velocity = Car.Velocity+8;
                //System.out.println("VelocityUP! to" + Car.Velocity);
            }
            if(Tree.Velocity<=500){
                Tree.Velocity = Tree.Velocity+8;
                //System.out.println("VelocityUP! to" + Tree.Velocity);
            }
        }   
    };
}
