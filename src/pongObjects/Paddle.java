package pongObjects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import pongLogic.Settings;

/**
 *
 * @author Tomáš Vondra
 */
public class Paddle extends GameObject {

    public static enum Movement {
        UP,
        DOWN,
        NONE
    }

    private final IntegerProperty scoreProperty = new SimpleIntegerProperty(0);

    private Movement movement = Movement.NONE;

    public Movement getMovement() {
        return movement;
    }

    public void setMovement(Movement movement) {
        this.movement = movement;
    }

    public IntegerProperty getScoreProperty() {
        return scoreProperty;
    }

    public void setScore(int score) {
        scoreProperty.set(score);
    }

    public int getScore() {
        return scoreProperty.get();
    }

    @Override
    public void update() {
        //Pohyb
        if (movement == Movement.DOWN) {
            setY(getY() + Settings.getPaddleSpeed());
        } else if (movement == Movement.UP) {
            setY(getY() - Settings.getPaddleSpeed());
        }
    }

}
