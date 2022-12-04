package PanelCentral;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.*;

import Otros.Score;

import java.awt.event.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.sound.sampled.FloatControl;
public class Status extends JPanel implements ActionListener {

    /**
     * Clase Status. Su función es inicializar los componentes de la ventana donde se muestra
     * toda la parte gráfica del código (botones, paints, el juego en general).
     */

    private static Status instance;

    public static Clip menu_theme, inGame_theme, GameOver_theme;
    public static JPanel Panels, Menu, GameOver;
    JButton Iniciar, Salir, Dificultad;
    JFrame target;
    static JLabel Puntaje;
    public static int dificultad=1; 
    public static CardLayout c1 = new CardLayout();
    JLabel texto2 = new JLabel();

    public static int highScore = Score.readHighscore();

    InGame enjuego = InGame.getInstance();

    boolean appIsRunning = false;

    /**
     * Constructor de Status.
     */

    private Status() {
    }

    /**
     * Se inician los paneles con la música, botones y labels.
     * 
     * @param target Recibe target desde Window donde se coloca todo lo generado por Start
     */

    public void Start(JFrame target){
        this.target = target;
        InitMusic();
        InitPanels();
        InitMenuComponents();
        InitRunningComponents();
        target.add(Panels);
    }

    public static Status getInstance(){
        if(instance == null){
            instance = new Status();
        }
        return instance;
    }

    /**
     * 
     * Genera los paneles a utilizar en el programa y el Menú. Panels 
     * 
     */

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
        Iniciar.setBounds(300, 100, 500, 60);
        ImageIcon Start = new ImageIcon("Multimedia//botones.png");
        Iniciar.setIcon(new ImageIcon(Start.getImage().getScaledInstance(Iniciar.getWidth(), Iniciar.getHeight(), Image.SCALE_SMOOTH)));
        Iniciar.addActionListener(this);
        Iniciar.setOpaque(false);
        Iniciar.setContentAreaFilled(false);
        Iniciar.setBorderPainted(false);
        Menu.add(Iniciar);
        JLabel texto1 = new JLabel();
        texto1.setBounds(520, 100, 500, 60);
        texto1.setText("Jugar");
        Menu.add(texto1);
        Menu.setComponentZOrder(texto1,0);

        Dificultad = new JButton();
        Dificultad.setBounds(300, 200, 500, 60);
        ImageIcon difficult = new ImageIcon("Multimedia//botones.png");
        Dificultad.setIcon(new ImageIcon(difficult.getImage().getScaledInstance(Dificultad.getWidth(), Dificultad.getHeight(), Image.SCALE_SMOOTH)));
        Dificultad.addActionListener(this);
        Dificultad.setOpaque(false);
        Dificultad.setContentAreaFilled(false);
        Dificultad.setBorderPainted(false);
        Menu.add(Dificultad);
        texto2.setBounds(500, 200, 500, 60);
        texto2.setText("Dificultad: Normal");
        Menu.add(texto2);
        Menu.setComponentZOrder(texto2,0);

        Salir = new JButton();
        Salir.setBounds(300, 300, 500, 60);
        ImageIcon SalirButton = new ImageIcon("Multimedia//botones.png");
        Salir.setIcon(new ImageIcon(SalirButton.getImage().getScaledInstance(Salir.getWidth(), Salir.getHeight(), Image.SCALE_SMOOTH)));
        Salir.setText("Salir");
        Salir.addActionListener(this);
        Salir.setOpaque(false);
        Salir.setContentAreaFilled(false);
        Salir.setBorderPainted(false);
        Menu.add(Salir);
        JLabel texto3 = new JLabel();
        texto3.setBounds(520, 300, 500, 60);
        texto3.setText("Salir");
        Menu.add(texto3);
        Menu.setComponentZOrder(texto3,0);
    }

    public void InitRunningComponents() {

        JLabel HIGH_SCORE = new JLabel();
        HIGH_SCORE.setBounds(385,370,325,225);
        ImageIcon score = new ImageIcon("Multimedia//mejorPuntaje.png");
        HIGH_SCORE.setIcon(new ImageIcon(score.getImage().getScaledInstance(HIGH_SCORE.getWidth(), HIGH_SCORE.getHeight(), Image.SCALE_SMOOTH)));
        Menu.add(HIGH_SCORE);

        Puntaje = new JLabel("" + highScore);
        Puntaje.setFont(new Font("Cascadia Mono SemiBold", Font.BOLD, 56));
        Puntaje.setForeground(Color.WHITE); 
        Puntaje.setBounds(480,500,300,100);
        Menu.add(Puntaje);

        JLabel marcoPT = new JLabel();
        marcoPT.setBounds(305,440,500,200);
        ImageIcon huella = new ImageIcon("Multimedia//puntuacion.png");
        marcoPT.setIcon(new ImageIcon(huella.getImage().getScaledInstance(marcoPT.getWidth(), marcoPT.getHeight(), Image.SCALE_SMOOTH)));
        Menu.add(marcoPT);

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
        Puntaje.setText("" + HS);

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
        gainControl
        .setValue(20f * (float) Math.log10(volume));

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
            if(dificultad==0) texto2.setText("Dificultad: Facil");
            if(dificultad==1) texto2.setText("Dificultad: Media");
            if(dificultad==2) texto2.setText("Dificultad: Dificil");
            System.out.println(dificultad);
        }

        if (e.getSource() == Salir) {
            System.exit(0);
        }
    }

}
