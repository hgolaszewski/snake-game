package Graphics;

import Moving.Controller;
import Game.SnakePart;
import Game.Snake;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Panel extends JPanel {

    private final Snake snake;
    private final Thread snakeThread;
    private final Controller controller;
    
    public Panel() {

        super.setFocusable(true);
        super.setBackground(Color.BLACK);
        super.setPreferredSize(new Dimension(600, 600));
        this.snake = new Snake(this);
        this.snakeThread = new Thread(snake);
        this.snakeThread.start();
        this.controller = new Controller(snake);
        super.addKeyListener(controller);

    }

    // Painting the snake and bonus
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
     
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((int)snake.getBonus().getX(), (int)snake.getBonus().getY(), 30, 30);
        g.setColor(Color.DARK_GRAY);
        g.drawRect((int)snake.getBonus().getX(), (int)snake.getBonus().getY(), 30, 30);
         
        int score = snake.getSnakeParts().size();
        
        for (SnakePart part : snake.getSnakeParts()) {

            g.setColor(Color.LIGHT_GRAY);
            g.fillRect((int)part.getX(), (int)part.getY(), 30, 30);
            g.setColor(Color.DARK_GRAY);
            g.drawRect((int)part.getX(), (int)part.getY(), 30, 30);
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("CourierNew", Font.BOLD, 15));
            g.drawString(Integer.toString(score), (int)part.getX() + 2, (int)part.getY() + 25);
            score--;

        }

    }

}
