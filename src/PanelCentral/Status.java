
package PanelCentral;

import ControlGUI.VControl;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.*;


public class Status extends JPanel implements ActionListener{
    
    JPanel Panels,Menu,Running;
    JButton Iniciar,Salir;
    JFrame target;
    CardLayout c1 = new CardLayout();
    boolean appIsRunning=false;


    public Status(JFrame target){
        this.target = target;
        
        InitPanels();
        InitMenuComponents();
        InitRunningComponents();
        
        target.add(Panels);
        

    }
    
    public void InitPanels(){
        //Panel contenedor, se utiliza CardLayout para hacer cambio entre los paneles internos.
        Panels = new JPanel();
        Panels.setLayout(c1);
        
        //Creamos los paneles de Menu y ProgramaIniciado
        Menu = new JPanel();
        Menu.setLayout(null);
        Running = new JPanel();
        Running.setLayout(null);
       
        //Se agregan estos paneles al panel contenedor
        Panels.add(Menu,"1");
        Panels.add(Running,"2");
        
    }
   
    
    public void InitMenuComponents(){
        //Creamos los botones del menu
        Iniciar = new JButton();
        Iniciar.setBounds(350,100,300,50);
        Iniciar.addActionListener(this);
        Menu.add(Iniciar);
        
        Salir = new JButton();
        Salir.setBounds(350,200,300,50);
        Salir.addActionListener(this);
        Menu.add(Salir);

    }
    
    public void InitRunningComponents(){
        //Creamos todos los elementos del panel ProgramaIniciado
        JLabel test = new JLabel();
        test.setOpaque(true);
        test.setBackground(Color.red);
        test.setBounds(100,100,50,50);
        Running.add(test);
        
        Vehicle car = new Vehicle(Running);
     
       
       

     
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==Iniciar){
          //Cambiamos al panel ProgramaIniciado
          c1.show(Panels, "2");
          appIsRunning=true;

        }
        if(e.getSource()==Salir){
            System.exit(0);
        }
            
    }
    
}
