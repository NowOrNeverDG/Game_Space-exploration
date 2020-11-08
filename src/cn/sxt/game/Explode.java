package cn.sxt.game;
import java.awt.Graphics;
import java.awt.Image;

public class Explode {
    double x,y;//Position of Explode
    static Image[] imgs = new Image[16];

    static {
        for (int i = 0; i < 16; i++) {
            imgs[i] = GameUtil.getImage("Images/Explode/e" + (i+1) + ".gif"); //Explode Images combined
            imgs[i].getWidth(null);
        }
    }

    int count=0;

    public void draw(Graphics g) {
        if (count <= 15) {
            g.drawImage(imgs[count], (int)x, (int)y, null);
            count++;
        }
    }

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }
}
