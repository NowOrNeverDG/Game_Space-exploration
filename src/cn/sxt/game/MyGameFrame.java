package cn.sxt.game;

import javax.swing.JFrame;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyGameFrame extends Frame {

    Image planeImg = GameUtil.getImage("Images/Plane.png");
    Image bgImg = GameUtil.getImage("Images/Bg.jpg");

    Plane plane = new Plane(planeImg,250,250,3);

    Shell shell = new Shell();
    Shell[] shells = new Shell[50];

    Explode bao;
    Date startTime = new Date();
    Date endTime;
    int period;//alive duration
    public static void main (String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }

    @Override
    public void paint(Graphics g) {
        Color originColor = g.getColor();
        g.drawImage(bgImg,0,0,null);
        plane.drawSelf(g);
        shell.draw(g);

        //Draw all shells
        for (int i = 0; i < shells.length; i++) {
            shells[i].draw(g);
            //If the plane intersects shells
            boolean peng = shells[i].getRect().intersects(plane.getRect());
            if(peng) {
                plane.live = false;
                if (bao == null) {
                    bao = new Explode(plane.x,plane.y);

                    endTime = new Date();
                    period = (int)((endTime.getTime()-startTime.getTime())/1000);
                }
                bao.draw(g);
            }
            if (!plane.live){
                g.setColor(Color.white);
                g.drawString("Durarion: " + period +"Sec", (int)plane.x, (int)plane.y);
            }

        }

        g.setColor(originColor);
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
        addKeyListener(new KeyMonitor());//Keyboard Monitor

        //initialize 50 Shells
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
