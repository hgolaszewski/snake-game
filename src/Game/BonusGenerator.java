package Game;

import java.awt.Rectangle;
import java.util.Random;

public class BonusGenerator extends Rectangle{
    
    private final Snake snake;
    private final Random rand;
    
    public BonusGenerator(Snake snake){
        
        this.snake = snake;
        rand = new Random();
        
    }
    
    // Generating bonus in the right random place
    public void generateBonus(){
        
        boolean occupied;
        
        while(true){
       
            this.x = 30 * rand.nextInt(20);
            this.y = 30 * rand.nextInt(20);
            
            occupied = false;
         
            for(SnakePart part: snake.getSnakeParts()){
                
                if(part.getX() == this.getX() && part.getY() == this.getY()) {
                    
                    occupied = true;
                    break;
                    
                }
                
            }
            
            if(occupied == false) break;
            
        }
        
    }
    
}
