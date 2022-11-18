package PanelCentral;
import java.awt.*;


public class Vehicle {
    
    private int X=250,Y=195;
    private Color colorAuto;
 
    
    public Vehicle(){
     
    }
    
    public void paint(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.yellow);
    g2d.fillRect(X, Y, 80, 60);


    }
    
    public void SetPosition(int x, int y){
        
    }
    
    public void MoveUp(){
         Y=Y-5;
    }
    
     public void MoveRight(){
         X=X+5;
    }
    
    public void MoveLeft(){
         X=X-5;
    }
     
    public void MoveDown(){
         Y=Y+5;

        
    }

}
