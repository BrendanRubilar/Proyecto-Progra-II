package PanelCentral;

import java.awt.*;

public class Vehicle {

    private int X = 250, Y = 195, H = 80, W = 60;
    private double  giro = 0;

    public Vehicle() {

    }

    public void paint(Graphics g) {
        Graphics2D car = (Graphics2D) g;
        Graphics2D wheel1 = (Graphics2D) g;
        Graphics2D wheel2 = (Graphics2D) g;
        Graphics2D wheel3 = (Graphics2D) g;
        Graphics2D wheel4 = (Graphics2D) g;

        wheel1.rotate(Math.toRadians(giro), X+30, Y+10);
        wheel1.setColor(Color.BLACK);
        wheel1.fillRect(X-5, Y+10, W-50, H-60);

        wheel2.rotate(Math.toRadians(giro), X+30, Y+10);
        wheel2.setColor(Color.BLACK);
        wheel2.fillRect(X+55, Y+10, W-50, H-60);

        wheel3.rotate(Math.toRadians(-giro), X+30, Y+50);
        wheel3.setColor(Color.BLACK);
        wheel3.fillRect(X-5, Y+50, W-50, H-60);

        wheel4.rotate(Math.toRadians(-giro), X+30, Y+50);
        wheel4.setColor(Color.BLACK);
        wheel4.fillRect(X+55, Y+50, W-50, H-60);

        car.rotate(Math.toRadians(-giro), X + 30, Y+80);
        car.setColor(Color.yellow);
        car.fillRect(X, Y, W, H);

    }

    public void SetPosition(int x, int y) {

    }
    public int x_locate(){
        return X;
    }
        public int y_locate(){
        return Y;
    }
    public void MoveUp() {
        if (Y >= 20) {
            Y = Y - 1;
            if(giro>0.4){
                giro=giro-0.5;
            }else if(giro<-0.4){
                giro=giro+0.5;
            }else{
                giro=0;
            }
        }
    }

    public void MoveRight() {
        if (X <= 1000) {
             X = X + 1;
             if(giro>=-5){
                giro = giro - 0.4;
            }
        }
    }

    public void MoveLeft() {
        if (X >= 20) {
            X = X - 1;
            if(giro<=5){
                giro = giro + 0.4;
            }
        }
    }

    public void MoveDown() {
        if (Y <= 600) {
            Y = Y + 1 ;
            if(giro>0.4){
                giro=giro-0.5;
            }else if(giro<-0.4){
                giro=giro+0.5;
            }else{
                giro=0;
            }
        }

    }

}
