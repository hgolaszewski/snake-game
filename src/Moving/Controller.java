package Moving;

import Exceptions.BufferOverFlowException;
import Game.Snake;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {

    private final Snake snake;

    public Controller(Snake snake) {

        this.snake = snake;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int keyCode = e.getKeyCode();
       
        switch (keyCode) {
            
            case KeyEvent.VK_UP:
                
                if(!snake.getState().equals(Direction.DOWN) && !snake.getState().equals(Direction.UP)) {
                    
                    try{
                        
                        if(snake.getStates().size() >= 6) throw new BufferOverFlowException("BufferOverFlow");
                        snake.getStates().add(Direction.UP);
                        snake.setState(Direction.UP);
                        
                    }
                    catch(BufferOverFlowException ex){
                        
                        System.err.println(ex.getMessage());
                        
                    }
                      
                }
                
            break;
                
            case KeyEvent.VK_DOWN:
                
                if(!snake.getState().equals(Direction.UP) && !snake.getState().equals(Direction.DOWN)) {
                      
                     try{
                        
                        if(snake.getStates().size() > 6) throw new BufferOverFlowException("BufferOverFlow");
                        snake.getStates().add(Direction.DOWN);
                        snake.setState(Direction.DOWN);
                        
                    }
                    catch(BufferOverFlowException ex){
                        
                        System.err.println(ex.getMessage());
                        
                    }
                    
                }
                
            break;
                
            case KeyEvent.VK_LEFT:
                
                if(!snake.getState().equals(Direction.RIGHT) && !snake.getState().equals(Direction.LEFT)){
                    
                     try{
                        
                        if(snake.getStates().size() > 6) throw new BufferOverFlowException("BufferOverFlow");
                        snake.getStates().add(Direction.LEFT);
                        snake.setState(Direction.LEFT);
                        
                    }
                    catch(BufferOverFlowException ex){
                        
                        System.err.println(ex.getMessage());
                        
                    }
                    
                }
                
            break;
                
            case KeyEvent.VK_RIGHT:
                
                if(!snake.getState().equals(Direction.LEFT) && !snake.getState().equals(Direction.RIGHT)){
                    
                     try{
                        
                        if(snake.getStates().size() > 6) throw new BufferOverFlowException("BufferOverFlow");
                        snake.getStates().add(Direction.RIGHT);
                        snake.setState(Direction.RIGHT);
                        
                    }
                    catch(BufferOverFlowException ex){
                        
                        System.err.println(ex.getMessage());
                        
                    }
                     
                }
                
            break;
                
        }
     
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    
    @Override
    public void keyReleased(KeyEvent e) {   
    }

}

