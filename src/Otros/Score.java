package Otros;

import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Score {

    static int highScore;

    public Score(){
    }

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

    public void writeHighscore(int score, int highScore){

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