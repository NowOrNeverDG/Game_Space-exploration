package cn.sxt.game;

import javax.swing.JFrame;

public class MyGameFrame extends JFrame {

    //initialized game window
    public void launchFrame() {
        this.setTitle("Worked by Genn");
        this.setVisible(true);
    }

    public static void main (String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }
}
