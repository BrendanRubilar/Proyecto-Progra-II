package PanelCentral;
import ControlGUI.VControl;
import javax.swing.*;
import java.awt.Image;



public class Vehicle extends JLabel{
    JPanel target;
    JLabel vehicle = new JLabel();
    

    public Vehicle(JPanel target){
        this.target = target;
        ImageIcon vehicleImage = new ImageIcon("Images//car.png");
        vehicle.setBounds(500,100,50,50);
        vehicle.setIcon(new ImageIcon(vehicleImage.getImage().getScaledInstance(vehicle.getWidth(),vehicle.getHeight(),Image.SCALE_SMOOTH)));
        
     

        target.add(vehicle);
    }
    
    public void SetPosition(int x, int y){
        vehicle.setLocation(x,y);
    }
    
    public void MoveUp(){
        vehicle.setLocation(vehicle.getX(),vehicle.getY()+10);
    }
    
    public JLabel getLabel(){
        return vehicle;
    }
    
}
