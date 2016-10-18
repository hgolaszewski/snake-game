package Graphics;

import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Window extends Applet{

    private final JFrame frame;
    private final Panel panel;
    
    public Window(String title) {

        frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(600, 600));
        panel = new Panel();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.setLocation(700, 300);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("ico/Snake.png"));
        frame.pack();

    }

    public static void main(String[] args) {

        new Window("Snake by Hubert Gołaszewski");

    }

}
