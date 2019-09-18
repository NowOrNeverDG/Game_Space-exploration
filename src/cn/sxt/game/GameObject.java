package cn.sxt.game;

import java.awt.*;

public class GameObject {
     Image img;
     int width,height;
     double x,y;
     int speed;


     //instruction
     public GameObject(){}

     public GameObject(Image img, double x, double y, int speed, int width, int height) {
         super();
         this.img = img;
         this.x = x;
         this.y = y;
         this.speed = speed;
         this.width = width;
         this.height = height;
     }


     public GameObject(Image img, double x, double y) {
         super();
         this.img = img;
         this.x = x;
         this.y = y;
     }

    public void drawSelf (Graphics g) {
        g.drawImage(img,(int)x, (int)y,null);
    }

    public Rectangle getRect() {
         return new Rectangle((int)x, (int)y, width,height);
    }

}
