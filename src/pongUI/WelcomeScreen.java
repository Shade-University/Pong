package pongUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pongLogic.Settings;

/**
 *
 * @author Tomáš Vondra
 */
public final class WelcomeScreen extends VBox {

    private Runnable onSettings = () -> {};
    private Runnable onNewGame = () -> {};
    
    public Runnable getOnNewGame() {
        return onNewGame;
    }

    public void setOnNewGame(Runnable onNewGame) {
        this.onNewGame = onNewGame;
    }

    public Runnable getOnSettings() {
        
        return onSettings;
    }

    public void setOnSettings(Runnable onSettings) {
        this.onSettings = onSettings;
    }

    public WelcomeScreen() {
        this.setPrefSize(Settings.getWindowWidth(),
                Settings.getWindowHeight());
        this.setStyle("-fx-background-color:black");
        this.setAlignment(Pos.CENTER);
        
        //TODO Pong animace

        Button btnNewGame = createButton("Nová hra");
        btnNewGame.setOnAction((e) -> onNewGame.run());
        Button btnSettings = createButton("Nastavení");
        btnSettings.setOnAction(e -> onSettings.run());
        Button btnEnd = createButton("Konec");
        btnEnd.setOnAction(e-> System.exit(0));
        
        this.getChildren().addAll(btnNewGame, btnSettings, btnEnd);
    }

    private Button createButton(String text) {
        Button temp = new Button(text);
        temp.setFont(new Font(50));
        temp.setTextFill(Color.WHITE);
        temp.setBackground(Background.EMPTY);

        return temp;
    }

}
