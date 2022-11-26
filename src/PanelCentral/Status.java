package PanelCentral;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.FloatControl;

public class Status extends JPanel implements ActionListener {

    //Creamos un panel contenedor, un panel Menu y un panel InGame (Este ultimo es una clase)
    private Clip menu_theme, inGame_theme;
    JPanel Panels, Menu;
    JButton Iniciar, Salir,Dificultad;
    JFrame target;
    public static int dificultad=1; // 0-Facil | 1-Medio | 2-Dificil
    CardLayout c1 = new CardLayout();

    InGame enjuego = new InGame(); //Panel in game

    boolean appIsRunning = false;

    public Status(JFrame target) {

        this.target = target;
        InitMusic();
        InitPanels();
        InitMenuComponents();
        InitRunningComponents();
        
        target.add(Panels);

    }

    public void InitPanels() {
        //Panel contenedor, se utiliza CardLayout para hacer cambio entre los paneles internos.
        Panels = new JPanel();
        Panels.setLayout(c1);

        //Creamos los paneles de Menu y ProgramaIniciado
        Menu = new JPanel();
        Menu.setLayout(null);

        //Se agregan estos paneles al panel contenedor
        Panels.add(Menu, "1");
        Panels.add(enjuego, "2");

    }

    public void InitMenuComponents() {
        //Creamos los botones del menu
        Iniciar = new JButton();
        Iniciar.setBounds(350, 100, 300, 50);
        Iniciar.setText("Jugar");
        Iniciar.addActionListener(this);
        Menu.add(Iniciar);

        Dificultad = new JButton();
        Dificultad.setBounds(350, 200, 300, 50);
        Dificultad.setText("Dificultad: Media");
        Dificultad.addActionListener(this);
        Menu.add(Dificultad);

        Salir = new JButton();
        Salir.setBounds(350, 300, 300, 50);
        Salir.setText("Salir");
        Salir.addActionListener(this);
        Menu.add(Salir);

    }

    public void InitRunningComponents() {
        //Creamos todos los elementos del panel ProgramaIniciado
        JLabel test = new JLabel();
        test.setOpaque(true);
        test.setBackground(Color.red);
        test.setBounds(100, 100, 50, 50);
        enjuego.add(test);

    }

    private void InitMusic(){
        try {
 
            // Se obtiene un Clip de sonido
            menu_theme = AudioSystem.getClip();
            inGame_theme = AudioSystem.getClip();
            
 
            // Se carga con un fichero wav
            menu_theme.open(AudioSystem.getAudioInputStream(new File("Multimedia//main_theme.wav")));
            inGame_theme.open(AudioSystem.getAudioInputStream(new File("Multimedia//ingameTheme.wav")));
            
            setVolumeMenu(0.15f);
            setVolumeGame(0.15f);

            // Comienza la reproducción
            menu_theme.loop(-1);

        } catch (Exception e) {
            System.out.println("No funcionó, verifique si tiene el archivo de audio en carpeta Multimedia " + e);
        }
    }

//Codigo para ajustar el volumen-------------------------------------------------------------------
    public float getVolumeMenu() {
        FloatControl gainControl = (FloatControl) menu_theme.getControl(FloatControl.Type.MASTER_GAIN);        
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolumeMenu(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) menu_theme.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
    public float getVolumeGame() {
        FloatControl gainControl = (FloatControl) inGame_theme.getControl(FloatControl.Type.MASTER_GAIN);        
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolumeGame(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) inGame_theme.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
//-------------------------------------------------------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Iniciar) {
            //Cambiamos al panel ProgramaIniciado
            menu_theme.stop();
            inGame_theme.loop(-1);
            c1.show(Panels, "2");
            enjuego.requestFocus();
            appIsRunning = true;

        }
        if (e.getSource() == Dificultad){

            dificultad++;
            if(dificultad>=3) dificultad=0;
            if(dificultad==0) Dificultad.setText("Dificultad: Facil");
            if(dificultad==1) Dificultad.setText("Dificultad: Media");
            if(dificultad==2) Dificultad.setText("Dificultad: Dificil");
            System.out.println(dificultad);

        }

        if (e.getSource() == Salir) {
            System.exit(0);
        }

    }

}
