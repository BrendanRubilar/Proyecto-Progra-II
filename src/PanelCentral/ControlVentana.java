package PanelCentral;
import Objetos.*;
import java.awt.event.*;
public class ControlVentana implements WindowListener{

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {
        if(InGame.isPlaying){
            Vehicle.giro=0;
            InGame.isOnPause = true;
        }
        
        System.out.println("Ventana Minimizada");
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        if(InGame.isPlaying){
            Vehicle.giro=0;
            InGame.isOnPause = true;
        }
        System.out.println("Ventana Minimizada");
    }
    
}
