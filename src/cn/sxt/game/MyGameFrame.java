package cn.sxt.game;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyGameFrame extends JFrame {

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    //initialized game window
    public void launchFrame() {
        this.setTitle("Worked by Genn");
        this.setVisible(true);
        this.setSize(500,500);
        this.setLocation(300,300);

        //Closed windows completely
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                System.exit(0);
            }
        });
    }

    public static void main (String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
}
