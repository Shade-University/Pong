package pongUI;

import java.util.function.Consumer;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import pongLogic.GameLogic;
import pongLogic.Settings;

/**
 *
 * @author Tomáš Vondra
 */
public class WinScreen extends Pane {

    private final Text header;
    private final Text restartText;
    private final Text quitText;

    private String winner;
    
    private Runnable onRestart;

    public void setOnRestart(Runnable onRestart) {
        this.onRestart = onRestart;
    }
    

    public void setWinner(String winner) {
        this.winner = winner;
        header.setText(winner + " win");
        header.setX((getPrefWidth() - header.getBoundsInLocal().getWidth()) / 2);
        header.setY(header.getBoundsInLocal().getHeight() + 10);
    }

    public WinScreen() {
        this.setPrefSize(Settings.getWindowWidth(),
                Settings.getWindowHeight());
        setStyle("-fx-background-color:black");

        header = new Text();
        header.setFont(new Font(70));
        header.setFill(Color.WHITE);
        header.setTextAlignment(TextAlignment.CENTER);

        restartText = new Text("Press enter to restart");
        restartText.setFont(new Font("Consolas", 20));
        restartText.setFill(Color.WHITE);
        restartText.setTextAlignment(TextAlignment.CENTER);

        restartText.setX((getPrefWidth() - restartText.getBoundsInLocal().getWidth()) / 2);
        restartText.setY(Settings.getWindowHeight()
                - restartText.getBoundsInLocal().getHeight()
                - 20);

        quitText = new Text("press escape to quit");
        quitText.setFont(new Font("Consolas", 15));
        quitText.setFill(Color.WHITE);
        quitText.setTextAlignment(TextAlignment.CENTER);

        quitText.setX((getPrefWidth() - quitText.getBoundsInLocal().getWidth()) / 2);
        quitText.setY(Settings.getWindowHeight() - quitText.getBoundsInLocal().getHeight()
                - 10);

        this.getChildren().addAll(header, restartText, quitText);
        
        this.setOnKeyPressed(this::onKeyPressed);
    }
    
    private void onKeyPressed(KeyEvent event){
        if(event.getCode() == KeyCode.ESCAPE){
            System.exit(0);
        }
        else if (event.getCode() == KeyCode.ENTER){
            onRestart.run();
        }
        
    }

}
