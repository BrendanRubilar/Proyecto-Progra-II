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
