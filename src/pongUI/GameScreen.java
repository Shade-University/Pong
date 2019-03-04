package pongUI;

import pongLogic.GameLogic;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.converter.NumberStringConverter;
import pongLogic.Settings;
import pongObjects.Paddle;

/**
 *
 * @author Tomáš Vondra
 */
public final class GameScreen extends Pane {

    private final Canvas canvas;
    private final GraphicsContext gc;
    private final GameLogic game;

    private final Circle ball; //Rectangle možná lepší
    private final Rectangle player;
    private final Rectangle opponent;

    private final Text playerScoreText;
    private final Text opponentScoreText;

    public GameScreen(GameLogic game) {
        this.game = game;
        this.setPrefSize(Settings.getWindowWidth(),
                Settings.getWindowHeight());
        canvas = new Canvas(Settings.getWindowWidth(),
                Settings.getWindowHeight());
        gc = canvas.getGraphicsContext2D();

        ball = new Circle(Settings.getBallSize() / 2);
        ball.setFill(Color.WHITE);
        player = new Rectangle(Settings.getPaddleWidth(),
                Settings.getPaddleHeight());
        player.setFill(Color.WHITE);
        opponent = new Rectangle(Settings.getPaddleWidth(),
                Settings.getPaddleHeight());
        opponent.setFill(Color.WHITE);

        playerScoreText = new Text((Settings.getWindowWidth() / 4) - 30,
                60.0, "0");
        playerScoreText.setFill(Color.WHITE);
        playerScoreText.setFont(new Font(30));
        
        opponentScoreText = new Text((Settings.getWindowWidth() / 2)
                + (Settings.getWindowWidth() / 4), 60.0, "0");
        opponentScoreText.setFill(Color.WHITE);
        opponentScoreText.setFont(new Font(30));
        //Inicializace

        backgroundScreen(); //Nakresli pozadí hry
        bindGame(); //Bind UI s hrou
        this.getChildren().addAll(canvas, ball, player, opponent,
                playerScoreText, opponentScoreText);

        setOnKeyPressed(this::keyPressed);
            setOnKeyReleased(this::keyReleased); //Odchytávání kláves bind
    }

    private void bindGame() {
        ball.centerXProperty().bind(game.getBall().getxProperty());
        ball.centerYProperty().bind(game.getBall().getyProperty());

        player.xProperty().bind(game.getPlayerPaddle().getxProperty());
        player.yProperty().bind(game.getPlayerPaddle().getyProperty());

        opponent.xProperty().bind(game.getOpponentPaddle().getxProperty());
        opponent.yProperty().bind(game.getOpponentPaddle().getyProperty());

        playerScoreText.textProperty()
                .bindBidirectional(game.getPlayerPaddle().getScoreProperty(),
                        new NumberStringConverter());
        opponentScoreText.textProperty()
                .bindBidirectional(game.getOpponentPaddle().getScoreProperty(),
                        new NumberStringConverter()); //bind int s textem
    }

    private void backgroundScreen() {
        this.setStyle("-fx-background-color:black;");
        gc.setStroke(Color.WHITE);
        gc.setLineDashes(20);
        gc.strokeLine(this.getPrefWidth() / 2, 0,
                this.getPrefWidth() / 2, this.getPrefHeight() - 1);
    }

    private void keyPressed(KeyEvent event) {
        //Pohyb - šipky nahoru/dolu
        if (game.getPlayerPaddle().getMovement() == Paddle.Movement.NONE 
                && event.getCode() == KeyCode.UP) {
            game.getPlayerPaddle().setMovement(Paddle.Movement.UP);
        } else if (game.getPlayerPaddle().getMovement() == Paddle.Movement.NONE 
                && event.getCode() == KeyCode.DOWN) {
            game.getPlayerPaddle().setMovement(Paddle.Movement.DOWN);
        }
    }

    private void keyReleased(KeyEvent event) {
        //Zruš pohyb při puštění kláves
        if (game.getPlayerPaddle().getMovement() == Paddle.Movement.UP 
                && event.getCode() == KeyCode.UP) {
            game.getPlayerPaddle().setMovement(Paddle.Movement.NONE);
        } else if (game.getPlayerPaddle().getMovement() == Paddle.Movement.DOWN 
                && event.getCode() == KeyCode.DOWN) {
            game.getPlayerPaddle().setMovement(Paddle.Movement.NONE);
        }
    }
}
