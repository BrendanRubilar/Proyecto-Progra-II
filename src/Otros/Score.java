package Otros;

import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * 
 * Clase Score. Su función es guardar y leer un archivo de texto con el mejor puntaje obtenido en el juego.
 *
 */

public class Score {

    static int highScore;

    /**
     * Constructor de Score.
     */

    public Score(){
    }

    /**
     * 
     * Método readHighscore. Su función es leer un archivo .txt en la carpeta Multimedia y extraer
     * el número escrito en él (el mejor puntaje obtenido anteriormente). Luego lo convierte en un int
     * y lo guarda.
     * @return Retorna el número obtenido del archivo .txt.
     */

    public static int readHighscore(){

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try{
            archivo = new File ("Multimedia//Highscore.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            String linea;
            linea=br.readLine();
            highScore = Integer.parseInt(linea);

        }

        catch(Exception e){
            e.printStackTrace();
        }finally{

            try{                    
                if( null != fr ){   
                    fr.close();     
                }                

        }catch (Exception e2){ 
            e2.printStackTrace();
            }

        }

        return highScore;
    }

    /**
     * 
     * Método writeHighscore. Su función es comparar el puntaje obtenido en la partida y compararlo con el mejor puntaje
     * registrado en el archivo .txt y si resulta ser mayor, lo escribe en el archivo.
     * 
     * @param score Puntaje obtenido en la partida.
     * @param highScore Mejor puntaje registrado en el archivo .txt.
     */

    public static void writeHighscore(int score, int highScore){

        if (score>highScore){

            FileWriter fichero = null;
            PrintWriter pw = null;

            try{
                fichero = new FileWriter("Multimedia//Highscore.txt");
                pw = new PrintWriter(fichero);
                pw.print(score);

            }catch (Exception e) {
                e.printStackTrace();
            }finally{
                try{
                    if (null != fichero)
                    fichero.close();
                    
            }catch (Exception e2) {
                e2.printStackTrace();
                }
            }
        }
    }
}