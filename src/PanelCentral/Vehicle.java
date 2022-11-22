package PanelCentral;

import java.awt.*;

public class Vehicle {

    private int X = 250, Y = 195, H = 80, W = 60, giro = 0;
    private Color colorAuto;

    public Vehicle() {

    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.rotate(Math.toRadians(giro), X + 30, Y);
        g2d.setColor(Color.yellow);
        g2d.fillRect(X, Y, W, H);

    }

    public void SetPosition(int x, int y) {

    }

    public void MoveUp() {
        if (Y >= 20) {
            Y = Y - 5;
        }
    }

    public void MoveRight() {
        if (X <= 1000) {
            //X = X + 5;
            giro = giro - 1;
        }
    }

    public void MoveLeft() {
        if (X >= 20) {
            //X = X - 5;
            giro = giro + 1;

        }
    }

    public void MoveDown() {
        if (Y <= 600) {
            Y = Y + 5;
        }

    }

}
