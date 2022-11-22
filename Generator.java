package Otros;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class Generator extends Thread{
    
    Timer timer = new Timer();
    
 
    public Generator(){
        
    }
        
    @Override
    public void run(){
        timer.schedule(tarea,0,10000);
    }

    TimerTask tarea = new TimerTask(){
        @Override 
        public void run(){
            System.out.println("XD " + new Date());
        }
        
        
    };
    
       
}
