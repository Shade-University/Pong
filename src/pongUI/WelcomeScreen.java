package pongUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pongLogic.Settings;

/**
 *
 * @author Tomáš Vondra
 */
public class WelcomeScreen extends VBox {

    private Runnable onSettings;
    private Runnable onNewGame;
    
    private final Button btnNewGame;
    private final Button btnSettings;
    
    public Runnable getOnNewGame() {
        return onNewGame;
    }

    public void setOnNewGame(Runnable onNewGame) {
        btnNewGame.setOnAction(e -> onNewGame.run());
        this.onNewGame = onNewGame;
    }

    public Runnable getOnSettings() {
        
        return onSettings;
    }

    public void setOnSettings(Runnable onSettings) {
        btnSettings.setOnAction(e -> onSettings.run());
        this.onSettings = onSettings;
    }

    public WelcomeScreen() {
        this.setPrefSize(Settings.getWindowWidth(),
                Settings.getWindowHeight());
        this.setStyle("-fx-background-color:black");
        this.setAlignment(Pos.CENTER);
        
        //TODO Pong animace

        btnNewGame = createButton("Nová hra", () -> {});
        btnSettings = createButton("Nastavení", () -> {});
        Button btnEnd = createButton("Konec", () -> System.exit(0));
        
        this.getChildren().addAll(btnNewGame, btnSettings, btnEnd);
    }

    private Button createButton(String text, Runnable action) {
        Button temp = new Button(text);
        temp.setFont(new Font(50));
        temp.setTextFill(Color.WHITE);
        temp.setBackground(Background.EMPTY);
        temp.setOnAction((e) -> action.run());

        return temp;
    }

}
