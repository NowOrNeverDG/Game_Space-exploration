package cn.sxt.game;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyGameFrame extends Frame {

    Image planeImg = GameUtil.getImage("Images/Plane.png");
    Image bgImg = GameUtil.getImage("Images/Bg.jpg");

    Plane plane = new Plane(planeImg,250,250);
    int planeX = 250; int planeY = 250;//pre-set the coordinate of plane

    Shell shell = new Shell();
    Shell[] shells = new Shell[50];

    public static void main (String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(bgImg,0,0,null);
        plane.drawSelf(g);
        shell.draw(g);

        //draw all shells
        for (int i = 0; i < shells.length; i++) {
            shells[i].draw(g);

            boolean peng = shells[i].getRect().intersects(plane.getRect());

            //Confirmed if the plane is lived
            if(peng) {plane.live = false;}
        }
    }

    //initialized game window
    public void launchFrame() {
        this.setTitle("Worked by Genn");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        this.setLocation(300,300);

        //Closed windows completely
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.exit(0);
            }
        });

        new PaintThread().start(); //Executed the thread repaints window
        addKeyListener(new KeyMonitor());

        //initialize Shells
        for ( int i = 0; i < shells.length; i++) {
            shells[i] = new Shell();
        }
    }

    //repainted
    class PaintThread extends Thread {

        @Override
        public void run() {
            while(true) {
                repaint(); //System.out.println("Repainted");

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //System.out.println("Pressed:" + e.getKeyCode());
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("Released:" + e.getKeyCode());
            plane.minusDirection(e);
        }
    }


    //Double buffering
    private Image offScreenImage = null;
    public void update(Graphics g) {
        if (offScreenImage == null) offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);


    }


}
