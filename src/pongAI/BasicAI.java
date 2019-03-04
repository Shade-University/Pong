package pongAI;

import pongLogic.GameLogic;
import pongLogic.Settings;
import pongObjects.Paddle;

/**
 *
 * @author Tomáš Vondra
 */
public class BasicAI extends DefaultAI {

    public BasicAI(Paddle paddle, GameLogic game) {
        super(paddle, game);
    }

    @Override
    public void update() {
        //AI z internetu
        double distanceFromPaddle = 
                getPaddle().getX() - getGame().getBall().getX();
        
        /*
         * Do nothing if the ball is not moving towards us.
         */
        if (getGame().getBall().getAngleX() < 0) {
            getPaddle().setMovement(Paddle.Movement.NONE);
            return;
        }

        /*
         * Find out where the ball is heading for and move in that direction 
        (this does not look
         * ahead past collisions).
         */
        double targetY = getGame().getBall().getY()
                + distanceFromPaddle 
                * Math.tan(getGame().getBall().getAngleY());
        
        boolean paddleOnTarget = targetY >= getPaddle().getY()
                && targetY + Settings.getBallSize() 
                <= getPaddle().getY() + Settings.getPaddleHeight();
        
        if (paddleOnTarget) {
            getPaddle().setMovement(Paddle.Movement.NONE);
        } else if (targetY < getPaddle().getY()) {
            getPaddle().setMovement(Paddle.Movement.UP);
        } else if (targetY > getPaddle().getY()) {
            getPaddle().setMovement(Paddle.Movement.DOWN);
        }

    }

}
