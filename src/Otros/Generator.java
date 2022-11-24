package Otros;


import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.ArrayList;

public class Generator extends Thread {
    
    Timer timer = new Timer();
    private ArrayList<Car> cars; 
    private ArrayList<Tree> trees;


    public Generator(ArrayList cars, ArrayList trees) {
        this.cars = cars;
        this.trees = trees;
    }

    @Override
    public void run() {
        timer.schedule(carGenerator, 0, 10000);
        timer.schedule(treeGenerator, 0, 8000);

    }

    TimerTask carGenerator = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Auto generado " + new Date());
            cars.add(new Car());
            
        }
    };

    TimerTask treeGenerator = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Arbol generado " + new Date());
            trees.add(new Tree());
            
        }
    };

}
