
package pongAI;

import pongLogic.GameLogic;
import pongObjects.Paddle;

/**
 *
 * @author Tomáš Vondra
 */
public  abstract class DefaultAI {

    private final Paddle paddle;
    private final GameLogic game;

    public DefaultAI(Paddle paddle, GameLogic game) {
        this.paddle = paddle;
        this.game = game;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public GameLogic getGame() {
        return game;
    }
    
    public abstract void update();
}
