package Graphics;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Window {

    private final JFrame frame;
    private final Panel panel;
    
    public Window(String title) {

        this.frame = new JFrame(title);
        this.frame.setPreferredSize(new Dimension(600, 600));
        this.panel = new Panel();
        this.frame.setContentPane(panel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.frame.setUndecorated(true);
        this.frame.setResizable(true);
        this.frame.setVisible(true);
        this.frame.setLocation(700, 300);
        this.frame.pack();

    }

    public static void main(String[] args) {

        new Window("Snake by Hubert Gołaszewski");

    }

}
