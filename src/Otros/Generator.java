package Otros;


import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.ArrayList;

public class Generator extends Thread {
    
    

    Timer timer = new Timer();
    private ArrayList<Car> cars; 

    public Generator(ArrayList cars) {
        this.cars = cars;
    }

    @Override
    public void run() {
        timer.schedule(tarea, 0, 10000);
    }

    TimerTask tarea = new TimerTask() {
        @Override
        public void run() {
            System.out.println("XD " + new Date());
            cars.add(new Car());
            
        }

    };

}
