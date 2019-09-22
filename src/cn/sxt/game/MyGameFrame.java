package cn.sxt.game;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyGameFrame extends JFrame {

    Image planeImg = GameUtil.getImage("Images/Plane.png");
    Image bgImg = GameUtil.getImage("Images/Bg.jpg");

    Plane plane = new Plane(planeImg,250,250);
    Shell shell = new Shell();
    int planeX = 250; int planeY = 250;//pre-set the coord of
    public static void main (String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        g.drawImage(bgImg,0,0,null);
        plane.drawSelf(g);
        shell.drawSelf(g);
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
    }

    //repainted
    class PaintThread extends Thread {

        @Override
        public void run() {
            while(true) {
                repaint(); //System.out.println("Repainted");

                try {
                    Thread.sleep(20);
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




}
