package Game;

import Moving.Direction;
import java.awt.Rectangle;

public class SnakePart extends Rectangle {

    private Direction direction;
    
    public SnakePart(int x, int y, Direction direction) {

        this.x = x;
        this.y = y;
        this.direction = direction;
        
    }
    
    public void setDirection(Direction direction){
        
        this.direction = direction;
        
    }
    
    public Direction getDirection(){
        
        return this.direction;
        
    }
    
    public void setX(int x){
        
        this.x = x;
        
    }
    
     public void setY(int y){
        
        this.y = y;
        
    }
    
}
