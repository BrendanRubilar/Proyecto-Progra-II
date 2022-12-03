package PanelCentral;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {

        setSize(1080, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("FORZA HORIZON 5");
        setResizable(false);
        setIconImage(new ImageIcon("Multimedia//icono.jpg").getImage());
        Status StatusController = Status.getInstance();
        StatusController.Start(this);

        setVisible(true);

    }

}
