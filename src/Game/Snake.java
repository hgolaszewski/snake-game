package Game;

import Graphics.Panel;
import Moving.Direction;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Snake implements Runnable {

    private final Vector<SnakePart> snakePart;
    private final ArrayList<Direction> states; // Directions buffer, which max capacity is 5 commands, otherwise BufferOverFlowException is thrown
    private Direction state;
    private final Panel panel;
    private BonusGenerator bonus;
    private int currentWay;

    public Snake(Panel panel) {

        this.panel = panel;
        currentWay = 0;
        states = new ArrayList<Direction>();
        states.add(Direction.RIGHT);
        snakePart = new Vector<SnakePart>();
        snakePart.add(new SnakePart(270, 300, Direction.RIGHT));
        snakePart.add(new SnakePart(240, 300, Direction.RIGHT));
        bonus = new BonusGenerator(this);
        bonus.generateBonus();
        state = Direction.RIGHT;

    }

    public BonusGenerator getBonus() {

        return this.bonus;

    }

    public ArrayList<Direction> getStates() {

        return this.states;

    }

    public Vector<SnakePart> getSnakeParts() {

        return snakePart;

    }

    public Direction getState() {

        return state;

    }

    public void setState(Direction state) {

        this.state = state;

    }

    private void catchBonus() {

        // Catching bonus
        if (snakePart.get(0).getX() == bonus.getX() && snakePart.get(0).getY() == bonus.getY()) {

            Direction newPartDirection = snakePart.get(snakePart.size() - 1).getDirection();

            if (newPartDirection == Direction.UP) {

                snakePart.add(new SnakePart((int) (snakePart.get(snakePart.size() - 1)).getX(), (int) (snakePart.get(snakePart.size() - 1)).getY() + 30, newPartDirection));

            } else if (newPartDirection == Direction.DOWN) {

                snakePart.add(new SnakePart((int) (snakePart.get(snakePart.size() - 1)).getX(), (int) (snakePart.get(snakePart.size() - 1)).getY() - 30, newPartDirection));

            } else if (newPartDirection == Direction.LEFT) {

                snakePart.add(new SnakePart((int) (snakePart.get(snakePart.size() - 1)).getX() + 30, (int) (snakePart.get(snakePart.size() - 1)).getY(), newPartDirection));

            } else if (newPartDirection == Direction.RIGHT) {

                snakePart.add(new SnakePart((int) (snakePart.get(snakePart.size() - 1)).getX() - 30, (int) (snakePart.get(snakePart.size() - 1)).getY(), newPartDirection));

            }

            bonus = new BonusGenerator(this);
            bonus.generateBonus();

        }

    }

    private void changePosition() {

        snakePart.get(0).setDirection(states.get(currentWay));

        for (int i = snakePart.size() - 1; i > 0; i--) {

            snakePart.get(i).setX((int) snakePart.get(i - 1).getX());
            snakePart.get(i).setY((int) snakePart.get(i - 1).getY());
            snakePart.get(i).setDirection(snakePart.get(i - 1).getDirection());

        }

    }

    private void checkCrash() {

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

    }

    private void checkSide() {

        // Going from other side
        if (snakePart.get(0).getX() >= 600) {

            snakePart.get(0).setX(0);

        } else if (snakePart.get(0).getX() <= -30) {

            snakePart.get(0).setX(570);

        } else if (snakePart.get(0).getY() >= 600) {

            snakePart.get(0).setY(0);

        } else if (snakePart.get(0).getY() <= -30) {

            snakePart.get(0).setY(570);

        }

    }

    private void clearBuffer() {

        // Clearing the buffer out of executed commands
        if (currentWay != states.size() - 1) {

            currentWay++;

        } else {

            int commands = states.size() - 1;

            for (int k = 0; k < commands; k++) {

                states.remove(0);
                currentWay = 0;

            }

        }

    }

    @Override
    public void run() {

        // Executing all commands storaged in the buffer (Directions)
        for (currentWay = 0;;) {

            if (states.get(currentWay).equals(Direction.RIGHT)) {

                changePosition();
                snakePart.get(0).setX((int) snakePart.get(0).getX() + 30);

            } else if (states.get(currentWay).equals(Direction.LEFT)) {

                changePosition();
                snakePart.get(0).setX((int) snakePart.get(0).getX() - 30);

            } else if (states.get(currentWay).equals(Direction.UP)) {

                changePosition();
                snakePart.get(0).setY((int) snakePart.get(0).getY() - 30);

            } else if (states.get(currentWay).equals(Direction.DOWN)) {

                changePosition();
                snakePart.get(0).setY((int) snakePart.get(0).getY() + 30);

            }

            checkCrash();
            checkSide();
            catchBonus();
            clearBuffer();
            panel.repaint(); // Repainting the snake and bonus

            try {

                Thread.sleep(100); // Level of speed

            } catch (InterruptedException ex) {

                Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);

            }

        }

    }

}
