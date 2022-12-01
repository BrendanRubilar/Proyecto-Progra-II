package PanelCentral;

import Otros.Score;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.FloatControl;
public class Status extends JPanel implements ActionListener {

    public static Clip menu_theme, inGame_theme, GameOver_theme;
    public static JPanel Panels, Menu, GameOver;
    JButton Iniciar, Salir, Dificultad;
    JFrame target;
    static JLabel Puntaje;
    public static int dificultad=1; 
    public static CardLayout c1 = new CardLayout();

    private static int highScore = Score.readHighscore();

    InGame enjuego = new InGame();

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
        Panels = new JPanel();
        Panels.setLayout(c1);

        Menu = new JPanel();
        Menu.setLayout(null);

        Panels.add(Menu, "1");
        Panels.add(enjuego, "2");
    }

    public void InitMenuComponents() {
        Iniciar = new JButton();
        Iniciar.setBounds(390, 100, 300, 50);
        Iniciar.setText("Jugar");
        Iniciar.addActionListener(this);
        Menu.add(Iniciar);

        Dificultad = new JButton();
        Dificultad.setBounds(390, 200, 300, 50);
        Dificultad.setText("Dificultad: Media");
        Dificultad.addActionListener(this);
        Menu.add(Dificultad);

        Salir = new JButton();
        Salir.setBounds(390, 300, 300, 50);
        Salir.setText("Salir");
        Salir.addActionListener(this);
        Menu.add(Salir);
    }

    public void InitRunningComponents() {

        Puntaje = new JLabel("Mejor puntaje: " + highScore);
        Puntaje.setBounds(475,350,300,100);
        Menu.add(Puntaje);

        JLabel test = new JLabel();
        ImageIcon intro = new ImageIcon("Multimedia//Introgame.gif");
        test.setIcon(new ImageIcon(intro.getImage()));
        test.setOpaque(false);
        test.setBounds(0, 0, 1080, 720);
        Menu.add(test);
    }

    public static void UpdateHighScoreOnMenu(int newHS){
        highScore = newHS;
        String HS = String.valueOf(highScore);
        Puntaje.setText("Mejor puntaje: " + HS);
    }

    private void InitMusic(){
        try {
            // Se obtiene un Clip de sonido
            menu_theme = AudioSystem.getClip();
            inGame_theme = AudioSystem.getClip();
            GameOver_theme = AudioSystem.getClip();
            
            // Se carga con un fichero wav
            menu_theme.open(AudioSystem.getAudioInputStream(new File("Multimedia//main_theme.wav")));
            inGame_theme.open(AudioSystem.getAudioInputStream(new File("Multimedia//ingameTheme.wav")));
            GameOver_theme.open(AudioSystem.getAudioInputStream(new File("Multimedia//GameO.wav")));
            
            setVolumeMenu(0.15f);
            setVolumeGame(0.15f);
            setVolumeGameOver(0.15f);

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
    public float getVolumeGameOver() {
        FloatControl gainControl = (FloatControl) GameOver_theme.getControl(FloatControl.Type.MASTER_GAIN);        
        return (float) Math.pow(10f, gainControl.getValue() / 20f);
    }

    public void setVolumeGameOver(float volume) {
        if (volume < 0f || volume > 1f)
            throw new IllegalArgumentException("Volume not valid: " + volume);
        FloatControl gainControl = (FloatControl) GameOver_theme.getControl(FloatControl.Type.MASTER_GAIN);        
        gainControl.setValue(20f * (float) Math.log10(volume));
    }
    //-------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Iniciar) {
            //Cambiamos al panel ProgramaIniciado
            menu_theme.stop();
            Status.inGame_theme.setMicrosecondPosition(0);
            inGame_theme.loop(-1);
            c1.show(Panels, "2");
            enjuego.requestFocus();
            appIsRunning = true;
            
            InGame.isPlaying=true;
            
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
