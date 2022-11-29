package Otros;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Score {

    int score;
    int highScore;

    public Score(int score){

        this.score = score;
    }

    public void readHighScore(){
        try{

            FileReader fr = new FileReader("Highscore.txt");
            highScore = fr.read();
            fr.close();

        }catch(IOException e){
            System.out.println("Error");
        }

    }

    public void writeHighScore(){

        if (score>highScore){
            try {

                FileWriter fw = new FileWriter("Highscore.txt");
                fw.write(score);
                fw.close();

            } catch (Exception e) {
                System.out.println("Error");
            }
        }
    }
}
