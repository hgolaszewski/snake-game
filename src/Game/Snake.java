package Game;

import Moving.Direction;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

public class Snake implements Runnable {

    private final Vector<SnakePart> snakePart;
    private final ArrayList<Direction> states; // Directions buffer, which max capacity is 5 commands, otherwise BufferOverFlowException is thrown
    private Direction state;
    private final JPanel panel;
    private BonusGenerator bonus;
    private int currentWay;

    public Snake(JPanel pan) {

        this.panel = pan;
        this.currentWay = 0;
        this.states = new ArrayList<Direction>();
        this.states.add(Direction.RIGHT);
        this.snakePart = new Vector<SnakePart>();
        this.snakePart.add(new SnakePart(270, 300, Direction.RIGHT));
        this.snakePart.add(new SnakePart(240, 300, Direction.RIGHT));
        this.bonus = new BonusGenerator(this);
        this.bonus.generateBonus();
        this.state = Direction.RIGHT;

    }

    public BonusGenerator getBonus(){
        
        return this.bonus;
        
    }
    
    public ArrayList<Direction> getStates(){
    
        return this.states;
        
    }
    
    public Vector<SnakePart> getSnakeParts(){
        
        return snakePart;
        
    }
    
    public Direction getState(){
        
        return state;
        
    }
    
    public void setState(Direction state){
        
        this.state = state;
        
    }
    
    @Override
    public void run() {

        // Executing all commands storaged in the buffer (Directions)
        for (currentWay = 0;;) {

            if (states.get(currentWay).equals(Direction.RIGHT)) {

                int i;

                for (i = snakePart.size() - 1; i > 0; i--) {

                    snakePart.get(i).setX((int)snakePart.get(i - 1).getX()); 
                    snakePart.get(i).setY((int)snakePart.get(i - 1).getY());
                    snakePart.get(i).setDirection(snakePart.get(i - 1).getDirection());

                }

                snakePart.get(0).x += 30;
                snakePart.get(0).setDirection(states.get(currentWay));
            
            } 
            else if (states.get(currentWay).equals(Direction.LEFT)) {

                int i;

                for (i = snakePart.size() - 1; i > 0; i--) {

                    snakePart.get(i).setX((int)snakePart.get(i - 1).getX()); 
                    snakePart.get(i).setY((int)snakePart.get(i - 1).getY());
                    snakePart.get(i).setDirection(snakePart.get(i - 1).getDirection());
                    
                }

                snakePart.get(0).x -= 30;
                snakePart.get(0).setDirection(states.get(currentWay));

            } 
            else if (states.get(currentWay).equals(Direction.UP)) {

                int i;

                for (i = snakePart.size() - 1; i > 0; i--) {

                    snakePart.get(i).setX((int)snakePart.get(i - 1).getX()); 
                    snakePart.get(i).setY((int)snakePart.get(i - 1).getY());
                    snakePart.get(i).setDirection(snakePart.get(i-1).getDirection());

                }

                snakePart.get(0).y -= 30;
                snakePart.get(0).setDirection(states.get(currentWay));

            } 
            else if (states.get(currentWay).equals(Direction.DOWN)) {

                int i;

                for (i = snakePart.size() - 1; i > 0; i--) {

                    snakePart.get(i).setX((int)snakePart.get(i - 1).getX()); 
                    snakePart.get(i).setY((int)snakePart.get(i - 1).getY());
                    snakePart.get(i).setDirection(snakePart.get(i-1).getDirection());
                    
                }

                snakePart.get(0).y += 30;
                snakePart.get(0).setDirection(states.get(currentWay));

            }

            for (SnakePart part : snakePart) { // Crash

                if (snakePart.get(0).getX() == part.getX() && snakePart.get(0).getY() == part.getY() && snakePart.get(0) != part) {
                    
                    try {
                        
                        Thread.sleep(2500);
                        System.exit(0);
                        
                    } catch (InterruptedException ex) {
                        
                        Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                        
                    }
                    
                }

            }

            // Going from other side
            if (snakePart.get(0).getX() >= 600) {
                
                snakePart.get(0).setX(0);
                
            }
            else if (snakePart.get(0).getX() <= -30) {
                
                snakePart.get(0).setX(600);
                
            }
            else if (snakePart.get(0).getY() >= 600) {
                
                snakePart.get(0).setY(0);
                
            }
            else if (snakePart.get(0).getY() <= -30) {
                
                snakePart.get(0).setY(600);
                
            }

            // Catching bonus
            if (snakePart.get(0).getX() == bonus.getX() && snakePart.get(0).getY() == bonus.getY()) {

                Direction newPartDirection = snakePart.get(snakePart.size() - 1).getDirection();
                
                if (newPartDirection == Direction.UP) {
                    
                    snakePart.add(new SnakePart((int)(snakePart.get(snakePart.size()-1)).getX(), (int)(snakePart.get(snakePart.size()-1)).getY() + 30, newPartDirection));
                    
                } 
                else if (newPartDirection ==  Direction.DOWN) {
                    
                    snakePart.add(new SnakePart((int)(snakePart.get(snakePart.size()-1)).getX(), (int)(snakePart.get(snakePart.size()-1)).getY() - 30, newPartDirection));
                    
                } 
                else if (newPartDirection ==  Direction.LEFT) {
                    
                    snakePart.add(new SnakePart((int)(snakePart.get(snakePart.size()-1)).getX() + 30, (int)(snakePart.get(snakePart.size()-1)).getY(), newPartDirection));
                    
                } 
                else if (newPartDirection ==  Direction.RIGHT) {
                    
                    snakePart.add(new SnakePart((int)(snakePart.get(snakePart.size()-1)).getX() - 30, (int)(snakePart.get(snakePart.size()-1)).getY(), newPartDirection));
                    
                }
                
                bonus = new BonusGenerator(this);
                bonus.generateBonus();

            }

            panel.repaint(); // Repainting the snake and bonus
            
            // Clearing the buffer out of executed commands
            if (currentWay != states.size() - 1) {
                
                currentWay++;
            
            }
            else{
                
                int commands = states.size() - 1;
                
                for(int k = 0; k < commands; k++){
                    
                    states.remove(0);
                    currentWay = 0;
                    
                }
                
            }
            
            try {
                
                Thread.sleep(100); // Level of speed
                
            } catch (InterruptedException ex) {
                
                Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
                
            }
            
        }
        
    }
    
}
