package pongLogic;

import java.util.Random;
import java.util.function.Consumer;
import javafx.animation.AnimationTimer;
import pongAI.BasicAI;
import pongAI.DefaultAI;
import pongObjects.Ball;
import pongObjects.Paddle;

/**
 *
 * @author Tomáš Vondra
 */
public class GameLogic {

    private final Paddle playerPaddle;
    private final Paddle opponentPaddle;
    private final DefaultAI ai;
    private final Ball ball;

    private final GameLoop loop;

    private Consumer<String> onWinConsumer; //delegát

    public void setOnWinConsumer(Consumer<String> onWinConsumer) {
        this.onWinConsumer = onWinConsumer;
    }

    public GameLogic() {
        playerPaddle = new Paddle();
        opponentPaddle = new Paddle();
        ball = new Ball();
        loop = new GameLoop(); //Inicializace
        ai = new BasicAI(opponentPaddle, this);

        playerPaddle.setX(10);
        playerPaddle.setY((Settings.getWindowHeight() / 2)
                - (Settings.getPaddleHeight() / 2));

        opponentPaddle.setX((Settings.getWindowWidth() 
                - Settings.getPaddleWidth()));
        opponentPaddle.setY((Settings.getWindowHeight() / 2)
                - (Settings.getPaddleHeight() / 2));      
    }

    public Paddle getPlayerPaddle() {
        return playerPaddle;
    }

    public Paddle getOpponentPaddle() {
        return opponentPaddle;
    }

    public Ball getBall() {
        return ball;
    }

    public class GameLoop extends AnimationTimer {

        @Override
        public void handle(long now) {
            //Hlavní smyčka, každý frame updatuj
            playerPaddle.update();
            ai.update();
            opponentPaddle.update();
            ball.update();

            keepInBounds(playerPaddle);
            keepInBounds(opponentPaddle); //Pálky nepřesahují obrazovku

            checkWallCollision(); //Odraz od zdi
            checkPaddleCollision(playerPaddle);
            checkPaddleCollision(opponentPaddle); //Odraz od pálky/skóre

        }

    }

    private final Random rand = new Random();

    private void launchBall() {
        int initialAngle = rand.nextInt(2);
        if (initialAngle == 1) {
            ball.setAngleX(1);
            ball.setAngleY(-1);
        } else {
            ball.setAngleX(-1);
            ball.setAngleY(1);
        } //Rozhodni kam míček poletí

        ball.setX(Settings.getWindowWidth() / 2);
        ball.setY(Settings.getWindowHeight() / 2); //Napozicuj

    }

    private void keepInBounds(Paddle paddle) {
        if (paddle.getY() + Settings.getPaddleHeight()
                >= Settings.getWindowHeight()) {
            paddle.setY(paddle.getY() - Settings.getPaddleSpeed());
        } else if (paddle.getY() <= 0) {
            paddle.setY(paddle.getY() + Settings.getPaddleSpeed());
        } //Pokud přejíždíme mimo okno, vrať nás do okna
    }

    private void checkWallCollision() {
        boolean ballHitTopWall = ball.getY() < 0;
        boolean ballHitBottomWall = ball.getY() + Settings.getBallSize()
                > Settings.getWindowHeight();

        if (ballHitTopWall || ballHitBottomWall) {
            ball.setAngleY(ball.getAngleY() * -1);
        } //Pokud trefíme zeď, změň úhel

        if (ballHitTopWall) {
            ball.setY(0);
        } else if (ballHitBottomWall) {
            ball.setY(Settings.getWindowHeight() - Settings.getBallSize());
        } //Vrať míček do okna (nepřesahuje screen)
    }

    private void checkWin(Paddle paddle) {
        if (paddle.getScore() == 2) { //TODO Win score do settings
            onWinConsumer.accept(paddle == playerPaddle ? "You" : "Opponent");
        }
    }

    private void checkPaddleCollision(Paddle paddle) {

        boolean ballHitEdge;
        if (paddle == playerPaddle) {
            ballHitEdge = ball.getX()
                    <= 10 + Settings.getPaddleWidth();
        } else {
            ballHitEdge = ball.getX()
                    >= Settings.getWindowWidth() - 10 - Settings.getPaddleWidth();
        } //Zjisti, pokud je míček na místě, kde by měla být pálka
        //TODO 10 je mezera od kraje => do settings
        if (!ballHitEdge) {
            return;
        } //Pokud není, neřeš

        boolean ballHitPaddle = ball.getY() + Settings.getBallSize()
                > paddle.getY()
                && ball.getY() < paddle.getY() + Settings.getPaddleHeight();

        if (ballHitPaddle) {
            ball.setAngleX(ball.getAngleX() * -1); //Otoč uhel
            if (paddle == playerPaddle) {
                ball.setX(10 + Settings.getPaddleWidth() + Settings.getBallSize());
            } else {
                ball.setX(Settings.getWindowWidth() - 10 
                        - Settings.getPaddleWidth() - Settings.getBallSize());
            } //vrať míček do okna
        } else if (ball.getX() < 0 || ball.getX() > Settings.getWindowWidth()) {
            if (paddle == playerPaddle) {
                opponentPaddle.setScore(opponentPaddle.getScore() + 1);
                checkWin(opponentPaddle);
            } else {
                playerPaddle.setScore(playerPaddle.getScore() + 1);
                checkWin(playerPaddle);
            }
            launchBall();
        } //Zjisti, pokud jsme trefili pálku, pokud ne, počkej než míček doletí
        //na okraj a zaskóruj
    }

    public void Stop() {
        loop.stop();
    }

    public void Start() {
        launchBall(); //Pozicování
        loop.start();
    }

    public void Restart() {
        playerPaddle.setMovement(Paddle.Movement.NONE);
        playerPaddle.setX(10);
        playerPaddle.setY((Settings.getWindowHeight() / 2)
                - (Settings.getPaddleHeight() / 2));

        opponentPaddle.setX((Settings.getWindowWidth() 
                - Settings.getPaddleWidth()));
        opponentPaddle.setY((Settings.getWindowHeight() / 2)
                - (Settings.getPaddleHeight() / 2));
        opponentPaddle.setMovement(Paddle.Movement.NONE);

        playerPaddle.setScore(0);
        opponentPaddle.setScore(0);

        this.Start();
        launchBall();

    }

}
